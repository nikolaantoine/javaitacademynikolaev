package hw15.task2;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> myList = Arrays.asList("a1", "a2", "a3", "b1", "b3", "c2", "c1", "c5");


        System.out.println(myList.stream()
                .filter(s -> !s.contains("3"))

                .sorted((o1, o2) -> {
                    int code1 = Integer.parseInt(o1.substring(1));
                    int code2 = Integer.parseInt(o2.substring(1));

                    if (code2 - code1 != 0) {
                        return code1 - code2;
                    } else {
                        char char1 = o1.charAt(1);
                        char char2 = o2.charAt(1);
                        return char2 - char1;
                    }
                })
                .map(Object::toString)
                .sorted(Comparator.comparing(String::valueOf).reversed())

                .map(String::toUpperCase)

                .collect(Collectors.toList()));


    }
}
