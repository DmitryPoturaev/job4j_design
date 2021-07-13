package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

/**
 * Класс выполняет функцию универсальной обертки над массивом заданного размера.
 * Позволяет создать новый тип данных на основе массива фиксированного размера
 * и работать с ним, как с коллекцией.
 */
public class SimpleArray<T> implements Iterable<T> {
    /**
     * Массив элементов.
     */
    T[] data;

    /**
     * Количество элементов в массива.
     */
    private int size = 0;

    /**
     * Конструктор.
     * @param length размер массива.
     */
    public SimpleArray(int length) {
        this.data = (T[]) new Object[length];
    }

    /**
     * Метод возвращает текущее количество элементов в массиве.
     * @return количество элементов в массиве.
     */
    public int size() {
        return size;
    }

    /**
     * Метод добавляет элемент в первую свободную ячейку.
     * @param model добавляемый элемент.
     * @return {@code true} в случае успешного добавления.
     */
    public boolean add(T model) {
        Objects.checkIndex(size, data.length);
        data[size++] = model;
        return true;
    }

    /**
     * Метод заменяет элемент массива по указанному индексу.
     * @param index индекс элемента.
     * @param model новый элемент.
     * @return заменяемый элемент.
     */
    public T set(int index, T model) {
        Objects.checkIndex(index, size);
        T old = data[index];
        data[index] = model;
        return old;
    }

    /**
     * Метод удаляет элемент по указанному индексу.
     * После удаления выполняется сдвиг элементов влево.
     * @param index индекс удаляемого элемента.
     * @return удаляемый элемент.
     */
    public T remove(int index) {
        Objects.checkIndex(index, size);
        T old = data[index];
        size--;
        if (size > 1) {
            System.arraycopy(data, index + 1, data, index, size - index);
        }
        data[size] = null;
        return old;
    }

    /**
     * Метод возвращает элемент, расположенный по указанному индексу.
     * @param index индекс элемента.
     * @return возвращаемый элемент.
     */
    public T get(int index) {
        Objects.checkIndex(index, size);
        return data[index];
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIt();
    }

    /**
     * Внутренний класс реализует интерфейс Iterator.
     */
    class ArrayIt implements Iterator<T> {
        private int point = 0;

        @Override
        public boolean hasNext() {
            return point < size;
        }

        @Override
        public T next() {
            return data[point++];
        }
    }

    // Демонстрация работы класса SimpleArray
    public static void main(String[] args) {
        SimpleArray<Integer> array = new SimpleArray<>(10);
        // Добавим элементы в список
        array.add(1);
        array.add(2);
        array.add(null);
        array.add(4);
        array.add(5);
        System.out.println("Исходный список:");
        Iterator<Integer> it = array.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        // Изменим часть элементов
        array.set(0, 10);
        array.set(2, 11);
        array.set(4, 12);
        System.out.println("Измененный список:");
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
        }
        // Удалим часть элементов
        array.remove(0);
        array.remove(2);
        array.remove(2);
        System.out.println("Удалили часть элементов:");
        for (var i : array) {
            System.out.println(i);
        }
        System.out.println("Осталось элементов: " + array.size());
        // Удалим оставшиеся элементы
        array.remove(1);
        array.remove(0);
        System.out.println("Удалили оставшиеся, теперь их: " + array.size());
    }
}
