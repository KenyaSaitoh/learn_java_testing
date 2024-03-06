package pro.kensait.mockito.calc.stateful;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

/*
 * 状態を持つ計算機（StatefulCalc）をスパイ化するテストクラス
 */
public class CalcTest_Spy {

    @Test
    void test_Case_1() {
        StatefulCalc calc = new StatefulCalc(5, 10);
        StatefulCalc spy = spy(calc);
        int retVal = spy.getX() + spy.getY();
        when(spy.compute()).thenReturn(retVal);
        int answer = spy.compute();
        assertEquals(15, answer);
    }
}