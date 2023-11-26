package pro.kensait.java.calc;

public class Calc_4 {
    private int x;
    private int y;
    private int z;

    public Calc_4(int x, int y, int z) {
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