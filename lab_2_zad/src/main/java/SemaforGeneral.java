/*
Semafor ogólny to uogólniony przypadek semafora binarnego. Semafor binarny da się zasymulować
za pomocą semafora ogólnego poprzez ustawienie górnego limitu na wartość licznika - wartości 1.
Wówczas wartości licznika będą przyjmować jedynie wartości z zakresu [0,1], zatem będzie się
zachowywał tak samo, jak semafor binarny.
*/

public class SemaforGeneral implements ISemafor{
    private int _counter;
    private final SemaforBin accessSemafor, decreaseSemafor;

    public SemaforGeneral(int value) {
        _counter = value;
        accessSemafor = new SemaforBin(true);
        decreaseSemafor = new SemaforBin(value>0);
    }

    public void P(){
        decreaseSemafor.P();
        accessSemafor.P();
        _counter--;
        if (_counter < 0) {
            throw new RuntimeException("Counter below zero!!");
        }
        if (_counter > 0)
            decreaseSemafor.V();
        accessSemafor.V();
    }

    public void V(){
        accessSemafor.P();
        _counter++;
        decreaseSemafor.V();
        accessSemafor.V();
    }
}
