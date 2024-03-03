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
public class SpyTest {

    @Test
    @DisplayName("スパイの基本的な挙動を確認する")
    void test_Case_1() {
        // Mapインタフェースをスパイ化する
        Map<Integer, String> mapSpy = spy(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mapSpy.get(0)).thenReturn("foo");
        when(mapSpy.get(1)).thenReturn("bar");
        when(mapSpy.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        MapUtil.printEntry(mapSpy, keyList, "test_Case_1");
    }

    @Test
    @DisplayName("モック化したHashMapに対して値を追加した場合の挙動を確認する（比較用）")
    void test_Case_2() {
        // HashMapクラスをモック化する
        Map<Integer, String> mapMock = mock(HashMap.class);
        mapMock.put(0, "foo");
        mapMock.put(1, "bar");
        mapMock.put(2, "baz");

        // 振る舞いを設定する（when-then方式）
        when(mapMock.get(2)).thenReturn("bazbaz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        MapUtil.printEntry(mapMock, keyList, "test_Mock");
    }

    @Test
    @DisplayName("スパイ化したHashMapに対して値を追加した場合の挙動を確認する")
    void test_Case_3() {
        // HashMapクラスをスパイ化する
        Map<Integer, String> mapSpy = spy(HashMap.class);
        mapSpy.put(0, "foo");
        mapSpy.put(1, "bar");
        mapSpy.put(2, "baz");

        // 振る舞いを設定する（when-then方式）
        when(mapSpy.get(2)).thenReturn("bazbaz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        MapUtil.printEntry(mapSpy, keyList, "test_Spy");
    }
}