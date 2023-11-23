package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class AssertEqualsTest {

    // 同じ値であること（等価性）を検証する
    @Test
    public void test01() {
        String expected = "foo";
        String actual = "foo";
        assertEquals(expected, actual);
    }

    // 同じ値であること（等価性）を検証する
    @Test
    public void test02() {
        Person expected = new Person("Alice", 25);
        Person actual = new Person("Alice", 25);
        assertEquals(expected, actual);
    }

    // 同じ値でないことを検証する
    @Test
    public void test03() {
        String expected = "foo";
        String actual = "bar";
        assertNotEquals(expected, actual);
    }

    // 同じ値であること（等価性）を検証する
    @Test
    public void test04() {
        Person expected = new Person("Alice", 25);
        Person actual = new Person("Alice", 25);
        assertEquals(expected, actual);
    } 
    
    // 指定された許容誤差の範囲内であることを検証する
    @Test
    public void test05() {
        double expected = 0.3;
        double actual = 0.1 + 0.2;
        assertNotEquals(expected, actual);
        assertEquals(expected, actual, 1.0); // 1.0は許容誤差
    }
}