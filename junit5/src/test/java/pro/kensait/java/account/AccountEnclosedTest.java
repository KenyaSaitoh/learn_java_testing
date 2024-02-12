package pro.kensait.java.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * Accountクラス（口座）のための構造化されたテストクラス
 */
public class AccountEnclosedTest {
    // 全テストケースで共通的なフィクスチャを、フィールドとして宣言する
    // テスト対象クラス
    Account account;

    // 残高ゼロの場合のテストケース
    @Nested
    class BalanceZeroTest {
        // 各テストケースで共通的な事前処理
        @BeforeEach
        void setUp() {
            // 共通フィクスチャを設定する
            account = new Account("00001234", 0);
        }

        // 入金のテスト（金額0円の口座に対して）
        @Test
        void test_Deposite_Balance_Zero() {
            // 実行フェーズ
            account.deposit(3000);
            // 検証フェーズ
            assertEquals(3000, account.getBalance());
        }

        // 出金のテスト（金額0円の口座に対して）
        @Test
        void test_Withdraw_Balance_Zero() {
            // 実行＋検証フェーズ
            assertThrows(InsufficientBalanceException.class, () -> {
                account.withdraw(3000);
            });
        }

        // 残高ゼロチェックのテスト（金額0円の口座に対して）
        @Test
        void test_IsBalanceZero_Balance_Zero() {
            // 実行＋検証フェーズ
            assertTrue(account.isBalanceZero());
        }
    }

    // 残高ゼロ以外の場合のテストケース
    @Nested
    class BalanceNonZeroTest {
        // 各テストケースで共通的な事前処理
        @BeforeEach
        void setUp() {
            // 共通フィクスチャを設定する
            account = new Account("00001234", 10000);
        }

        // 出金のテスト（金額10000円の口座に対して）
        @Test
        void test_Withdraw_Balance_NonZero() {
            // 準備フェーズ
            Account account = new Account("00001234", 10000);
            try {
                // 実行フェーズ
                account.withdraw(3000);
            } catch (InsufficientBalanceException ibe) {
                fail();
            }
            // 検証フェーズ
            assertEquals(7000, account.getBalance());
        }

        // 残高ゼロチェックのテスト（金額10000円の口座に対して）
        @Test
        void test_IsNotBalanceZero_Balance_NonZero() {
            // 準備フェーズ
            Account account = new Account("00001234", 10000);
            // 実行＋検証フェーズ
            assertFalse(account.isBalanceZero());
        }
    }
}