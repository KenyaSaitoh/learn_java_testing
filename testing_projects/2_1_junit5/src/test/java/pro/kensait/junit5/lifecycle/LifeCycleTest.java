package pro.kensait.junit5.lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * ライフサイクルを確認するためのテストクラス
 */
public class LifeCycleTest {

    // テストクラス全体の前処理
    @BeforeAll
    static void initAll() {
        System.out.println("@BeforeAll");
    }

    // 各テストメソッドで共通的な前処理
    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach");
    }

    // テストケース1
    @Test
    void test_Case_1() {
        System.out.println("テストケース1");
    }

    // テストケース2
    @Test
    void test_Case_2() {
        System.out.println("テストケース2");
    }

    // 各テストメソッドで共通的な後処理
    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach");
    }

    // テストクラス全体の後処理
    @AfterAll
    static void cleanupAll() {
        System.out.println("@AfterAll");
    }
}