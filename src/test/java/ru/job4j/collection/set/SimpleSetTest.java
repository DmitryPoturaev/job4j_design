package ru.job4j.collection.set;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddThenIt() {
        Set<String> set = new SimpleSet<>();
        set.add("one");
        set.add("two");
        set.add("three");
        Iterator<String> it = set.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is("one"));
        assertTrue(it.hasNext());
        assertThat(it.next(), is("two"));
        assertTrue(it.hasNext());
        assertThat(it.next(), is("three"));
        assertFalse(it.hasNext());
    }

}