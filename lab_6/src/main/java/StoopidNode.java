import java.util.concurrent.Semaphore;

public class StoopidNode {
    private MyObject object;
    private StoopidNode nextStoopidNode;

    public StoopidNode(MyObject o, StoopidNode nextStoopidNode){
        this.object = o;
        this.nextStoopidNode = nextStoopidNode;
    }
    public void setNext(StoopidNode StoopidNode){
        nextStoopidNode = StoopidNode;
    }
    public StoopidNode getNext(){
        return this.nextStoopidNode;
    }
    public MyObject getObject(){
        return this.object;
    }

}
