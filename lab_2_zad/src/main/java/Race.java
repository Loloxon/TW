import java.util.ArrayList;

public class Race {
    int counter;
    int M;
    int n = 5;

    public Race(){
    }

    public String start(int M, ISemafor semafor) throws InterruptedException {
        this.M = M;
        this.counter = 0;
        Thread[] incThreads = new Thread[n];
        Thread[] decThreads = new Thread[n];

        for(int k=0;k<n;k++) {
            incThreads[k] = new Thread(() -> {
                for (int i = 0; i < M; i++) {
                    try {
                        semafor.P();
                        counter++;
                        semafor.V();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        for(int k=0;k<n;k++) {
            decThreads[k] = new Thread(() -> {
                for (int i = 0; i < M; i++) {
                    try {
                        semafor.P();
                        counter--;
                        semafor.V();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for(int k=0;k<n;k++){
            incThreads[k].start();
        }
        for(int k=0;k<n;k++){
            incThreads[k].join();
        }
        for(int k=0;k<n;k++){
            decThreads[k].start();
        }
        for(int k=0;k<n;k++){
            decThreads[k].join();
        }
        System.out.println("============================");
        return semafor.getClass() + ": " + counter + "\n";
    }

//    public void startGen(int M) throws InterruptedException{
//        this.M = M;
//        SemaforGeneral semafor = new SemaforGeneral(1);
//
//        Thread incT = new Thread(() -> {
//            for (int i = 0; i < M; i++) {
//                try {
//                    semafor.V();
//                    counter++;
//                    semafor.P();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        Thread decT = new Thread(() -> {
//            for (int i = 0; i < M; i++) {
//                try {
//                    semafor.V();
//                    counter--;
//                    semafor.P();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        incT.start();
//        decT.start();
//        incT.join();
//        decT.join();
//        System.out.println("Counter: " + counter);
//    }
}
