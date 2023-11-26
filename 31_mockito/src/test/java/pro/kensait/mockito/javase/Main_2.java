package pro.kensait.mockito.javase;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class Main_2 {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        {
            System.out.println("***** snippet_1 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(0)).thenReturn("foo");
            when(mock.get(1)).thenReturn("bar");
            when(mock.get(2)).thenThrow(new RuntimeException());

            List<Integer> keyList = List.of(0, 1, 2);
            for (Integer key : keyList) {
                try {
                    String value = mock.get(key);
                    System.out.println(key + " => " + value);
                } catch(RuntimeException ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("-- end --");
        }
        {
            System.out.println("***** snippet_2 *****");
            Map<Integer, String> mock = mock(Map.class);

            when(mock.get(0)).thenThrow(
                    new RuntimeException("1st Exception"),
                    new IllegalArgumentException("2nd Exception"),
                    new NoSuchElementException("3rd Exception"));

            List<Integer> keyList = List.of(0, 0, 0);
            for (Integer key : keyList) {
                try {
                    String value = mock.get(key);
                    System.out.println(key + " => " + value);
                } catch(RuntimeException ex) {
                    System.out.println(ex);
                }
            }
            System.out.println("-- end --");
        }
    }
}