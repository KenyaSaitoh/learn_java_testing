package pro.kensait.junit5.calc.sideeffect;

/*
 * 状態を保持する計算機を表すクラス（テスト対象）
 */
public class StatefulCalc {
    private final int param1;
    private final int param2;
    private int answer;

    public StatefulCalc(int param1, int param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    // 足し算を実行する【1】
    public void add() {
        answer = param1 + param2;
    }

    // 引き算を実行する【2】
    public void subtract() {
        answer = param1 - param2;
    }

    // 答えを取得する【3】
    public int getAnswer() {
        return answer;
    }
}