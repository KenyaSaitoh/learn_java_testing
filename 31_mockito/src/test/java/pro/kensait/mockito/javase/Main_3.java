package pro.kensait.mockito.javase;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.mockito.exceptions.base.MockitoException;

public class Main_3 {

    public static void main(String[] args) {
        // 引数のないコンストラクタがないとモック不可
        {
            System.out.println("***** snippet_2 *****");
            try {
                LocalDate spy = spy(LocalDate.class);
                when(spy.getDayOfMonth()).thenReturn(10);
                System.out.println("getDayOfMonth() => " + spy.getDayOfMonth());
            } catch(MockitoException me) {
                System.out.println(me);
            }
            System.out.println("-- end --");
        }

        // StringクラスやClassクラスはモック不可
        {
            System.out.println("***** snippet_3 *****");
            try {
                String spy = spy(String.class);
                when(spy.length()).thenReturn(10);
                System.out.println("length() => " + spy.length());
            } catch(MockitoException me) {
                System.out.println(me);
            }
            System.out.println("-- end --");
        }
    }
}