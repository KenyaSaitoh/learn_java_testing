package pro.kensait.mockito.mock.verify;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import pro.kensait.mockito.util.MapUtil;

/*
 * モックのコミュニケーションベース検証を確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class VerifyTest {

    @Test
    @DisplayName("モックのメソッドが呼び出されたかを検証する")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0);
        MapUtil.printEntry(mock, keyList, "test_Case_1");

        // モック（Map）の`get(0)`が一度だけ呼び出されたことを検証する
        verify(mock).get(0);
    }

    @Test
    @DisplayName("メソッド呼び出し回数を検証する")
    void test_Case_2() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 0, 0);
        MapUtil.printEntry(mock, keyList, "test_Case_2");

        // モック（Map）の`get(0)`が3回呼び出されたことを検証する
        verify(mock, times(3)).get(0);
    }

    @Test
    @DisplayName("メソッド呼び出し回数が特定の範囲内であることを検証する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 0, 0);
        MapUtil.printEntry(mock, keyList, "test_Case_3");

        // モック（Map）の`get(0)`呼び出しが最大でも5回であることを検証する
        verify(mock, atMost(5)).get(0);
    }

    @Test
    @DisplayName("メソッド呼び出しが一度も行われていないことを検証する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 0, 0);
        MapUtil.printEntry(mock, keyList, "test_Case_4");

        // モック（Map）の`get(1)`が一度も呼び出されていないことを検証する
        verify(mock, never()).get(1);
    }

    @Test
    @DisplayName("メソッド呼び出しが順番通りであることを検証する")
    void test_Case_5() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        MapUtil.printEntry(mock, keyList, "test_Case_5");

        // モック（Map）の`get(0)`、`get(1)`、`get(2)`がこの順番で呼び出されたことを検証する
        InOrder inOrder = inOrder(mock);
        inOrder.verify(mock).get(0);
        inOrder.verify(mock).get(1);
        inOrder.verify(mock).get(2);
    }
}