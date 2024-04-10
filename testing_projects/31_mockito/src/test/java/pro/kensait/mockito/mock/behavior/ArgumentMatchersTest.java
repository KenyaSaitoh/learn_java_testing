package pro.kensait.mockito.mock.behavior;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pro.kensait.mockito.util.MapUtil;

/*
 * 引数マッチング（ArgumentMatchers）の挙動を確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class ArgumentMatchersTest {

    @Test
    @DisplayName("anyInt()による引数マッチングの挙動を確認する")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mapMock.get(anyInt())).thenReturn("foo");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, null);
        MapUtil.printEntry(mapMock, keyList, "test_Case_1");
    }

    @Test
    @DisplayName("any()による引数マッチングの挙動を確認する")
    void test_Case_2() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mapMock.get(any(Integer.class))).thenReturn("hoge");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, null);
        MapUtil.printEntry(mapMock, keyList, "test_Case_2");
    }

    @Test
    @DisplayName("eq()による引数マッチングの挙動を確認する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mapMock.get(eq(1))).thenReturn("hoge");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, null);
        MapUtil.printEntry(mapMock, keyList, "test_Case_3");
    }

    @Test
    @DisplayName("nullable()による引数マッチングの挙動を確認する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mapMock.get(nullable(Integer.class))).thenReturn("hoge");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, null);
        MapUtil.printEntry(mapMock, keyList, "test_Case_4");
    }
}