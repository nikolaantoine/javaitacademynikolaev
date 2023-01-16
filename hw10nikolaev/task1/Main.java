package hw10nikolaev.task1;

public class Main {
    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.addPhone("Вася", 7522232);
        phoneBook.addPhone("Петя", 8653682);
        phoneBook.addPhone("Иван", 7252621);


        System.out.println(phoneBook.info);

        System.out.println(phoneBook.getPhoneByName("Вася"));

        System.out.println(phoneBook.getPhoneByName("Назар"));

        phoneBook.addPhone("Вася", 3427816);

        System.out.println(phoneBook.getPhoneByName("Вася"));
    }
}
