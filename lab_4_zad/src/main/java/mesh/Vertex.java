package mesh;

public class Vertex {

    //label
    String mLabel;
    //links to adjacent elements
    Vertex mLeft;
    Vertex mRight;
    Vertex mTop;
    Vertex mDown;
    boolean printed = false;
    int idx, idy;

    //methods for adding links
    public Vertex(Vertex _left, Vertex _right, String _lab) {
        this.mLeft = _left;
        this.mRight = _right;
        this.mLabel = _lab;
    }
    public Vertex(Vertex _left, Vertex _right, Vertex _top, Vertex _down, String _lab) {
        this.mLeft = _left;
        this.mRight = _right;
        this.mTop = _top;
        this.mDown = _down;
        this.mLabel = _lab;
    }
    //empty constructor

    public Vertex() {
    }

    public void setLeft(Vertex _left) {
        this.mLeft = _left;
    }

    public void setRight(Vertex _right) {
        this.mRight = _right;
    }

    public void setTop(Vertex mTop) {
        this.mTop = mTop;
    }

    public void setDown(Vertex mDown) {
        this.mDown = mDown;
    }

    public void setLabel(String _lab) {
        this.mLabel = _lab;
    }

    public Vertex getLeft() {
        return this.mLeft;
    }

    public Vertex getRight() {
        return this.mRight;
    }

    public Vertex getTop() {
        return mTop;
    }

    public Vertex getDown() {
        return mDown;
    }

    public String getLabel() {
        return this.mLabel;
    }
}
