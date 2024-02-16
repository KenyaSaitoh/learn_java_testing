package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("スパイの挙動を確認するテストクラス")
@SuppressWarnings("unchecked")
public class SpyTest {

    @Test
    @DisplayName("スパイの基本的な挙動を確認する")
    void test_Case_1() {
        // Mapインタフェースをスパイ化する
        Map<Integer, String> mapSpy = spy(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapSpy.get(0)).thenReturn("foo");
        when(mapSpy.get(1)).thenReturn("bar");
        when(mapSpy.get(2)).thenReturn("baz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapSpy, Arrays.asList(0, 1, 2, 3), "test_Case_1");
    }

    @Test
    @DisplayName("モック化したHashMapに対して値を追加した場合の挙動を確認する（比較用）")
    void test_Case_2() {
        // HashMapクラスをモック化する
        Map<Integer, String> mapMock = mock(HashMap.class);
        mapMock.put(0, "foo");
        mapMock.put(1, "bar");
        mapMock.put(2, "baz");

        // 振る舞いを設定する（when方式）
        when(mapMock.get(2)).thenReturn("bazbaz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, 2), "test_Mock");
    }

    @Test
    @DisplayName("スパイしたHashMapに対して値を追加した場合の挙動を確認する")
    void test_Case_3() {
        // HashMapクラスをスパイ化する
        Map<Integer, String> mapSpy = spy(HashMap.class);
        mapSpy.put(0, "foo");
        mapSpy.put(1, "bar");
        mapSpy.put(2, "baz");

        // 振る舞いを設定する（when方式）
        when(mapSpy.get(2)).thenReturn("bazbaz");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapSpy, Arrays.asList(0, 1, 2), "test_Spy");
    }
}