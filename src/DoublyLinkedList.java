import java.util.NoSuchElementException;

class DLLNode {
    int element;
    DLLNode next;
    DLLNode prev;

    public DLLNode(int element, DLLNode next, DLLNode prev) {
        this.element = element;
        this.next = next;
        this.prev = prev;
    }
}

public class DoublyLinkedList{	 
    DLLNode head;
    DLLNode tail;
    int size;
     
    public DoublyLinkedList(){ size = 0; }

    public int size() { return size; }
    
    public boolean isEmpty() { return size == 0; }
    
    public void addFirst(int element) {
        DLLNode tmp = new DLLNode(element, head, null);
        if(head != null ) {head.prev = tmp;}
        head = tmp;
        if(tail == null) { tail = tmp;}
        size++;
        System.out.println("adding at first: "+element);
    }

    public void addLast(int element){
        DLLNode tmp = new DLLNode(element, null, tail);
        if(tail != null) {tail.next = tmp;}
        tail = tmp;
        if(head == null) { head = tmp;}
        size++;
        System.out.println("adding at last: "+element);
    }
    public void iterateForward(){       
        System.out.println("iterating forward..");
        DLLNode tmp = head;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.next;
        }
    }

    public void iterateBackward(){
        System.out.println("iterating backword..");
        DLLNode tmp = tail;
        while(tmp != null){
            System.out.println(tmp.element);
            tmp = tmp.prev;
        }
    }
    public void removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        System.out.println("deleted: "+head.element);
        head = head.next;
        head.prev = null;
        size--;
    }
    
    public void removeLast() {
        if (size == 0) throw new NoSuchElementException();
        System.out.println("deleted: "+tail.element);
        tail = tail.prev;
        tail.next = null;
        size--;
        
    }
     
    public static void main(String a[]){
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.addFirst(10);
        dll.addFirst(34);
        dll.addLast(56);
        dll.addLast(364);
        dll.iterateForward();
        dll.removeFirst();
        dll.removeLast();
        dll.iterateBackward();
    }
}