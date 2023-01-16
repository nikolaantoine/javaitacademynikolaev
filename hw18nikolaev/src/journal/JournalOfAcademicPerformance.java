package journal;


import java.time.OffsetDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class JournalOfAcademicPerformance {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Map<String, Map<Integer, Integer>> journal = new HashMap<>();

    public JournalOfAcademicPerformance(String... surnames) {
        for (String s : surnames) {
            journal.put(s, new TreeMap<>());
        }
    }


    public void addStudent(String surname) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " adding student- " + surname + " at " +
                OffsetDateTime.now());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (!journal.containsKey(surname)) {
            journal.put(surname, new TreeMap<>());
        } else {
            System.out.println("Такой студент уже есть в журнале");
        }

        System.out.println(Thread.currentThread().getName() + " finished adding student - " + surname +
                " at " + OffsetDateTime.now());
        lock.writeLock().unlock();
    }


    public void setMarkByClassNum(String surname, int classNum, int mark) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + " setting mark " + mark + " for student- " + surname +
                " on " + classNum + " class at " + OffsetDateTime.now());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (mark < 0) {
            System.out.println("Оценка не может быть отрицательной");
            return;
        }
        if (classNum <= 0) {
            System.out.println("Номер занятия не может быть отрицательным или равным 0");
            return;
        }
        if (journal.containsKey(surname)) {
            journal.get(surname).put(classNum - 1, mark);
        } else {
            System.out.println("Такого студента нет в журнале");
        }
        System.out.println(Thread.currentThread().getName() + " already set mark for student- " + surname +
                " on " + classNum + " class at " + OffsetDateTime.now());

        lock.writeLock().unlock();
    }

    public Integer getMarkByClassNum(String surname, int classNum) {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " getting mark for student- " + surname +
                " on " + classNum + " class at " + OffsetDateTime.now());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        var res = journal.get(surname).get(classNum - 1);

        System.out.println(Thread.currentThread().getName() + " already get mark  for student- " + surname +
                " on " + classNum + " class at" + OffsetDateTime.now());
        lock.readLock().unlock();
        return res;
    }

    public Double getAverageMark(String surname) {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " getting average mark for student- " + surname +
                " at " + OffsetDateTime.now());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (journal.get(surname).size() == 0) {
            return null;
        }
        Double averageMark = 0D;
        for (var v : journal.get(surname).entrySet()) {
            averageMark += v.getValue();
        }
        averageMark /= journal.get(surname).size();

        System.out.println(Thread.currentThread().getName() + " already get average mark for student- " + surname +
                " at " + OffsetDateTime.now());
        lock.readLock().unlock();
        return averageMark;
    }

    public List<String> filteringStudents(double averageMarkM) {
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + " filtering students by average mark at "
                + OffsetDateTime.now());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        List<String> list = new ArrayList<>();
        for (var p : journal.keySet()) {
            if (journal.get(p).size() == 0) {
                continue;
            }
            double averageMark = 0d;
            for (var v : journal.get(p).entrySet()) {
                averageMark += v.getValue();
            }
            averageMark /= journal.get(p).size();
            if (averageMark >= averageMarkM) {
                list.add(p);
            }
        }

        System.out.println(Thread.currentThread().getName() + " already filtered student at " + OffsetDateTime.now());
        lock.readLock().unlock();
        return list;
    }

}
