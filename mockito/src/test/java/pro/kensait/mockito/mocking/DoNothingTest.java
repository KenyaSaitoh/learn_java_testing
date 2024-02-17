package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * 副作用を抑止する挙動を確認するテストクラス
 */
@SuppressWarnings("unchecked")
public class DoNothingTest {

    @Test
    @DisplayName("メソッドは呼び出したが、何も行なわれないことを確認する")
    void test() {
        // HashMapクラスをスパイ化する
        Map<Integer, String> mapSpy = spy(HashMap.class);
        mapSpy.put(0, "foo");
        mapSpy.put(1, "bar");
        mapSpy.put(2, "baz");

        // 振る舞いを設定する（do方式）
        doNothing().when(mapSpy).clear();

        // モック化されたMapのclear()メソッドを呼び出す
        mapSpy.clear();

        // ユーティリティ呼び出し（Mapから指定されたキーを持つ値を取り出してコンソールに表示）
        extractEntry(mapSpy, Arrays.asList(0, 1, 2), "test");

        // clear()メソッド呼び出しを検証する
        verify(mapSpy).clear();
    }
}