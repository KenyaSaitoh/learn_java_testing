package pro.kensait.mockito.mocking;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class MatcherTest {

    @Test
    void test_Case_1() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(anyInt())).thenReturn("foo");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        extractEntry(mapMock, keyList, "test_Case_1");
    }

    @Test
    void test_Case_2() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(any(Integer.class))).thenReturn("hoge");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, null);
        extractEntry(mapMock, keyList, "test_Case_2");
    }

    @Test
    void test_Case_3() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(eq(1))).thenReturn("hoge");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, null);
        extractEntry(mapMock, keyList, "test_Case_3");
    }

    @Test
    void test_Case_4() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(nullable(Integer.class))).thenReturn("hoge");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, null);
        extractEntry(mapMock, keyList, "test_Case_4");
    }
}