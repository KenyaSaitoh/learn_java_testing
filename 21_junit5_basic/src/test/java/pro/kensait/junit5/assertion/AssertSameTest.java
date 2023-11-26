package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssertSameTest {

    // 同じインスタンスであること（同一性）を検証する
    @Test
    public void test01() {
        String expected = "foo";
        String actual = "foo";
        assertSame(expected, actual);
    }

    // 同じインスタンスではないことを検証する
    @Test
    public void test02() {
        Person expected = new Person("Alice", 25);
        Person actual = new Person("Alice", 25);
        assertNotSame (expected, actual);
    }
}