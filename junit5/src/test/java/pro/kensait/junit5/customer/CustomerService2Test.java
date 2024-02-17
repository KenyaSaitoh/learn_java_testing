package pro.kensait.junit5.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * CustomerService2（顧客サービス）のためのテストクラス
 */
class CustomerService2Test {

    @Test
    @DisplayName("分岐網羅によるテストケース（条件が真の場合）")
    void test_BranchCoverage_1() {
        int actual = CustomerService2.calcDeliveryFee(CustomerType.GOLD, 0);
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("分岐網羅によるテストケース（条件が偽の場合）")
    void test_BranchCoverage_2() {
        int actual = CustomerService2.calcDeliveryFee(CustomerType.GENERAL, 0);
        assertEquals(600, actual);
    }

    @Test
    @DisplayName("条件網羅の場合に追加が必要なテストケース")
    void test_ConditionCoverage() {
        int actual = CustomerService2.calcDeliveryFee(CustomerType.GENERAL, 5000);
        assertEquals(0, actual);
    }
}
