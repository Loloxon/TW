package parallelism;

import production.IProduction;
import production.PDrawer;

public interface BlockRunner {

    //starts all threads
    public void startAll();

    //adds a thread to poll
    public void addThread(IProduction pThread);
}
