package pro.kensait.java.fee;

public class FeeService {
    private static final String OUR_BANK_CODE = "B001"; // 自行

    // 振込手数料の計算を行う
    public int calcFee(String bankCode, int amount) {

        // 自行かどうかを判定する
        if (OUR_BANK_CODE.equals(bankCode)) {
            // 自行の場合、30000円以上は無料、それ以外は100円
            if (30000 <= amount) {
                return 0;
            } else {
                return 100;
            }

        } else {
            // 他行の場合、40000円以上は200円、それ以外は500円
            if (40000 <= amount) {
                return 200;
            } else {
                return 500;
            }
        }
    }
}