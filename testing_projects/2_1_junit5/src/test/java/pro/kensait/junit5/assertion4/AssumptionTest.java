package pro.kensait.junit5.assertion4;

import static org.junit.jupiter.api.Assumptions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * アサンプション（前提条件によって実施可否を動的に判定する機能）を確認するテストクラス
 */
public class AssumptionTest {

    @Test
    @DisplayName("Windows環境の場合にのみ実行されるテスト")
    void test_Assumption_WindowsOnly() {
        String osName = System.getProperty("os.name").toLowerCase();
        assumeTrue(osName.contains("win"));
        // Windows環境の場合、以降の処理が実行される
        System.out.println("Environment is Windows!");
    }

    @Test
    @DisplayName("Windows以外の環境の場合に実行されるテスト")
    void test_Assumption_NotWindows() {
        String osName = System.getProperty("os.name").toLowerCase();
        assumeFalse(osName.contains("win"));
        // Windows以外の環境の場合、以降の処理が実行される
        System.out.println("Environment is not Windows!");
    }
}
