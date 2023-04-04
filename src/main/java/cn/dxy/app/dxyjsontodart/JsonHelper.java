package cn.dxy.app.dxyjsontodart;

import cn.dxy.app.dxyjsontodart.setting.FlutterJsonToDartSetting;
import com.google.common.base.CaseFormat;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.intellij.openapi.util.text.StringUtil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonHelper {


    /**
     * @param className   用户输入的类名
     * @param originalStr 用户输入的 json
     */
    public static String generateDartClassesToString(String className, String originalStr, FlutterJsonToDartSetting setting) {

        Map<String, Map<String, Object>> map = new LinkedHashMap<>();

        //遍历 json 数据，找到所有对象
        getAllModel(className, originalStr, map);

        StringBuilder sb = new StringBuilder();

        //遍历找到的对象，一个对象生成一个模型
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : map.entrySet()) {
            String classContent = generateClassContent(stringMapEntry.getKey(), stringMapEntry.getValue(), setting);
            sb.append(classContent);
        }

        //生成
        return "import 'package:json_annotation/json_annotation.dart';\n" +
                "\npart '" + StringUtils.getFileName(className) + ".g.dart';\n" +
                sb;
    }

    //处理内部类
    private static void getAllModel(String className, String originalStr, Map<String, Map<String, Object>> map) {

        String name = StringUtils.getClassName(className);

        JsonElement jsonElement = JsonParser.parseString(originalStr);

        if (jsonElement.isJsonObject()) {
            Map<String, Object> item = fromJsonToMap(originalStr);
            map.put(name, item);

            for (Map.Entry<String, Object> entry : item.entrySet()) {
                //首字母大写
                String key = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, entry.getKey());

                Object value = entry.getValue();

                if (value instanceof Map) {
                    getAllModel(key, new Gson().toJson(value), map);
                } else if (value instanceof List) {
                    //处理这种情况 []
                    List<?> list = (List<?>) value;
                    if (!list.isEmpty()) {
                        getAllModel(key, new Gson().toJson(list.get(0)), map);
                    }
                }
            }

        } else if (jsonElement.isJsonArray()) {
            JsonArray array = jsonElement.getAsJsonArray();
            JsonElement jsonElement1 = array.get(0);

            getAllModel(name, jsonElement1.toString(), map);
        }
    }

    /**
     * 根据 item 中的数据生成一个模型
     */
    private static String generateClassContent(String fileName1, Map<String, Object> item, FlutterJsonToDartSetting setting) {
        boolean createToJson = setting.createToJson;
        boolean defaultValue = setting.defaultValue;
        boolean useJsonKeyName = setting.useJsonKeyName;
        boolean explicitToJson = setting.explicitToJson;
        boolean fieldRename = setting.fieldRename;
        boolean copyWith = setting.copyWith;

        String fileName = StringUtils.getClassName(fileName1);

        StringBuilder sb = new StringBuilder();

        sb.append("\n@JsonSerializable(");
        if (explicitToJson) {
            sb.append("explicitToJson: true");
            if (fieldRename) {
                sb.append(", ");
                sb.append("fieldRename: FieldRename.none");
            }
        } else {
            if (fieldRename) {
                sb.append("fieldRename: FieldRename.none");
            }
        }
        sb.append(")");

        sb.append("\nclass ");
        sb.append(fileName).append(" {\n");


        for (Map.Entry<String, Object> entry : item.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            String paramsType;
            String paramsName;
            String defaultValueStr = getDefaultValue(value);
            //如果 json 中的属性名称中包含下划线，添加 JsonKey 注解，如果不包含，就不加注解
            if (key.contains("_")) {
                paramsName = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, key);
                paramsType = getObjectType(paramsName, value, defaultValue);
                if (useJsonKeyName) {
                    if (defaultValue && StringUtil.isNotEmpty(defaultValueStr)) {
                        sb.append("  @JsonKey(name: '").append(key).append("', ").append(defaultValueStr).append(")\n");
                    } else {
                        sb.append("  @JsonKey(name: '").append(key).append("')\n");
                    }
                } else {
                    if (defaultValue && StringUtil.isNotEmpty(defaultValueStr)) {
                        sb.append("  @JsonKey(").append(defaultValueStr).append(")\n");
                    }
                }
            } else {
                paramsName = key;
                paramsType = getObjectType(key, value, defaultValue);

                if (defaultValue && StringUtil.isNotEmpty(defaultValueStr)) {
                    sb.append("  @JsonKey(").append(defaultValueStr).append(")\n");
                }
            }
            sb.append("  final ").append(paramsType).append(" ").append(paramsName).append(";\n");
        }

        sb.append("\n  const ").append(fileName).append("({\n");


        for (Map.Entry<String, Object> entry : item.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key.contains("_")) {
                key = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getKey());
            }
            String defaultValueStr = getDefaultValue(value);
            if (defaultValue && StringUtil.isNotEmpty(defaultValueStr)) {
                sb.append("    required this.");
            } else {
                sb.append("    this.");
            }
            sb.append(key).append(",\n");
        }

        sb.append("  });\n\n");

        // fromJson 方法
        sb.append("  factory ").append(fileName).append(".fromJson(Map<String, dynamic> json) =>\n      _$").append(fileName).append("FromJson(json);\n");

        // toJson 方法
        if (createToJson) {
            sb.append("\n  ");
            sb.append("Map<String, dynamic> toJson() => _$").append(fileName).append("ToJson(this);");
            sb.append("\n");
        }

        // copyWith 方法
        if (copyWith) {
            sb.append("\n  ");
            sb.append(fileName).append(" copyWith({\n");

            for (Map.Entry<String, Object> entry : item.entrySet()) {
                String key = entry.getKey();
                Object value = entry.getValue();
                if (key.contains("_")) {
                    key = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getKey());
                }
                String type = getObjectType(key, value, false);
                sb.append("    ").append(type).append(" ").append(key).append(",\n");
            }

            sb.append("  }) {\n    return ").append(fileName).append("(\n");

            for (Map.Entry<String, Object> entry : item.entrySet()) {
                String key = entry.getKey();
                if (key.contains("_")) {
                    key = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entry.getKey());
                }
                sb.append("      ").append(key).append(": ").append(key).append(" ?? this.").append(key).append(",\n");
            }
            sb.append("    );\n  }\n");
        }

        sb.append("}\n");

        return sb.toString();
    }

    private static String getDefaultValue(Object value) {
        if (value instanceof Boolean) {
            return "defaultValue: false";
        } else if (value instanceof Double) {
            return "defaultValue: 0";
        } else if (value instanceof Long || value instanceof Integer) {
            return "defaultValue: 0";
        } else if (value instanceof List) {
            return "defaultValue: []";
        } else if (value instanceof String) {
            return "defaultValue: ''";
        }
        return null;
    }

    private static String getObjectType(String key, Object value, boolean defaultValue) {
        String classType = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, key);
        if (value instanceof Boolean) {
            return defaultValue ? "bool" : "bool?";
        } else if (value instanceof Double) {
            return defaultValue ? "double" : "double?";
        } else if (value instanceof Long || value instanceof Integer) {
            return defaultValue ? "int" : "int?";
        } else if (value instanceof List) {
            List<?> list = (List<?>) value;
            // 处理这种数据 []
            if (!list.isEmpty()) {
                Object o = list.get(0);
                if (o instanceof Boolean) {
                    classType = "bool";
                } else if (o instanceof Double) {
                    classType = "double";
                } else if (o instanceof Long) {
                    classType = "int";
                } else if (o instanceof Integer) {
                    classType = "int";
                }
                if (o instanceof String) {
                    classType = "String";
                }
                return defaultValue ? "List<" + classType + ">" : "List<" + classType + ">?";
            }
            return defaultValue ? "List<dynamic>" : "List<dynamic>?";
        } else if (value instanceof Map) {
            return classType + "?";
        } else if (value instanceof String) {
            return defaultValue ? "String" : "String?";
        }
        return "dynamic";
    }

    private static Map<String, Object> fromJsonToMap(String json) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {
        }.getType(), new MapDeserializerDoubleAsIntFix());
        Gson gson = gsonBuilder.create();
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
    }
}
