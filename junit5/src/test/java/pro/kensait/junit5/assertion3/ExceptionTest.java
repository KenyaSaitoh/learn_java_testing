package pro.kensait.junit5.assertion3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * 例外の送出を検証するためのテストクラス
 */
public class ExceptionTest {

    // IllegalArgumentExceptionの送出を検証する
    @Test
    void test_Throw_Exceptiopn_1() {
        assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException();
        });
    }

    // IllegalArgumentExceptionの送出と例外メッセージを検証する
    @Test
    void test_Throw_Exceptiopn_2() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            throw new IllegalArgumentException("ERROR!");
        });
        // 加えて、例外メッセージも検証する
        assertEquals("ERROR!", thrown.getMessage());
    }
}