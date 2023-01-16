package hw16nikolaev.task2;

import java.util.*;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество столбиков");
        int n = scanner.nextInt();
        System.out.println("Введите количество строк");
        int m = scanner.nextInt();

        if (n < 2 || m < 2) {
            throw new RuntimeException("Введена неправильная матрица");
        }

        int[][] matrix = new int[n][m];

        Random random = new Random();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[i][j] = random.nextInt(1, 6);
            }
        }

        for (int[] ints : matrix) {
            System.out.println(Arrays.toString(ints));
        }

        SumString sumString = new SumString(matrix);
        SumRows sumRows = new SumRows(matrix);

        var executorService = Executors.newCachedThreadPool();

        Future<Integer> fut1 = executorService.submit(sumString);
        Future<Integer> fut2 = executorService.submit(sumRows);

        System.out.println("Getting results...");
        try {
            ;
            System.out.println(fut1.get());
            System.out.println(fut2.get());
            if (fut1.get() > fut2.get()) {
                System.out.println("Больше строковые = " + fut1.get());
            } else
                System.out.println("Больше столбиковые = " + fut2.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Ошибка олучения числа из потока");
        }

        System.out.println("Завершение потоков");
        executorService.shutdown();
    }
}
