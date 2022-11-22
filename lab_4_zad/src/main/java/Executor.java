import myProductions.*;
import mesh.Vertex;
import mesh.GraphDrawer;
import parallelism.BlockRunner;
import production.AbstractProduction;
import production.PDrawer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Executor extends Thread {
    
    private final BlockRunner runner;
    
    public Executor(BlockRunner _runner){
        this.runner = _runner;
    }

    @Override
    public void run() {
        int N = 5;
        PDrawer<Vertex> drawer = new GraphDrawer();
        //axiom
        Vertex s = new Vertex(null, null, null, null, "S");

        PW west;
        PN north;
        ArrayList<AbstractProduction<Vertex>> diagonalCurrent = new ArrayList<>();
        ArrayList<AbstractProduction<Vertex>> diagonal = new ArrayList<>();
        ArrayList<AbstractProduction<Vertex>> diagonalPrev;

        //first cell
        PI init = new PI(s, drawer);
        this.runner.addThread(init);
        diagonal.add(init);
        this.runner.startAll();

        //first corner
        west = new PW(init.getObj(), drawer);
        this.runner.addThread(west);
        north = new PN(init.getObj(), drawer);
        this.runner.addThread(north);
        diagonalPrev = (ArrayList<AbstractProduction<Vertex>>) diagonal.clone();
        diagonal.clear();
        diagonal.add(west);
        diagonal.add(north);
        this.runner.startAll();

        for(int i=3;i<=N;i++){
            //cells on left diagonals increasing size
            diagonalCurrent.clear();
            west = west!=null ? new PW(west.getObj(), drawer) : new PW(init.getObj(), drawer);
            this.runner.addThread(west);
            diagonalCurrent.add(west);

            diagonalPrev.forEach(item -> {
                PNW mid = new PNW(item.getObj(), drawer);
                diagonalCurrent.add(mid);
                this.runner.addThread(mid);
            });
            north = north!=null ? new PN(north.getObj(), drawer) : new PN(init.getObj(), drawer);
            this.runner.addThread(north);
            diagonalCurrent.add(north);

            diagonalPrev = (ArrayList<AbstractProduction<Vertex>>) diagonal.clone();
            diagonal = (ArrayList<AbstractProduction<Vertex>>) diagonalCurrent.clone();

            this.runner.startAll();
        }
        for(int i=N-1;i>1;i--) {
            //cells on left diagonals decreasing size
            diagonalCurrent.clear();
            diagonalPrev.forEach(item -> {
                PNW mid = new PNW(item.getObj(), drawer);
                diagonalCurrent.add(mid);
                this.runner.addThread(mid);
            });
            diagonalPrev.clear();
            for (int k = 1; k < diagonal.size() - 1; k++) {
                diagonalPrev.add(diagonal.get(k));
            }
            diagonal = (ArrayList<AbstractProduction<Vertex>>) diagonalCurrent.clone();
            this.runner.startAll();
        }
        //last cell
        PNW mid = new PNW(diagonalPrev.get(0).getObj(), drawer);
        this.runner.addThread(mid);
        this.runner.startAll();


        System.out.println("done");
        drawer.draw(mid.getObj());

    }
}
