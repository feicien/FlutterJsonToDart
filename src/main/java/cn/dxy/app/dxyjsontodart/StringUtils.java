package cn.dxy.app.dxyjsontodart;

import com.google.common.base.CaseFormat;

public class StringUtils {


    /**
     * 获取小写文件名
     * @param text 用户输入 HelloWorld
     * @return hello_world
     */
    public static String getFileName(String text) {
        String fileName;

        if (text.contains("_")) {
            fileName = text.toLowerCase();
        } else {
            fileName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, text);
        }

        return fileName;
    }

    /**
     * 获取大写的类名
     * @param text 用户输入 hello_world
     * @return  HelloWorld
     */
    public static String getClassName(String text) {
        if (text.contains("_")) {
            return CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, text);
        } else {
            return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, text);
        }
    }
}
