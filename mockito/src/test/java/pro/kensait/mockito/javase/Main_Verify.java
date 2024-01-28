package pro.kensait.mockito.javase;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.mockito.InOrder;

public class Main_Verify {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        {
            System.out.println("***** snippet_1 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(0)).thenReturn("foo");
            when(mock.get(1)).thenReturn("bar");
            when(mock.get(2)).thenReturn("baz");

            List<Integer> keyList = Arrays.asList(0, 0, 0);
            for (Integer key : keyList) {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            }

            // メソッド呼び出し回数を検証する
            verify(mock, times(3)).get(0);
            System.out.println("");
        }

        {
            System.out.println("***** snippet_2 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(0)).thenReturn("foo");
            when(mock.get(1)).thenReturn("bar");
            when(mock.get(2)).thenReturn("baz");

            List<Integer> keyList = Arrays.asList(0, 0, 0);
            for (Integer key : keyList) {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            }

            // メソッド呼び出し回数が最大でも5回であることを検証する
            verify(mock, atMost(5)).get(0);
            System.out.println("");
        }

        {
            System.out.println("***** snippet_3 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(0)).thenReturn("foo");
            when(mock.get(1)).thenReturn("bar");
            when(mock.get(2)).thenReturn("baz");

            List<Integer> keyList = Arrays.asList(0, 0, 0);
            for (Integer key : keyList) {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            }

            // メソッド呼び出しが一度も行われていないことを検証する
            verify(mock, never()).get(1);
            System.out.println("");
        }

        {
            System.out.println("***** snippet_4 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(0)).thenReturn("foo");
            when(mock.get(1)).thenReturn("bar");
            when(mock.get(2)).thenReturn("baz");

            List<Integer> keyList = Arrays.asList(0, 1, 2);
            for (Integer key : keyList) {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            }

            // メソッドが指定された引数の順番に呼び出されていることを検証する
            InOrder inOrder = inOrder(mock);
            inOrder.verify(mock).get(0);
            inOrder.verify(mock).get(1);
            inOrder.verify(mock).get(2);
            System.out.println("");
        }
    }
}