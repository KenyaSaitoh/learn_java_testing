package pro.kensait.mockito.calc.normal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * 計算機クラス（Calculator）をモック化するテストクラス
 * ArgumentMatchersによって、引数マッチングを行う
 */
public class CalcTest_ArgMatchers {
    // モック
    @Mock
    Calculator mock;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // すべての@Mockアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);

        // モックの疑似的な振る舞いをすべて設定する（暗黙的セットアップ）
        // 振る舞い1
        when(mock.compute(any(Integer.class), nullable(Integer.class), anyInt()))
                .thenReturn(100);
        // 振る舞い2
        when(mock.compute(any(Integer.class), nullable(Integer.class), eq(2)))
                .thenReturn(200);
        // 振る舞い3
        when(mock.compute(any(Integer.class), isNull(Integer.class), eq(3)))
                .thenReturn(300);
        // 振る舞い4
        when(mock.compute(argThat(arg -> arg < 0), nullable(Integer.class), anyInt()))
                .thenThrow(IllegalArgumentException.class);

        /*
         * 注意点1
         * 以下のようにもう一つwhen-then方式の振る舞いを追加で設定し、
         * 第1引数に別のargThat()によるマッチングを指定すると、
         * このときに事前に設定した振る舞い4のwhen()がマッチ呼び出されてしまう。
         * 振る舞い4のwhen()では、argがnullで渡されるため、NullPointerExceptionが発生してしまう。
         * when(mock.compute(argThat(arg -> 100 < arg), nullable(Integer.class), anyInt()))
         *         .thenThrow(IllegalArgumentException.class);
         * 解決のためには、以下のようにdo-when方式で振る舞いを設定すればOK。
         * doThrow(IllegalArgumentException.class).when(mock).compute(
         *         argThat(arg -> arg < 0), nullable(Integer.class), anyInt());
         */

        /*
         * 注意点2
         * 以下のように第2引数に`argThat(arg -> arg < 0)`を設定すると、
         * testest_Case_3()で第2引数にnullが渡されたときに、NullPointerExceptionが発生してしまう。
         * この課題は、do-when方式でも解決しない。
         * そもそもモックの振る舞い設定をインラインセットアップに切り替えた方がよい。
         * when(mock.compute(any(Integer.class), argThat(arg -> arg < 0), anyInt()))
         *         .thenThrow(IllegalArgumentException.class);
         */
    }

    @Test
    @DisplayName("振る舞い1にマッチ")
    void testest_Case_1() {
        int answer = mock.compute(0, 0, 1);
        assertEquals(100, answer);
    }

    @Test
    @DisplayName("振る舞い2にマッチ")
    void testest_Case_2() {
        int answer = mock.compute(0, 0, 2);
        assertEquals(200, answer);
    }

    @Test
    @DisplayName("振る舞い3にマッチ")
    void testest_Case_3() {
        int answer = mock.compute(0, null, 3);
        assertEquals(300, answer);
    }

    @Test
    @DisplayName("振る舞い4にマッチ")
    void testest_Case_4() {
        assertThrows(IllegalArgumentException.class, () -> mock.compute(-1, 0, 0));
    }
}