package pro.kensait.junit5.lifecycle;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * JUnit5のライフサイクルを確認するためのテストクラス
 */
public class LifeCycleTest {

    // テストプログラム全体に対する事前処理
    @BeforeAll
    static void initAll() {
        System.out.println("***** @BeforeAll *****");
    }

    // 各テストケースで共通的な事前処理
    @BeforeEach
    void setUp() {
        System.out.println("***** @BeforeEach *****");
    }

    // テストケース1
    @Test
    void test_Case_1() {
        System.out.println("***** @Test [test_Case_1] *****");
    }

    // テストケース2
    @Test
    void test_Case_2() {
        System.out.println("***** @Test [test_Case_2] *****");
    }

    // 各テストケースで共通的な事後処理
    @AfterEach
    void tearDown() {
        System.out.println("***** @AfterEach *****");
    }

    // テストプログラム全体に対する事後処理
    @AfterAll
    static void cleanupAll() {
        System.out.println("***** @AfterAll *****");
    }
}