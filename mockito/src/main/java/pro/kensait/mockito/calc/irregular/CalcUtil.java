package pro.kensait.mockito.calc.irregular;

public class CalcUtil {
    public static int compute(int x, int y, int z) {
        int answer = (x + y) * z;
        if (answer < 0) throw new IllegalArgumentException("結果不正");
        return answer;
    }
}