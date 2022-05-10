package pro.kensait.java.hamcrest;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class HamcrestLibMatchersTest2 {

    // すべての要素が完全に一致することを検証する
    @Test
    public void test01() {
        List<String> actual = List.of("foo", "bar", "baz");
        assertThat(actual, contains("foo", "bar", "baz"));
        // assertThat(actual, contains("baz", "bar", "foo")); // エラー
    }

    // すべての要素が一致する（ソート順は無視）ことを検証する
    @Test
    public void test02() {
        List<String> actual = List.of("foo", "bar", "baz");
        assertThat(actual, containsInAnyOrder("baz", "bar", "foo"));
    }

    // 指定した要素がすべて含まれていることを検証する
    @Test
    public void test03() {
        List<String> actual = List.of("foo", "bar", "baz");
        assertThat(actual, hasItems("baz", "foo"));
    }

    // 指定したサイズであることを検証する
    @Test
    public void test04() {
        List<String> actual = List.of("foo", "bar", "baz");
        assertThat(actual, hasSize(3));
    }

    // 空であることを検証する
    @Test
    public void test05() {
        List<String> actual = List.of();
        assertThat(actual, empty());
    }

    // Mapに指定したキーが含まれていることを検証する
    @Test
    public void test06() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertThat(actual, hasKey(2));
    }

    // Mapに指定した値が含まれていることを検証する
    @Test
    public void test07() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertThat(actual, hasValue("Two"));
    }

    // Mapに指定したエントリーが含まれていることを検証する
    @Test
    public void test08() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertThat(actual, hasEntry(2, "Two"));
    }
}