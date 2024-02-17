package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * モックの基本的な挙動を確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class MockTest {

    @Test
    @DisplayName("when方式：モックの基本的な挙動を確認する（インタフェース）")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, 2, 3), "test_Case_1");
    }

    @Test
    @DisplayName("when方式：モックの基本的な挙動を確認する（クラス）")
    void test_Case_2() {
        // HashMapクラスをモック化する
        Map<Integer, String> mapMock = mock(HashMap.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, 2, 3), "test_Case_2");
    }

    @Test
    @DisplayName("when方式：振る舞い設定の判定方法を確認する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<String, String> mock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mock.get(new String("1"))).thenReturn("one");

        // インスタンスが別でも等価であれば同じ引数と見なされる
        String value = mock.get(new String("1"));
        System.out.println(value);
    }

    @Test
    @DisplayName("when方式：戻り値が複数設定されていた場合の挙動を確認する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenReturn("baz0", "baz1", "baz2");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 2, 1, 2, 2);
        extractEntry(mapMock, keyList, "test_Case_5");
    }

    @Test
    @DisplayName("do方式：モックの基本的な挙動を確認する")
    void test_Case_5() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（do方式）
        doReturn("foo").when(mapMock).get(0);
        doReturn("bar").when(mapMock).get(1);
        doReturn("baz").when(mapMock).get(2);

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, 2, 3), "test_Case_5");
    }
}