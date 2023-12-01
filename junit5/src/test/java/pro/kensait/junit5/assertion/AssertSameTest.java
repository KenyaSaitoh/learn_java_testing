package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssertSameTest {

    // 同じインスタンスであること（同一性）を検証する
    @Test
    public void test_SameInstance() {
        String expected = "foo";
        String actual = "foo";
        assertSame(expected, actual);
    }

    // 同じインスタンスではないことを検証する
    @Test
    public void test_NotSameInstance() {
        String expected = new String("foo");
        String actual = new String("foo");
        assertNotSame(expected, actual);
    }
}