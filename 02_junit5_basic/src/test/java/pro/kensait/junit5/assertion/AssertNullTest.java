package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssertNullTest {

    // null値であることを検証する
    @Test
    public void test01() {
        String actual = null;
        assertNull(actual);
    }

    // null値でないことを検証する
    @Test
    public void test02() {
        String actual = "foo";
        assertNotNull(actual);
    }
}