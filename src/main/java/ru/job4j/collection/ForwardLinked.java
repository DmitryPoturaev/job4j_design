package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    private int size = 0;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        size++;
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
     }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T value = head.value;
        if (head.next == null) {
            head = null;
        } else {
            Node<T> next = head.next;
            head.next = null;
            head = next;
        }
        size--;
        return value;
    }

    public void addFirst(T value) {
        head = new Node<>(value, head);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean revert() {
        if (size <= 1) {
            return false;
        }
        Node<T> prev = null;
        Node<T> cur = head;
        while (cur != null) {
            Node<T> next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}