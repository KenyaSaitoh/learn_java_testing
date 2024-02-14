package pro.kensait.mockito.customer;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;

import pro.kensait.java.customer.Customer;
import pro.kensait.java.customer.CustomerLogic;
import pro.kensait.java.customer.CustomerType;

public class Main_ArgumentMatcher {
    CustomerLogic mock;
    //ArgumentMatcher<Customer> isGeneral;
    //ArgumentMatcher<Customer> isGold;

    @BeforeEach
    void setUp() {
        // Calculatorクラスのモックを生成する
        mock = mock(CustomerLogic.class);
    }
    
    @Test
    void main() {
        // ArgumentMatcherを使ってモックの振る舞いを定義する
        // ArgumentMatcherを定義する
        ArgumentMatcher<Customer> isGeneral = arg -> {
            System.out.println(arg + "####");
            return arg.getCustomerType() == CustomerType.GENERAL;
        };
        when(mock.calcDeliveryFee(argThat(isGeneral))).thenReturn(500);

        // モックを呼び出す
        // ケース1の挙動を確認する
        int fee1 = mock.calcDeliveryFee(new Customer(1, "Alice", CustomerType.GENERAL));
        System.out.println("answer1 => " + fee1);

        /*
         * whenを連続して呼び出すと、argがnullになってしまう
         * whenは`mock = mock(CustomerLogic.class);`呼び出し1回につき1回とする
         */
    }

    @Test
    void main2() {
        // ケース2
        ArgumentMatcher<Customer> isGold =  arg -> {
            System.out.println(arg + "!!!!");
            return arg.getCustomerType() == CustomerType.GOLD;
        };
        when(mock.calcDeliveryFee(argThat(isGold))).thenReturn(300);
        // モックを呼び出す

        // ケース2の挙動を確認する
        int fee2 = mock.calcDeliveryFee(new Customer(2, "Bob", CustomerType.GOLD));
        System.out.println("answer2 => " + fee2);
    }
}