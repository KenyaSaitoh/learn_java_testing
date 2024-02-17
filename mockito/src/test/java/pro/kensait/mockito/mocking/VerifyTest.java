package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

/*
 * モックのコミュニケーションベース検証を確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class VerifyTest {

    @Test
    @DisplayName("メソッド呼び出し回数を検証する")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_1");

        // `get(0)`の呼び出し回数を検証する
        verify(mapMock, times(3)).get(0);
    }

    @Test
    @DisplayName("メソッド呼び出し回数が特定の範囲内であることを検証する")
    void test_Case_2() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_2");

        // `get(0)`の呼び出し回数が最大でも5回であることを検証する
        verify(mapMock, atMost(5)).get(0);
    }

    @Test
    @DisplayName("メソッド呼び出しが一度も行われていないことを検証する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_3");

        // `get(1)`の呼び出しが一度も行われていないことを検証する
        verify(mapMock, never()).get(1);
    }

    @Test
    @DisplayName("メソッド呼び出しが順番通りであることを検証する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, 2), "test_Case_4");

        // `get(0)`、`get(1)`、`get(2)`という順番に呼び出されていることを検証する
        InOrder inOrder = inOrder(mapMock);
        inOrder.verify(mapMock).get(0);
        inOrder.verify(mapMock).get(1);
        inOrder.verify(mapMock).get(2);
    }
}