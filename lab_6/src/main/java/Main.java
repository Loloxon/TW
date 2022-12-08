import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 1000;
        List LL = new List(new MyObject(0));
        for(int i=1;i<100000;i++){
            LL.add(new MyObject(i));
        }

        Thread[] threads = new Thread[N];
        for(int i=0;i<N;i++){
            threads[i] = new Thread(() -> {
                LL.contains(new MyObject(100000-1));
            });
        }

        long startTime = System.currentTimeMillis();
        for(int i=0;i<N;i++){
            threads[i].start();
        }
        for(int i=0;i<N;i++){
            threads[i].join();
        }
        System.out.println(System.currentTimeMillis()-startTime);


        StoopidList SLL = new StoopidList(new MyObject(0));
        for(int i=1;i<1000;i++){
            SLL.add(new MyObject(i));
        }

        for(int i=0;i<N;i++){
            threads[i] = new Thread(() -> {
                SLL.contains(new MyObject(100000-1));
            });
        }

        startTime = System.currentTimeMillis();
        for(int i=0;i<N;i++){
            threads[i].start();
        }
        for(int i=0;i<N;i++){
            threads[i].join();
        }
        System.out.println(System.currentTimeMillis()-startTime);

    }
}
