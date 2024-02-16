package pro.kensait.mockito.mocking;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("引数マッチングの挙動を確認するテストクラス")
@SuppressWarnings("unchecked")
public class MatcherTest {

    @Test
    @DisplayName("anyInt()による引数マッチングの挙動を確認する")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(anyInt())).thenReturn("foo");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, null), "test_Case_1");
    }

    @Test
    @DisplayName("any()による引数マッチングの挙動を確認する")
    void test_Case_2() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(any(Integer.class))).thenReturn("hoge");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, null), "test_Case_2");
    }

    @Test
    @DisplayName("eq()による引数マッチングの挙動を確認する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(eq(1))).thenReturn("hoge");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, null), "test_Case_3");
    }

    @Test
    @DisplayName("nullable()による引数マッチングの挙動を確認する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(nullable(Integer.class))).thenReturn("hoge");

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, null), "test_Case_4");
    }
}