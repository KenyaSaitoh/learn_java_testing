package pro.kensait.junit5.assertion2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.Test;

/*
 * セットを検証するためのテストクラス
 */
public class SetTest {

    // 期待値セットと実測値セットを比較し、すべての要素が一致することを検証する
    @Test
    void test_All_Match() {
        Set<String> expected = Set.of("foo", "bar", "baz");
        Set<String> actual = Set.of("baz", "bar", "foo");
        assertIterableEquals(expected, actual);
    }
}