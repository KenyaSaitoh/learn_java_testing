package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;

public class NGTest {

    @Test
    void test1() {
        // 引数のないコンストラクタがないとモック不可
            System.out.println("***** snippet_1 *****");
            try {
                LocalDate spy = spy(LocalDate.class);
                when(spy.getDayOfMonth()).thenReturn(10);
                System.out.println("getDayOfMonth() => " + spy.getDayOfMonth());
            } catch(MockitoException me) {
                System.out.println(me);
            }
            System.out.println("");
    }
    @Test
    void test2() {
        // StringクラスやClassクラスはモック不可
            System.out.println("***** snippet_2 *****");
            try {
                String spy = spy(String.class);
                when(spy.length()).thenReturn(10);
                System.out.println("length() => " + spy.length());
            } catch(MockitoException me) {
                System.out.println(me);
            }
            System.out.println("");
    }
}