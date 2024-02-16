package pro.kensait.mockito.calc.irregular;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class FinalClassInlineTest {
    @Mock
    FinalCalc mock;

    @BeforeEach
    void setUp() {
        // すべての@Mockアノテーションが付与されたフィールドをモック化する
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test() {
        when(mock.compute(5, 10, 3)).thenReturn(50);
        int answer1 = mock.compute(5, 10, 3);
        System.out.println("answer1 => " + answer1);
    }
}