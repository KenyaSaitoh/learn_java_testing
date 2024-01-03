package pro.kensait.java.calc.case3_factory;

import pro.kensait.java.calc.case2_di.PowerProcessorIF;

/*
 * 指数演算の機能を表すクラス（インスタンス生成版）
 */
public class PowerProcessor implements PowerProcessorIF {

    // インスタンスをシングルトン化して、ファクトリメソッド経由で返す［1］
    private static PowerProcessor instance = new PowerProcessor();
    private PowerProcessor() {};
    public static PowerProcessor getInstance() {
        return instance;
    }

    // 指数演算を行う［2］
    @Override
    public int power(int x, int y) {
        return (int) Math.pow(x, y);
    }
}