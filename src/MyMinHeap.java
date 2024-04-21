import java.util.Comparator;

public class MyMinHeap<T> {
    private MyArrayList<T> heap;
    private Comparator<T> cmp;

    public MyMinHeap(Comparator<T> cmp) {
        heap = new MyArrayList<>();
        this.cmp = cmp;
    }

    public void insert(T item) {
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public T extractMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = heap.get(0);
        int last = heap.size() - 1;
        heap.set(0, heap.get(last));
        heap.remove(last);
        siftDown(0);
        return min;
    }

    public T peekMin() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap.get(0);
    }

    public boolean isEmpty() {
        return heap.size() == 0;
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (cmp.compare(heap.get(index), heap.get(parent)) >= 0) {
                break;
            }
            swap(index, parent);
            index = parent;
        }
    }

    private void siftDown(int index) {
        int leftChild;
        while ((leftChild = 2 * index + 1) < heap.size()) {
            int rightChild = leftChild + 1;
            int minChild = (rightChild < heap.size() && cmp.compare(heap.get(rightChild), heap.get(leftChild)) < 0) ? rightChild : leftChild;
            if (cmp.compare(heap.get(index), heap.get(minChild)) <= 0) {
                break;
            }
            swap(index, minChild);
            index = minChild;
        }
    }

    private void swap(int i, int j) {
        T temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}