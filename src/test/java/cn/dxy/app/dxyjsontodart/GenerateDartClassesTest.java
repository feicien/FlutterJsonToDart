package cn.dxy.app.dxyjsontodart;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;

public class GenerateDartClassesTest {



    @Test
    public void testJsonToDart() {
        try {
            URL url = Resources.getResource("test1.json");
            String json = Resources.toString(url, Charsets.UTF_8);
            String str = JsonHelper.generateDartClassesToString("HelloWorld", json);
            System.out.printf(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
