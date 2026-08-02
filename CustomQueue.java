//Custome Queue class
public class CustomQueue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public CustomQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    // Void method: No return statement allowed
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (this.rear == null) {
            this.front = newNode;
            this.rear = newNode;
        } else {
            this.rear.next = newNode;
            this.rear = newNode;
        }
        
        this.size++;
    }

    // Non-void method: One return statement at the very end
    public T dequeue() {
        T dequeuedData = null;
        
        if (this.front != null) {
            dequeuedData = this.front.data;
            this.front = this.front.next;
            
            // If the queue becomes empty after dequeue, update the rear pointer too
            if (this.front == null) {
                this.rear = null;
            }
            
            this.size--;
        }
        
        return dequeuedData;
    }

    public T peek() {
        T peekedData = null;
        
        if (this.front != null) {
            peekedData = this.front.data;
        }
        
        return peekedData;
    }

    public boolean isEmpty() {
        boolean empty = false;
        
        if (this.front == null) {
            empty = true;
        }
        
        return empty;
    }
    
    public int getSize() {
        return this.size;
    }
}