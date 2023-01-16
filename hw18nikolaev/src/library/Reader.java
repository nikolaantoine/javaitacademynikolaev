package library;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Reader {
    private final String surname;
    private final Library library;
    private final Map<String, Boolean> booksOnHand = new HashMap<>();

    public Reader(String surname, Library library) {
        this.surname = surname;
        this.library = library;
    }

    public String getSurname() {
        return surname;
    }

    public void getBook(String bookName) {
        System.out.println(Thread.currentThread().getName() + ". " + surname + " хочет взять книгу: '" + bookName +
                "' в " + LocalTime.now());
        try {
            var b = library.getBook(bookName);
            booksOnHand.put(bookName, b);

            TimeUnit.SECONDS.sleep(3);

            System.out.println(Thread.currentThread().getName() + ". " + surname + " взял книгу '" + bookName + "' в "
                    + LocalTime.now());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }

    public void goHome() {
        System.out.println(Thread.currentThread().getName() + ". " + surname + " собирается уйти домой в "
                + LocalTime.now());
        for (var v : booksOnHand.entrySet()) {
            if (v.getValue().equals(true)) {
                System.out.println("Запрещено идти домой когда взял книгу '" + v.getKey() +
                        "' предназначеную для читательного зала");
                return;
            }
        }
        System.out.println(Thread.currentThread().getName() + ". " + surname + " ушел домой в " + LocalTime.now());

    }

    public void goToReadingRoom() {
        System.out.println(Thread.currentThread().getName() + ". " + surname + " собирается зайти в читальный зал в "
                + LocalTime.now());
        library.goToReadingRoom(this);

    }

    public void goFromReadingRoom() {
        library.goFromReadingRoom(this);
    }

    public void returnBook(String bookName) {
        System.out.println(Thread.currentThread().getName() + ". " + surname + " пошел возвращать книгу '" + bookName
                + "' в " + LocalTime.now());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (booksOnHand.containsKey(bookName)) {

            try {
                library.returnBook(this, bookName, booksOnHand.get(bookName));
            } catch (RuntimeException ex) {
                System.out.println(ex.getMessage());
            }

            booksOnHand.remove(bookName);
            System.out.println(Thread.currentThread().getName() + ". " + surname + " вернул книгу '" + bookName + "' в "
                    + LocalTime.now());

        } else {
            System.out.println("Книга '" + bookName + "' не библиотечная");
        }
    }

    public void showBooksOnHand() {
        System.out.println("Книги на руках у " + surname + " :" + booksOnHand.keySet());
    }

}
