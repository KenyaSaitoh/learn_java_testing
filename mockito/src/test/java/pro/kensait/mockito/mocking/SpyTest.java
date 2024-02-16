package pro.kensait.mockito.mocking;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class SpyTest {

    @Test
    void test1() {
        Map<Integer, String> mock = spy(Map.class);

        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        for (Integer key : keyList) {
            String value = mock.get(key);
            System.out.println(key + " => " + value);
        }
    }

    @Test
    void test2() {
        Map<String, String> mock = spy(Map.class);

        when(mock.get(new String("1"))).thenReturn("one");

        // インスタンスが別でも等価であれば同じ引数と見なされる
        String value = mock.get(new String("1"));
        System.out.println(value);
        System.out.println("");
    }

    @Test
    void test3() {
        Map<Integer, String> mock = spy(Map.class);

        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(1)).thenReturn("barbar");
        when(mock.get(2)).thenReturn("baz0", "baz1", "baz2");

        List<Integer> keyList = Arrays.asList(0, 1, 2, 2, 1, 2, 2);
        for (Integer key : keyList) {
            String value = mock.get(key);
            System.out.println(key + " => " + value);
        }
    }

    @Test
    void test4() {
        Map<Integer, String> mock = spy(Map.class);

        when(mock.get(anyInt())).thenReturn("foo");

        List<Integer> keyList = Arrays.asList(0, 1, 2);
        for (Integer key : keyList) {
            String value = mock.get(key);
            System.out.println(key + " => " + value);
        }
    }

    @Test
    void test5() {
        Map<Integer, String> mock = spy(HashMap.class);

        mock.put(0, "foo");
        mock.put(1, "bar");
        mock.put(2, "baz");
        when(mock.get(2)).thenReturn("bazbaz");

        List<Integer> keyList = Arrays.asList(0, 1, 2);
        for (Integer key : keyList) {
            String value = mock.get(key);
            System.out.println(key + " => " + value);
        }
    }
}