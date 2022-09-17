class Node<T> {
    private T value;
    private Node next;
    private Node prev;

    protected static class NodeBuilder<T> {
        // required params
        private T value;

        // optional params
        private Node next = null;
        private Node prev = null;

        NodeBuilder(T val) {this.value = val;}

        NodeBuilder next(Node node) {
            this.next = node;
            return this;
        }
        NodeBuilder prev(Node node) {
            this.prev = node;
            return this;
        }

        Node build() {
            return new Node(this);
        }
    }

    /* Private constructor uses the builder pattern */
    private Node(NodeBuilder<T> nodeBuilder) {
        this.value = nodeBuilder.value;

        this.setNext(nodeBuilder.next);
        this.setPrev(nodeBuilder.prev);
    }

    /* accessor methods */
    public T getValue() {return this.value;}
    public Node getPrev() {return this.prev;}
    public Node getNext() {return this.next;}
    protected void setValue(T val) {this.value = val;}
    protected void setPrev(Node node) {
        this.prev = node;
        if (node != null && node.next != this) {
            node.setNext(this);
        }
    }
    protected void setNext(Node node) {
        this.next = node;
        if (node != null && node.prev != this) {
            node.setPrev(this);
        }
    }
}
