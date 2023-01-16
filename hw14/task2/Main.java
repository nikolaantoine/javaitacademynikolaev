package hw14.task2;

public class Main {
    public static void main(String[] args) {
        InterfaceRealise interfaceRealise = (int a, int b, int c) -> {
            int max = a;
            if (max < b) max = b;
            if (max < c) max = c;
            return max;
        };
        int result = interfaceRealise.numbers(5, 4, 3);
        System.out.println(result);
    }
}

