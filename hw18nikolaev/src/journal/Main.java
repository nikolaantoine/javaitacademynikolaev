package journal;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        JournalOfAcademicPerformance journal = new JournalOfAcademicPerformance("Aa", "Bb", "Cc");

        var t1 = CompletableFuture.runAsync(
                () -> journal.setMarkByClassNum("Aa", 1, 10));
        var t2 = CompletableFuture.runAsync(
                () -> journal.setMarkByClassNum("Aa", 2, 7));

        var t3 = CompletableFuture.supplyAsync(
                        () -> journal.getMarkByClassNum("Aa", 1))
                .thenAccept(System.out::println);
        var t4 = CompletableFuture.supplyAsync(
                        () -> journal.getMarkByClassNum("Aa", 2))
                .thenAccept(System.out::println);
        var t5 = CompletableFuture.runAsync(() -> journal.addStudent("Dd"))
                .thenRun(() -> journal.setMarkByClassNum("Dd", 3, 5))
                .thenRunAsync(() -> journal.setMarkByClassNum("Bb", 3, 8));

        var t6 = CompletableFuture.supplyAsync(() -> journal.getAverageMark("Aa"))
                .thenAccept(res -> System.out.println("Средний балл Аа=" + res));

        var t7 = CompletableFuture.supplyAsync(() -> journal.filteringStudents(4d))
                .thenAccept(res -> System.out.println("Студенты с балом выше чем 4: \n" + res));


        try {
            t1.get();
            t2.get();
            t3.get();
            t4.get();
            t5.get();
            t6.get();
            t7.get();
        } catch (InterruptedException e) {
            System.out.println("Поток был прерван");
            Thread.currentThread().interrupt();
            return;
        } catch (ExecutionException e) {
            System.out.println("Ошибка во время выполнения");
            System.out.println(e.getCause().toString());
            return;
        }
        System.out.println("End");
    }
}
