package hw10nikolaev.task3;

public class Main {
    public static void main(String[] args) {
        Gradebook gradebook = new Gradebook();

        gradebook.grading("Мишалкин", 1, 10);
        gradebook.grading("Мишалкин", 2, 10);
        gradebook.grading("Мишалкин", 3, 9);
        gradebook.grading("Крестьянов", 1, 8);
        gradebook.grading("Крестьянов", 2, 9);
        gradebook.grading("Крестьянов", 3, 6);
        gradebook.grading("Сапунов", 1, 5);
        gradebook.grading("Сапунов", 2, 3);
        gradebook.grading("Сапунов", 3, 7);


        System.out.println(gradebook.showMark("Мишалкин", 2));
        System.out.println(gradebook.averageMark("Сапунов"));
        gradebook.listOfStudents(6.0);
    }
}
