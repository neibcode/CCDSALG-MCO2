import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TestController {
    private MainMenu mainMenu = new MainMenu();
    private Maze mazeView = new Maze();
    private GUI gui;
    private String[] tileMap;
    private int rows;
    private int columns;

    public TestController(){
        this.gui = new GUI(mainMenu, mazeView);

        this.mainMenu.addMenuListener(new MenuListener());
        this.mazeView.addMazeListener(new MazeListener());
    }

    class MenuListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            
            switch (e.getActionCommand()) {
                case "START":
                    System.out.println("START BUTTON clicked");
                    rows = 4;
                    columns = 15;
                    //THIS IS JUST A TEST VALUE, VALUE MUST COME FROM AFTER CHECKING A MAZE .TXT FILE <<-----

                    gui.showMaze(tileMap, rows, columns);
                    
                    break;
                case "LOAD":
                    System.out.println("LOAD BUTTON clicked");
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
                    int response = fileChooser.showOpenDialog(null);

                    if(response == JFileChooser.APPROVE_OPTION){
                        try {
                            Path filePath = fileChooser.getSelectedFile().toPath();
                            List<String> lineList = Files.readAllLines(filePath);
                            tileMap = lineList.toArray(new String[0]);
                            mainMenu.enableStartButton();
                        } catch (IOException t) {
                            System.out.println("INVALID FILE");
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
                    System.out.println("REPLAY clicked");
                    mazeView.loadMap(tileMap, rows, columns);
                    mazeView.repaint();
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
}
