package pro.kensait.junit5.account;

/*
 * 銀行口座を表すクラス（テスト対象）
 */
public class Account {
    private String accountNum; // 口座番号
    private int balance; // 残高

    // コンストラクタ
    public Account(String accountNum, int balance) {
        this.accountNum = accountNum;
        this.balance = balance;
    }

    // 入金する
    public void deposit(int amount) {
        balance += amount; // 残高を加算する
    }

    // 出金する
    public void withdraw(int amount) throws InsufficientBalanceException {
        if (balance < amount) { // 残高不足の場合はInsufficientBalanceException例外を送出する
            throw new InsufficientBalanceException();
        }
        balance -= amount; // 残高を減算する
    }

    // 残高ゼロチェックする
    public boolean isBalanceZero() {
        return balance == 0;
    }

    // アクセサメソッド
    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account [accountNum=" + accountNum + ", balance=" + balance + "]";
    }
}
