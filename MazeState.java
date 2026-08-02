public class MazeState {
    public int row;
    public int col;
    public int cost; // Lower cost means higher priority

    public MazeState(int row, int col, int cost) {
        this.row = row;
        this.col = col;
        this.cost = cost;
    }
}