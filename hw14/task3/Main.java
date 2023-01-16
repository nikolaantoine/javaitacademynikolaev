package hw14.task3;

import java.util.Objects;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Predicate<String> predicate = Objects::nonNull;

        Predicate<String> predicate1 = s -> !s.isEmpty();

        System.out.println(predicate.test(null));
        System.out.println(predicate.test(""));
        System.out.println(predicate.test("hello"));

        System.out.println(predicate.and(predicate1).test(null));
        System.out.println(predicate.and(predicate1).test(""));
        System.out.println(predicate.and(predicate1).test("hello"));
    }
}
