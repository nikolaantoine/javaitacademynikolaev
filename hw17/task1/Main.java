package hw17.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите numberCount");
        int numberCount = scanner.nextInt();
        System.out.println("Введите fileCount");
        int fileCount = scanner.nextInt();

        if (numberCount < 1 || fileCount < 1) {
            throw new RuntimeException("Неправильно введены данные");
        }

        String name;

        List<Integer> minimumCollection = new ArrayList<>();

        for (int i = 0; i < fileCount; i++) {
            name = Integer.toString(i);
            CompletableFuture.supplyAsync(new GenerateThread1(numberCount, name))
                    .exceptionally(ex -> {
                        System.out.println("Ошибка генерации");
                        return null;
                    });

            try {
                minimumCollection.add(CompletableFuture.supplyAsync(new MinFinderThread1(name)).handle((res, ex) -> {
                    if (ex != null) {
                        System.out.println("Ошибка поиска числа " + ex.getMessage());
                    } else System.out.println("Finding! " + res);
                    return res;
                }).get());
            } catch (InterruptedException e) {
                System.out.println("Прерывание потока");
            } catch (ExecutionException e) {
                System.out.println("Ошибка получения числа");
            }
        }

        System.out.println("The most minimum " + Collections.min(minimumCollection));
    }

}
