package pro.kensait.java.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * Accountクラス（口座）のためのテストクラス
 */
public class AccountTest {
    // 入金のテスト（金額0円の口座に対して）
    @Test
    void test_Deposite_Balance_Zero() {
        // 準備フェーズ
        Account account = new Account("00001234", 0); //［1］
        // 実行フェーズ
        account.deposit(3000);
        // 検証フェーズ
        assertEquals(3000, account.getBalance());
    }

    // 出金のテスト（金額0円の口座に対して）
    @Test
    void test_Withdraw_Balance_Zero() {
        // 準備フェーズ
        Account account = new Account("00001234", 0); //［2］
        // 実行＋検証フェーズ
        assertThrows(InsufficientBalanceException.class, () -> {
            account.withdraw(3000);
        });
    }

    // 出金のテスト（金額10000円の口座に対して）
    @Test
    void test_Withdraw_Balance_NonZero() {
        // 準備フェーズ
        Account account = new Account("00001234", 10000); //［3］
        try {
            // 実行フェーズ
            account.withdraw(3000);
        } catch (InsufficientBalanceException ibe) {
            fail();
        }
        // 検証フェーズ
        assertEquals(7000, account.getBalance());
    }

    // 残高ゼロチェックのテスト（金額0円の口座に対して）
    @Test
    void test_IsBalanceZero_Balance_Zero() {
        // 準備フェーズ
        Account account = new Account("00001234", 0); //［6］
        // 実行＋検証フェーズ
        assertTrue(account.isBalanceZero());
    }

    // 残高ゼロチェックのテスト（金額10000円の口座に対して）
    @Test
    void test_IsNotBalanceZero_Balance_NonZero() {
        // 準備フェーズ
        Account account = new Account("00001234", 10000); //［5］
        // 実行＋検証フェーズ
        assertFalse(account.isBalanceZero());
    }
}