import java.util.Iterator;
import java.util.Comparator;

public class MyArrayList<T> implements MyList<T>, Iterable<T> {
    private Object[] arr;

    private int size;

    private final static int DEFAULT_CAPACITY = 5;

    public MyArrayList() {
        arr = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int initialCapacity) {
        arr = new Object[initialCapacity];
    }

    private void increaseCapacity() {
        Object[] newArr = new Object[arr.length * 2];
        if (size >= 0) System.arraycopy(arr, 0, newArr, 0, size);
        arr = newArr;
    }

    @Override
    public void add(T item) {
        if (size == arr.length) {
            increaseCapacity();
        }
        arr[size++] = arr;
    }

    @Override
    public void add(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (size == arr.length) {
            increaseCapacity();
        }
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = item;
        size++;
    }

    @Override
    public void set(int index, T item) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        arr[index] = item;
    }

    @Override
    public void addFirst(T item) {
        add(0, item);
    }

    @Override
    public void addLast(T item) {
        add(size, item);
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        return (T) arr[index];
    }

    @Override
    public T getFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return (T) arr[0];
    }

    @Override
    public T getLast() {
        if (isEmpty()) {
            throw new IllegalStateException("List is empty");
        }
        return (T) arr[size - 1];
    }

    private boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[size - 1] = null;
            size--;
        }
    }

    @Override
    public void removeFirst() {
        remove(0);
    }

    @Override
    public void removeLast() {
        remove(size-1);
    }

    @Override
    public void sort(Comparator<T> cmp) {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (cmp.compare((T) arr[j], (T) arr[j + 1]) > 0) {
                    Object temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        for (int i = 0; i < size; i++) {
            array[i] = arr[i];
        }
        return array;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            arr[i] = null;
        }
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
        for (int i = size - 1; i >= 0; i--) {
            if (arr[i] == object) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOf(Object object) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == object) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T>, MyArrayListIterator {
        private int currentIndex;

        public MyArrayListIterator() {
            currentIndex = 0;
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more elements in the list");
            }
            return (T) arr[currentIndex++];
        }
    }
}
