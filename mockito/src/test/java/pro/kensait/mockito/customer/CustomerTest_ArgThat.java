package pro.kensait.mockito.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/*
 * 顧客サービス（CustomerService）をモック化するテストクラス
 * ArgumentMatchers（argThat()）によって、引数マッチングを行う
 */
public class CustomerTest_ArgThat {
    // モック
    @Mock
    CustomerService mock;

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        // すべての@Mockアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);

        // ArgumentMatcherを使ってモックの振る舞いを設定する
        // 顧客種別が一般会員の場合の振る舞い
        ArgumentMatcher<Customer> isGeneral = arg -> arg.getCustomerType() == CustomerType.GENERAL;
        doReturn(500).when(mock).calcDeliveryFee(argThat(isGeneral));

        // 顧客種別がゴールド会員の場合の振る舞い
        ArgumentMatcher<Customer> isGold =  arg -> arg.getCustomerType() == CustomerType.GOLD;
        doReturn(300).when(mock).calcDeliveryFee(argThat(isGold));
    }

    @Test
    @DisplayName("顧客種別が一般会員の場合の振る舞いをテストする")
    void test_General_Customer() {
        int fee = mock.calcDeliveryFee(new Customer(1, "Alice", CustomerType.GENERAL));
        assertEquals(500, fee);
    }

    @Test
    @DisplayName("顧客種別がゴールド会員の場合の振る舞いをテストする")
    void test_Gold_Customer() {
        int fee = mock.calcDeliveryFee(new Customer(2, "Bob", CustomerType.GOLD));
        assertEquals(300, fee);
    }
}