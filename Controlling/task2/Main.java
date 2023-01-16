package Controlling.task2;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        int[] array = new int[10];

        for (int i = 0; i < array.length; i++) {
            array[i] = new Random().nextInt(601) - 300;
        }

        System.out.println(Arrays.toString(array));

        int max = Arrays.stream(array).max().orElse(array[0]);
        int min = Arrays.stream(array).min().orElse(array[0]);

        System.out.println("min = " + min + ", max = " + max);

        for (int i = 0; i < array.length; i++) {
            if (array[i] == max) {
                array[i] = max * min;
            }
        }

        System.out.println(Arrays.toString(array));
    }
}
