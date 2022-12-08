public class List {
    final Node head;

    public List(MyObject o) {
        head = new Node(o, null);
    }

    boolean contains(MyObject o) {
        try {
            head.lock.acquire();
            if (head.getObject() == o) {
                head.lock.release();
                return true;
            }
            head.lock.release();
            Node tail = head;
            tail.lock.acquire();
            while (tail.getNext() != null) {
                tail.getNext().lock.acquire();
                tail.lock.release();

                tail = tail.getNext();
                if (tail.getObject().equals(o)) {
                    tail.lock.release();
                    return true;
                }
            }
            tail.lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean remove(MyObject o) {
        try {
            head.lock.acquire();
            if (head.getObject() == o) {
                head.lock.release();
                return true;
            }
            head.lock.release();
            Node tail = head;
            Node tail2 = head;
            tail.lock.acquire();
            tail2.lock.acquire();
            while (tail.getNext() != null) {
                tail.getNext().lock.acquire();
                tail.lock.release();

                tail = tail.getNext();
                if (tail.getObject().equals(o)) {
                    tail2.setNext(tail.getNext());
                    tail.lock.release();
                    return true;
                }
                tail2.lock.release();
                tail2 = tail2.getNext();
                tail2.lock.acquire();
            }
            tail.lock.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    boolean add(MyObject o) {
        try {
            head.lock.acquire();
            Node tail = head;
            head.lock.release();
            tail.lock.acquire();
            while (tail.getNext() != null) {
                tail.getNext().lock.acquire();
                tail.lock.release();
                tail = tail.getNext();
            }
            tail.setNext(new Node(o, null));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
