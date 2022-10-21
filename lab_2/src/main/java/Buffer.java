import java.beans.Customizer;
import java.util.ArrayList;
import java.util.Stack;

class Buffer {
    private final Stack<Integer> values = new Stack<>();
    private final ArrayList<Producer> producers = new ArrayList<>();
    private final ArrayList<Consumer> consumers = new ArrayList<>();
    private final int stackSize = 2;

    public synchronized void put(int i) throws InterruptedException {
        while(values.size()==stackSize){
            wait();
        }
        values.add(i);
        notifyAll();
        if(values.size()>stackSize){
            throw new RuntimeException("Stack Overflow!!!!!!!");
        }
//        if(values.size()==stackSize){
//            for(Producer producer:producers){
//                producer.wait();
//            }
//        }
//        if(values.size()==0){
//            for(Consumer consumer:consumers){
//                consumer.notify();
//            }
//        }
    }

    public int get() throws InterruptedException {
        while(values.size()==0){
            wait();
        }
        int tmp = values.pop();
        notifyAll();
//        if(values.size()==0){
//            for(Consumer consumer:consumers){
//                consumer.wait();
//            }

//        }
//        if(values.size()==stackSize){
//            for(Producer producer:producers){
//                producer.notify();
//            }
//        }
        return tmp;
    }
    public boolean isEmpty(){
        return values.isEmpty();
    }
    public boolean isFull(){
        return values.size()==stackSize-1;
    }

    public void addProducer(Producer producer){
        producers.add(producer);
    }
    public void addCustomer(Consumer consumer){
        consumers.add(consumer);
    }
}