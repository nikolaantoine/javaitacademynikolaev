package hw10nikolaev.task4;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set <Integer> set = new HashSet<>();
        Set <Integer> set1 = new HashSet<>();
        Set <Integer> set2 = new HashSet<>();

        Something something = new Something();

        something.filling(set1, set2);
        System.out.println(set1);
        System.out.println(set2);

        something.intersection(set1, set2, set);
        System.out.println("Пересечение: " + set);

        something.union(set1, set2, set);
        System.out.println("Объединение: " + set);
    }
}
