package hw11nikolaev.task2;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
            throws InputMismatchException, MyExceptionLength {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        int guess = random.nextInt(100) + 1;

        System.out.println("Введите число");

        while (true) {
            try {
                int input = scanner.nextInt();

                if (input < 1 || input > 100) {
                    throw new MyExceptionLength("Введите число от 1 до 100");
                }

                if (input > guess) {
                    System.out.println("Введи поменьше");
                } else if (input < guess) {
                    System.out.println("Введи побольше");
                } else {
                    System.out.println("Вы угадали");
                    break;
                }

            } catch (InputMismatchException exception) {
                throw new InputMismatchException("Неверный формат Integer"); // 242134.2413412
            }
        }
    }
}
