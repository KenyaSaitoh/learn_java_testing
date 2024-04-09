package pro.kensait.junit5.assertion3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

/*
 * fail()と制御構文を組み合わせた検証を確認するためのテストクラス
 */
public class FailTest {

    // 同じ値であること（等価性）をfail()で検証する
    @Test
    void test_Fail() {
        String expected = "foo";
        String actual = "foo";

        // if文＋fail()による検証は、以下と同じ
        // assertEquals(expected, actual);

        // 期待値と実測値が不一致だった場合は、fail()を呼び出して失敗させる
        if (! expected.equals(actual)) {
            fail();
        }
    }

    // RuntimeExceptionの送出をfail()で検証する
    @Test
    void test_Fail_Exception_1() {
        long random = (new Random()).nextLong();
        try {
            // RuntimeExceptionが送出される可能性のある処理（ほとんど発生する）
            if (random != 0L) throw new RuntimeException();
        } catch (RuntimeException re) {
            return; // 成功
        }
        // 期待とは逆に例外が発生しなかった場合は、fail()を呼び出して失敗させる
        fail();
    }

    // RuntimeExceptionが送出されないことをfail()で検証する
    @Test
    void test_Fail_Exception_2() {
        long random = (new Random()).nextLong();
        try {
            // RuntimeExceptionが送出される可能性のある処理（ほとんど発生しない）
            if (random == 0L) throw new RuntimeException();
        } catch (RuntimeException re) {
            fail(); // 失敗
        }
        // 期待通りに例外が発生しなかった場合は、そのままメソッドを抜ける（成功）
    }
}