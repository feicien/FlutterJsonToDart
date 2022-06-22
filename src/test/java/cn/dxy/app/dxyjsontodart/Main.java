package cn.dxy.app.dxyjsontodart;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        try {
            URL url = Resources.getResource("test1.json");
            String json = Resources.toString(url, Charsets.UTF_8);
            String str = JsonHelper.generateDartClassesToString("HelloWorld", json);
            System.out.printf(str);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
