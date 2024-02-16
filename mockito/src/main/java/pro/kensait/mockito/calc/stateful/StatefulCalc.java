package pro.kensait.mockito.calc.stateful;

/*
 * 計算機を表すクラス（状態を保持する）
 */
public class StatefulCalc {
    private int x;
    private int y;
    private int z;

    public StatefulCalc(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int compute() {
        int answer = (x + y) * z;
        if (answer < 0) {
            throw new IllegalArgumentException("引数不正");
        }
        return answer;
    }
}