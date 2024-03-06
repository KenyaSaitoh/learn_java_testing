package pro.kensait.mockito.spy;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import pro.kensait.mockito.util.MapUtil;

/*
 * スパイの挙動を確認するテストクラス
 */
public class BasicSpyTest_2 {

    // スパイ
    @Spy
    HashMap<Integer, String> spy;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // すべての@Spyアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);
        spy.put(0, "foo");
        spy.put(1, "bar");
        spy.put(2, "baz");
    }

    @Test
    @DisplayName("スパイ化したHashMapに対して値を追加した場合の挙動を確認する")
    void test_Case_1() {
        // 振る舞いを設定する（when-then方式）
        when(spy.get(2)).thenReturn("bazbaz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2);
        MapUtil.printEntry(spy, keyList, "test_Case_1");
    }
}