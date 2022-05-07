package pro.kensait.java.app.calc.case2;

public class Calculator {
    private final int param1;
    private final int param2;
    private int answer;

    public Calculator(int param1, int param2) {
        super();
        this.param1 = param1;
        this.param2 = param2;
    }

    // 足し算を実行する
    public void add() {
        answer = param1 + param2;
    }

    // 引き算を実行する
    public void subtract() {
        answer = param1 - param2;
    }

    // 答えを取得する
    public int getAnswer() {
        return answer;
    }
}