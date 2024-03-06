package pro.kensait.mockito.spy;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pro.kensait.mockito.util.MapUtil;

/*
 * スパイの挙動を確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class BasicSpyTest_1 {

    @Test
    @DisplayName("スパイの基本的な挙動を確認する")
    void test_Case_1() {
        // Mapインタフェースをスパイ化する
        Map<Integer, String> spy = spy(Map.class);
        spy.put(0, "foo");
        spy.put(1, "bar");
        spy.put(2, "baz");

        // 振る舞いを設定する（when-then方式）
        when(spy.get(0)).thenReturn("foo");
        when(spy.get(1)).thenReturn("bar");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        MapUtil.printEntry(spy, keyList, "test_Case_1");
    }

    @Test
    @DisplayName("モック化したHashMapに対して値を追加した場合の挙動を確認する（比較用）")
    void test_Case_2() {
        // HashMapクラスをモック化する
        Map<Integer, String> mock = mock(HashMap.class);
        mock.put(0, "foo");
        mock.put(1, "bar");
        mock.put(2, "baz");

        // 振る舞いを設定する（when-then方式）
        when(mock.get(2)).thenReturn("bazbaz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        MapUtil.printEntry(mock, keyList, "test_Case_2");
    }

    @Test
    @DisplayName("スパイ化したHashMapに対して値を追加した場合の挙動を確認する")
    void test_Case_3() {
        // HashMapクラスをスパイ化する
        Map<Integer, String> spy = spy(HashMap.class);
        spy.put(0, "foo");
        spy.put(1, "bar");
        spy.put(2, "baz");

        // 振る舞いを設定する（when-then方式）
        when(spy.get(2)).thenReturn("bazbaz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        MapUtil.printEntry(spy, keyList, "test_Case_3");
    }
}