package pro.kensait.junit5.assertion1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * NULL値を検証するためのテストクラス
 */
public class AssertNullTest {

    // 実測値が、null値であることを検証する
    @Test
    void test_IsNull() {
        String actual = null;
        assertNull(actual);
    }

    // 実測値が、null値でないことを検証する
    @Test
    void test_IsNotNull() {
        String actual = "foo";
        assertNotNull(actual);
    }
}