package pro.kensait.java.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pro.kensait.java.customer.Customer.CustomerType;

/*
 * コンストラクタによるインスタンス生成を検証するためのテストクラス
 */
public class CustomerTest {

    @Test
    void test_Customer_Constructor() {
        // 実行フェーズ
        Customer customer = new Customer(1, "foo");
        // 検証フェーズ
        assertEquals(1, customer.getId());
        assertEquals("foo", customer.getName());
        assertEquals(CustomerType.GENERAL, customer.getCustomerType());
    }
}