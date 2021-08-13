package ru.job4j.io;

import org.junit.Test;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("first"), is("Gagarin"));
        assertThat(config.value("second"), is(Matchers.nullValue()));
    }

    @Test
    public void whenTwoPairWithComment() {
        String path = "./data/two_pair_with_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("first"), is("Gagarin"));
        assertThat(config.value("second"), is("Titov"));
    }

    @Test
    public void whenEmptyLines() {
        String path = "./data/empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("first"), is("Gagarin"));
        assertThat(config.value("second"), is("Titov"));
        assertThat(config.value("third"), is("Nikolaev"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenFirstFieldEmpty() {
        String path = "./data/first_field_empty.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenSecondFieldEmpty() {
        String path = "./data/second_field_empty.properties";
        Config config = new Config(path);
        config.load();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenPairWithoutDelimiter() {
        String path = "./data/pair_without_delimiter.properties";
        Config config = new Config(path);
        config.load();
    }

}