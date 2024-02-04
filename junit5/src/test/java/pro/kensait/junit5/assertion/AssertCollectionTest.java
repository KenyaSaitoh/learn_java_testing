package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

public class AssertCollectionTest {

    // すべての要素が完全に一致することを検証する
    @Test
    public void test_AllElements_Mach() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertIterableEquals(expected, actual);
    }

    // すべての要素が一致する（ソート順は無視）ことを検証する
    @Test
    public void test_AllElements_Maches_IgnoreSort() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("baz", "bar", "foo");
        Collections.sort(expected);
        Collections.sort(actual);
        assertIterableEquals(expected, actual);
    }

    // 指定した要素がすべて含まれていることを検証する
    @Test
    public void test_ContainsElements() {
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertTrue(actual.containsAll(Arrays.asList("baz", "foo")));
    }

    // 指定したサイズであることを検証する
    @Test
    public void test_ElementsSize() {
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertEquals(3, actual.size());
    }

    // 空であることを検証する
    @Test
    public void test_ElementsEmpty() {
        List<String> actual = List.of();
        assertTrue(actual.isEmpty());
    }
}