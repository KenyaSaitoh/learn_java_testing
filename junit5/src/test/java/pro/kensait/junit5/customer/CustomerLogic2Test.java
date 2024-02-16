package pro.kensait.junit5.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pro.kensait.junit5.customer.CustomerLogic2;
import pro.kensait.junit5.customer.CustomerType;

class CustomerLogic2Test {
    // 分岐網羅によるテストケース（条件が真の場合）
    @Test
    void test_BranchCoverage_1() {
        int actual = CustomerLogic2.calcDeliveryFee(CustomerType.GOLD, 0);
        assertEquals(0, actual);
    }

    // 分岐網羅によるテストケース（条件が偽の場合）
    @Test
    void test_BranchCoverage_2() {
        int actual = CustomerLogic2.calcDeliveryFee(CustomerType.GENERAL, 0);
        assertEquals(600, actual);
    }

    // 条件網羅の場合に追加が必要なテストケース
    @Test
    void test_ConditionCoverage() {
        int actual = CustomerLogic2.calcDeliveryFee(CustomerType.GENERAL, 5000);
        assertEquals(0, actual);
    }
}
