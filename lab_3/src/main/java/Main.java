import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int N = 5;
        Semaphore[] semaphores = new Semaphore[N];
        for(int i=0;i<N;i++)
            semaphores[i] = new Semaphore(1);
        Thread[] threads = new Thread[N];
        for(int i=0;i<N;i++){
            int finalI = i;
            threads[i] = new Thread(() -> {
                int counter = 0, left = finalI, right = (finalI+1)%N;
                long startTime = System.currentTimeMillis();
                try {
                    while (System.currentTimeMillis() - startTime <= 60000) {
                        semaphores[left].acquire();
                        if (semaphores[right].tryAcquire()) {
                            counter++;
//                            sleep(ThreadLocalRandom.current().nextInt(4, 6));
                            sleep(100);
                            semaphores[right].release();
                        }
                        semaphores[left].release();
//                        sleep(1);
                    }
                    System.out.println("ID " + finalI + " eating for " + counter + " time");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }


        for(int i=0;i<N;i++){
            threads[i].start();
        }
        for(int i=0;i<N;i++){
            threads[i].join();
        }
    }
}
