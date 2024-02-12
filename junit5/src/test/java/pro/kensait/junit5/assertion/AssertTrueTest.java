package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * assertTrue()による真偽の検証方法を説明するためのテストクラス
 */
public class AssertTrueTest {

    // boolean値trueであることを検証する
    @Test
    public void test_True() {
        boolean actual = true;
        assertTrue(actual);
    }

    // 指定したクラスのインスタンス型であることを検証する
    @Test
    public void test_InstanceOf() {
        Integer actual = 10;
        assertTrue(actual instanceof Number);
    }

    // 指定された値より小さいことを検証する
    @Test
    public void test_LessThan() {
        int actual = 9;
        assertTrue(actual < 10);
    }

    // 指定された値以下であることを検証する
    @Test
    public void test_Less() {
        int actual = 10;
        assertTrue(actual <= 10);
    }

    // 指定した文字で始まることを検証する
    @Test
    public void test_StartsWith() {
        String actual = "foo";
        assertTrue(actual.startsWith("f"));
    }

    // 指定した文字が含まれることを検証する
    @Test
    public void test_Contains() {
        String actual = "foofoo";
        assertTrue(actual.contains("of"));
    }

    // 空文字であることを検証する
    @Test
    public void test_IsEmpty() {
        String actual = "";
        assertTrue(actual.isEmpty());
    }

    // 空文字またはnullであることを検証する
    @Test
    public void test_IsNullOrEmpty() {
        String actual = "";
        assertTrue(actual == null || actual.isEmpty());
    }
}