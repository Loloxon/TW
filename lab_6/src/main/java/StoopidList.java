import java.util.concurrent.Semaphore;

public class StoopidList {
    final StoopidNode head;
    Semaphore lock;


    public StoopidList(MyObject o) {
        head = new StoopidNode(o, null);
        lock = new Semaphore(1);
    }

    boolean contains(MyObject o) {
        try {
            lock.acquire();
            if (head.getObject() == o) {
                return true;
            }
            StoopidNode tail = head;
            while (tail.getNext() != null) {
                tail = tail.getNext();
                if (tail.getObject().equals(o)) {
                    lock.release();
                    return true;
                }
            }
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean remove(MyObject o) {
        try {
            lock.acquire();
            if (head.getObject() == o) {
                return true;
            }
            StoopidNode tail = head;
            StoopidNode tail2 = head;
            while (tail.getNext() != null) {
                tail = tail.getNext();
                if (tail.getObject().equals(o)) {
                    tail2.setNext(tail.getNext());
                    lock.release();
                    return true;
                }
                tail2 = tail2.getNext();
            }
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean add(MyObject o) {
        try {
            lock.acquire();
            StoopidNode tail = head;
            while (tail.getNext() != null) {
                tail = tail.getNext();
            }
            tail.setNext(new StoopidNode(o, null));
            lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
