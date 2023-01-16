package hw17.task2;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SumRows implements Supplier<Integer> {
    private final int[][] array;

    public SumRows(int[][] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    @Override
    public Integer get() {
        System.out.println(Thread.currentThread() + " task with rows started at " + OffsetDateTime.now());
        int sum = 0;
        List<Integer> multipliedValue = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            int product = 1;
            for (int j = 0; j < array[0].length; j++) {
                product = product * array[j][i];
            }
            multipliedValue.add(product);
        }
        for (Integer integer : multipliedValue
        ) {
            sum = sum + integer;
        }
        return sum;
    }
}
