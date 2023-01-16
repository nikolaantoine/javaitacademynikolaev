package hw13nikolaev.task1;

import java.util.Arrays;
import java.util.Scanner;


public class Main {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("Укажите размер массива");
        int number = scanner.nextInt();
        int [] array = new int[number];

        System.out.println("Введите элементы массива");
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        System.out.println(Arrays.toString(array));


        FindMin thread1 = new FindMin (array);
        FindMax thread2 = new FindMax(array);

        try {
            thread1.getThread().join();
            thread2.getThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Минимум: " + thread1.getMin());
        System.out.println("Максимум: " + thread2.getMax());
    }
}
