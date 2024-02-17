package pro.kensait.junit5.assertion2;

import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.Test;

/*
 * Assumption（前提条件によって実施可否を動的に判定する機能）を検証するテストクラス
 */
public class AssumptionTest {

    // Windows環境の場合にのみ実行されるテスト
    @Test
    void test_Assumption_WindowsOnly() {
        String osName = System.getProperty("os.name").toLowerCase();
        assumeTrue(osName.contains("win"));
        // Windows環境の場合、以降の処理が実行される
        System.out.println("Environment is Windows!");
    }

    // Windows以外の環境の場合に実行されるテスト
    @Test
    void test_Assumption_NotWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        assumeFalse(osName.contains("win"));
        // Windows以外の環境の場合、以降の処理が実行される
        System.out.println("Environment is not Windows!");
    }
}
