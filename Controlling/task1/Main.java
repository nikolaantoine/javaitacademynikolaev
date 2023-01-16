package Controlling.task1;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int x = new Random().nextInt(100), y = new Random().nextInt(100), z = new Random().nextInt(100);

        if (x > z) {
            System.out.println("x + y = " + (x + y));
        } else {
            System.out.println("z = " + z);
        }

        System.out.println((x + y + z) / 3);
    }
}
