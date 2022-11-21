

# FlutterJsonToDart 


<!-- Plugin description -->
The plugin can help you generate dart model needed for json_serializable from a JSON data and add json_serializable dependencies and run the `flutter pub run build_runner build -delete-conflicting-outputs` command. 

### how to use
1. Launch the IDE and open plugin settings 
2. Search for "FlutterJsonToDart" and click install 
3. Open a flutter project, Right click on package -> New -> FlutterJsonToDart 
4. Input ClassName and JSON Text，and click Ok button 
5. The plugin will automatically add json_serializable related dependencies in the pubspec.yaml file 
6. The plugin will automatically generate model classes 
7. The plugin will automatically execute the command：flutter packages pub run build_runner build

这个插件可以帮你自动把 json 数据生成带 json_serializable 注解的模型类，可以自动在 pubspec.yaml 文件添加 json_serializable 相关依赖，并且可以自动运行 `flutter pub run build_runner build -delete-conflicting-outputs` 命令，
如果你需要修改生成字段，可以通过快捷键 alt + W 或通过菜单 Build -> Run 'flutter pub run build_runner watch' 运行 watch 命令

<!-- Plugin description end -->


一个可以自动把 `JSON` 解析为 `Dart` 模型类的 `Android Studio` 插件。

它采用的是官方推荐的 `json_serializable` 方案（[点击查看官方文档](https://docs.flutter.dev/development/data-and-backend/json)）

可以帮助 Flutter 开发者自动生成带 `@JsonSerializable` 注解的模型类；

自动在 `pubspec.yaml` 文件中添加依赖；

自动执行解析生成命令：`flutter pub run build_runner build `

## 安装

 在 `Android Studio` 插件中搜索 `FlutterJsonToDart` 即可安装

## 使用

选择需要保存模型类的文件夹，点击鼠标右键，选择 `New` , 然后点击 `FlutterJsonToDart`, 在弹出的 `Dialog` 中输入类名和 `JSON` 字符串，然后点击 `OK` 按钮即可。


## Demo
![image](images/DxyJsonToDart.gif)


## 更新日志

See [CHANGELOG](CHANGELOG.md) to see what's new.


## 感谢

本人之前没有写过 `IDEA` 插件，在写 `FlutterJsonToDart` 插件时，参考了 [FlutterJsonBeanFactory](https://github.com/fluttercandies/FlutterJsonBeanFactory) 和 [AutoJson](https://github.com/LuodiJackShen/AutoJson) 的代码, 在此向他们表示感谢。