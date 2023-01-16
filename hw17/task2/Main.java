package hw17.task2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class Main {
    public static void main(String[] args) {
        int numberString = 0;
        int numberRows = 0;
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

        try {
            numberString = CompletableFuture.supplyAsync(new SumString(matrix))
                    .handle((res, ex) -> {
                        if (ex != null) {
                            System.out.println("Ошибка работы класса SumString" + ex.getMessage());
                            return null;
                        } else
                            System.out.println("Finding! " + res);
                        return res;
                    }).get();
        } catch (InterruptedException e) {
            System.out.println("Прерывание потока");
        } catch (ExecutionException e) {
            System.out.println("Ошибка получения числа");
        }

        try {
            numberRows = CompletableFuture.supplyAsync(new SumRows(matrix))
                    .handle((res, ex) -> {
                        if (ex != null) {
                            System.out.println("Ошибка работы класса SumString" + ex.getMessage());
                            return null;
                        } else
                            System.out.println("Finding! " + res);
                        return res;
                    }).get();
        } catch (InterruptedException e) {
            System.out.println("Прерывание потока");
        } catch (ExecutionException e) {
            System.out.println("Ошибка получения числа");
        }

        if (numberRows < numberString) {
            System.out.println("Строковая больше " + numberString);
        } else System.out.println("Столбиковая больше " + numberRows);
    }
}

