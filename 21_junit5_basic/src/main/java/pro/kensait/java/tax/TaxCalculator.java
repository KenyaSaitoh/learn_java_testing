package pro.kensait.java.tax;

public class TaxCalculator {
    private int value;
    private int year;

    public TaxCalculator(int value, int year) {
        this.value = value;
        this.year = year;
    }

    // 消費税の計算を行う
    public double calcTax() {
        if (1989 <= year && year < 1997) {
            return value * 1.03;
        } else if (1997 <= year && year < 2014) {
            return value * 1.05;
        } else if (2014 <= year && year < 2019) {
            return value * 1.08;
        } else if (2019 <= year) {
            return value * 1.1;
        } else {
            return value;
        }
    }
}