import 'package:json_annotation/json_annotation.dart';

part 'test3.g.dart';

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class Test3 {
  @JsonKey(defaultValue: '')
  final String commodityName;
  @JsonKey(defaultValue: '')
  final String commodityId;
  @JsonKey(defaultValue: '')
  final String specificationOptionNames;
  @JsonKey(defaultValue: 0)
  final int expireTime;
  final ReserveStoreInfo? reserveStoreInfo;
  @JsonKey(defaultValue: '')
  final String id;
  @JsonKey(defaultValue: 0)
  final int orderType;
  @JsonKey(defaultValue: '')
  final String orderItemId;
  @JsonKey(defaultValue: false)
  final bool deleted;
  @JsonKey(defaultValue: '')
  final String orderSubmitId;
  @JsonKey(defaultValue: 0)
  final int status;
  @JsonKey(defaultValue: '')
  final String reason;
  @JsonKey(defaultValue: '')
  final String verificationCode;
  final ReserveUserInfo? reserveUserInfo;
  final ReserveTimeInfo? reserveTimeInfo;
  @JsonKey(defaultValue: false)
  final bool showTreat;
  @JsonKey(defaultValue: '')
  final String supplierId;
  @JsonKey(defaultValue: '')
  final String shuffleActivityId;
  final OrderInfo? orderInfo;

  const Test3({
    required this.commodityName,
    required this.commodityId,
    required this.specificationOptionNames,
    required this.expireTime,
    this.reserveStoreInfo,
    required this.id,
    required this.orderType,
    required this.orderItemId,
    required this.deleted,
    required this.orderSubmitId,
    required this.status,
    required this.reason,
    required this.verificationCode,
    this.reserveUserInfo,
    this.reserveTimeInfo,
    required this.showTreat,
    required this.supplierId,
    required this.shuffleActivityId,
    this.orderInfo,
  });

  factory Test3.fromJson(Map<String, dynamic> json) =>
      _$Test3FromJson(json);

  Test3 copyWith({
    String? commodityName,
    String? commodityId,
    String? specificationOptionNames,
    int? expireTime,
    ReserveStoreInfo? reserveStoreInfo,
    String? id,
    int? orderType,
    String? orderItemId,
    bool? deleted,
    String? orderSubmitId,
    int? status,
    String? reason,
    String? verificationCode,
    ReserveUserInfo? reserveUserInfo,
    ReserveTimeInfo? reserveTimeInfo,
    bool? showTreat,
    String? supplierId,
    String? shuffleActivityId,
    OrderInfo? orderInfo,
  }) {
    return Test3(
      commodityName: commodityName ?? this.commodityName,
      commodityId: commodityId ?? this.commodityId,
      specificationOptionNames: specificationOptionNames ?? this.specificationOptionNames,
      expireTime: expireTime ?? this.expireTime,
      reserveStoreInfo: reserveStoreInfo ?? this.reserveStoreInfo,
      id: id ?? this.id,
      orderType: orderType ?? this.orderType,
      orderItemId: orderItemId ?? this.orderItemId,
      deleted: deleted ?? this.deleted,
      orderSubmitId: orderSubmitId ?? this.orderSubmitId,
      status: status ?? this.status,
      reason: reason ?? this.reason,
      verificationCode: verificationCode ?? this.verificationCode,
      reserveUserInfo: reserveUserInfo ?? this.reserveUserInfo,
      reserveTimeInfo: reserveTimeInfo ?? this.reserveTimeInfo,
      showTreat: showTreat ?? this.showTreat,
      supplierId: supplierId ?? this.supplierId,
      shuffleActivityId: shuffleActivityId ?? this.shuffleActivityId,
      orderInfo: orderInfo ?? this.orderInfo,
    );
  }
}

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class ReserveStoreInfo {
  @JsonKey(defaultValue: '')
  final String storeName;
  @JsonKey(defaultValue: '')
  final String storeAddress;
  @JsonKey(defaultValue: '')
  final String storeCellphone;
  @JsonKey(defaultValue: '')
  final String supplierLogo;
  @JsonKey(defaultValue: '')
  final String latitude;
  @JsonKey(defaultValue: '')
  final String longitude;
  @JsonKey(defaultValue: '')
  final String supplierNotice;
  @JsonKey(defaultValue: 0)
  final int serviceNature;

  const ReserveStoreInfo({
    required this.storeName,
    required this.storeAddress,
    required this.storeCellphone,
    required this.supplierLogo,
    required this.latitude,
    required this.longitude,
    required this.supplierNotice,
    required this.serviceNature,
  });

  factory ReserveStoreInfo.fromJson(Map<String, dynamic> json) =>
      _$ReserveStoreInfoFromJson(json);

  ReserveStoreInfo copyWith({
    String? storeName,
    String? storeAddress,
    String? storeCellphone,
    String? supplierLogo,
    String? latitude,
    String? longitude,
    String? supplierNotice,
    int? serviceNature,
  }) {
    return ReserveStoreInfo(
      storeName: storeName ?? this.storeName,
      storeAddress: storeAddress ?? this.storeAddress,
      storeCellphone: storeCellphone ?? this.storeCellphone,
      supplierLogo: supplierLogo ?? this.supplierLogo,
      latitude: latitude ?? this.latitude,
      longitude: longitude ?? this.longitude,
      supplierNotice: supplierNotice ?? this.supplierNotice,
      serviceNature: serviceNature ?? this.serviceNature,
    );
  }
}

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class ReserveUserInfo {
  @JsonKey(defaultValue: '')
  final String id;
  @JsonKey(defaultValue: '')
  final String realName;
  @JsonKey(defaultValue: 0)
  final int sex;
  @JsonKey(defaultValue: '')
  final String ageStr;

  const ReserveUserInfo({
    required this.id,
    required this.realName,
    required this.sex,
    required this.ageStr,
  });

  factory ReserveUserInfo.fromJson(Map<String, dynamic> json) =>
      _$ReserveUserInfoFromJson(json);

  ReserveUserInfo copyWith({
    String? id,
    String? realName,
    int? sex,
    String? ageStr,
  }) {
    return ReserveUserInfo(
      id: id ?? this.id,
      realName: realName ?? this.realName,
      sex: sex ?? this.sex,
      ageStr: ageStr ?? this.ageStr,
    );
  }
}

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class ReserveTimeInfo {
  @JsonKey(defaultValue: '')
  final String reserveDate;

  const ReserveTimeInfo({
    required this.reserveDate,
  });

  factory ReserveTimeInfo.fromJson(Map<String, dynamic> json) =>
      _$ReserveTimeInfoFromJson(json);

  ReserveTimeInfo copyWith({
    String? reserveDate,
  }) {
    return ReserveTimeInfo(
      reserveDate: reserveDate ?? this.reserveDate,
    );
  }
}

