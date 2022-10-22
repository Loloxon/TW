public class SemaforIF implements ISemafor{
    private boolean _stan = true;
    private int _czeka = 0;

    public SemaforIF() {
    }
    public synchronized void P() throws InterruptedException {
        if(!_stan){
            this.wait();
        }
        System.out.println("Peee");
        _stan = false;
        this.notifyAll();
    }

    public synchronized void V() throws InterruptedException {
        if(_stan){
            this.wait();
        }
        System.out.println("Viiiiii");
        _stan = true;
        this.notifyAll();
    }
}
