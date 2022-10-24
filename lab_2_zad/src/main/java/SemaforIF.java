public class SemaforIF implements ISemafor{
    private boolean _stan = true;
    private int _czeka = 0;

    public SemaforIF() {
    }
    public synchronized void P(){
        try{
            if(!_stan){
                this.wait();
            }
            System.out.println("Peee");
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
            System.out.println("Viiiiii");
            _stan = true;
            this.notifyAll();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println(e);
            System.exit(-1);
        }
    }
}
