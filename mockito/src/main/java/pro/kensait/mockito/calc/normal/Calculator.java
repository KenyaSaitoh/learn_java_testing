package pro.kensait.mockito.calc.normal;

public class Calculator {

    // argThat()の対象にプリミティブ型は認められない模様
    public int compute(int x, int y, Integer z) {
        int answer = (x + y) * z;
        if (answer < 0) {
            throw new IllegalArgumentException("引数不正");
        }
        return answer;
    }
}