package pro.kensait.mockito.mocking;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ArgumentMatchersによる引数マッチングの挙動を確認する")
@SuppressWarnings("unchecked")
public class MatcherTest {

    @Test
    @DisplayName("anyInt()による引数マッチングの挙動を確認する")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(anyInt())).thenReturn("foo");

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapMock, List.of(0, 1, 2), "test_Case_1");
    }

    @Test
    @DisplayName("any()による引数マッチングの挙動を確認する")
    void test_Case_2() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(any(Integer.class))).thenReturn("hoge");

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapMock, List.of(0, 1, null), "test_Case_2");
    }

    @Test
    @DisplayName("eq()による引数マッチングの挙動を確認する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(eq(1))).thenReturn("hoge");

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapMock, List.of(0, 1, null), "test_Case_3");
    }

    @Test
    @DisplayName("nullable()による引数マッチングの挙動を確認する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(nullable(Integer.class))).thenReturn("hoge");

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapMock, List.of(0, 1, null), "test_Case_4");
    }
}