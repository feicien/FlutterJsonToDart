

# FlutterJsonToDart 


<!-- Plugin description -->
The plugin can help you generate dart model needed for json_serializable from a JSON data and add json_serializable dependencies and run the `flutter pub run build_runner build -delete-conflicting-outputs` command. 

### Screenshot
<img src="https://plugins.jetbrains.com/files/19409/screenshot_76b4f3ed-b0d6-4ab3-b135-5779cd94f368" width="500" height="400"/>

### 简介
这个插件可以帮你自动把 JSON 数据生成带 json_serializable 注解的模型类

如果遇到问题，可以加我微信：feicien，添加时请备注 FlutterJsonToDart


一个可以自动把 `JSON` 解析为 `Dart` 模型类的 `Android Studio` 插件。

它采用的是官方推荐的 `json_serializable` 方案（[点击查看官方文档](https://docs.flutter.dev/development/data-and-backend/json)）

可以帮助 Flutter 开发者自动生成带 `@JsonSerializable` 注解的模型类；

自动在 `pubspec.yaml` 文件中添加依赖；

自动执行解析生成命令：`flutter pub run build_runner build `

### 安装

 在 `Android Studio` 插件中搜索 `FlutterJsonToDart` 即可安装

### 使用

选择需要保存模型类的文件夹，点击鼠标右键，选择 `New` , 然后点击 `FlutterJsonToDart`, 在弹出的 `Dialog` 中输入类名和 `JSON` 字符串，然后点击 `OK` 按钮即可。

<!-- Plugin description end -->

### Demo
![image](images/DxyJsonToDart.gif)


### 更新日志

See [CHANGELOG](CHANGELOG.md) to see what's new.


### 感谢

本人之前没有写过 `IDEA` 插件，在写 `FlutterJsonToDart` 插件时，参考了 [FlutterJsonBeanFactory](https://github.com/fluttercandies/FlutterJsonBeanFactory) 和 [AutoJson](https://github.com/LuodiJackShen/AutoJson) 的代码, 在此向他们表示感谢。