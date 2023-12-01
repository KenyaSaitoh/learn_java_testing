package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class AssertCollectionTest {

    // すべての要素が完全に一致することを検証する
    @Test
    public void test01() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertIterableEquals(expected, actual);
    }

    // すべての要素が一致する（ソート順は無視）ことを検証する
    @Test
    public void test02() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("baz", "bar", "foo");
        Collections.sort(expected);
        Collections.sort(actual);
        assertIterableEquals(expected, actual);
    }

    // 指定した要素がすべて含まれていることを検証する
    @Test
    public void test03() {
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertTrue(actual.containsAll(Arrays.asList("baz", "foo")));
    }

    // 指定したサイズであることを検証する
    @Test
    public void test04() {
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertEquals(3, actual.size());
    }

    // 空であることを検証する
    @Test
    public void test05() {
        List<String> actual = List.of();
        assertTrue(actual.isEmpty());
    }

    // Mapに指定したキーが含まれていることを検証する
    @Test
    public void test06() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertTrue(actual.containsKey(2));
    }

    // Mapに指定した値が含まれていることを検証する
    @Test
    public void test07() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertTrue(actual.containsValue("Two"));
    }

    // Mapに指定したエントリーが含まれていることを検証する
    @Test
    public void test08() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertTrue(actual.entrySet().contains(Map.entry(2, "Two")));
    }
}