package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;
import static pro.kensait.mockito.mocking.Util.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class DoNothingTest {

    @Test
    @DisplayName("doNothing()：メソッドは呼び出したが、何も行なわれないこと（副作用なし）を検証する")
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

        // ユーティリティ（Mapからすべてのエントリを抽出しコンソールに表示）
        extractEntry(mapSpy, List.of(0, 1, 2), "test");

        // clear()メソッド呼び出しを検証する
        verify(mapSpy).clear();
    }
}