package hw10nikolaev.task4;

import java.util.Random;
import java.util.Set;

public class Something {

    Random random = new Random();

    public void filling(Set<Integer> set1, Set<Integer> set2) {
        for (int i = 0; i < 30; i++) {
            set1.add(random.nextInt(30));
            set2.add(random.nextInt(30));
        }
    }

    public void intersection (Set<Integer> set1, Set<Integer> set2, Set<Integer> set) {
        set.addAll(set1);
        set.retainAll(set2);
    }

    public void union (Set<Integer> set1, Set<Integer> set2, Set<Integer> set) {
        set.clear();
        set.addAll(set1);
        set.addAll(set2);
    }
}
