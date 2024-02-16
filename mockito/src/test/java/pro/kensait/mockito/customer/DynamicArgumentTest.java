package pro.kensait.mockito.customer;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import pro.kensait.mockito.customer.Customer;
import pro.kensait.mockito.customer.CustomerLogic;
import pro.kensait.mockito.customer.CustomerType;

public class DynamicArgumentTest {
    CustomerLogic mock;
    //ArgumentMatcher<Customer> isGeneral;
    //ArgumentMatcher<Customer> isGold;

    @BeforeEach
    void setUp() {
        // Calculatorクラスのモックを生成する
        mock = mock(CustomerLogic.class);

        // ArgumentMatcherを使ってモックの振る舞いを定義する
        // ArgumentMatcherを定義する
        ArgumentMatcher<Customer> isGeneral = arg -> arg.getCustomerType() == CustomerType.GENERAL;
        doReturn(500).when(mock).calcDeliveryFee(argThat(isGeneral));

        ArgumentMatcher<Customer> isGold =  arg -> arg.getCustomerType() == CustomerType.GOLD;
        doReturn(300).when(mock).calcDeliveryFee(argThat(isGold));
    }

    @Test
    void test() {
        // ケース1の挙動を確認する
        int fee1 = mock.calcDeliveryFee(new Customer(1, "Alice", CustomerType.GENERAL));
        System.out.println("answer1 => " + fee1);
    }

    //@Test
    void main2() {
        // ケース2の挙動を確認する
        int fee2 = mock.calcDeliveryFee(new Customer(2, "Bob", CustomerType.GOLD));
        System.out.println("answer2 => " + fee2);
    }
}