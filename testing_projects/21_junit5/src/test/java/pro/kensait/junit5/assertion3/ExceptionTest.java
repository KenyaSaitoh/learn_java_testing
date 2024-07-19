package pro.kensait.junit5.assertion3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * 例外の送出を検証するためのテストクラス
 */
public class ExceptionTest {

    private static final String ERROR_MESSAGE = "ERROR!";

    // RuntimeExceptionの送出を検証する
    @Test
    void test_Throw_Exceptiopn() {
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException();
        });
    }

    // RuntimeExceptionの送出と例外メッセージを検証する
    @Test
    void test_Throw_Exceptiopn_Message() {
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException(ERROR_MESSAGE);
        });
        // 加えて、例外メッセージも検証する
        assertEquals(ERROR_MESSAGE, thrown.getMessage());
    }

    // RuntimeExceptionが送出されないことを検証する
    @Test
    void test_DoesNot_Throw_Exception() {
        assertDoesNotThrow(() -> 30 + 10);
    }
}