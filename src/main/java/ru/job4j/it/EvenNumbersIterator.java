package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс последовательно возвращает четные числа из произвольного списка целочисленных значений.
 */
public class EvenNumbersIterator implements Iterator<Integer> {
    private final int[] data;
    private int point = 0;

    public EvenNumbersIterator(int[] data) {
        this.data = data;
    }

    /**
     * Метод выполняет проверку наличия в списке следующего четного числа.
     * @return {@code true} если есть следующее четное число, иначе {@code false}.
     */
    @Override
    public boolean hasNext() {
        while (point < data.length && data[point] % 2 != 0) {
            point++;
        }
        return point < data.length;
    }

    /**
     * Метод возвращает следующее четное число из списка.
     * Если четных чисел в списке нет, то генерируется исключение NoSuchElementException.
     * @return следующее в списке четное число.
     */
    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
