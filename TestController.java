import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.security.auth.kerberos.KerberosCredMessage;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TestController {
    private MainMenu mainMenu;
    private Maze mazeView;
    private GUI gui;

    public TestController(MainMenu mainMenu, Maze mazeView){
        this.mainMenu = mainMenu;
        this.mazeView = mazeView;
        this.gui = new GUI(mainMenu, mazeView);

        this.mainMenu.addMenuListener(new MenuListener());
        this.mazeView.addMazeListener(new MazeListener());
    }

    class MenuListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            
            switch (e.getActionCommand()) {
                case "START":
                    System.out.println("START BUTTON clicked");
                    //Do something make button not transparent
                    gui.showMaze();
                    break;
                case "LOAD":
                    System.out.println("LOAD BUTTON clicked");
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new FileNameExtensionFilter(".txt", "txt"));
                    int response = fileChooser.showOpenDialog(null);

                    if(response == JFileChooser.APPROVE_OPTION){
                        File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                        System.out.println(file);
                        mainMenu.enableStartButton();
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
                    mainMenu.revalidate();
                    mainMenu.repaint();
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
