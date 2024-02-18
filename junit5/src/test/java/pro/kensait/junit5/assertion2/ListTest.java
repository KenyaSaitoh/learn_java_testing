package pro.kensait.junit5.assertion2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
 * リストを検証するためのテストクラス
 */
public class ListTest {

    // 期待値リストのすべての要素が実測値リストの中に含まれていることを検証する
    @Test
    void test_Some_Match() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("baz", "bar", "qux", "foobar", "foo");
        assertTrue(actual.containsAll(expected));
    }

    // 期待値リストと実測値リストを比較し、すべての要素が一致する（ソート順は無視）ことを検証する
    @Test
    void test_All_Match_IgnoreSort_1() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("baz", "bar", "foo");
        Collections.sort(expected);
        Collections.sort(actual);
        assertIterableEquals(expected, actual);
    }

    // 期待値リストと実測値リストを比較し、すべての要素が一致する（ソート順は無視）ことを検証する
    @Test
    void test_All_Match_IgnoreSort_2() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("baz", "bar", "foo");
        assertTrue(expected.containsAll(actual) && actual.containsAll(expected));
    }

    // 実測値が、期待されるサイズであることを検証する
    @Test
    void test_ElementsSize() {
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertEquals(3, actual.size());
    }

    // 実測値が、空であることを検証する
    @Test
    void test_ElementsEmpty() {
        List<String> actual = List.of();
        assertTrue(actual.isEmpty());
    }
}