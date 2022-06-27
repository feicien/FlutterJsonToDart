package cn.dxy.app.dxyjsontodart;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 参考 FlutterPubVersionChecker 插件 中的 DependencyUtils
 */
public class YamlUtils {

    private static final String DEPENDENCIES_PATTERN = "^(?!#)\\s*(?!(?:version|sdk|ref|url|flutter)\\b)(\\S+):\\s*[<|=>^]*([0-9]+\\.[0-9]+\\.[0-9]+\\+?\\S*)";


    public static List<FlutterDependencyBean> getDependencies(@NotNull String yaml) {
        List<FlutterDependencyBean> dependencyList = new ArrayList<>();
        String line = "";
        int counter = 0;

        for (int var7 = 0; var7 < yaml.length(); ++var7) {
            char c = yaml.charAt(var7);
            counter++;
            if (c == '\n') {
                if (isDependencyName(line)) {
                    String packageName = getPackageName(line);
                    String currentVersion = getVersionName(line);
                    dependencyList.add(new FlutterDependencyBean(packageName, currentVersion, counter - 2));
                }
                line = "";
            } else {
                line += c;
            }
        }

        return dependencyList;
    }

    private static boolean isDependencyName(@NotNull String str) {
        Pattern pattern = Pattern.compile(DEPENDENCIES_PATTERN);
        return pattern.matcher(str).find();
    }

    private static String getPackageName(@NotNull String str) {

        Pattern pattern = Pattern.compile(DEPENDENCIES_PATTERN);

        Matcher matcher = pattern.matcher(str);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    private static String getVersionName(@NotNull String str) {
        Pattern pattern = Pattern.compile(DEPENDENCIES_PATTERN);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(2);
        }
        return null;
    }

}
