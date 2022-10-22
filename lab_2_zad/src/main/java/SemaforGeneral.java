/*
Semafor ogólny to uogólniony przypadek semafora binarnego. Semafor binarny da się zasymulować
za pomocą semafora ogólnego poprzez ustawienie górnego limitu na wartość licznika - wartości 1.
Wówczas wartości licznika będą przyjmować jeydnie wartości z zakresu [0,1], zatem będzie się
zachowywał tak samo, jak semafor binarny.
*/

public class SemaforGeneral implements ISemafor{
    private int _counter = 0;
    private SemaforBin semafor;

    public SemaforGeneral(int value) {
        _counter = value;
        semafor = new SemaforBin();
    }

    public synchronized void P() throws InterruptedException {
        semafor.P();
        System.out.println("    Increased! +++++");
        _counter++;
        semafor.V();
    }

    public synchronized void V() throws InterruptedException {
        while(_counter==0){
            this.wait();
        }
        semafor.P();
        if(_counter<=0){
            throw new RuntimeException("Counter below zero!!");
        }
        System.out.println("    Decreased!  ---");
        _counter--;
        this.notifyAll();
        semafor.V();
    }
}
