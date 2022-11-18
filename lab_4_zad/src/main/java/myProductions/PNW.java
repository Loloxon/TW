package myProductions;

import mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class PNW extends AbstractProduction<Vertex> {

    public PNW(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
    }

    @Override
    public Vertex apply(Vertex t1) {
        System.out.println("PNW");
        Vertex t2 = new Vertex(null, t1.getTop(), null, t1.getLeft(), "R");
        t1.getTop().setLeft(t2);
        t1.getLeft().setTop(t2);
        this.addVertexToDrawer(t2);
        return t2;
    }
}
