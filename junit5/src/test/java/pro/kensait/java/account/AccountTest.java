package pro.kensait.java.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Accountクラス（口座）のためのテストクラス
 */
public class AccountTest {
    @Test
    @DisplayName("残高ゼロの口座に対する入金テスト")
    void test_Deposite_Balance_Zero() {
        // 準備フェーズ
        Account account = new Account("00001234", 0); //［1］
        // 実行フェーズ
        account.deposit(3000);
        // 検証フェーズ
        assertEquals(3000, account.getBalance());
    }

    @Test
    @DisplayName("残高ゼロ以外の口座に対する入金テスト")
    void test_Deposite_Balance_NonZero() {
        // 準備フェーズ
        Account account = new Account("00001234", 10000); //［2］
        // 実行フェーズ
        account.deposit(3000);
        // 検証フェーズ
        assertEquals(13000, account.getBalance());
    }

    @Test
    @DisplayName("残高ゼロの口座に対する出金テスト")
    void test_Withdraw_Balance_Zero() {
        // 準備フェーズ
        Account account = new Account("00001234", 0); //［3］
        // 実行＋検証フェーズ
        assertThrows(InsufficientBalanceException.class, () -> {
            account.withdraw(3000);
        });
    }

    @Test
    @DisplayName("残高ゼロ以外の口座に対する出金テスト")
    void test_Withdraw_Balance_NonZero() {
        // 準備フェーズ
        Account account = new Account("00001234", 10000); //［4］
        try {
            // 実行フェーズ
            account.withdraw(3000);
        } catch (InsufficientBalanceException ibe) {
            fail();
        }
        // 検証フェーズ
        assertEquals(7000, account.getBalance());
    }

    @Test
    @DisplayName("残高ゼロの口座に対する残高ゼロチェックのテスト")
    void test_IsBalanceZero_Balance_Zero() {
        // 準備フェーズ
        Account account = new Account("00001234", 0); //［5］
        // 実行＋検証フェーズ
        assertTrue(account.isBalanceZero());
    }

    @Test
    @DisplayName("残高ゼロ以外の口座に対する残高ゼロチェックのテスト")
    void test_IsNotBalanceZero_Balance_NonZero() {
        // 準備フェーズ
        Account account = new Account("00001234", 10000); //［6］
        // 実行＋検証フェーズ
        assertFalse(account.isBalanceZero());
    }
}