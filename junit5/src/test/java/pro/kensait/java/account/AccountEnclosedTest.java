package pro.kensait.java.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * Accountクラスのためのテストクラス
 */
public class AccountEnclosedTest {
    // 各テストケースで共通的なフィクスチャを、フィールドとして宣言する
    Account account;

    @Nested
    class BalanceZeroTest {
        // 各テストケースで共通的な事前処理
        @BeforeEach
        public void setUp() {
            // 共通フィクスチャを設定する
            account = new Account("00001234", 0);
        }

        // 入金のテスト（金額0円の口座に対して）
        @Test
        public void test_Deposite_Balance_Zero() {
            // 実行フェーズ
            account.deposit(3000);
            // 検証フェーズ
            assertEquals(3000, account.getBalance());
        }

        // 出金のテスト（金額0円の口座に対して）
        @Test
        public void test_Withdraw_Balance_Zero() {
            // 実行＋検証フェーズ
            assertThrows(InsufficientBalanceException.class, () -> {
                account.withdraw(3000);
            });
        }

        // ゼロ口座確認のテスト（金額0円の口座に対して）
        @Test
        public void test_IsBalanceZero_Balance_Zero() {
            // 実行＋検証フェーズ
            assertTrue(account.isBalanceZero());
        }
    }

    @Nested
    class BalanceNonZeroTest {
        // 各テストケースで共通的な事前処理
        @BeforeEach
        public void setUp() {
            // 共通フィクスチャを設定する
            account = new Account("00001234", 10000);
        }

        // 出金のテスト（金額10000円の口座に対して）
        @Test
        public void test_Withdraw_Balance_NonZero() {
            // 準備フェーズ
            Account account = new Account("00001234", 10000);
            try {
                account.withdraw(3000);
            } catch (InsufficientBalanceException ibe) {
                fail();
            }
            // 検証フェーズ
            assertEquals(7000, account.getBalance());
        }

        // ゼロ口座確認のテスト（金額10000円の口座に対して）
        @Test
        public void test_IsNotBalanceZero_Balance_NonZero() {
            // 準備フェーズ
            Account account = new Account("00001234", 10000);
            // 実行＋検証フェーズ
            assertFalse(account.isBalanceZero());
        }
    }
}