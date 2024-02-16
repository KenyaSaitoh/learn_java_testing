package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class ExceptionTest {

    @Test
    void test_Case_1() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenThrow(new RuntimeException());

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        extractEntry(mapMock, keyList, "test_Case_1");
    }

    @Test
    void test_Case_2() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(0)).thenThrow(
                new RuntimeException("1st Exception"),
                new IllegalArgumentException("2nd Exception"),
                new NoSuchElementException("3rd Exception"));

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 0, 0);
        extractEntry(mapMock, keyList, "test_Case_2");
    }
}