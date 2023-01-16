package hw16nikolaev.task1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

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

        var executorService = Executors.newCachedThreadPool();
        List<GenerateThread> collectionGenerateThreadVoid = new ArrayList<>();
        List<MinFinderThread> collectionMinFinderThread = new ArrayList<>();

        for (int i = 0; i < fileCount; i++) {
            String name = Integer.toString(i);
            collectionGenerateThreadVoid.add(new GenerateThread(numberCount, name));
            collectionMinFinderThread.add(new MinFinderThread(name));
        }

        List<Future<Integer>> futures2 = null;

        try {
            System.out.println("Invoking tasks");
            futures2 = executorService.invokeAll(collectionMinFinderThread);
        } catch (InterruptedException e) {
            System.out.println("Ошибка пробуждения Future");
        }


        List<String> names = new ArrayList<>();
        for (int i = 0; i < collectionGenerateThreadVoid.size(); i++) {
            names.add(collectionMinFinderThread.get(i).getName());
        }

        for (int i = 0; i < fileCount; i++) {
            var name = names.get(i);
            collectionMinFinderThread.add(new MinFinderThread(name));
        }

        System.out.println("Getting results...");
        List<Integer> minimumNumber = new ArrayList<>();
        futures2.forEach(f -> {
            try {
                Integer integer = f.get();
                System.out.println("Result of task = " + f.get() + " " + f.isDone());
                minimumNumber.add(integer);

            } catch (InterruptedException e) {
                System.out.println("Поток был прерван");
            } catch (ExecutionException e) {
                System.out.println("Ошибка выполнения задачи");
            }
        });
        System.out.println(minimumNumber);
        System.out.println("The most minimum  " + Collections.min(minimumNumber));

        System.out.println("Завершение потоков");
        executorService.shutdown();
    }
}
