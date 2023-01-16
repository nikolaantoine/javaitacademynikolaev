package hw14.task4;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        InterfaceTask4 interfaceTask4 = (String s1, String s2) -> {
            char[] chars1 = s1.toCharArray();
            char[] chars2 = s2.toCharArray();

            Arrays.sort(chars1);
            Arrays.sort(chars2);

            if (String.valueOf(chars1).contentEquals(String.valueOf(chars2))) {
                System.out.println("анаграммы");
            } else System.out.println("нет");
        };

        interfaceTask4.strings("Hello", "Helol");
        interfaceTask4.strings("Hello", "Helo");
    }

}
