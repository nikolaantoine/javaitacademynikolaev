package hw17.task1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.OffsetDateTime;
import java.util.Random;
import java.util.function.Supplier;

public class GenerateThread1 implements Supplier<Void> {
    private final int numberCount;
    private final String name;

    public GenerateThread1(int numberCount, String name) {
        this.numberCount = numberCount;
        this.name = name;
    }

    @Override
    public Void get() {
        System.out.println(Thread.currentThread() + " started task with generate at " + OffsetDateTime.now());
        Random random = new Random();
        try (PrintWriter printWriter = new PrintWriter(name + "Generation")) {
            for (int j = 0; j < numberCount; j++) {
                printWriter.write(Integer.toString(random.nextInt(300)));
                printWriter.write("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Ошибка в записи PrintWriter");
        }
        return null;
    }


}