@JsonSerializable(explicitToJson: true, fieldRename: FieldRename.none)
class OrderInfo {
  @JsonKey(defaultValue: 0)
  final int price;
  @JsonKey(defaultValue: 0)
  final int appointmentPrice;
  @JsonKey(defaultValue: 0)
  final int deductionCouponDenomination;
  @JsonKey(defaultValue: 0)
  final int payedAmount;
  @JsonKey(defaultValue: false)
  final bool reserveFee;
  @JsonKey(defaultValue: 0)
  final int commentType;
  @JsonKey(defaultValue: 0)
  final int groupPrice;

  const OrderInfo({
    required this.price,
    required this.appointmentPrice,
    required this.deductionCouponDenomination,
    required this.payedAmount,
    required this.reserveFee,
    required this.commentType,
    required this.groupPrice,
  });

  factory OrderInfo.fromJson(Map<String, dynamic> json) =>
      _$OrderInfoFromJson(json);

  OrderInfo copyWith({
    int? price,
    int? appointmentPrice,
    int? deductionCouponDenomination,
    int? payedAmount,
    bool? reserveFee,
    int? commentType,
    int? groupPrice,
  }) {
    return OrderInfo(
      price: price ?? this.price,
      appointmentPrice: appointmentPrice ?? this.appointmentPrice,
      deductionCouponDenomination: deductionCouponDenomination ?? this.deductionCouponDenomination,
      payedAmount: payedAmount ?? this.payedAmount,
      reserveFee: reserveFee ?? this.reserveFee,
      commentType: commentType ?? this.commentType,
      groupPrice: groupPrice ?? this.groupPrice,
    );
  }
}
