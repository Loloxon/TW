package production;

import mesh.Vertex;

import java.util.ArrayList;
import java.util.List;

public interface PDrawer<P> {
    List<Vertex> vertexList = new ArrayList<>();

    public void draw(P p);

    public void addVertex(Vertex v);
}
