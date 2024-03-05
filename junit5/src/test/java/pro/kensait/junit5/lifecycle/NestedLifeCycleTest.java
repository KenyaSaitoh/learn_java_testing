package pro.kensait.junit5.lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/*
 * ネステッドクラスとライフサイクルを確認するためのテストクラス
 */
public class NestedLifeCycleTest {

    // テストクラス全体の前処理【1】
    @BeforeAll
    static void initAll() {
        System.out.println("テストクラス全体の@BeforeAll【1】");
    }

    // テストクラス全体の各テストメソッドで共通的な前処理【2】
    @BeforeEach
    void setUp() {
        System.out.println("テストクラス全体の@BeforeEach【2】");
    }

    @Nested
    class NestedClass1 {
        // ネステッドクラス内の各テストメソッドで共通的な前処理【3】
        @BeforeEach
        void setUp() {
            System.out.println("NestedClass1の@BeforeEach【3】");
        }

        // テストケース1【4】
        @Test
        void test_Case_1() {
            System.out.println("テストケース1【4】");
        }

        // テストケース2【5】
        @Test
        void test_Case_2() {
            System.out.println("テストケース2【5】");
        }

        // ネステッドクラス内の各テストメソッドで共通的な後処理【6】
        @AfterEach
        void tearDown() {
            System.out.println("NestedClass1の@AfterEach【6】");
        }
    }

    @Nested
    class NestedClass2 {
        // ネステッドクラス内の各テストメソッドで共通的な前処理【7】
        @BeforeEach
        void setUp() {
            System.out.println("NestedClass2の@BeforeEach【7】");
        }

        // テストケース3【8】
        @Test
        void test_Case_3() {
            System.out.println("テストケース3【8】");
        }

        // テストケース4【9】
        @Test
        void test_Case_4() {
            System.out.println("テストケース4【9】");
        }

        // ネステッドクラス内の各テストメソッドで共通的な後処理【10】
        @AfterEach
        void tearDown() {
            System.out.println("NestedClass2の@AfterEach【10】");
        }
    }

    // テストクラス全体の各テストメソッドで共通的な後処理【11】
    @AfterEach
    void tearDown() {
        System.out.println("テストクラス全体の@AfterEach【11】");
    }

    // テストクラス全体の後処理【12】
    @AfterAll
    static void cleanupAll() {
        System.out.println("テストクラス全体の@AfterAll【12】");
    }
}