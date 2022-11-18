import myProductions.*;
import mesh.Vertex;
import mesh.GraphDrawer;
import parallelism.BlockRunner;
import production.PDrawer;

public class Executor extends Thread {
    
    private final BlockRunner runner;
    
    public Executor(BlockRunner _runner){
        this.runner = _runner;
    }

    @Override
    public void run() {

        PDrawer<Vertex> drawer = new GraphDrawer();
        //axiom
        Vertex s = new Vertex(null, null, null, null, "S");

        PI p00 = new PI(s, drawer);
        this.runner.addThread(p00);
        this.runner.startAll();

        PW p01 = new PW(p00.getObj(), drawer);
        this.runner.addThread(p01);
        this.runner.startAll();
        PW p02 = new PW(p01.getObj(), drawer);
        this.runner.addThread(p02);
        this.runner.startAll();
        PW p03 = new PW(p02.getObj(), drawer);
        this.runner.addThread(p03);
        this.runner.startAll();

        PN p10 = new PN(p00.getObj(), drawer);
        this.runner.addThread(p10);
        this.runner.startAll();
        PN p20 = new PN(p10.getObj(), drawer);
        this.runner.addThread(p20);
        this.runner.startAll();

        PNW p11 = new PNW(p00.getObj(), drawer);
        this.runner.addThread(p11);
        this.runner.startAll();
        PNW p21 = new PNW(p10.getObj(), drawer);
        this.runner.addThread(p21);
        this.runner.startAll();
        PNW p12 = new PNW(p01.getObj(), drawer);
        this.runner.addThread(p12);
        this.runner.startAll();
        PNW p22 = new PNW(p11.getObj(), drawer);
        this.runner.addThread(p22);
        this.runner.startAll();
        PNW p13 = new PNW(p02.getObj(), drawer);
        this.runner.addThread(p13);
        this.runner.startAll();
        PNW p23 = new PNW(p12.getObj(), drawer);
        this.runner.addThread(p23);
        this.runner.startAll();

        //done
        System.out.println("done");
        drawer.draw(p23.getObj());

    }
}
