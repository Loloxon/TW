public class Counter{
    private int i=0;

    public int getI() {
        return i;
    }
    void increment(){
        i++;
    }
    void decrement(){
        i--;
    }
    synchronized void incrementSynch(){
        i++;
    }
    synchronized void decrementSynch(){
        i--;
    }
}
