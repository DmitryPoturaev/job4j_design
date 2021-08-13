package ru.job4j.io;

import java.io.*;

public class Analizy {
    private boolean badStatus;

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            badStatus = false;
            read.lines()
                    .map(l -> l.split(" "))
                    .filter(
                            a -> a.length == 2
                            && (!badStatus && Integer.parseInt(a[0]) > 300
                            || badStatus && Integer.parseInt(a[0]) < 400)
                    )
                    .forEach(a -> {
                        badStatus = !badStatus;
                        out.printf("%s;%s", a[1], badStatus ? "" : System.lineSeparator());
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "./data/analizy/server.log";
        String target = "./data/analizy/unavailable.csv";
        new Analizy().unavailable(source, target);
        try (BufferedReader read = new BufferedReader(new FileReader(target))) {
            read.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}