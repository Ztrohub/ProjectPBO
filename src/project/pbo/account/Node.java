package project.pbo.account;

import java.io.Serializable;

public class Node <T> implements Serializable {
    private T value;
    private int idx;
    private Node<T> next = null;

    public Node(T value, int idx) {
        this.value = value;
        this.idx = idx;
    }

    public int getIdx() {
        return idx;
    }

    public void setIdx(int idx) {
        this.idx = idx;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
