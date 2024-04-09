package pro.kensait.junit5.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Customerを対象にしたテストクラス
 */
public class CustomerTest {

    @Test
    @DisplayName("Customerクラスのインスタンス生成をテストする")
    void test_Customer_Constructor() {
        // 実行フェーズ
        Customer customer = new Customer(1, "foo");
        // 検証フェーズ
        assertEquals(1, customer.getId());
        assertEquals("foo", customer.getName());
        assertEquals(CustomerType.GENERAL, customer.getCustomerType());
    }
}