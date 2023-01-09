import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.date
import org.jetbrains.changelog.markdownToHTML


fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "1.7.21"
    id("org.jetbrains.intellij") version "1.11.0"
    id("org.jetbrains.changelog") version "2.0.0"
}

version = properties("pluginVersion")

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.3.3")
    type.set("IC") // Target IDE Platform

    plugins.set(
        listOf(
            "com.intellij.java",
            //因为要生成 dart 文件，需要使用到 dart 插件中的类，所以这里要引入 dart 插件
            "Dart:213.5744.122", //https://plugins.jetbrains.com/plugin/6351-dart/versions
//            "io.flutter:63.2.4",//https://plugins.jetbrains.com/plugin/9212-flutter/versions/stable
            "com.jetbrains.sh:213.5744.121", ////https://plugins.jetbrains.com/plugin/13122-shell-script/versions
            "org.jetbrains.plugins.terminal:213.5744.121", //https://plugins.jetbrains.com/plugin/13123-terminal/versions
        )
    )
}

changelog {
    version.set(properties("pluginVersion"))
    header.set(provider { "[${version.get()}] - ${date()}" })
    groups.set(listOf("Added", "Changed", "Deprecated", "Removed", "Fixed", "Security"))
}

tasks {

    buildSearchableOptions {
        enabled = false
    }

    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("200.*")
        untilBuild.set("300.*")

        // Extract the <!-- Plugin description --> section from README.md and provide for the plugin's manifest
        pluginDescription.set(
            projectDir.resolve("README.md").readText().lines().run {
                val start = "<!-- Plugin description -->"
                val end = "<!-- Plugin description end -->"

                if (!containsAll(listOf(start, end))) {
                    throw GradleException("Plugin description section not found in README.md:\n$start ... $end")
                }
                subList(indexOf(start) + 1, indexOf(end))
            }.joinToString("\n").run { markdownToHTML(this) }
        )

        // Get the latest available change notes from the changelog file
        changeNotes.set(provider {
            changelog.renderItem(
                changelog.getLatest().withHeader(false).withEmptySections(false),
                Changelog.OutputType.HTML
            )
        })
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        dependsOn("patchChangelog")
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    runPluginVerifier {
        ideVersions.set(properties("pluginVerifierIdeVersions").split(',').map(String::trim).filter(String::isNotEmpty))
    }
}
