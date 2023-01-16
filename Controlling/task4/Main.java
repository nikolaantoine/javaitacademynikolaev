package Controlling.task4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        List<Player> playerList = new ArrayList<>();

        System.out.println("Введите имя файла");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();

        PlayerGeneratorTask playerGeneratorTask = new PlayerGeneratorTask(playerList, name);
        PlayerReaderTask playerReaderTask = new PlayerReaderTask(playerList, name);

        var executorService = Executors.newFixedThreadPool(4);

        List <PlayerGeneratorTask> playerGeneratorTasks = new ArrayList<>();
        List <PlayerReaderTask> playerReaderTasks = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            playerGeneratorTasks.add(playerGeneratorTask);
            playerReaderTasks.add(playerReaderTask);
        }

        try {
            executorService.invokeAll(playerGeneratorTasks);
            executorService.invokeAll(playerReaderTasks);
        } catch (InterruptedException e) {
            System.out.println("Ошибка пробуждения");
        }

        playerList.stream().sorted().forEach(System.out::println);

        executorService.shutdown();

    }
}
