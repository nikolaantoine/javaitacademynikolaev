package Controlling.task3;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Car> list = List.of(
                new Car("Audi", Car.CarModel.AUTO, new Random().nextInt(3) + 1),
                new Car("BMW", Car.CarModel.AUTO, new Random().nextInt(3) + 1),
                new Car("MAN", Car.CarModel.TRUCK, new Random().nextInt(3) + 1),
                new Car("Mers", Car.CarModel.AUTO, new Random().nextInt(3) + 1),
                new Car("Suzuki", Car.CarModel.TRUCK, new Random().nextInt(3) + 1),
                new Car("DAF", Car.CarModel.TRUCK, new Random().nextInt(3) + 1),
                new Car("Lexus", Car.CarModel.AUTO, new Random().nextInt(3) + 1),
                new Car("Range", Car.CarModel.BUS, new Random().nextInt(3) + 1),
                new Car("Mix", Car.CarModel.BUS, new Random().nextInt(3) + 1),
                new Car("BMW", Car.CarModel.BUS, new Random().nextInt(3) + 1)
        );

        Map<Integer, List<Car>> map = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите произвольное число");
        int number = scanner.nextInt();
        for (Car car : list) {
            map.put(car.getEngineCapacity(), list);
            if (car.getEngineCapacity() == number) {
                FileWriter fileWriter;
                    fileWriter = new FileWriter("test.txt", true);
                    fileWriter.write(car.getName() + " : " + car.getModel() + " : " + car.getEngineCapacity() + "\n");
                    fileWriter.close();
                }
            }
        }
}
