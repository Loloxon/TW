class Producer extends Thread {
    private Buffer _buf;
    private int ID;

    public Producer(Buffer _buf, int ID) {
        this._buf = _buf;
        this.ID = ID;
        _buf.addProducer(this);
    }

    public void run() {
        for (int i = 0; i < 10; ++i) {
            try {
//                while(_buf.isFull()) {
//                    this.wait();
//                }
                _buf.put(i);
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}