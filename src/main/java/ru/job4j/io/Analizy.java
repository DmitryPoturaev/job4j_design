package ru.job4j.io;

import java.io.*;

public class Analizy {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source));
             PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            boolean badStatus = false;
            String line;
            while ((line = read.readLine()) != null) {
                String[] lines = line.split(" ");
                if (lines.length == 2) {
                    int status = Integer.parseInt(lines[0]);
                    if (!badStatus && status > 300 || badStatus && status < 400) {
                        badStatus = !badStatus;
                        out.printf("%s;%s", lines[1], badStatus ? "" : System.lineSeparator());
                    }
                }
            }
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