package pro.kensait.mockito.calc.stateful;

/*
 * 状態を保持する計算機を表すクラス（テスト対象）
 */
public class StatefulCalc {
    private final int x;
    private final int y;

    public StatefulCalc(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int compute() {
        return x * y;
    }
}