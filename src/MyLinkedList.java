public class MyLinkedList<T> implements MyList<T> {
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

    }

    @Override
    public void addFirst(T item) {
        MyNode<T> newNode = new MyNode<>(item);
        newNode.next = head;
        head = newNode;
        size++;
    }

    @Override
    public void addLast(T item) {

    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getFirst() {
        return null;
    }

    @Override
    public T getLast() {
        return null;
    }

    @Override
    public void remove(int index) {

    }

    @Override
    public void removeFirst() {

    }

    @Override
    public void removeLast() {

    }

    @Override
    public void sort(Comparator<T> cmp) {

    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public void clear() {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean exists(Object object) {
        return false;
    }

    @Override
    public int lastIndexOf(Object object) {
        return 0;
    }

    @Override
    public int indexOf(Object object) {
        return 0;
    }
}
