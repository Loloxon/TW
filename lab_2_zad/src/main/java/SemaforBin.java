class SemaforBin implements ISemafor{
    private boolean _stan = true;
    private int _czeka = 0;

    public SemaforBin() {
    }
/*
    nie moża użyć w tym miejscu funkcji IF, ponieważ wtedy, jeśli kilka wątków próbuje
    na raz uzyskać dostęp do semafora i się blokuje, to po odblokowaniu wszystkie wyszły by
    z IF'a i poszły dalej. Uzycie pętki WHILE zapewnia, że na raz tylko jeden wątek będzie na raz
    korzystał z zasobu semafora
*/
    public synchronized void P() throws InterruptedException {
        while(!_stan){
            this.wait();
        }
        System.out.println("Peee");
        _stan = false;
        this.notifyAll();
    }

    public synchronized void V() throws InterruptedException {
        while(_stan){
            this.wait();
        }
        System.out.println("Viiiiii");
        _stan = true;
        this.notifyAll();
    }
}