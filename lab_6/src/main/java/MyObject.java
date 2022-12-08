import java.util.Objects;

public class MyObject {
    int value;
    public MyObject(int value){
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyObject)) return false;
        MyObject myObject = (MyObject) o;
        return value == myObject.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
