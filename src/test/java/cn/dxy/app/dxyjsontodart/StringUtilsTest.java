package cn.dxy.app.dxyjsontodart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void testName() {

        String s1 = StringUtils.getFileName("hello_world");
        assertEquals(s1, "hello_world");

        String s2 = StringUtils.getClassName("hello_world");
        assertEquals(s2, "HelloWorld");

        String s3 = StringUtils.toUpperCamel("hello_world");
        assertEquals(s3, "HelloWorld");

        String s4 = StringUtils.toLowerCamel("hello_world");
        assertEquals(s4, "helloWorld");

        String s5 = StringUtils.toUpperCaseFirstOne("hello_world");
        assertEquals(s5, "Hello_world");

    }
}
