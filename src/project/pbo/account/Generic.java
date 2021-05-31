package project.pbo.account;

import java.io.Serializable;

public class Generic <T> implements Serializable {
    private Node<T> header = null;
    private int idx = 0;

    public void add(T value) {
        if (header == null) header = new Node<>(value, idx);
        else {
            Node<T> temp = header;
            while (temp.getNext() != null) temp = temp.getNext();
            temp.setNext(new Node<>(value, idx));
        }
        idx++;
    }

    public T get(int idx) {
        Node<T> temp = header;
        while (temp.getIdx() != idx) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    public void set(int idx, T value){
        Node<T> temp = header;
        while (temp.getIdx() != idx) temp = temp.getNext();
        temp.setValue(value);
    }

    public int getIdx() {
        return idx;
    }
}
