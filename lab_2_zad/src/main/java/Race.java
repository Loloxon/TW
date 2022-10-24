public class Race {
    int counter;
    int M;
    int semaforNo;

    public Race(){
    }

    public String start(int M, int semaforNo, ISemafor semafor) throws InterruptedException {
        this.M = M;
        this.semaforNo = semaforNo;
        this.counter = 0;
        Thread[] incThreads = new Thread[semaforNo];
        Thread[] decThreads = new Thread[semaforNo];

        for(int k=0;k<semaforNo;k++) {
            int finalK = k;
            incThreads[k] = new Thread(() -> {
                for (int i = 0; i < M; i++) {
                    try {
                        semafor.P();
                        if(i%(M/10)==0) {
                            System.out.print("ID");
                            for(int tmp = 0; tmp <= finalK; tmp++)
                                System.out.print("  ");
                            System.out.println(finalK + ": increment iteration: " + i*10/M);
                        }
                        counter++;
                        semafor.V();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        for(int k=0;k<semaforNo;k++) {
            int finalK = k;
            decThreads[k] = new Thread(() -> {
                for (int i = 0; i < M; i++) {
                    try {
                        semafor.P();
                        if(i%(M/10)==0) {
                            System.out.print("  ID");
                            for(int tmp = 0; tmp <= finalK; tmp++)
                                System.out.print("  ");
                            System.out.println(finalK + ": decrement iteration: " + i*10/M);
                        }
                        counter--;
                        semafor.V();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        for(int k=0;k<semaforNo;k++){
            incThreads[k].start();
        }
        for(int k=0;k<semaforNo;k++){
            decThreads[k].start();
        }
        for(int k=0;k<semaforNo;k++){
            incThreads[k].join();
        }
        for(int k=0;k<semaforNo;k++){
            decThreads[k].join();
        }
        System.out.println("============================");
        return semafor.getClass() + ": " + counter + "\n";
    }

}
