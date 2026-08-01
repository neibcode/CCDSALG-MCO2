Documentation by: Fernando Jr O. Cagigas

1. Basic Classes:

Node(T data): Constructor that initializes the node with the provided data and sets the next pointer to null

a. CustomStack:

A generic Last-In-First-Out (LIFO) stack implementation built from scratch using Node<T>. Designed for Depth-First Search (DFS) or backtracking.

push(T data) Adds a new node to the top of the stack and increments the size.  
pop() Removes and returns the data from the top node. Decrements size. Uses a strict single return at the end.  
peek() Returns the data at the top of the stack without removing it.  
isEmpty() Returns true if the stack size is 0 (top is null), otherwise false.  
getSize() Returns the current integer size of the stack.

b. CustomQueue:

A generic First-In-First-Out (FIFO) queue implementation built from scratch. Designed for Breadth-First Search (BFS).

enqueue(T data) Adds a new node to the rear of the queue and updates pointers
dequeue() Removes and returns the data from the front of the queue. If the queue becomes empty, updates the rear pointer to null
peek() Returns the data at the front of the queue without removing it
isEmpty() Returns true if the front pointer is null
getSize() Returns the current integer size of the queue.

c. StatePriorityQueue

A specialized priority queue designed specifically to hold MazeState objects. It sorts elements upon insertion based on path cost, making it ideal for informed search algorithms like A\* or Dijkstra's

enqueue(MazeState data) Inserts the state into the correct sorted position (lowest cost at the front) using a while loop controlled by a boolean flag to avoid break statements
dequeue() Removes and returns the lowest-cost state from the front
peek() Returns the lowest-cost state without removing it
isEmpty() Checks if the queue has elements
getSize() Returns the number of elements in the priority queue

2. State Containers:

d. Position
A simple wrapper for grid coordinates

Position(int row, int col): Initializes the object with specific row and column indices

e. MazeState
An extension of positional data used specifically for informed searches

MazeState(int row, int col, int cost): Stores the coordinate alongside a cost value (e.g., distance traveled + heuristic) to be sorted by the StatePriorityQueue

f. Cell
Defines the valid states of a grid coordinate based on the loaded .txt file

OPEN: Represents a walkable path (space character)
WALL: Represents an impassable wall (#)
START: Represents the character's starting position (S)
GOAL: Represents the cheese or goal position (G)

3. Grid Management

g. MazeGrid
These classes handle the M X M matrix operations, memory tracking, and performance statistics

setCell(int row, int col, Cell type) Assigns a Cell enum to a specific coordinate. If the type is START or GOAL, it updates the internal tracker for those key positions
isValidMove(int row, int col) Checks if a coordinate is within grid boundaries and is not a WALL. Prevents diagonal movement indexing.  
getCell(int row, int col) Returns the Cell type at the specified coordinate
getStartPos() / getGoalPos() Returns the Position object for the start or goal
getRows() / getCols() Returns the M and N dimensions of the grid

h. Visited Matrix
A b2d boolean array to detect infinite loops by tracking explored nodes

markVisited(int row, int col) Safely sets the boolean state of a valid coordinate to true
isVisited(int row, int col) Returns true if the cell has been explored, false otherwise

i. ParentMap
A 2D array of Position objects used to trace the final path backwards from the goal to the start

setParent(int row, int col, Position parentPos) Maps a coordinate to the node that discovered it
getParent(int row, int col) Retrieves the parent Position of a given coordinate

j. SimulationMetrics
Aggregates the required runtime statistics for the final UI display.

incrementSteps() Adds 1 to the total number of cells explored.  
setPathLength(int length) Sets the final calculated length of the successful path.  
setExecutionTime(long timeMs) Sets the total algorithm execution time in milliseconds.  
getTotalSteps() / getPathLength() / getExecutionTimeMs() Getters used by the UI layer to display the final results
