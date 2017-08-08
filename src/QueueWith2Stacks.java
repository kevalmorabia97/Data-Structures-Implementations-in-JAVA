import java.util.Stack;

public class QueueWith2Stacks<E>{

    private Stack<E> inbox = new Stack<E>();
    private Stack<E> outbox = new Stack<E>();

    public void enqueue(E item) {
        inbox.push(item);
    }

    public E dequeue() {
        if (outbox.isEmpty()) 
            while (!inbox.isEmpty()) outbox.push(inbox.pop());
        return outbox.pop();
    }

    public static void main(String[] args){
    	QueueWith2Stacks<Integer> q = new QueueWith2Stacks<Integer>();
    	q.enqueue(10);
    	q.enqueue(16);
    	q.enqueue(84);
    	q.enqueue(1);
    	q.enqueue(6);
    	q.dequeue();
    	q.dequeue();
    	q.enqueue(4);
    }
}