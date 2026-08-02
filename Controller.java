import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Controller {
    private javax.swing.Timer animationTimer;
    private MainMenu mainMenu = new MainMenu();
    private Maze mazeView = new Maze();
    private GUI gui;

    private MazeLoader mzLoader;
    private MazeGrid mzGrid;


    public Controller(){
        this.gui = new GUI(mainMenu, mazeView);

        this.mainMenu.addMenuListener(new MenuListener());
        this.mazeView.addMazeListener(new MazeListener());

        mzLoader = new MazeLoader();
    }

    class MenuListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            
            switch (e.getActionCommand()) {
                case "START":
                    System.out.println("START BUTTON clicked");
                    gui.showMaze(mzGrid);
                    mazeView.loadMap(mzGrid);   // you'll need this call too, see note below

                    break;
                case "LOAD":
                    System.out.println("LOAD BUTTON clicked");
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
                    int response = fileChooser.showOpenDialog(null);

                    if(response == JFileChooser.APPROVE_OPTION){
                        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
                        MazeGrid loaded = mzLoader.loadFromFile(filePath);

                        if (loaded == null) {
                            JOptionPane.showMessageDialog(null, "INVALID FILE", "ERROR", JOptionPane.WARNING_MESSAGE);
                            System.out.println("INVALID FILE");
                        } else {
                            mzGrid = loaded;
                            mainMenu.enableStartButton();
                        }
                    }
                    break;
                case "EXIT":
                    System.out.println("EXIT");
                    System.exit(0);
                default:
                    break;
            }
        }

    }

    class MazeListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            
            switch (e.getActionCommand()) {
                case "REPLAY":
                    gui.showMaze(mzGrid);
                    mazeView.loadMap(mzGrid); 
                    mazeView.repaint();

                    MazeSolver solver = new MazeSolver(mzGrid);
                    CustomStack<Position> path = solver.solve();
                    SimulationMetrics metrics = solver.getMetrics();

                    animatePath(pathToDirections(path), () -> 
                    JOptionPane.showMessageDialog(null, "Steps explored: " + metrics.getTotalSteps() + "\nPath length: " + metrics.getPathLength() + "\nExecution time: " + metrics.getExecutionTimeMs() + "ms"));
                    
                    break;
                case "MENU":
                    System.out.println("MAIN MENU clicked");
                    gui.showMenu();
                    //Do something make button not transparent
                    break;
                default:
                    break;
            }
        }
    }

    private java.util.List<String> pathToDirections(CustomStack<Position> path) {
        java.util.List<Position> positions = new java.util.ArrayList<>();
        while (!path.isEmpty()) {
            positions.add(path.pop());
        }

        java.util.List<String> directions = new java.util.ArrayList<>();
        for (int i = 1; i < positions.size(); i++) {
            Position prev = positions.get(i - 1);
            Position curr = positions.get(i);

            if (curr.row < prev.row) directions.add("UP");
            else if (curr.row > prev.row) directions.add("DOWN");
            else if (curr.col < prev.col) directions.add("LEFT");
            else if (curr.col > prev.col) directions.add("RIGHT");
        }
        return directions;
    }

    private void animatePath(java.util.List<String> directions, Runnable onFinished) {
        if (animationTimer != null && animationTimer.isRunning()) {
            animationTimer.stop();
        }

        animationTimer = new javax.swing.Timer(200, null);
        final int[] index = {0};

        animationTimer.addActionListener(e -> {
            if (index[0] < directions.size()) {
                mazeView.moveCharacter(directions.get(index[0]));
                index[0]++;
            } else {
                animationTimer.stop();
                if (onFinished != null) {
                    onFinished.run();
                }
            }
        });

        animationTimer.start();
    }
}
