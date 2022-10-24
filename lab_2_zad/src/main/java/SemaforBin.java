class SemaforBin implements ISemafor{
    private boolean _stan;

    public SemaforBin(boolean state) {
        _stan = state;
    }
    public synchronized void P(){
        try{
            while(!_stan){
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
            while(_stan){
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