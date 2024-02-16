package pro.kensait.mockito.calc.normal;

/*
 * 計算機を実装するクラス
 */
public class CalcImpl implements CalcIF {
    @Override
    public int compute(int x, int y, int z) {
        int answer = (x + y) * z;
        if (answer < 0) {
            throw new IllegalArgumentException("引数不正");
        }
        return answer;
    }
}