package myProductions;

import mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class PN extends AbstractProduction<Vertex> {

    public PN(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
    }

    @Override
    public Vertex apply(Vertex t1) {
        System.out.println("PN");
        Vertex t2 = new Vertex(null, null, null, t1, "N");
        t1.setTop(t2);
        return t2;
    }
}
