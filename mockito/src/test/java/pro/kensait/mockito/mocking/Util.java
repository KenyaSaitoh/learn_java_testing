package pro.kensait.mockito.mocking;

import java.util.List;
import java.util.Map;

public class Util {
    /*
     * Mapからすべてのエントリを抽出し、コンソールに表示する
     */
    public static void extractEntry(Map<Integer, String> map, List<Integer> keyList,
            String name) {
        System.out.println("***** [ "+ name + " ] *****");
        for (Integer key : keyList) {
            try {
                String value = map.get(key);
                System.out.println(key + " => " + value);
            } catch (RuntimeException ex) {
                System.out.println(ex);
            }
        }
    }
}