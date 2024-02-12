package pro.kensait.junit5.assertion;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * fail()による検証方法を説明するためのテストクラス
 */
public class Fail {

    @Test
    public void test_Fail() {
        int result = 30 + 10;
        if (40 != result) {
            fail();
        }
    }
}