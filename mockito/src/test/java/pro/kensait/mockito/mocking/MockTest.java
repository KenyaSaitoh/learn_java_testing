package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class MockTest {

    @Test
    void test_Case_1() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        extractEntry(mapMock, keyList, "test_Case_1");
    }

    @Test
    void test_Case_2() {
        Map<Integer, String> mapMock = mock(HashMap.class);

        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        extractEntry(mapMock, keyList, "test_Case_2");
    }

    @Test
    void test_Case_3() {
        Map<String, String> mock = mock(Map.class);

        when(mock.get(new String("1"))).thenReturn("one");

        // インスタンスが別でも等価であれば同じ引数と見なされる
        String value = mock.get(new String("1"));
        System.out.println(value);
    }

    @Test
    void test_Case_4() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(1)).thenReturn("barbar");
        when(mapMock.get(2)).thenReturn("baz0", "baz1", "baz2");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 2, 1, 2, 2);
        extractEntry(mapMock, keyList, "test_Case_4");
    }

    @Test
    void test_Case_5() {
        Map<Integer, String> mapMock = mock(Map.class);

        mapMock.put(0, "foo");
        mapMock.put(1, "bar");
        mapMock.put(2, "baz");
        when(mapMock.get(2)).thenReturn("bazbaz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        extractEntry(mapMock, keyList, "test_Case_5");
    }
}