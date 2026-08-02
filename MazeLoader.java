import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// MazeLoader: reads a maze .txt file (per spec Section 2.3) and builds a
// MazeGrid from it. File format:
//   line 1: M (rows)
//   line 2: N (cols)
//   next M lines: the grid itself, using ' ' (open), '#' (wall),
//                 'S' (start), 'G' (goal)

public class MazeLoader {

    // Non-void method: single return at the very end.
    // Returns the loaded MazeGrid, or null if the file could not be read.
    public MazeGrid loadFromFile(String filePath) {
        MazeGrid grid = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String firstLine = reader.readLine().trim();
            String[] firstTokens = firstLine.split("\\s+");

            int rows = 0;
            int cols = 0;

            if (firstTokens.length >= 2) {
                // Format: "M N" on a single line.
                rows = Integer.parseInt(firstTokens[0]);
                cols = Integer.parseInt(firstTokens[1]);
            } else {
                // Format: M and N on two separate lines.
                rows = Integer.parseInt(firstTokens[0]);
                cols = Integer.parseInt(reader.readLine().trim());
            }

            grid = new MazeGrid(rows, cols);

            for (int row = 0; row < rows; row++) {
                String line = reader.readLine();
                parseRow(grid, line, row, cols);
            }

            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading maze file: " + e.getMessage());
            grid = null;
        }

        return grid;
    }

    // Void method: no return statements.
    // Parses a single row of the maze text into Cell values and writes
    // them into the grid. Lines shorter than 'cols' (trailing spaces
    // often get trimmed by editors) are treated as OPEN for the rest.
    private void parseRow(MazeGrid grid, String line, int row, int cols) {
        for (int col = 0; col < cols; col++) {
            char symbol = ' ';

            if (line != null && col < line.length()) {
                symbol = line.charAt(col);
            }

            Cell type = symbolToCell(symbol);
            grid.setCell(row, col, type);
        }
    }

    // Non-void method: single return at the very end.
    // Maps a single maze-file character to its Cell type.
    private Cell symbolToCell(char symbol) {
        Cell type = Cell.OPEN;

        if (symbol == '#') {
            type = Cell.WALL;
        } else if (symbol == 'S') {
            type = Cell.START;
        } else if (symbol == 'G') {
            type = Cell.GOAL;
        }

        return type;
    }
}
