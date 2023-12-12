package pro.kensait.mockito.calc.inline;

import static org.mockito.Mockito.*;

import pro.kensait.java.calc.CalcFinal;

public class Main_Final {
    public static void main(String[] args) {
        CalcFinal mock = mock(CalcFinal.class);
        when(mock.compute(5, 10, 3)).thenReturn(50);
        int answer1 = mock.compute(5, 10, 3);
        System.out.println("answer1 => " + answer1);
    }
}