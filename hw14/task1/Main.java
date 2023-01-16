package hw14.task1;

public class Main {
    public static void main(String[] args) {
        StringInterface stringInterface = (String s1, String s2) -> s1.length() > s2.length() ? s1 : s2;
        String result = stringInterface.strings ("Anton", "Somebody");
        System.out.println(result);
    }
}
