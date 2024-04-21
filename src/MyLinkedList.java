import java.util.Iterator;

public class MyLinkedList<T> implements MyList<T>, Iterable<T> {
    private MyNode<T> head;
    private MyNode<T> tail;
    private int size;

    private class MyNode<T> {
        T data;
        MyNode<T> next;

        MyNode(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        MyNode<T> newNode = new MyNode<>(item);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        MyNode<T> newNode = new MyNode<>(item);

        if (head == null) {
            head = newNode;
        } else {
            MyNode<T> curr = head;

            while(curr.next != null) {
                curr = curr.next;
            }

            curr.next = newNode;
        }

        size++;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        MyNode<T> curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        curr.data = item;
    }

    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        newNode.next = head;
        head = newNode;
        if (tail == null) {
            tail = newNode;
        }
        size++;
    }

    @Override
    public void addLast(T item) {
        MyNode<T> newNode = new MyNode<>(item);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        MyNode<T> curr = head;

        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }

        return curr.data;
    }

    @Override
    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        return head.data;
    }

    @Override
    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        return tail.data;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        if (index == 0) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else {
            MyNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.next;
            }
            curr.next = curr.next.next;
            if (curr.next == null) {
                tail = curr;
            }
        }

        size--;
    }

    @Override
    public void removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
        size--;
    }

    @Override
    public void removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }

        if (head == tail) {
            head = null;
            tail = null;
        } else {
            MyNode<T> curr = head;
            while (curr.next != tail) {
                curr = curr.next;
            }
            curr.next = null;
            tail = curr;
        }

        size--;
    }

    @Override
    public void sort(Comparator<T> cmp) {
        if (size > 1) {
            T[] array = (T[]) toArray();
            arraySort(array, cmp);
            clear();
            for (T item : array) {
                addLast(item);
            }
        }
    }

    private void arraySort(T[] array, Comparator<T> cmp) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (cmp.compare(array[i], array[j]) > 0) {
                    T temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        MyNode<T> curr = head;
        int index = 0;
        while (curr != null) {
            array[index++] = curr.data;
            curr = curr.next;
        }
        return array;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean exists(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public int lastIndexOf(Object object) {
        MyNode<T> curr = head;
        int lastIndex = -1;
        int index = 0;
        while (curr != null) {
            if (curr.data == object) {
                lastIndex = index;
            }
            curr = curr.next;
            index++;
        }
        return lastIndex;
    }

    @Override
    public int indexOf(Object object) {
        MyNode<T> curr = head;
        int index = 0;
        while (curr != null) {
            if (curr.data == object) {
                return index;
            }
            curr = curr.next;
            index++;
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyLinkedListIterator();
    }

    private class MyLinkedListIterator implements Iterator<T>, MyLinkedListIterator {
        private MyNode<T> curr;

        public MyLinkedListIterator() {
            curr = head;
        }

        public boolean hasNext() {
            return curr != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list");
            }
            T data = curr.data;
            curr = curr.next;
            return data;
        }
    }
}
