package hw12nikolaev.task2;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        User user1 = new User();
        User user2 = new User();

        user1.setName("Test");

        try {
            FileOutputStream file = new FileOutputStream("serialization.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(user1);
            out.close();
            file.close();
            System.out.println("Просериализирован");

        } catch (IOException ex) {
            System.out.println("IOException поймано");
        }

        try {
            FileInputStream file = new FileInputStream("serialization.ser");
            ObjectInputStream in = new ObjectInputStream(file);
            user2 = (User) in.readObject();
            in.close();
            file.close();

            System.out.println("Десериализирован ");
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }

        if (user1.equals(user2)) {
            System.out.println("Равны");
        } else System.out.println("Не равны");
    }
}
