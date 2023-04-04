import 'package:json_annotation/json_annotation.dart';

part 'test2.g.dart';

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class Test2 {
  final Data? data;
  @JsonKey(defaultValue: 0)
  final int errorCode;
  @JsonKey(defaultValue: '')
  final String errorMsg;

  const Test2({
    this.data,
    required this.errorCode,
    required this.errorMsg,
  });

  factory Test2.fromJson(Map<String, dynamic> json) =>
      _$Test2FromJson(json);

  Test2 copyWith({
    Data? data,
    int? errorCode,
    String? errorMsg,
  }) {
    return Test2(
      data: data ?? this.data,
      errorCode: errorCode ?? this.errorCode,
      errorMsg: errorMsg ?? this.errorMsg,
    );
  }
}

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class Data {
  @JsonKey(defaultValue: 0)
  final int curPage;
  @JsonKey(defaultValue: [])
  final List<Datas> datas;
  @JsonKey(defaultValue: 0)
  final int offset;
  @JsonKey(defaultValue: false)
  final bool over;
  @JsonKey(defaultValue: 0)
  final int pageCount;
  @JsonKey(defaultValue: 0)
  final int size;
  @JsonKey(defaultValue: 0)
  final int total;

  const Data({
    required this.curPage,
    required this.datas,
    required this.offset,
    required this.over,
    required this.pageCount,
    required this.size,
    required this.total,
  });

  factory Data.fromJson(Map<String, dynamic> json) =>
      _$DataFromJson(json);

  Data copyWith({
    int? curPage,
    List<Datas>? datas,
    int? offset,
    bool? over,
    int? pageCount,
    int? size,
    int? total,
  }) {
    return Data(
      curPage: curPage ?? this.curPage,
      datas: datas ?? this.datas,
      offset: offset ?? this.offset,
      over: over ?? this.over,
      pageCount: pageCount ?? this.pageCount,
      size: size ?? this.size,
      total: total ?? this.total,
    );
  }
}

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class Datas {
  @JsonKey(defaultValue: '')
  final String link;
  @JsonKey(defaultValue: '')
  final String niceDate;
  @JsonKey(defaultValue: '')
  final String niceShareDate;
  @JsonKey(defaultValue: '')
  final String shareUser;
  @JsonKey(defaultValue: '')
  final String superChapterName;
  @JsonKey(defaultValue: '')
  final String title;

  const Datas({
    required this.link,
    required this.niceDate,
    required this.niceShareDate,
    required this.shareUser,
    required this.superChapterName,
    required this.title,
  });

  factory Datas.fromJson(Map<String, dynamic> json) =>
      _$DatasFromJson(json);

  Datas copyWith({
    String? link,
    String? niceDate,
    String? niceShareDate,
    String? shareUser,
    String? superChapterName,
    String? title,
  }) {
    return Datas(
      link: link ?? this.link,
      niceDate: niceDate ?? this.niceDate,
      niceShareDate: niceShareDate ?? this.niceShareDate,
      shareUser: shareUser ?? this.shareUser,
      superChapterName: superChapterName ?? this.superChapterName,
      title: title ?? this.title,
    );
  }
}
