package pro.kensait.mockito.basic;

import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class MockitoTest {

    @SuppressWarnings("unchecked")
    @Test
    void test01() {
        Map<Integer, String> mock = mock(Map.class);

        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz0", "baz1", "baz2");

        System.out.println(mock.get(0));
        System.out.println(mock.get(0));
        System.out.println(mock.get(1));
        System.out.println(mock.get(2));
        System.out.println(mock.get(2));
        System.out.println(mock.get(2));
        System.out.println(mock.get(2));
    }
}