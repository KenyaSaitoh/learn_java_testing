package pro.kensait.junit5.calc.sideeffect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 副作用（計算結果をコンソールやログに表示）を持つ計算機を表すクラス（テスト対象）
 */
public class OutputCalc {
    private static final Logger logger = LoggerFactory.getLogger(OutputCalc.class);

    // 足し算を実行する［1］
    public void add(int param1, int param2) {
        int answer = param1 + param2;
        System.out.println(answer);
    }

    // 引き算を実行する［2］
    public void subtract(int param1, int param2) {
        int answer = param1 - param2;
        logger.info("The answer is " + String.valueOf(answer));
    }
}