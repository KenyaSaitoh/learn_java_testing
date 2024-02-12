package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pro.kensait.java.coverage.CustomerType;

/*
 * コンストラクタの検証方法を説明するためのテストクラス
 */
public class ConstructorTest {

    @Test
    public void test_Constructor() {
        Customer customer = new Customer(1, "foo");
        assertEquals(1, customer.getId());
        assertEquals("foo", customer.getName());
        assertEquals(CustomerType.GENERAL, customer.getCustomerType());
    }
}