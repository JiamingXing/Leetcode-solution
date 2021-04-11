package amazon;

import java.util.Iterator;

public class CustomLinkedList<T> implements Iterable<T> {
    class Node<T> {
        T data;
        Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    Node<T> head, tail;

    public void add(T data) {
        Node<T> node = new Node<>(data, null);
        if (head == null) {
            tail = head = node;
        } else {
            tail.setNext(node);
            tail = node;
        }
    }

    public Node<T> getHead() {
        return this.head;
    }

    public Node<T> getTail() {
        return this.tail;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator<>(this);
    }

    class ListIterator<T> implements  Iterator<T> {
        CustomLinkedList<T>.Node<T> cursor;

        public ListIterator(CustomLinkedList<T> list) {
            this.cursor = list.getHead();
        }

        @Override
        public boolean hasNext() {
            return this.cursor == null;
        }

        @Override
        public T next() {
            T data = this.cursor.getData();
            this.cursor = this.cursor.getNext();
            return data;
        }
    }
}
