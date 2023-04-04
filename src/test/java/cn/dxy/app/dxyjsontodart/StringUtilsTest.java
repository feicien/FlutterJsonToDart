package cn.dxy.app.dxyjsontodart;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilsTest {

    @Test
    public void testFileName() {
        String s1 = StringUtils.getFileName("hello_world");
        assertEquals(s1, "hello_world");
    }

    @Test
    public void testClassName0() {
        String s2 = StringUtils.getClassName("hello_world");
        assertEquals(s2, "HelloWorld");
    }

    @Test
    public void testClassName1() {
        String s2 = StringUtils.getClassName("orderInfo");
        assertEquals(s2, "OrderInfo");
    }

    @Test
    public void testClassName2() {
        String s2 = StringUtils.getClassName("OrderInfo");
        assertEquals(s2, "OrderInfo");
    }
}
