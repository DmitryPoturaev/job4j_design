package ru.job4j.collection;

import java.util.*;

/**
 * Класс реализует простой динамический список на массиве.
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     * Размер массива по умолчанию.
     */
    static final int DEFAULT_CAPACITY = 10;

    /**
     * Массив элементов.
     */
    private Object[] container;

    /**
     * Счетчик изменений массива.
     */
    private int modCount;

    /**
     * Количество элементов в массиве.
     */
    private int size = 0;

    /**
     * Конструктор по умолчанию.
     * Создает массив длиной 10 элементов.
     */
    public SimpleArray() {
        this.container = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Конструктор создает массив заданного размера.
     * @param initialCapacity размер массива.
     */
    public SimpleArray(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: "
                    + initialCapacity);
        }
        this.container = new Object[initialCapacity];
    }

    /**
     * Метод возвращает текущее количество элементов в массиве.
     * @return количество элементов в массиве.
     */
    public int size() {
        return size;
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     * @param index индекс элемента.
     * @return возвращаемый элемент.
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) container[index];
    }

    /**
     * Метод добавляет элемент в первую свободную ячейку.
     * В текущей простой реализации принято:
     * - в случае полного заполнения массива создается новый массив с длиной
     * на 10 элементов больше текущего;
     * - переполнение значения int не проверяется.
     * @param model добавляемый элемент.
     */
    public void add(T model) {
        if (size == container.length) {
            container = Arrays.copyOf(container, size + DEFAULT_CAPACITY);
        }
        container[size++] = model;
        modCount++;
    }

    /**
     * Метод заменяет элемент массива по указанному индексу.
     * @param index индекс элемента.
     * @param model новый элемент.
     * @return заменяемый элемент.
     */
    public T set(int index, T model) {
        Objects.checkIndex(index, size);
        Object old = container[index];
        container[index] = model;
        modCount++;
        return (T) old;
    }

    /**
     * Метод удаляет элемент по указанному индексу.
     * После удаления выполняется сдвиг элементов влево.
     * @param index индекс удаляемого элемента.
     * @return удаляемый элемент.
     */
    public T remove(int index) {
        Objects.checkIndex(index, size);
        Object old = container[index];
        size--;
        if (size > 1) {
            System.arraycopy(container, index + 1, container, index, size - index);
        }
        container[size] = null;
        modCount++;
        return (T) old;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIt();
    }

    /**
     * Внутренний класс реализует интерфейс Iterator.
     */
    private class ArrayIt implements Iterator<T> {
        int point = 0;
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return point < size;
        }

        @Override
        public T next() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
            if (point >= size) {
                throw new NoSuchElementException();
            }
            return (T) container[point++];
        }
    }
}
