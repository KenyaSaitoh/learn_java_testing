package pro.kensait.java.calc;

public final class Calc_5 {
    public int compute(int x, int y, int z) {
        int answer = (x + y) * z;
        if (answer < 0) {
            throw new IllegalArgumentException("引数不正");
        }
        return answer;
    }
}