package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 3));
        ListUtils.addAfter(input, 1, 2);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }

    @Test
    public void whenAddAfterFirst() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 2, 3));
        ListUtils.addAfter(input, 0, 1);
        assertThat(Arrays.asList(0, 1, 2, 3), Is.is(input));
    }


    @Test
    public void whenRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        ListUtils.removeIf(input, i -> i % 2 == 0);
        assertThat(Arrays.asList(1, 3, 5, 7, 9), Is.is(input));
    }

    @Test
    public void whenReplaceIf() {
        List<String> input = new ArrayList<>(Arrays.asList("A", "", "B", "", "C", ""));
        ListUtils.replaceIf(input, String::isEmpty, "X");
        assertThat(Arrays.asList("A", "X", "B", "X", "C", "X"), Is.is(input));
    }

    @Test
    public void whenRemoveAll() {
        List<String> input = new ArrayList<>(Arrays.asList("A", "X", "B", "Y", "C", "Z"));
        List<String> remove = new ArrayList<>(Arrays.asList("X", "Y", "Z"));
        ListUtils.removeAll(input, remove);
        assertThat(Arrays.asList("A", "B", "C"), Is.is(input));
    }
}