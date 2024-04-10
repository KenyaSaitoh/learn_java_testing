package pro.kensait.mockito.calc.normal;

/*
 * 計算機を表すクラス（テスト対象）
 */
public class Calculator {
    public int compute(Integer x, Integer y, int z) {
        int answer = (x + y) * z;
        if (answer < 0) throw new IllegalArgumentException("結果不正");
        return answer;
    }
}