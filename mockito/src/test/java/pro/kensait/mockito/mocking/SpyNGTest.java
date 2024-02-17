package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.base.MockitoException;

/*
 * スパイの制約を確認するテストクラス
 */
public class SpyNGTest {

    @Test
    @DisplayName("引数のないコンストラクタがないとスパイ化できないことを確認する")
    void test_Case_1() {
        // 
        try {
            LocalDate spy = spy(LocalDate.class);
            when(spy.getDayOfMonth()).thenReturn(10);
            System.out.println("getDayOfMonth() => " + spy.getDayOfMonth());
        } catch (MockitoException me) {
            System.out.println(me);
        }
    }

    @Test
    @DisplayName("StringクラスやClassクラスはスパイ化できないことを確認する")
    void test_Case_2() {
        try {
            String spy = spy(String.class);
            when(spy.length()).thenReturn(10);
            System.out.println("length() => " + spy.length());
        } catch (MockitoException me) {
            System.out.println(me);
        }
    }
}