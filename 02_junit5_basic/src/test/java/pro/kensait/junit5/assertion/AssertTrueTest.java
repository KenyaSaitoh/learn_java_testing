package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssertTrueTest {

    // boolean値trueであることを検証する
    @Test
    public void test01() {
        boolean actual = true;
        assertTrue(actual);
    }

    // 指定したクラスのインスタンス型であることを検証する
    @Test
    public void test02() {
        Integer actual = 10;
        assertTrue(actual instanceof Number);
    }

    // 指定された値より小さいことを検証する
    @Test
    public void test03() {
        int actual = 9;
        assertTrue(actual < 10);
    }

    // 指定された値以下であることを検証する
    @Test
    public void test04() {
        int actual = 10;
        assertTrue(actual <= 10);
    }

    // 指定した文字で始まることを検証する
    @Test
    public void test05() {
        String actual = "foo";
        assertTrue(actual.startsWith("f"));
    }

    // 指定した文字が含まれることを検証する
    @Test
    public void test06() {
        String actual = "foofoo";
        assertTrue(actual.contains("of"));
    }

    // 空文字であることを検証する
    @Test
    public void test07() {
        String actual = "";
        assertTrue(actual.isEmpty());
    }

    // 空文字またはnullであることを検証する
    @Test
    public void test08() {
        String actual = "";
        assertTrue(actual == null || actual.isEmpty());
    }
}