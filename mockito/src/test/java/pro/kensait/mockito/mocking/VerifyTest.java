package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

@SuppressWarnings("unchecked")
public class VerifyTest {

    @Test
    void test_Case_1() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 0, 0);
        extractEntry(mapMock, keyList, "test_Case_1");

        // メソッド呼び出し回数を検証する
        verify(mapMock, times(3)).get(0);
    }

    @Test
    void test_Case_2() {

        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 0, 0);
        extractEntry(mapMock, keyList, "test_Case_2");

        // メソッド呼び出し回数が最大でも5回であることを検証する
        verify(mapMock, atMost(5)).get(0);
        System.out.println("");
    }

    @Test
    void test_Case_3() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 0, 0);
        extractEntry(mapMock, keyList, "test_Case_3");

        // メソッド呼び出しが一度も行われていないことを検証する
        verify(mapMock, never()).get(1);
    }

    @Test
    void test_Case_4() {
        Map<Integer, String> mapMock = mock(Map.class);

        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // マップからすべてのエントリを抽出する（実行フェーズ）
        List<Integer> keyList = Arrays.asList(0, 1, 2);       
        extractEntry(mapMock, keyList, "test_Case_4");

        // メソッドが指定された引数の順番に呼び出されていることを検証する
        InOrder inOrder = inOrder(mapMock);
        inOrder.verify(mapMock).get(0);
        inOrder.verify(mapMock).get(1);
        inOrder.verify(mapMock).get(2);
    }
}