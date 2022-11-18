package mesh;

import production.PDrawer;

import java.util.LinkedList;
import java.util.Queue;

public class GraphDrawer implements PDrawer<Vertex> {
    int xSize = 9;
    int ySize = 9;
    @Override
    public void draw(Vertex v) {
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
    }
// 1  procedure BFS(G, root) is
// 2      let Q be a queue
// 3      label root as explored
// 4      Q.enqueue(root)
//            5      while Q is not empty do
//            6          v := Q.dequeue()
//            7          if v is the goal then
// 8              return v
// 9          for all edges from v to w in G.adjacentEdges(v) do
//            10              if w is not labeled as explored then
//11                  label w as explored
//12                  w.parent := v
//13                  Q.enqueue(w)
}
