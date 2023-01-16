package hw14.task6;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        try {
            UnaryOperator<Integer> unaryOperator = Math::abs;
            System.out.println(unaryOperator.apply(null));
        } catch (NullPointerException nullPointerException) {
            System.out.println("Введено нулл значение " + nullPointerException.getMessage());
        }

        try {
            BinaryOperator<Integer> binaryOperator = (integer, integer2) -> integer * integer2;
            System.out.println(binaryOperator.apply(12, 13));
        } catch (NullPointerException nullPointerException) {
            System.out.println("Введено нулл значение " + nullPointerException.getMessage());
        }

        try {
            Consumer <Integer> consumer = System.out::println;
            consumer.accept(24);
        } catch (NullPointerException nullPointerException) {
            System.out.println("Введено нулл значение " + nullPointerException.getMessage());
        }

        try {
            Predicate <Integer> predicate = (s) -> s / 2 == 2;
            System.out.println(predicate.test( 4));
        } catch (NullPointerException nullPointerException) {
            System.out.println("Введено нулл значение " + nullPointerException.getMessage());
        }
    }
}
