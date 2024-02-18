package pro.kensait.junit5.assertion1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * 真偽値を検証するためのテストクラス
 */
public class AssertTrueTest {

    // 実測値が、boolean値trueであることを検証する
    @Test
    void test_True() {
        boolean actual = true;
        assertTrue(actual);
    }

    // 実測値が、boolean値falseであることを検証する
    @Test
    void test_False() {
        boolean actual = false;
        assertFalse(actual);
    }

    // 実測値が、指定されたクラスのインスタンスであることを検証する
    @Test
    void test_InstanceOf() {
        Integer actual = 10;
        assertTrue(actual instanceof Number);
    }

    // 実測値が、指定された値より小さいことを検証する
    @Test
    void test_LessThan() {
        int actual = 9;
        assertTrue(actual < 10);
    }

    // 実測値が、指定された値以下であることを検証する
    @Test
    void test_Less() {
        int actual = 10;
        assertTrue(actual <= 10);
    }

    // 実測値が、指定した文字で始まることを検証する
    @Test
    void test_StartsWith() {
        String actual = "foo";
        assertTrue(actual.startsWith("f"));
    }

    // 実測値に、指定した文字が含まれることを検証する
    @Test
    void test_Contains() {
        String actual = "foofoo";
        assertTrue(actual.contains("of"));
    }

    // 実測値が、空文字であることを検証する
    @Test
    void test_IsEmpty() {
        String actual = "";
        assertTrue(actual.isEmpty());
    }

    // 実測値が、空文字またはnullであることを検証する
    @Test
    void test_IsNullOrEmpty() {
        String actual = "";
        assertTrue(actual == null || actual.isEmpty());
    }
}