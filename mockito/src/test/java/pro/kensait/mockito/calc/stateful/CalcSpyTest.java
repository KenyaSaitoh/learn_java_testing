package pro.kensait.mockito.calc.stateful;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

public class CalcSpyTest {
    // スパイ
    @Spy
    StatefulCalc spy1 = new StatefulCalc(5, 10, 3);
    @Spy
    StatefulCalc spy2 = new StatefulCalc(5, 10, 8);
    @Spy
    StatefulCalc spy3 = new StatefulCalc(5, 10, -1);

    @BeforeEach
    void setUp() {
        // すべての@Spyアノテーションが付与されたフィールドをスパイ化する
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("ケース1の振る舞いをテストする")
    void test_Case_1() {
        doReturn(50).when(spy1).compute();
        when(spy1.compute()).thenReturn(50);
        int answer = spy1.compute();
        assertEquals(50, answer);
    }

    @Test
    @DisplayName("ケース2の振る舞いをテストする")
    void test_Case_2() {
        doReturn(100).when(spy2).compute();
        int answer = spy2.compute();
        assertEquals(100, answer);
    }

    @Test
    @DisplayName("ケース3の振る舞い（例外発生）をテストする")
    void test_Case_3() {
        doThrow(new IllegalArgumentException("エラー")).when(spy3).compute();
        assertThrows(IllegalArgumentException.class, () -> spy3.compute());
    }
}