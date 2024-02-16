package pro.kensait.mockito.mocking;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class ExceptionTest {

    @Test
    void test() {
        Map<Integer, String> mock = mock(Map.class);

        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenThrow(new RuntimeException());

        List<Integer> keyList = Arrays.asList(0, 1, 2);
        for (Integer key : keyList) {
            try {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            } catch (RuntimeException ex) {
                System.out.println(ex);
            }
        }
    }

    @Test
    void test2() {
        Map<Integer, String> mock = mock(Map.class);

        when(mock.get(0)).thenThrow(
                new RuntimeException("1st Exception"),
                new IllegalArgumentException("2nd Exception"),
                new NoSuchElementException("3rd Exception"));

        List<Integer> keyList = Arrays.asList(0, 0, 0);
        for (Integer key : keyList) {
            try {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            } catch (RuntimeException ex) {
                System.out.println(ex);
            }
        }
    }
}