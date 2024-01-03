package pro.kensait.java.calc.case4_static;

/*
 * 指数演算の機能を表すクラス（スタティック版）
 */
public class PowerProcessor {

    // 指数演算を行う
    public static int power(int x, int y) {
        return (int) Math.pow(x, y);
    }
}