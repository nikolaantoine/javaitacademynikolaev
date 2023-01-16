package hw16nikolaev.task2;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class SumString implements Callable<Integer> {
    private final int[][] array;

    public SumString(int[][] array) {
        this.array = Arrays.copyOf(array, array.length);
    }

    @Override
    public Integer call() {
        System.out.println(Thread.currentThread() + " task with strings started at " + OffsetDateTime.now());
        List<Integer> multipliedValue = new ArrayList<>();
        int sum = 0;

        for (int i = 0; i < array.length; i++) {
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < array.length; j++) {
                list.add(array[i][j]);
            }
            int product = 1;
            for (int j = 0; j < array.length; j++) {
                product = product * array[i][j];
            }
            multipliedValue.add(product);
        }
        for (Integer integer : multipliedValue) {
            sum = sum + integer;
        }
        return sum;
    }
}
