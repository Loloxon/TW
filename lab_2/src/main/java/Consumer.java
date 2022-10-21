class Consumer extends Thread {
    private Buffer _buf;
    private int ID;

    public Consumer(Buffer _buf, int ID) {
        this._buf = _buf;
        this.ID = ID;
        _buf.addCustomer(this);
    }

    public void run() {
        for (int i = 0; i < 10; ++i) {
            try {
//                while(_buf.isEmpty()){
//                    this.wait();
//                }
                System.out.println("Im consumer " + ID + " reading no. " + _buf.get());
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}