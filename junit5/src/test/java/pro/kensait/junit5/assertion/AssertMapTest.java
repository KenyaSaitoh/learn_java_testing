package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

/*
 * マップを検証するためのテストクラス
 */
public class AssertMapTest {

    // Mapに指定したキーが含まれていることを検証する
    @Test
    void test_ContainsKey() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertTrue(actual.containsKey(2));
    }

    // Mapに指定した値が含まれていることを検証する
    @Test
    void test_ContainsValue() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertTrue(actual.containsValue("Two"));
    }

    // Mapに指定したエントリーが含まれていることを検証する
    @Test
    void test_Contains() {
        Map<Integer, String> actual = Map.of(1, "One", 2, "Two", 3, "Three");
        assertTrue(actual.entrySet().contains(Map.entry(2, "Two")));
    }
}