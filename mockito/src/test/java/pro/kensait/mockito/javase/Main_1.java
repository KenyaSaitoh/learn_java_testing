package pro.kensait.mockito.javase;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main_1 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        {
            System.out.println("***** snippet_1 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(0)).thenReturn("foo");
            when(mock.get(1)).thenReturn("bar");
            when(mock.get(2)).thenReturn("baz");

            List<Integer> keyList = List.of(0, 1, 2, 3);
            for (Integer key : keyList) {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            }
            System.out.println("-- end --");
        }
        {
            System.out.println("***** snippet_2 *****");
            Map<Date, String> mock = mock(Map.class);

            Date now = new Date();
            when(mock.get(now)).thenReturn("2023年12月1日");

            String value = mock.get(new Date());
            System.out.println("one => " + value);
            System.out.println("-- end --");
        }
        {
            System.out.println("***** snippet_3 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(0)).thenReturn("foo");
            when(mock.get(1)).thenReturn("bar");
            when(mock.get(1)).thenReturn("barbar");
            when(mock.get(2)).thenReturn("baz0", "baz1", "baz2");

            List<Integer> keyList = List.of(0, 1, 2, 2, 1, 2, 2);
            for (Integer key : keyList) {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            }
            System.out.println("-- end --");
        }

        {
            System.out.println("***** snippet_4 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(anyInt())).thenReturn("foo");

            List<Integer> keyList = List.of(0, 1, 2);
            for (Integer key : keyList) {
                String value = mock.get(key);
                System.out.println(key + " => " + value);
            }
            System.out.println("-- end --");
        }
    }
}