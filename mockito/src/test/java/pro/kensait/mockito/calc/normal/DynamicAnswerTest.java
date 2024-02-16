package pro.kensait.mockito.calc.normal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;

/*
 * 計算機インタフェース（CaclIF）をモック化するテスト
 * インタフェースと実装は分離する
 * @Mockアノテーションでモック化する
 */
public class DynamicAnswerTest {
    // モック（CalcIF）
    @Mock
    CalcIF mock;

    @BeforeEach
    void setUp() {
        // すべての@Mockアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);

        // 任意の振る舞いをAnswerとして定義する
        Answer<Integer> answer = invocation -> {
            int x = invocation.getArgument(0); // 第1引数
            int y = invocation.getArgument(1); // 第2引数
            int z = invocation.getArgument(2); // 第3引数
            if (z == -1)
                throw new IllegalArgumentException("エラー");
            // 3つの引数から、その合計値を返すように振る舞いを定義する
            return x + y + z;
        };

        // モックの振る舞いを定義する（すべての振る舞いはAnswerで決まる）
        when(mock.compute(anyInt(), anyInt(), anyInt())).thenAnswer(answer);
    }

    @Test
    @DisplayName("ケース1の振る舞いをテストする")
    void test_Case_1() {
        int answer = mock.compute(5, 10, 3);
        assertEquals(18, answer);
    }

    @Test
    @DisplayName("ケース2の振る舞いをテストする")
    void test_Case_2() {
        int answer = mock.compute(5, 10, 8);
        assertEquals(23, answer);
    }

    @Test
    @DisplayName("ケース3の振る舞い（例外発生）をテストする")
    void test_Case_3() {
        assertThrows(IllegalArgumentException.class, () -> mock.compute(5, 10, -1));
    }
}