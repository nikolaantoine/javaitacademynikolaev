package Controlling.task4;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

public class PlayerGeneratorTask implements Callable <String> {
    private List<Player> playerList;
    private String name;

    public PlayerGeneratorTask(List<Player> playerList, String name) {
        this.playerList = playerList;
        this.name = name;
    }

    @Override
    public String call() {

        playerList = List.of(
                new Player("Alex", 20, true),
                new Player("Bob", 22, true),
                new Player("Vladimir", 24, true),
                new Player("Gena", 25, true),
                new Player("Zebra", 26, false),
                new Player("Kent", 27, false),
                new Player("Nazar", 32, false),
                new Player("Mat", 23, false),
                new Player("Wolf", 28, false)
        );

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(name);
            for (Player player: playerList
                 ) {
                fileWriter.write(player.getName() + " : " + player.getAge() + " : " + player.isActive() + "\n");
            }
        } catch (IOException ioException) {
            System.out.println("Ошибка записи файла");
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                System.out.println("Ошибка закрытия");
            }
        }
        return name;
    }


    @Override
    public String toString() {
        return "PlayerGeneratorTask{" +
                "playerList=" + playerList +
                '}';
    }





}
