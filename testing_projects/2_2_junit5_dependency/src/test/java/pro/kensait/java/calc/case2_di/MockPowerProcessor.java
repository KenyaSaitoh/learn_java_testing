package pro.kensait.java.calc.case2_di;

/*
 * PowerProcessorIFをimplementsしたモック
 */
public class MockPowerProcessor implements PowerProcessorIF {

    // 指数演算の代わりに加算を行う
    @Override
    public int power(int param1, int param2) {
        return param1 + param2;
    }
}