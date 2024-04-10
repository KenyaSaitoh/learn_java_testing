package pro.kensait.junit5.assertion1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*
 * インスタンスの型を検証するためのテストクラス
 */
public class AssertInstanceOfTest {

    // 実測値が、指定されたクラスの型であることを検証する
    @Test
    void test_InstanceOf() {
        Integer actual = 10;
        assertInstanceOf(Number.class, actual);
    }
}