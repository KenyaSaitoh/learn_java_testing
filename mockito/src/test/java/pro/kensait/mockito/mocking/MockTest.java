package pro.kensait.mockito.mocking;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

@SuppressWarnings("unchecked")
public class MockTest {

    @Test
    void test_Case_1() {
        Map<Integer, String> mock = mock(Map.class);

        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        printMock(mock, keyList);
    }

    @Test
    void test_Case_2() {
        Map<Integer, String> mock = mock(HashMap.class);

        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(2)).thenReturn("baz");

        List<Integer> keyList = Arrays.asList(0, 1, 2, 3);
        printMock(mock, keyList);
    }

    @Test
    void test_Case_3() {
        Map<String, String> mock = mock(Map.class);

        when(mock.get(new String("1"))).thenReturn("one");

        // インスタンスが別でも等価であれば同じ引数と見なされる
        String value = mock.get(new String("1"));
        System.out.println(value);
    }

    @Test
    void test_Case_4() {
        Map<Integer, String> mock = mock(Map.class);

        when(mock.get(0)).thenReturn("foo");
        when(mock.get(1)).thenReturn("bar");
        when(mock.get(1)).thenReturn("barbar");
        when(mock.get(2)).thenReturn("baz0", "baz1", "baz2");

        List<Integer> keyList = Arrays.asList(0, 1, 2, 2, 1, 2, 2);
        printMock(mock, keyList);
    }

    @Test
    void test_Case_5() {
        Map<Integer, String> mock = mock(Map.class);

        when(mock.get(anyInt())).thenReturn("foo");

        List<Integer> keyList = Arrays.asList(0, 1, 2);
        printMock(mock, keyList);
    }

    @Test
    void test_Case_6() {
        Map<Integer, String> mock = mock(Map.class);

        mock.put(0, "foo");
        mock.put(1, "bar");
        mock.put(2, "baz");
        when(mock.get(2)).thenReturn("bazbaz");

        List<Integer> keyList = Arrays.asList(0, 1, 2);
        printMock(mock, keyList);
    }

    @Test
    void test_Case_7() {
        Map<Integer, String> mock = mock(Map.class);

        when(mock.get(any(Integer.class))).thenReturn("hoge");

        List<Integer> keyList = Arrays.asList(0, 1, null);
        printMock(mock, keyList);
    }

    @Test
    void test_Case_8() {
        Map<Integer, String> mock = mock(Map.class);

        when(mock.get(nullable(Integer.class))).thenReturn("hoge");

        List<Integer> keyList = Arrays.asList(0, 1, null);
        printMock(mock, keyList);
    }

    private static void printMock(Map<Integer, String> mock, List<Integer> keyList) {
        for (Integer key : keyList) {
            String value = mock.get(key);
            System.out.println(key + " => " + value);
        }
    }
}