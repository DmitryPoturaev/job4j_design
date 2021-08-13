package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() throws IllegalArgumentException {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            values = read.lines()
                    .map(String::strip)
                    .filter(s -> {
                        if (s.isEmpty() || s.startsWith("#")) {
                            return false;
                        } else if (!s.contains("=") || s.startsWith("=") || s.endsWith("=")) {
                            throw new IllegalArgumentException();
                        }
                        return true;
                    })
                    .collect(Collectors.toMap(
                            s -> s.substring(0, s.indexOf("=")), s -> s.substring(s.indexOf("=") + 1))
                    );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config config = new Config("app.properties");
        System.out.println(config);
        try {
            config.load();
        } catch (IllegalArgumentException e) {
            System.out.println("format error");
        }
        System.out.println();
        for (var entry : config.values.entrySet()) {
            System.out.println("key: " + entry.getKey() + "; value: " + entry.getValue());
        }
    }
}
