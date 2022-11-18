package myProductions;

import mesh.Vertex;
import production.AbstractProduction;
import production.PDrawer;

public class PI extends AbstractProduction<Vertex> {

    public PI(Vertex _obj, PDrawer<Vertex> _drawer) {
        super(_obj, _drawer);
    }

    @Override
    public Vertex apply(Vertex s) {
        System.out.println("PI");
        return new Vertex(null, null, null, null, "M");
    }
}
