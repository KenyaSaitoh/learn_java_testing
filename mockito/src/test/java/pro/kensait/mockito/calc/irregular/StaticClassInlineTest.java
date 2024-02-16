package pro.kensait.mockito.calc.irregular;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import pro.kensait.mockito.calc.irregular.CalcUtil;

public class StaticClassInlineTest {

    @BeforeAll
    static void configureStaticMocks() {
        // CalcUtilクラスのスタティックモックを生成する
        MockedStatic<CalcUtil> mock = mockStatic(CalcUtil.class);

        // モックの振る舞いを設定する
        // ケース1
        mock.when(() -> CalcUtil.compute(5, 10, 3)).thenReturn(50);
        // ケース2
        mock.when(() -> CalcUtil.compute(5, 10, 8)).thenReturn(100);
        // ケース3
        mock.when(() -> CalcUtil.compute(5, 10, -1))
                .thenThrow(new IllegalArgumentException("エラー"));
    }

    @Test
    void test1() {
        int answer = CalcUtil.compute(5, 10, 3);
        assertEquals(50, answer);
    }

    @Test
    void test2() {
        int answer = CalcUtil.compute(5, 10, 8);
        assertEquals(100, answer);
    }

    @Test
    void test3() {
        int answer = CalcUtil.compute(5, 10, -1);
        assertEquals(100, answer);
    }
}