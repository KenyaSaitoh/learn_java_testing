package pro.kensait.mockito.mock.behavior;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pro.kensait.mockito.util.MapUtil;

/*
 * モックの基本的な挙動を確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class BasicMockTest_1 {

    @Test
    @DisplayName("when-then方式：モックの基本的な挙動を確認する（インタフェース）")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        MapUtil.printEntry(mock, keyList, "test_Case_1");
    }

    @Test
    @DisplayName("when-then方式：モックの基本的な挙動を確認する（クラス）")
    void test_Case_2() {
        // HashMapクラスをモック化する
        Map<Integer, String> mock = mock(HashMap.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        MapUtil.printEntry(mock, keyList, "test_Case_2");
    }

    @Test
    @DisplayName("when-then方式：振る舞い設定の判定方法を確認する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<String, String> mock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(new String("1"))).thenReturn("one");

        // インスタンスが別でも等価であれば同じ引数と見なされる
        String value = mock.get(new String("1"));
        System.out.println(value);
    }

    @Test
    @DisplayName("when-then方式：戻り値が複数設定されていた場合の挙動を確認する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz0", "baz1", "baz2");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 2, 1, 2, 2);
        MapUtil.printEntry(mock, keyList, "test_Case_4");
    }

    @Test
    @DisplayName("do-when方式：モックの基本的な挙動を確認する")
    void test_Case_5() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（do-when方式）
        doReturn("foo").when(mock).get(0);
        doReturn("bar").when(mock).get(1);
        doReturn("baz").when(mock).get(2);

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        MapUtil.printEntry(mock, keyList, "test_Case_5");
    }

    @Test
    @DisplayName("do-when方式：戻り値が複数設定されていた場合の挙動を確認する")
    void test_Case_6() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mock = mock(Map.class);

        // 振る舞いを設定する（do-when方式）
        doReturn("foo").when(mock).get(0);
        doReturn("bar").when(mock).get(1);
        doReturn("baz0", "baz1", "baz2").when(mock).get(2);

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 2, 1, 2, 2);
        MapUtil.printEntry(mock, keyList, "test_Case_6");
    }
}