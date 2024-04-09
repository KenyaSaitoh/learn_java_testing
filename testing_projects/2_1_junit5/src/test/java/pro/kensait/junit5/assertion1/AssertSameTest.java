package pro.kensait.junit5.assertion1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * 同一性を検証するためのテストクラス
 */
public class AssertSameTest {

    // 実測値が、期待値と同じインスタンスであること（同一性）を検証する
    @Test
    void test_SameInstance() {
        String expected = "foo";
        String actual = "foo";
        assertSame(expected, actual);
    }

    // 実測値が、期待値と同じインスタンスでないことを検証する
    @Test
    void test_NotSameInstance() {
        String expected = new String("foo");
        String actual = new String("foo");
        assertNotSame(expected, actual);
    }
}