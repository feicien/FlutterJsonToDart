package cn.dxy.app.dxyjsontodart;

import cn.dxy.app.dxyjsontodart.setting.FlutterJsonToDartSetting;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;

public class GenerateDartClassesTest {


    @Test
    public void testJsonToDart() {
        try {
            URL url = Resources.getResource("test1.json");
            String json = Resources.toString(url, Charsets.UTF_8);
            FlutterJsonToDartSetting setting = new FlutterJsonToDartSetting();
            setting.createToJson = false;
            setting.defaultValue = true;
            setting.useJsonKeyName = true;
            setting.explicitToJson = true;
            setting.fieldRename = true;
            setting.copyWith = true;
            String str = JsonHelper.generateDartClassesToString("test1", json, setting);

            URL url1 = Resources.getResource("test1.dart");
            String test2 = Resources.toString(url1, Charsets.UTF_8);

            assertEquals(str, test2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsonToDart2() {
        try {
            URL url = Resources.getResource("test2.json");
            String json = Resources.toString(url, Charsets.UTF_8);
            FlutterJsonToDartSetting setting = new FlutterJsonToDartSetting();
            setting.createToJson = false;
            setting.defaultValue = true;
            setting.useJsonKeyName = true;
            setting.explicitToJson = true;
            setting.fieldRename = true;
            setting.copyWith = true;
            String str = JsonHelper.generateDartClassesToString("test2", json, setting);

            URL url1 = Resources.getResource("test2.dart");
            String test2 = Resources.toString(url1, Charsets.UTF_8);

            assertEquals(str, test2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJsonToDart3() {
        try {
            URL url = Resources.getResource("test3.json");
            String json = Resources.toString(url, Charsets.UTF_8);
            FlutterJsonToDartSetting setting = new FlutterJsonToDartSetting();
            setting.createToJson = false;
            setting.defaultValue = true;
            setting.useJsonKeyName = true;
            setting.explicitToJson = true;
            setting.fieldRename = true;
            setting.copyWith = true;
            String str = JsonHelper.generateDartClassesToString("test3", json, setting);

            URL url1 = Resources.getResource("test3.dart");
            String test2 = Resources.toString(url1, Charsets.UTF_8);

            assertEquals(str, test2);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
