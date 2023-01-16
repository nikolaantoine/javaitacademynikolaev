package hw17.task1;

import java.io.*;
import java.time.OffsetDateTime;
import java.util.function.Supplier;

public class MinFinderThread1 implements Supplier<Integer> {
    private final String name;
    int min;

    public MinFinderThread1(String name) {
        this.name = name;
    }

    @Override
    public Integer get() {
        System.out.println(Thread.currentThread() + " started task with finder min at " + OffsetDateTime.now());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException interruptedException) {
            System.out.println("Ошибка ожидания");
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(name + "Generation")))) {
            String text;
            min = Integer.MAX_VALUE;
            while (true) {
                try {
                    if ((text = in.readLine()) == null) break;
                    if (Integer.parseInt(text) < min)
                        min = Integer.parseInt(text);
                } catch (IOException e) {
                    System.out.println("Ошибка чтения строки");
                }
                try (PrintWriter printWriter = new PrintWriter(name + "Finder")) {
                    printWriter.print("Минимальное число файла " + min);
                } catch (FileNotFoundException e) {
                    System.out.println("Файл не найден");
                }
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Ошибка открытия fileNotFoundException");
        } catch (IOException e) {
            System.out.println("Ошибка IOException");
        }
        return min;
    }

    public String getName() {
        return name;
    }
}
