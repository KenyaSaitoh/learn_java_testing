package pro.kensait.junit5.account;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * Account（口座）のためのテストクラス
 * ネステッドクラスによって階層化
 */
public class AccountNestedTest {
    // このテストクラス全体で共通的なフィクスチャを、フィールドとして宣言する
    // テスト対象クラス
    Account account;

    @Nested // ネステッドクラスを表す
    @DisplayName("残高ゼロの口座に対するテスト")
    class BalanceZeroTest {
        // ネステッドクラス内の各テストメソッドで共通的な前処理
        @BeforeEach
        void setUp() {
            // 共通フィクスチャを設定する
            account = new Account("00001234", 0);
        }

        @Test
        @DisplayName("入金のテスト")
        void test_Deposite_Balance_Zero() {
            // 実行フェーズ
            account.deposit(3000);
            // 検証フェーズ
            assertEquals(3000, account.getBalance());
        }

        @Test
        @DisplayName("出金のテスト")
        void test_Withdraw_Balance_Zero() {
            // 実行＋検証フェーズ
            assertThrows(InsufficientBalanceException.class, () -> {
                account.withdraw(3000);
            });
        }

        @Test
        @DisplayName("残高ゼロチェックのテスト")
        void test_IsBalanceZero_Balance_Zero() {
            // 実行＋検証フェーズ
            assertTrue(account.isBalanceZero());
        }
    }

    @Nested // ネステッドクラスを表す
    @DisplayName("残高ゼロ以外の口座に対するテスト")
    class BalanceNonZeroTest {
        // ネステッドクラス内の各テストメソッドで共通的な前処理
        @BeforeEach
        void setUp() {
            // 共通フィクスチャを設定する
            account = new Account("00001234", 10000);
        }

        @Test
        @DisplayName("入金のテスト")
        void test_Deposite_Balance_NonZero() {
            // 実行フェーズ
            account.deposit(3000);
            // 検証フェーズ
            assertEquals(13000, account.getBalance());
        }

        @Test
        @DisplayName("出金のテスト")
        void test_Withdraw_Balance_NonZero() {
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
        @DisplayName("残高ゼロチェックのテスト")
        void test_IsNotBalanceZero_Balance_NonZero() {
            // 実行＋検証フェーズ
            assertFalse(account.isBalanceZero());
        }
    }
}