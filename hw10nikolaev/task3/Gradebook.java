package hw10nikolaev.task3;

import java.util.*;

public class Gradebook {

    public Gradebook() {
        map.put("Мишалкин", new HashMap<>());
        map.put("Крестьянов", new HashMap<>());
        map.put("Сапунов", new HashMap<>());
    }


    static Map<String, Map<Integer, Integer>> map = new HashMap<>(); // фамилия и числа


    public void grading(String surname, int lessonNumber, int mark) {
        map.get(surname).put(lessonNumber, mark);
    }

    public Integer showMark(String surname, int lessonNumber) {
        if (map.containsKey(surname) && map.get(surname).containsKey(lessonNumber)) {
            return map.get(surname).get(lessonNumber);
        }
        return null;
    }

    public Double averageMark(String surname) {
        int amount = 0;
        double sum = 0.0;
        for (Integer k : map.get(surname).values()
        ) {
            sum = sum + k;
            amount++;
        }
        return sum / amount;
    }

    public void listOfStudents(double a) {
        Set<Map.Entry<String, Map<Integer, Integer>>> set = map.entrySet();
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Map<Integer, Integer>> fill : set) {
            Double sum = 0.0;
            int amount = 0;
            double average = 0.0;
            for (Integer k : fill.getValue().values()) {
                sum += k;
                amount++;
                average = sum / amount;
            }
            if (average >= a) {
                list.add(fill.getKey());
            }
        }
        System.out.println(list);
    }
}
