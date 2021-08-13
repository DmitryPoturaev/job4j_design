package ru.job4j.io;

import java.io.File;
import java.util.Arrays;

public class Dir {
    public static void main(String[] args) {
        File file = new File("c:\\projects");
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Not exist %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("Not directory %s", file.getAbsoluteFile()));
        }
        Arrays.stream(file.listFiles())
                .filter(File::isDirectory)
                .flatMap(
                        subDir -> Arrays.stream(subDir.listFiles())
                        .filter(File::isFile)
                )
                .forEach(f -> System.out.println(f.getName() + ", size: " + f.length()));
    }
}
