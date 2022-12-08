import java.util.concurrent.Semaphore;

public class Node {
    private MyObject object;
    private Node nextNode;
    Semaphore lock;

    public Node(MyObject o, Node nextNode) {
        this.object = o;
        this.nextNode = nextNode;
        lock = new Semaphore(1);
    }

    public void setNext(Node node) {
        nextNode = node;
    }

    public Node getNext() {
        return this.nextNode;
    }

    public MyObject getObject() {
        return this.object;
    }


}
