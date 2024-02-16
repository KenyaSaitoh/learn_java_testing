package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

@DisplayName("verify()によるコミュニケーション検証を確認するテスト")
@SuppressWarnings("unchecked")
public class VerifyTest {

    @Test
    @DisplayName("")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapMock, List.of(0, 0, 0), "test_Case_1");

        // メソッド呼び出し回数を検証する
        verify(mapMock, times(3)).get(0);
    }

    @Test
    @DisplayName("")
    void test_Case_2() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapMock, List.of(0, 0, 0), "test_Case_2");

        // メソッド呼び出し回数が最大でも5回であることを検証する
        verify(mapMock, atMost(5)).get(0);
    }

    @Test
    @DisplayName("")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapMock, List.of(0, 0, 0), "test_Case_3");

        // メソッド呼び出しが一度も行われていないことを検証する
        verify(mapMock, never()).get(1);
    }

    @Test
    @DisplayName("")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapMock, List.of(0, 1, 2), "test_Case_4");

        // メソッドが指定された引数の順番に呼び出されていることを検証する
        InOrder inOrder = inOrder(mapMock);
        inOrder.verify(mapMock).get(0);
        inOrder.verify(mapMock).get(1);
        inOrder.verify(mapMock).get(2);
    }
}