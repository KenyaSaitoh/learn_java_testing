package pro.kensait.junit5.customer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * CustomerService1（顧客サービス）のためのテストクラス
 */
class CustomerService1Test {

    @Test
    @DisplayName("命令網羅によるテストケース")
    void test_StatementCoverage() {
        int actual = CustomerService1.calcDeliveryFee(CustomerType.GOLD);
        assertEquals(0, actual);
    }

    @Test
    @DisplayName("分岐網羅の場合に追加が必要なテストケース")
    void test_BranchCoverage() {
        int actual = CustomerService1.calcDeliveryFee(CustomerType.GENERAL);
        assertEquals(600, actual);
    }
}
