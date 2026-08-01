public class DataStructureTester {

    public static void main(String[] args) {
        testStack();
        testQueue();
        testPriorityQueue();
        testGridAndCoordinates();
        testTrackingMatrices();
        testMetrics();
        
        System.out.println("\nAll data structure tests completed.");
    }

    // Void methods: No return statements allowed[cite: 1]
    private static void testStack() {
        System.out.println("\n--- Testing CustomStack ---");
        CustomStack<Integer> stack = new CustomStack<>();
        
        stack.push(10);
        stack.push(20);
        stack.push(30);
        
        System.out.println("Stack size after 3 pushes: " + stack.getSize() + " (Expected: 3)");
        System.out.println("Peek top: " + stack.peek() + " (Expected: 30)");
        System.out.println("Pop top: " + stack.pop() + " (Expected: 30)");
        System.out.println("Stack size after 1 pop: " + stack.getSize() + " (Expected: 2)");
        System.out.println("Is Stack empty? " + stack.isEmpty() + " (Expected: false)");
    }

    private static void testQueue() {
        System.out.println("\n--- Testing CustomQueue ---");
        CustomQueue<String> queue = new CustomQueue<>();
        
        queue.enqueue("First");
        queue.enqueue("Second");
        queue.enqueue("Third");
        
        System.out.println("Queue size after 3 enqueues: " + queue.getSize() + " (Expected: 3)");
        System.out.println("Peek front: " + queue.peek() + " (Expected: First)");
        System.out.println("Dequeue front: " + queue.dequeue() + " (Expected: First)");
        System.out.println("Queue size after 1 dequeue: " + queue.getSize() + " (Expected: 2)");
    }

    private static void testPriorityQueue() {
        System.out.println("\n--- Testing StatePriorityQueue ---");
        StatePriorityQueue pq = new StatePriorityQueue();
        
        // Inserting out of order based on cost
        pq.enqueue(new MazeState(0, 0, 50));
        pq.enqueue(new MazeState(1, 1, 10)); 
        pq.enqueue(new MazeState(2, 2, 30));
        
        System.out.println("PQ size: " + pq.getSize() + " (Expected: 3)");
        System.out.println("Dequeue lowest cost: " + pq.dequeue().cost + " (Expected: 10)");
        System.out.println("Dequeue next lowest: " + pq.dequeue().cost + " (Expected: 30)");
        System.out.println("Dequeue highest cost: " + pq.dequeue().cost + " (Expected: 50)");
    }

    private static void testGridAndCoordinates() {
        System.out.println("\n--- Testing MazeGrid, Cell & Position ---");
        MazeGrid grid = new MazeGrid(15, 15); // Testing minimum size 15x15[cite: 1]
        
        grid.setCell(0, 0, Cell.START);
        grid.setCell(5, 5, Cell.WALL);
        grid.setCell(14, 14, Cell.GOAL);
        
        System.out.println("Start Position: Row " + grid.getStartPos().row + ", Col " + grid.getStartPos().col + " (Expected: 0, 0)");
        System.out.println("Goal Position: Row " + grid.getGoalPos().row + ", Col " + grid.getGoalPos().col + " (Expected: 14, 14)");
        System.out.println("Is (5,5) a valid move? " + grid.isValidMove(5, 5) + " (Expected: false, it's a WALL)");
        System.out.println("Is (0,1) a valid move? " + grid.isValidMove(0, 1) + " (Expected: true, defaults to null/open)");
        System.out.println("Is (20,20) out of bounds caught? " + grid.isValidMove(20, 20) + " (Expected: false)");
    }

    private static void testTrackingMatrices() {
        System.out.println("\n--- Testing VisitedMatrix & ParentMap ---");
        VisitedMatrix visited = new VisitedMatrix(15, 15);
        ParentMap parents = new ParentMap(15, 15);
        
        visited.markVisited(3, 3);
        System.out.println("Is (3,3) visited? " + visited.isVisited(3, 3) + " (Expected: true)");
        System.out.println("Is (4,4) visited? " + visited.isVisited(4, 4) + " (Expected: false)");
        
        Position parentNode = new Position(2, 2);
        parents.setParent(3, 3, parentNode);
        System.out.println("Parent of (3,3): Row " + parents.getParent(3, 3).row + ", Col " + parents.getParent(3, 3).col + " (Expected: 2, 2)");
    }

    private static void testMetrics() {
        System.out.println("\n--- Testing SimulationMetrics ---");
        SimulationMetrics metrics = new SimulationMetrics();
        
        metrics.incrementSteps();
        metrics.incrementSteps();
        metrics.setPathLength(25);
        metrics.setExecutionTime(1042);
        
        System.out.println("Total Steps: " + metrics.getTotalSteps() + " (Expected: 2)");
        System.out.println("Path Length: " + metrics.getPathLength() + " (Expected: 25)");
        System.out.println("Execution Time: " + metrics.getExecutionTimeMs() + "ms (Expected: 1042ms)");
    }
}