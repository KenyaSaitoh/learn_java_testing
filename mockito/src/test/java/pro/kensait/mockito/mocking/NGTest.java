package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;

public class NGTest {

    @Test
    void test_Case_1() {
        // 引数のないコンストラクタがないとスパイ化できない
        try {
            LocalDate spy = spy(LocalDate.class);
            when(spy.getDayOfMonth()).thenReturn(10);
            System.out.println("getDayOfMonth() => " + spy.getDayOfMonth());
        } catch (MockitoException me) {
            System.out.println(me);
        }
    }

    @Test
    void test_Case_2() {
        // StringクラスやClassクラスはスパイ化できない
        try {
            String spy = spy(String.class);
            when(spy.length()).thenReturn(10);
            System.out.println("length() => " + spy.length());
        } catch (MockitoException me) {
            System.out.println(me);
        }
    }
}