package ru.job4j.io;

import java.io.FileInputStream;

public class EvenNumberFile {
    public static void main(String[] args) {
        String[] numbers = {};
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            numbers = text.toString().split(System.lineSeparator());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (var s : numbers) {
            int i = Integer.parseInt(s);
            System.out.print("Число " + i + " - ");
            if (i % 2 == 0) {
                System.out.println("четное");
            } else {
                System.out.println("нечетное");
            }
        }
    }
}
