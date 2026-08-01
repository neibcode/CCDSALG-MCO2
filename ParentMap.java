public class ParentMap {
    private Position[][] parent;
    private int rows;
    private int cols;

    public ParentMap(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.parent = new Position[rows][cols];
    }

    // Void method: No return statements allowed[cite: 1]
    public void setParent(int row, int col, Position parentPos) {
        if (row >= 0 && row < this.rows && col >= 0 && col < this.cols) {
            this.parent[row][col] = parentPos;
        }
    }

    // Non-void method: Exactly one return statement at the end[cite: 1]
    public Position getParent(int row, int col) {
        Position p = null;
        
        if (row >= 0 && row < this.rows && col >= 0 && col < this.cols) {
            p = this.parent[row][col];
        }
        
        return p;
    }
}