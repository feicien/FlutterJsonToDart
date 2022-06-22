plugins {
    id("java")
    id("org.jetbrains.intellij") version "1.6.0"
}

group = "cn.dxy.app"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    version.set("2021.3")
    type.set("IC") // Target IDE Platform

    plugins.set(
        listOf(
            //因为要生成 dart 文件，需要使用到 dart 插件中的类，所以这里要引入 dart 插件
            "Dart:213.5744.122", //https://plugins.jetbrains.com/plugin/6351-dart/versions
//            "io.flutter:63.2.4",//https://plugins.jetbrains.com/plugin/9212-flutter/versions/stable
        )
    )
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "11"
        targetCompatibility = "11"
    }

    patchPluginXml {
        sinceBuild.set("212")
        untilBuild.set("223.*")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }
}
