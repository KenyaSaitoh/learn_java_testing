package pro.kensait.java.calc.case1_new;

/*
 * 指数演算の機能を表すクラス（通常版）
 */
public class PowerProcessor implements PowerProcessorIF {

    // 指数演算を行う
    @Override
    public int power(int x, int y) {
        return (int) Math.pow(x, y);
    }
}