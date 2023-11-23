package pro.kensait.java.calc;

public class StatefulCalc {
    private int x;
    private int y;

    public StatefulCalc(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int add() {
        return x + y;
    }

    public int subtract() {
        return x - y;
    }
}