package mesh;

import production.PDrawer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphDrawer implements PDrawer<Vertex> {
    List<Vertex> vertexList = new ArrayList<>();
    int xSize = 9;
    int ySize = 9;

    @Override
    public void addVertex(Vertex v){
        vertexList.add(v);
    }
    @Override
    public void draw(Vertex v) {
        System.out.println("-".repeat(xSize+4));
        vertexList.forEach(item -> item.printed = false);
        int idx = xSize/2, idy = ySize/2;
        String[][] table = new String[xSize][ySize];
        for(int y=0;y<ySize;y++){
            for(int x=0;x<xSize;x++){
                table[x][y] = " ";
            }
        }
        Queue<Vertex> Q = new LinkedList<>();
        v.printed = true;
        v.idx = idx;
        v.idy = idy;
        table[v.idx][v.idy] = v.mLabel;
        Q.add(v);
        while(!Q.isEmpty()){
            v = Q.poll();
            if(v.getLeft()!=null && !v.getLeft().printed){
                Vertex neighbour = v.getLeft();
                neighbour.printed=true;
                neighbour.idx=v.idx-1;
                neighbour.idy=v.idy;
                table[neighbour.idx][neighbour.idy] = neighbour.mLabel;
                Q.add(neighbour);
            }
            if(v.getRight()!=null && !v.getRight().printed){
                Vertex neighbour = v.getRight();
                neighbour.printed=true;
                neighbour.idx=v.idx+1;
                neighbour.idy=v.idy;
                table[neighbour.idx][neighbour.idy] = neighbour.mLabel;
                Q.add(neighbour);
            }
            if(v.getTop()!=null && !v.getTop().printed){
                Vertex neighbour = v.getTop();
                neighbour.printed=true;
                neighbour.idx=v.idx;
                neighbour.idy=v.idy-1;
                table[neighbour.idx][neighbour.idy] = neighbour.mLabel;
                Q.add(neighbour);
            }
            if(v.getDown()!=null && !v.getDown().printed){
                Vertex neighbour = v.getDown();
                neighbour.printed=true;
                neighbour.idx=v.idx;
                neighbour.idy=v.idy+1;
                table[neighbour.idx][neighbour.idy] = neighbour.mLabel;
                Q.add(neighbour);
            }
        }
        for(int y=0;y<ySize;y++){
            for(int x=0;x<xSize;x++){
                System.out.print(table[x][y] + "  ");
            }
            System.out.println();
        }

//
//        //go left
//        while (v.mLeft != null) {
//            v = v.mLeft;
//        }
//        //plot
//        while (v.mRight != null) {
//            System.out.print(v.mLabel + "--");
//            v = v.mRight;
//        }
//        System.out.println(v.mLabel);
        System.out.println("-".repeat(xSize+4));
    }
}
