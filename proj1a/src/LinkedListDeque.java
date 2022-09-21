public class LinkedListDeque<T> implements Deque<T> {

    private int size;
    private Node sentinel;
    private final T SENTINEL_VALUE = null;

    public LinkedListDeque() {
        this.size = 0;
        this.sentinel = new Node.NodeBuilder<>(SENTINEL_VALUE).build();
        this.sentinel.setNext(this.sentinel);
    }

    public int size() {return this.size;}
    public boolean isEmpty() {return this.size == 0;}

    public void addLast(T value) {
        Node last = this.sentinel.getPrev();
        Node node = new Node.NodeBuilder(value).build();

        this.sentinel.setPrev(node);
        node.setPrev(last);
        this.size += 1;
    }
    public void addFirst(T value) {
        Node first = this.sentinel.getNext();
        Node node = new Node.NodeBuilder(value).build();

        this.sentinel.setNext(node);
        node.setNext(first);

        this.size += 1;
    }

    public T removeFirst() {
        if (isEmpty()) {return null;}

        T first = (T) this.sentinel.getNext().getValue();
        Node secondNode = this.sentinel.getNext().getNext();

        this.sentinel.setNext(secondNode);
        this.size -= 1;

        return first;
    }

    public T removeLast() {
        if (isEmpty()) {return null;}

        T last = (T) this.sentinel.getPrev().getValue();
        Node secondToLastNode = this.sentinel.getPrev().getPrev();

        this.sentinel.setPrev(secondToLastNode);
        this.size -= 1;

        return last;
    }

    public T get(int i) {
        if (i < 0 || this.size() <= i) {
            System.out.printf("Index %d out of bounds", this.size());
            return null;
        }

        Node p = this.sentinel.getNext();
        for (int j = 0; j < i; j += 1) {
            p = p.getNext();
        }

        return (T) p.getValue();
    }
}
