package Controlling.task4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class PlayerReaderTask implements Callable <String> {
    List<Player> playerList;
    private String name;

    public PlayerReaderTask(List<Player> playerList, String name) {
        this.playerList = playerList;
        this.name = name;
    }

    @Override
    public String call() {
        try {
            FileReader fileReader = new FileReader(name);
            fileReader.read();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List <Player> filterCollection = new ArrayList<>();

        for (Player player: playerList
             ) {
            if (player.getAge() >= 25 && player.getAge() <= 30) {
                filterCollection.add(player);
            }
        }
        return filterCollection.toString();
    }
}

