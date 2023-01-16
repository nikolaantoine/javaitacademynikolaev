package library;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Library {

    private final Lock lock = new ReentrantLock(true);
    private final Map<String, Boolean> booksMap = new HashMap<>();
    private final ReadingRoom readingRoom;

    public Library(Map<String, Boolean> books, ReadingRoom readingRoom) {
        booksMap.putAll(books);
        this.readingRoom = readingRoom;
    }

    public boolean getBook(String book) throws IllegalArgumentException {
        lock.lock();
        boolean b;
        if (booksMap.containsKey(book)) {
            b = booksMap.get(book);
            booksMap.remove(book);
        } else {
            lock.unlock();
            throw new IllegalArgumentException("Книги '" + book + "' нету в данный момент времени");
        }
        lock.unlock();
        return b;
    }

    public String getAvailableBooks() {
        return booksMap.keySet().toString();
    }

    public void goToReadingRoom(Reader reader) {
        readingRoom.enterReadingRoom(reader);
    }

    public void goFromReadingRoom(Reader reader) {
        readingRoom.exitReadingRoom(reader);
    }

    public void returnBook(Reader r, String book, boolean b) throws RuntimeException {
        lock.lock();

        if (!readingRoom.getSurnames().contains(r.getSurname())) {
            booksMap.put(book, b);
        } else {
            lock.unlock();
            throw new RuntimeException(Thread.currentThread().getName() + ". " + r.getSurname() + " все еще сидит в" +
                    " читальном зале");
        }

        lock.unlock();
    }
}
