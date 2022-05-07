package pro.kensait.java.app.calc.case3;

public class FeeCalculator {
    private static final String OUR_BANK_CODE = "B001"; // 自行

    // 振込手数料の計算を行う
    public int calcFee(String bankCode, int amount) {
        if (OUR_BANK_CODE.equals(bankCode)) {
            return 0;
        } else {
            if (amount < 30000) {
                return 110;
            } else {
                return 220;
            }
        }
    }
}