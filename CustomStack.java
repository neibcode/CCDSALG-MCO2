//Custom Stack class

public class CustomStack<T> {
    private Node<T> top;
    private int size; 

    public CustomStack() {
        this.top = null;
        this.size = 0;
    }


    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = this.top;
        this.top = newNode;
        this.size++;
    }


    public T pop() {
        T poppedData = null;
        
        if (this.top != null) {
            poppedData = this.top.data;
            this.top = this.top.next;
            this.size--;
        }
        
        return poppedData;
    }

    public T peek() {
        T peekedData = null;
        
        if (this.top != null) {
            peekedData = this.top.data;
        }
        
        return peekedData;
    }

    public boolean isEmpty() {
        boolean empty = false;
        
        if (this.top == null) {
            empty = true;
        }
        
        return empty;
    }
    
    public int getSize() {
        return this.size;
    }
}