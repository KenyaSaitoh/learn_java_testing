package pro.kensait.mockito.calc.basic;

import static org.mockito.Mockito.*;

import pro.kensait.java.calc.Calc_5;

public class Main_5 {
    public static void main(String[] args) {
        Calc_5 mock = mock(Calc_5.class);
        when(mock.compute(5, 10, 8)).thenReturn(100);
        int answer1 = mock.compute(5, 10, 8);
        System.out.println("answer1 => " + answer1);
    }
}