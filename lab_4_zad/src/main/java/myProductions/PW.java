package myProductions;

import mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class PW extends AbstractProduction<Vertex> {

    public PW(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
        _drawer.addVertex(_obj);
    }

    @Override
    public Vertex apply(Vertex t1) {
        System.out.println("PW");
        Vertex t2 = new Vertex(null, t1, null, null, "W");
        t1.setLeft(t2);
        this.addVertexToDrawer(t2);
        return t2;
    }
}
