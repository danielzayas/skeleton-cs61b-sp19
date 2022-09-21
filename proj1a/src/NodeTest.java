import static org.junit.Assert.*;
import org.junit.Test;

public class NodeTest {
    @Test
    public void TestNodeBuilder() {
        Node node1 = new Node.NodeBuilder(1).build();
        assertEquals(1, node1.getValue());

        Node node2 = new Node.NodeBuilder(2).prev(node1).build();
        assertEquals(1, node2.getPrev().getValue());
        assertEquals(2, node1.getNext().getValue());
    }

    @Test
    public void TestNodeSetter() {
        Node node1 = new Node.NodeBuilder(1).build();
        Node node2 = new Node.NodeBuilder(2).build();
        Node node3 = new Node.NodeBuilder(3).build();

        node1.setNext(node2);
        node2.setNext(node3);

        assertEquals(1, node3.getPrev().getPrev().getValue());
        assertEquals(3, node1.getNext().getNext().getValue());
    }
}
