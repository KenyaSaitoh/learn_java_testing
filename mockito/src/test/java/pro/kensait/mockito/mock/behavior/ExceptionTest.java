package pro.kensait.mockito.mock.behavior;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import pro.kensait.mockito.util.MapUtil;

/*
 * モックの例外を送出する振る舞いを確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class ExceptionTest {

    @Test
    @DisplayName("when-then方式：例外を1つ送出する振る舞いを確認する")
    void test_Case_1() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mapMock.get(0)).thenReturn("foo");
        when(mapMock.get(1)).thenReturn("bar");
        when(mapMock.get(2)).thenThrow(new RuntimeException());

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        MapUtil.printEntry(mapMock, Arrays.asList(0, 1, 2), "test_Case_1");
    }

    @Test
    @DisplayName("when-then方式：例外を順番に送出する振る舞いを確認する")
    void test_Case_2() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（when-then方式）
        when(mapMock.get(0)).thenThrow(
                new RuntimeException("1st Exception"),
                new IllegalArgumentException("2nd Exception"),
                new NoSuchElementException("3rd Exception"));

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        MapUtil.printEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_2");
    }

    @Test
    @DisplayName("do-when方式：例外を1つ送出する振る舞いを確認する")
    void test_Case_3() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（do-when方式）
        doThrow(RuntimeException.class).when(mapMock).get(0);

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        MapUtil.printEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_3");
    }

    @Test
    @DisplayName("do-when方式：例外を順番に送出する振る舞いを確認する")
    void test_Case_4() {
        // Mapインタフェースをモック化する
        Map<Integer, String> mapMock = mock(Map.class);

        // 振る舞いを設定する（do-when方式）
        doThrow(RuntimeException.class,
                IllegalArgumentException.class,
                NoSuchElementException.class)
                .when(mapMock).get(0);

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        MapUtil.printEntry(mapMock, Arrays.asList(0, 0, 0), "test_Case_4");
    }
}