package cn.dxy.app.dxyjsontodart;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {

//        testJsonToDart();

        testYmal();
    }

    private static void testJsonToDart() {
        try {
            URL url = Resources.getResource("test1.json");
            String json = Resources.toString(url, Charsets.UTF_8);
            String str = JsonHelper.generateDartClassesToString("HelloWorld", json);
            System.out.printf(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void testYmal(){
        String yaml = "name: flutter_plugin_demo\n" +
                "description: A new Flutter project.\n" +
                "\n" +
                "# The following line prevents the package from being accidentally published to\n" +
                "# pub.dev using `flutter pub publish`. This is preferred for private packages.\n" +
                "publish_to: 'none' # Remove this line if you wish to publish to pub.dev\n" +
                "\n" +
                "# The following defines the version and build number for your application.\n" +
                "# A version number is three numbers separated by dots, like 1.2.43\n" +
                "# followed by an optional build number separated by a +.\n" +
                "# Both the version and the builder number may be overridden in flutter\n" +
                "# build by specifying --build-name and --build-number, respectively.\n" +
                "# In Android, build-name is used as versionName while build-number used as versionCode.\n" +
                "# Read more about Android versioning at https://developer.android.com/studio/publish/versioning\n" +
                "# In iOS, build-name is used as CFBundleShortVersionString while build-number used as CFBundleVersion.\n" +
                "# Read more about iOS versioning at\n" +
                "# https://developer.apple.com/library/archive/documentation/General/Reference/InfoPlistKeyReference/Articles/CoreFoundationKeys.html\n" +
                "version: 1.0.0+1\n" +
                "\n" +
                "environment:\n" +
                "  sdk: \">=2.17.1 <3.0.0\"\n" +
                "\n" +
                "# Dependencies specify other packages that your package needs in order to work.\n" +
                "# To automatically upgrade your package dependencies to the latest versions\n" +
                "# consider running `flutter pub upgrade --major-versions`. Alternatively,\n" +
                "# dependencies can be manually updated by changing the version numbers below to\n" +
                "# the latest version available on pub.dev. To see which dependencies have newer\n" +
                "# versions available, run `flutter pub outdated`.\n" +
                "dependencies:\n" +
                "  flutter:\n" +
                "    sdk: flutter\n" +
                "\n" +
                "\n" +
                "  # The following adds the Cupertino Icons font to your application.\n" +
                "  # Use with the CupertinoIcons class for iOS style icons.\n" +
                "  cupertino_icons: ^1.0.2\n" +
                "  json_annotation: ^4.5.0\n" +
                "\n" +
                "dev_dependencies:\n" +
                "  flutter_test:\n" +
                "    sdk: flutter\n" +
                "\n" +
                "  # The \"flutter_lints\" package below contains a set of recommended lints to\n" +
                "  # encourage good coding practices. The lint set provided by the package is\n" +
                "  # activated in the `analysis_options.yaml` file located at the root of your\n" +
                "  # package. See that file for information about deactivating specific lint\n" +
                "  # rules and activating additional ones.\n" +
                "  flutter_lints: ^2.0.0\n" +
                "  json_serializable: ^6.2.0\n" +
                "  build_runner: ^2.1.11\n" +
                "\n" +
                "# For information on the generic Dart part of this file, see the\n" +
                "# following page: https://dart.dev/tools/pub/pubspec\n" +
                "\n" +
                "# The following section is specific to Flutter packages.\n" +
                "flutter:\n" +
                "\n" +
                "  # The following line ensures that the Material Icons font is\n" +
                "  # included with your application, so that you can use the icons in\n" +
                "  # the material Icons class.\n" +
                "  uses-material-design: true\n" +
                "\n" +
                "  # To add assets to your application, add an assets section, like this:\n" +
                "  # assets:\n" +
                "  #   - images/a_dot_burr.jpeg\n" +
                "  #   - images/a_dot_ham.jpeg\n" +
                "\n" +
                "  # An image asset can refer to one or more resolution-specific \"variants\", see\n" +
                "  # https://flutter.dev/assets-and-images/#resolution-aware\n" +
                "\n" +
                "  # For details regarding adding assets from package dependencies, see\n" +
                "  # https://flutter.dev/assets-and-images/#from-packages\n" +
                "\n" +
                "  # To add custom fonts to your application, add a fonts section here,\n" +
                "  # in this \"flutter\" section. Each entry in this list should have a\n" +
                "  # \"family\" key with the font family name, and a \"fonts\" key with a\n" +
                "  # list giving the asset and other descriptors for the font. For\n" +
                "  # example:\n" +
                "  # fonts:\n" +
                "  #   - family: Schyler\n" +
                "  #     fonts:\n" +
                "  #       - asset: fonts/Schyler-Regular.ttf\n" +
                "  #       - asset: fonts/Schyler-Italic.ttf\n" +
                "  #         style: italic\n" +
                "  #   - family: Trajan Pro\n" +
                "  #     fonts:\n" +
                "  #       - asset: fonts/TrajanPro.ttf\n" +
                "  #       - asset: fonts/TrajanPro_Bold.ttf\n" +
                "  #         weight: 700\n" +
                "  #\n" +
                "  # For details regarding fonts from package dependencies,\n" +
                "  # see https://flutter.dev/custom-fonts/#from-packages\n";

        List<FlutterDependencyBean> dependencies = YamlUtils.getDependencies(yaml);
        for (FlutterDependencyBean dependency : dependencies) {
            System.out.println(dependency);
        }
    }
}
