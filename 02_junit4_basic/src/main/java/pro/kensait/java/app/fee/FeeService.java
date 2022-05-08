package pro.kensait.java.app.fee;

public class FeeService {
    private static final String OUR_BANK_CODE = "B001"; // 自行

    // 振込手数料の計算を行う
    public int calcFee(String bankCode, int amount) {
        if (OUR_BANK_CODE.equals(bankCode)) {
            if (amount < 50000) {
                return 0;
            } else {
                return 100;
            }
        } else {
            if (amount < 30000) {
                return 200;
            } else {
                return 300;
            }
        }
    }
}