package hw11nikolaev.task1;


public class Main {
    public static void main(String[] args)  {
        SomethingElse somethingElse = new SomethingElse();
        try {
            somethingElse.toNumber("-1", "/", "8", "0");

        } catch (MyNumberFormatException | MyOwnNullPointerException | MyExceptionInInitializerError e) {
            e.printStackTrace();
        }
    }
}