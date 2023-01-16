package library;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        Map<String, Boolean> map = Map.of("A", true, "B", true, "C", true, "D", false);
        ReadingRoom readingRoom;

        try {
            readingRoom = new ReadingRoom(2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        Library library = new Library(map, readingRoom);
        Reader reader1 = new Reader("Petrov", library);
        Reader reader2 = new Reader("Smirnov", library);
        Reader reader3 = new Reader("Pushkin", library);

        System.out.println(library.getAvailableBooks());

        var t1 = CompletableFuture.runAsync(() -> reader1.getBook("A"))
                .thenRun(() -> reader1.getBook("D"))
                .thenRun(reader1::showBooksOnHand)
                .thenRun(reader1::goHome)
                .thenRun(reader1::goToReadingRoom)
                .thenRun(reader1::goFromReadingRoom)
                .thenRun(() -> reader1.returnBook("A"))
                .thenRun(reader1::goHome)
                .thenRun(() -> reader1.returnBook("D"))
                .thenRun(reader1::showBooksOnHand)
                .thenRun(reader1::goHome);

        var t2 = CompletableFuture.runAsync(() -> reader2.getBook("B"))
                .thenRun(() -> reader2.getBook("A"))
                .thenRun(reader2::showBooksOnHand)
                .thenRun(reader2::goToReadingRoom)
                .thenRun(reader2::goFromReadingRoom)
                .thenRun(() -> reader2.returnBook("B"))
                .thenRun(reader2::showBooksOnHand)
                .thenRun(reader2::goHome);

        var t3 = CompletableFuture.runAsync(() -> reader3.getBook("C"))
                .thenRun(reader3::showBooksOnHand)
                .thenRun(reader3::goToReadingRoom)
                .thenRun(() -> reader3.returnBook("C"))
                .thenRun(reader3::goFromReadingRoom)
                .thenRun(() -> reader3.returnBook("C"))
                .thenRun(reader3::showBooksOnHand)
                .thenRun(reader3::goHome);

        try {
            t1.get();
            t2.get();
            t3.get();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }

}
