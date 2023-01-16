package hw14.task5;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int amountOfPoint;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество точек ");
        amountOfPoint = scanner.nextInt();
        if (amountOfPoint < 2) try {
            throw new Exception("Мало точек");
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Integer> integerList = new ArrayList<>();
        List<Point> pointList = new ArrayList<>();

        System.out.println("Введите точки");
        for (int i = 0; i < amountOfPoint * 2; i++) {
            integerList.add(scanner.nextInt());
        }

        int count = 0;
        int count2 = 0;
        while (count != amountOfPoint) {
            pointList.add(new Point(integerList.get(count2), integerList.get(count2 + 1)));
            count++;
            count2 += 2;
        }
        pointList.sort(Comparator.comparingInt(num -> num.getX() * num.getX() + num.getY() * num.getY()));
        for (Point p : pointList) {
            System.out.println(p);
        }
    }
}