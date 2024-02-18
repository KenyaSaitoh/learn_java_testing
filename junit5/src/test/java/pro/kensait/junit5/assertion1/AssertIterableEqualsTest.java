package pro.kensait.junit5.assertion1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
 * コレクションを検証するためのテストクラス
 */
public class AssertIterableEqualsTest {

    // すべての要素が完全に一致することを検証する
    @Test
    void test_IterableEquals() {
        List<String> expected = Arrays.asList("foo", "bar", "baz");
        List<String> actual = Arrays.asList("foo", "bar", "baz");
        assertIterableEquals(expected, actual);
    }
}