package pro.kensait.mockito.calc.normal;

/*
 * 計算機を表すクラス
 */
public class Calculator {
    public int compute(int x, int y, int z) {
        int answer = (x + y) * z;
        if (answer < 0) {
            throw new IllegalArgumentException("引数不正");
        }
        return answer;
    }
}