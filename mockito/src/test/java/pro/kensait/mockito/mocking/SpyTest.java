package pro.kensait.mockito.mocking;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class SpyTest {

    @Test
    void test_Case_1() {
        Map<Integer, String> mapSpy = spy(Map.class);

        when(mapSpy.get(0)).thenReturn("foo");
        when(mapSpy.get(1)).thenReturn("bar");
        when(mapSpy.get(2)).thenReturn("baz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        extractEntry(mapSpy, keyList, "test_Case_4");
    }

    @Test
    void test_Case_2() {
        Map<String, String> mapSpy = spy(Map.class);

        when(mapSpy.get(new String("1"))).thenReturn("one");

        // インスタンスが別でも等価であれば同じ引数と見なされる
        String value = mapSpy.get(new String("1"));
        System.out.println(value);
    }

    @Test
    void test_Case_3() {
        Map<Integer, String> mapSpy = spy(Map.class);

        when(mapSpy.get(0)).thenReturn("foo");
        when(mapSpy.get(1)).thenReturn("bar");
        when(mapSpy.get(1)).thenReturn("barbar");
        when(mapSpy.get(2)).thenReturn("baz0", "baz1", "baz2");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 2, 1, 2, 2);
        extractEntry(mapSpy, keyList, "test_Case_4");
    }

    @Test
    void test_Case_4() {
        Map<Integer, String> mapSpy = spy(Map.class);

        when(mapSpy.get(anyInt())).thenReturn("foo");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        extractEntry(mapSpy, keyList, "test_Case_4");
    }

    @Test
    void test_Case_5() {
        Map<Integer, String> mapSpy = spy(HashMap.class);

        mapSpy.put(0, "foo");
        mapSpy.put(1, "bar");
        mapSpy.put(2, "baz");
        when(mapSpy.get(2)).thenReturn("bazbaz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        extractEntry(mapSpy, keyList, "test_Case_5");
    }
}