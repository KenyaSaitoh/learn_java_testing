package pro.kensait.junit5.assertion1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
 * コレクションや配列を検証するためのテストクラス
 */
public class AssertIterableTest {

    // 期待値と実測値（コレクション）を比較し、すべての要素が一致することを検証する
    @Test
    void test_IterableEquals() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertIterableEquals(expected, actual);
    }

    // 期待値と実測値（配列）を比較し、すべての要素が一致することを検証する
    @Test
    void test_ArrayEquals() {
        String[] expected = {"foo", "bar", "baz"};
        String[] actual = {"foo", "bar", "baz"};
        assertArrayEquals(expected, actual);
    }
}