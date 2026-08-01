public class VisitedMatrix {
    private boolean[][] visited;
    private int rows;
    private int cols;

    public VisitedMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.visited = new boolean[rows][cols];
    }

    // Void method: No return statements allowed
    public void markVisited(int row, int col) {
        if (row >= 0 && row < this.rows && col >= 0 && col < this.cols) {
            this.visited[row][col] = true;
        }
    }

    // Non-void method: Exactly one return statement at the end
    public boolean isVisited(int row, int col) {
        boolean result = false;
        
        if (row >= 0 && row < this.rows && col >= 0 && col < this.cols) {
            result = this.visited[row][col];
        }
        
        return result;
    }
}