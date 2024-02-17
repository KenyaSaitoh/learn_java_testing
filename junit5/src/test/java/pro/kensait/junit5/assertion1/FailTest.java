package pro.kensait.junit5.assertion1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * 明示的に失敗を引き起こすためのテストクラス
 */
public class FailTest {

    @Test
    void test_Fail() {
        int result = 30 + 10;
        if (40 != result) {
            fail();
        }
    }
}