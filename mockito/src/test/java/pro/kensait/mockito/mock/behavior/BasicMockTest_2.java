package pro.kensait.mockito.mock.behavior;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pro.kensait.mockito.util.MapUtil;

/*
 * アノテーションによるモック作成を確認するテストクラス
 */
public class BasicMockTest_2 {

    // モック
    @Mock
    Map<Integer, String> mock;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // すべての@Mockアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_Case_1() {
        // 振る舞いを設定する（when-then方式）
        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        // ユーティリティ（指定されたキーに対するエントリをMapから取り出してコンソール表示）
        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        MapUtil.printEntry(mock, keyList, "test_Case_1");
    }
}