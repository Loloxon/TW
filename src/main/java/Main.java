import static java.lang.Thread.sleep;

public class Main {
    static int M = 1000000;
    public static void main(String[] args) throws InterruptedException {
        for(int i=0;i<5;i++){
            zad1();
        }
    }

    private static void zad1() throws InterruptedException {
        long start = System.nanoTime();
        check();
        showTime(start);
        sleep(3000);
        start = System.nanoTime();
        checkSynchronized();
        showTime(start);
        System.out.println();
    }

    private static void check() throws InterruptedException {
        Counter counter = new Counter();
        Thread incT = new Thread(() -> {
            for (int i=0;i<M;i++) {
                counter.increment();
            }
        });
        incT.start();
        Thread decT = new Thread(() -> {
            for (int i=0;i<M;i++) {
                counter.decrement();
            }
        });
        decT.start();
        incT.join();
        decT.join();
        System.out.print("Counter: " + counter.getI() + ", ");
    }

    private static void checkSynchronized() throws InterruptedException {
        Counter counter = new Counter();
        Thread incT = new Thread(() -> {
            for (int i=0;i<M;i++) {
                counter.incrementSynch();
            }
        });
        incT.start();
        Thread decT = new Thread(() -> {
            for (int i=0;i<M;i++) {
                counter.decrementSynch();
            }
        });
        decT.start();
        incT.join();
        decT.join();
        System.out.print("Counter: " + counter.getI() + ", ");
    }
    private static void showTime(long start){
        System.out.print("Time: " + (System.nanoTime()-start) + "; ");
    }
}
