package hw12nikolaev.task1;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        String dirPath = "C://Users//Admin//IdeaProjects//untitled2//src//hw12nikolaev//task1";
        String newFileName = "file1.txt";
        int i; //для подсчета элементов
        Scanner scanner = new Scanner(System.in); //для ввода текста в файл

        File folder = new File(dirPath);
        boolean isCreated = folder.isDirectory();

        if (isCreated) {
            System.out.printf("1. Директорий создан ",
                    folder.getCanonicalPath());
        } else if (folder.exists()) {
            System.out.printf("1.Директорий уже есть ",
                    folder.getCanonicalPath());
        } else {
            System.out.println("1. Директорий не найден");
        }

        File newFile = new File(dirPath + File.separator + newFileName);
        isCreated = newFile.createNewFile();
        if (isCreated) {
            System.out.printf("\n2. Файл создан",
                    newFile.getCanonicalPath());
        } else {
            System.out.print("\n2. Файл не создан");
        }


        FileReader fileReader = new FileReader(dirPath + File.separator + newFileName);
        PrintWriter printWriter = new PrintWriter(dirPath + File.separator + newFileName);
        System.out.println("\nНапишите текст для файла");
        printWriter.print(scanner.nextLine());
        for (File files : folder.listFiles()) {
            printWriter.println(files);
        }
        printWriter.close();

        //2
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line != null) {
            System.out.println(line);
            line = bufferedReader.readLine();
        }

        //3
        try (FileReader reader = new FileReader(dirPath + File.separator + newFileName)) {
            int count = 0;
            do {
                i = reader.read();
                char c = (char) i;
                if (i == -1) {
                    break;
                }
                if (c == 'а') {
                    count++;
                }
            } while (true);

            System.out.println("Количество символов а : " + count);
            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //4
        Files.delete(Paths.get(dirPath + File.separator + newFileName));
    }
}