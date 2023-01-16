package hw15.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        var list = generate(); // очень сомневаюсь что я должен так создавать коллекцию

        System.out.println(list);

        System.out.println("--------------------");
        list.stream().filter((p) -> p.getAge() < 21)
                .distinct() //необязательное думаю

                .sorted((o1, o2) -> -o1.getSurname().compareTo(o2.getSurname()))
                .sorted((o1, o2) -> -o1.getName().compareTo(o2.getName()))
                // Comparator.comparing(Person::getSurname).thenComparing(Person::getName)

                .limit(4)

                .forEach(e -> System.out.print(e + " "));
    }

    static List<Person> generate() {
        List<Person> result = new ArrayList<>();
        String name = null;
        String surname = null;
        int age = 0;
        for (int i = 3; i <= 103; i++) {
            if (i % 5 == 0) {
                name = "Ivan";
            }
            if (i % 4 == 0) {
                name = "Petya";
            }
            if (i % 3 == 0) {
                name = "Sasha";
            }
            if (i % 7 == 0) {
                name = "Vlad";
            }
            if (i % 2 == 0) {
                name = "Katya";
            }
            if (i % 5 == 1) {
                surname = "Ivanov";
            }
            if (i % 4 == 1) {
                surname = "Petrov";
            }
            if (i % 3 == 1) {
                surname = "Nikolaev";
            }
            if (i % 7 == 1) {
                surname = "Yakovlev";
            }
            if (i % 2 == 1) {
                surname = "Antonov";
            }
            Random random = new Random();
            age = random.nextInt(15, 30);
            Person person = new Person(name, surname, age);
            result.add(person);
        }
        return result;
    }
}

