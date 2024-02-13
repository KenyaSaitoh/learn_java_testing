package pro.kensait.java.calc.case2_di;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatorTest {
    // すべてのテストメソッドに共通的な変数はフィールドとして宣言する
    // テスト対象クラス
    Calculator calc;

    // テスト対象クラスが依存しているクラス（モック対象）
    PowerProcessorIF mock;

    @Test
    void test() {
        // モックを生成する
        mock = new MockPowerProcessor();

        // モックをテスト対象クラスに注入する（依存性注入）
        calc = new Calculator(mock);

        // テスト対象クラスを実行し、戻り値を検証する
        assertEquals(60, calc.calc(3, 1, 2, 10));
    }
}