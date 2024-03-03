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

        // 振る舞いを設定する（do-when方式）
        doNothing().when(mapSpy).clear();

        // モック化されたMapのclear()メソッドを呼び出す
        mapSpy.clear();

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        MapUtil.printEntry(mapSpy, keyList, "test");

        // clear()メソッド呼び出しを検証する
        verify(mapSpy).clear();
    }
}