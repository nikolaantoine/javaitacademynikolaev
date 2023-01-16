package hw10nikolaev.task2;

import java.util.HashMap;
import java.util.Map;

public class Method {
    public static <K> Map<K, Integer> arrayToMap(K[] ks) {
        Map<K, Integer> map = new HashMap<>();
        for (K key : ks) {
            map.compute(key, (k, count) -> count == null ? 1 : count + 1);
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(arrayToMap(new Object[3214]));
    }
}