import java.util.ArrayList;

public class PKmon {
    public static void main(String[] args) throws InterruptedException {
        int n = 5;
        int m = 5;

        Buffer buffer = new Buffer();
        ArrayList<Producer> producers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();
        for(int i=0;i<n;i++) {
            producers.add(new Producer(buffer, i));
        }
        for(int i=0;i<m;i++) {
            consumers.add(new Consumer(buffer, i));
        }

        for(Producer producer:producers){
            producer.start();
        }
        for(Consumer consumer:consumers){
            consumer.start();
        }

        for(Producer producer:producers){
            producer.join();
        }
        for(Consumer consumer:consumers){
            consumer.join();
        }
    }
}
