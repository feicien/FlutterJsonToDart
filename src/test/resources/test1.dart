import 'package:json_annotation/json_annotation.dart';

part 'test1.g.dart';

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class Test1 {
  final Data? data;
  @JsonKey(defaultValue: 0)
  final int errorCode;
  @JsonKey(defaultValue: '')
  final String errorMsg;

  const Test1({
    this.data,
    required this.errorCode,
    required this.errorMsg,
  });

  factory Test1.fromJson(Map<String, dynamic> json) =>
      _$Test1FromJson(json);

  Test1 copyWith({
    Data? data,
    int? errorCode,
    String? errorMsg,
  }) {
    return Test1(
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
  final String apkLink;
  @JsonKey(defaultValue: 0)
  final int audit;
  @JsonKey(defaultValue: '')
  final String author;
  @JsonKey(defaultValue: false)
  final bool canEdit;
  @JsonKey(defaultValue: 0)
  final int chapterId;
  @JsonKey(defaultValue: '')
  final String chapterName;
  @JsonKey(defaultValue: false)
  final bool collect;
  @JsonKey(defaultValue: 0)
  final int courseId;
  @JsonKey(defaultValue: '')
  final String desc;
  @JsonKey(defaultValue: '')
  final String descMd;
  @JsonKey(defaultValue: '')
  final String envelopePic;
  @JsonKey(defaultValue: false)
  final bool fresh;
  @JsonKey(defaultValue: '')
  final String host;
  @JsonKey(defaultValue: 0)
  final int id;
  @JsonKey(defaultValue: '')
  final String link;
  @JsonKey(defaultValue: '')
  final String niceDate;
  @JsonKey(defaultValue: '')
  final String niceShareDate;
  @JsonKey(defaultValue: '')
  final String origin;
  @JsonKey(defaultValue: '')
  final String prefix;
  @JsonKey(defaultValue: '')
  final String projectLink;
  @JsonKey(defaultValue: 0)
  final int publishTime;
  @JsonKey(defaultValue: 0)
  final int realSuperChapterId;
  @JsonKey(defaultValue: 0)
  final int selfVisible;
  @JsonKey(defaultValue: 0)
  final int shareDate;
  @JsonKey(defaultValue: '')
  final String shareUser;
  @JsonKey(defaultValue: 0)
  final int superChapterId;
  @JsonKey(defaultValue: '')
  final String superChapterName;
  @JsonKey(defaultValue: [])
  final List<dynamic> tags;
  @JsonKey(defaultValue: '')
  final String title;
  @JsonKey(defaultValue: 0)
  final int type;
  @JsonKey(defaultValue: 0)
  final int userId;
  @JsonKey(defaultValue: 0)
  final int visible;
  @JsonKey(defaultValue: 0)
  final int zan;

  const Datas({
    required this.apkLink,
    required this.audit,
    required this.author,
    required this.canEdit,
    required this.chapterId,
    required this.chapterName,
    required this.collect,
    required this.courseId,
    required this.desc,
    required this.descMd,
    required this.envelopePic,
    required this.fresh,
    required this.host,
    required this.id,
    required this.link,
    required this.niceDate,
    required this.niceShareDate,
    required this.origin,
    required this.prefix,
    required this.projectLink,
    required this.publishTime,
    required this.realSuperChapterId,
    required this.selfVisible,
    required this.shareDate,
    required this.shareUser,
    required this.superChapterId,
    required this.superChapterName,
    required this.tags,
    required this.title,
    required this.type,
    required this.userId,
    required this.visible,
    required this.zan,
  });

  factory Datas.fromJson(Map<String, dynamic> json) =>
      _$DatasFromJson(json);

  Datas copyWith({
    String? apkLink,
    int? audit,
    String? author,
    bool? canEdit,
    int? chapterId,
    String? chapterName,
    bool? collect,
    int? courseId,
    String? desc,
    String? descMd,
    String? envelopePic,
    bool? fresh,
    String? host,
    int? id,
    String? link,
    String? niceDate,
    String? niceShareDate,
    String? origin,
    String? prefix,
    String? projectLink,
    int? publishTime,
    int? realSuperChapterId,
    int? selfVisible,
    int? shareDate,
    String? shareUser,
    int? superChapterId,
    String? superChapterName,
    List<dynamic>? tags,
    String? title,
    int? type,
    int? userId,
    int? visible,
    int? zan,
  }) {
    return Datas(
      apkLink: apkLink ?? this.apkLink,
      audit: audit ?? this.audit,
      author: author ?? this.author,
      canEdit: canEdit ?? this.canEdit,
      chapterId: chapterId ?? this.chapterId,
      chapterName: chapterName ?? this.chapterName,
      collect: collect ?? this.collect,
      courseId: courseId ?? this.courseId,
      desc: desc ?? this.desc,
      descMd: descMd ?? this.descMd,
      envelopePic: envelopePic ?? this.envelopePic,
      fresh: fresh ?? this.fresh,
      host: host ?? this.host,
      id: id ?? this.id,
      link: link ?? this.link,
      niceDate: niceDate ?? this.niceDate,
      niceShareDate: niceShareDate ?? this.niceShareDate,
      origin: origin ?? this.origin,
      prefix: prefix ?? this.prefix,
      projectLink: projectLink ?? this.projectLink,
      publishTime: publishTime ?? this.publishTime,
      realSuperChapterId: realSuperChapterId ?? this.realSuperChapterId,
      selfVisible: selfVisible ?? this.selfVisible,
      shareDate: shareDate ?? this.shareDate,
      shareUser: shareUser ?? this.shareUser,
      superChapterId: superChapterId ?? this.superChapterId,
      superChapterName: superChapterName ?? this.superChapterName,
      tags: tags ?? this.tags,
      title: title ?? this.title,
      type: type ?? this.type,
      userId: userId ?? this.userId,
      visible: visible ?? this.visible,
      zan: zan ?? this.zan,
    );
  }
}
