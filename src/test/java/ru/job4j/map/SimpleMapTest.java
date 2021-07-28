package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenAddThenGet() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "first"));
        String rsl = map.get(1);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddDuplicateKey() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertTrue(map.put(1, "first"));
        assertFalse(map.put(1, "second"));
    }

    @Test
    public void whenGetEmpty() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertNull(map.get(1));
    }

    @Test
    public void whenAddThenRemove() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        assertTrue(map.remove(1));
        assertNull(map.get(1));
    }

    @Test
    public void whenRemoveEmpty() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        assertFalse(map.remove(1));
    }

    @Test
    public void whenAddThenIt() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        map.put(2, "second");
        map.put(3, "third");
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertThat(it.next(), is(1));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(2));
        assertTrue(it.hasNext());
        assertThat(it.next(), is(3));
        assertFalse(it.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "first");
        Iterator<Integer> it = map.iterator();
        map.put(2, "second");
        it.next();
    }

    @Test
    public void whenExpandMap() {
        SimpleMap<Integer, Integer> map = new SimpleMap<>();
        assertTrue(map.put(1, 10));
        assertTrue(map.put(2, 20));
        assertTrue(map.put(3, 30));
        assertTrue(map.put(4, 40));
        assertTrue(map.put(5, 50));
        assertTrue(map.put(6, 60));
        assertTrue(map.put(7, 70));
        assertTrue(map.put(8, 80));
        assertTrue(map.put(9, 90));
        assertTrue(map.put(10, 100));
        assertThat(map.get(1), is(10));
        assertThat(map.get(2), is(20));
        assertThat(map.get(3), is(30));
        assertThat(map.get(4), is(40));
        assertThat(map.get(5), is(50));
        assertThat(map.get(6), is(60));
        assertThat(map.get(7), is(70));
        assertThat(map.get(8), is(80));
        assertThat(map.get(9), is(90));
        assertThat(map.get(10), is(100));
    }
}