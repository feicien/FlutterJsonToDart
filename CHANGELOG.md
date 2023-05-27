<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# FlutterJsonToDart Changelog

## Unreleased

### Added

### Changed

### Deprecated

### Removed

### Fixed

### Security

## 1.1.3 - 2023-05-27

### Added
- support fvm
- add two action in Build menu related to fvm 

### Fixed
- fix json text field is read-only

## 1.1.1 - 2023-04-05

### Added
- Option added explicitToJson: true
- Option added fieldRename: FieldRename.none
- Option added copyWith method

### Fixed
- bug fixes

## 1.0.9 - 2023-01-09

### Added
- Enhanced JSON Input Dialog，can preview the generated dart model

## 1.0.8 - 2022-11-21

### Added
- Enhanced text field, support highlighting, code folding
- Option added to Whether to create toJson function
- Option added to Whether to set a default value

### Fixed
- Minor Bug fixes

## 1.0.7 - 2022-09-16
- make Property final
- support shortcut 'alt + W' run "flutter pub run build_runner watch --delete-conflicting-outputs" command
- support shortcut 'alt + B' run "flutter pub run build_runner build --delete-conflicting-outputs" command

## 1.0.6 - 2022-07-05
- Support shortcut alt + F  or click menu Build -> Run 'flutter pub run build_runner watch'
- modify input json dialog ui
- DxyJsonToDart rename to FlutterJsonToDart

## 1.0.5 - 2022-06-27
- New: Automatically generate dart model classes from JSON
- New: Automatically add json_serializable related dependencies in the pubspec.yaml file
- New: Automatically execute the command：flutter packages pub run build_runner build
