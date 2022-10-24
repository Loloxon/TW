public class SemaforIF implements ISemafor{
    private boolean _stan = true;

    public SemaforIF(boolean state) {
        _stan = state;
    }
/*
    Nie moża użyć w tym miejscu funkcji IF, ponieważ wtedy, jeśli kilka wątków próbuje
    na raz uzyskać dostęp do semafora i się wzajemnie blokuje, to po odblokowaniu wszystkie wyszły by
    z IF'a i poszły dalej lub też blokowały by się wzajemnie i zawieszały program. Uzycie pętki WHILE
    zapewnia, że na raz tylko jeden wątek będzie na raz korzystał z zasobu semafora.

    W obecnym programie objawia się to poprzez nachodzące na siebie instrukcje wypisania lub też zawieszenie programu.
*/
    public synchronized void P(){
        try{
            if(!_stan){
                this.wait();
            }
            _stan = false;
            this.notifyAll();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
            System.exit(-1);
        }
    }

    public synchronized void V(){
        try{
            if(_stan){
                this.wait();
            }
            _stan = true;
            this.notifyAll();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
            System.exit(-1);
        }
    }
}
