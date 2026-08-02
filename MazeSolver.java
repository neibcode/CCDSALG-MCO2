// MazeSolver: implements BFS graph traversal.
// Uses CustomQueue to move and VisitedMatrix to avoid revisits,
// ParentMap to reconstruct the path, and CustomStack to reverse it
//SimulationMetrics tracks steps/time/path length.

public class MazeSolver {
    private MazeGrid grid;
    private VisitedMatrix visited;
    private ParentMap parents;
    private SimulationMetrics metrics;
    private CustomQueue<Position> explorationOrder;

    public MazeSolver(MazeGrid grid) {
        this.grid = grid;
        this.visited = new VisitedMatrix(grid.getRows(), grid.getCols());
        this.parents = new ParentMap(grid.getRows(), grid.getCols());
        this.metrics = new SimulationMetrics();
        this.explorationOrder = new CustomQueue<>();
    }

    // Non-void method: single return at the very end.
    // Returns a CustomStack<Position> representing the path from start to goal,
    // or an empty stack if no path exists.
    public CustomStack<Position> solve() {
        CustomStack<Position> path = new CustomStack<>();
        long startTime = System.currentTimeMillis();

        Position start = grid.getStartPos();
        Position goal = grid.getGoalPos();

        CustomQueue<Position> frontier = new CustomQueue<>();
        frontier.enqueue(start);
        visited.markVisited(start.row, start.col);

        boolean goalFound = false;

        while (!frontier.isEmpty() && !goalFound) {
            Position current = frontier.dequeue();
            metrics.incrementSteps();
            explorationOrder.enqueue(current);

            if (current.row == goal.row && current.col == goal.col) {
                goalFound = true;
            } else {
                exploreNeighbors(current, frontier);
            }
        }

        if (goalFound) {
            buildPath(path, start, goal);
        }

        long endTime = System.currentTimeMillis();
        metrics.setExecutionTime(endTime - startTime);
        metrics.setPathLength(path.getSize());

        return path;
    }

    // Void method: no return statements, no break/continue.
    // Checks the 4 cardinal neighbors of 'current' and enqueues valid,
    // unvisited ones.
    private void exploreNeighbors(Position current, CustomQueue<Position> frontier) {
        int[] rowOffsets = {-1, 1, 0, 0};
        int[] colOffsets = {0, 0, -1, 1};

        for (int i = 0; i < 4; i++) {
            int newRow = current.row + rowOffsets[i];
            int newCol = current.col + colOffsets[i];

            if (grid.isValidMove(newRow, newCol) && !visited.isVisited(newRow, newCol)) {
                visited.markVisited(newRow, newCol);
                parents.setParent(newRow, newCol, current);
                frontier.enqueue(new Position(newRow, newCol));
            }
        }
    }

    // Void method: no return statements.
    // Walks backward from goal to start via ParentMap, pushing onto 'path'
    // so that popping the stack later yields start -> goal order.
    private void buildPath(CustomStack<Position> path, Position start, Position goal) {
        Position current = goal;
        boolean reachedStart = false;

        while (!reachedStart) {
            path.push(current);

            if (current.row == start.row && current.col == start.col) {
                reachedStart = true;
            } else {
                current = parents.getParent(current.row, current.col);
            }
        }
    }

    public SimulationMetrics getMetrics() {
        return this.metrics;
    }

    // Non-void method: single return at the very end.
    // Returns every cell in the order it was visited during the search
    // (dead ends included), for animating the full search process -- as
    // opposed to solve()'s returned CustomStack, which is only the final
    // shortest path.
    public CustomQueue<Position> getExplorationOrder() {
        return this.explorationOrder;
    }
}
