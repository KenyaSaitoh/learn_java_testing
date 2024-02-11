package pro.kensait.java.coverage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomerLogic1Test {

    // 命令網羅によるテストケース
    @Test
    void test_StatementCoverage() {
        int actual = CustomerLogic1.calcDeliveryFee(CustomerType.GOLD);
        assertEquals(0, actual);
    }

    // 分岐網羅の場合に追加が必要なテストケース
    @Test
    void test_BranchCoverage() {
        int actual = CustomerLogic1.calcDeliveryFee(CustomerType.GENERAL);
        assertEquals(600, actual);
    }
}
