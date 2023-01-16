package library;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ReadingRoom {
    private final Semaphore semaphore;
    private final List<String> surnames;

    public ReadingRoom(int places) {
        if (places < 1) {
            throw new IllegalArgumentException("Мест в читальном зале не может быть меньше 1");
        }
        semaphore = new Semaphore(places);
        surnames = new ArrayList<>(places);
    }

    public List<String> getSurnames() {
        return surnames;
    }

    public void enterReadingRoom(Reader r) {
        if (!surnames.contains(r.getSurname())) {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + ". " + r.getSurname() + " сел в читальный зал" +
                        " в " + LocalTime.now());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            surnames.add(r.getSurname());
        } else {
            System.out.println(Thread.currentThread().getName() + ". " + r.getSurname() + " уже сидит в читальном зале");
        }

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void exitReadingRoom(Reader r) {
        if (surnames.contains(r.getSurname())) {
            System.out.println(Thread.currentThread().getName() + ". " + r.getSurname() + " уходит с читального зала" +
                    " в " + LocalTime.now());
            surnames.remove(r.getSurname());

            semaphore.release();
        } else {
            System.out.println(Thread.currentThread().getName() + ". " + r.getSurname() + " не в читальном зале");
        }
    }

}
