public class StatePriorityQueue {
    // Uses your existing Node.java
    private Node<MazeState> front; 
    private int size;

    public StatePriorityQueue() {
        this.front = null;
        this.size = 0;
    }

    // Void method: No return, break, or continue allowed[cite: 1]
    public void enqueue(MazeState data) {
        Node<MazeState> newNode = new Node<>(data);

        if (this.front == null) {
            // Queue is empty
            this.front = newNode;
        } else if (data.cost < this.front.data.cost) {
            // New node has a smaller cost, so it goes to the very front
            newNode.next = this.front;
            this.front = newNode;
        } else {
            // Traverse to find the correct sorted position using simple integer comparison
            Node<MazeState> current = this.front;
            boolean foundInsertionPoint = false;

            while (current.next != null && !foundInsertionPoint) {
                if (data.cost < current.next.data.cost) {
                    foundInsertionPoint = true;
                } else {
                    current = current.next;
                }
            }

            newNode.next = current.next;
            current.next = newNode;
        }

        this.size++;
    }

    // Non-void method: One return statement at the very end[cite: 1]
    public MazeState dequeue() {
        MazeState dequeuedData = null;

        if (this.front != null) {
            dequeuedData = this.front.data;
            this.front = this.front.next;
            this.size--;
        }

        return dequeuedData;
    }

    public MazeState peek() {
        MazeState peekedData = null;

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