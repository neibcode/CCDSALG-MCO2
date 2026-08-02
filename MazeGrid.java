public class MazeGrid {
    private Cell[][] grid;
    private int rows;
    private int cols;
    private Position startPos;
    private Position goalPos;

    public MazeGrid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new Cell[rows][cols];
    }

    // Void method: No return statements allowed
    public void setCell(int row, int col, Cell type) {
        if (row >= 0 && row < this.rows && col >= 0 && col < this.cols) {
            this.grid[row][col] = type;
            
            if (type == Cell.START) {
                this.startPos = new Position(row, col);
            } else if (type == Cell.GOAL) {
                this.goalPos = new Position(row, col);
            }
        }
    }

    // Non-void method: Exactly one return statement at the end
    public boolean isValidMove(int row, int col) {
        boolean valid = false;

        // Check bounds first (between 0 and M/N)[cite: 1]
        if (row >= 0 && row < this.rows && col >= 0 && col < this.cols) {
            
            // Diagonal movements are not allowed per spec[cite: 1], 
            // but this just checks if the target cell is walkable.
            if (this.grid[row][col] != Cell.WALL) {
                valid = true;
            }
        }

        return valid;
    }

    // Getters with single returns at the end[cite: 1]
    public Cell getCell(int row, int col) {
        Cell cellType = null;
        
        if (row >= 0 && row < this.rows && col >= 0 && col < this.cols) {
            cellType = this.grid[row][col];
        }
        
        return cellType;
    }

    public Position getStartPos() {
        return this.startPos;
    }

    public Position getGoalPos() {
        return this.goalPos;
    }
    
    public int getRows() {
        return this.rows;
    }
    
    public int getCols() {
        return this.cols;
    }
}