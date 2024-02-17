package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * モックの例外を送出する振る舞いを確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class ExceptionTest {

    @Test
    @DisplayName("when方式：例外を1つ送出する振る舞いを確認する")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenThrow(new RuntimeException());

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 1, 2), "test_Case_1");
    }

    @Test
    @DisplayName("when方式：例外を順番に送出する振る舞いを確認する")
    void test_Case_2() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when方式）
        when(mapMock.get(0)).thenThrow(
                new RuntimeException("1st Exception"),
                new IllegalArgumentException("2nd Exception"),
                new NoSuchElementException("3rd Exception"));

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_2");
    }

    @Test
    @DisplayName("do方式：例外を1つ送出する振る舞いを確認する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（do方式）
        doThrow(RuntimeException.class).when(mapMock).get(0);

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_3");
    }

    @Test
    @DisplayName("do方式：例外を順番に送出する振る舞いを確認する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（do方式）
        doThrow(RuntimeException.class,
                IllegalArgumentException.class,
                NoSuchElementException.class)
                .when(mapMock).get(0);

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_4");
    }
}