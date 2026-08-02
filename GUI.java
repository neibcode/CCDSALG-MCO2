import java.awt.*;

import javax.swing.*;

public class GUI extends JFrame{
    final Dimension appDimension = new Dimension(600, 600);

    private CardLayout cardLayout;
    private JPanel cards;
    private MainMenu menu;
    private Maze maze;

    public GUI(MainMenu menu, Maze maze){
        //Settings for The Main Frame
        this.setTitle("PAC-MAN (BALAGTAS, CAGIGAS, COVAR)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(appDimension);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.black);

        cardLayout = new CardLayout();
        cards = new JPanel(cardLayout);

        this.menu = menu;
        this.maze = maze;

        cards.add(menu, "MENU");
        cards.add(maze, "MAZE");

        this.add(cards);

        this.setVisible(true);
        this.showMenu();
    }

    public void showMenu(){
        cardLayout.show(cards, "MENU");
    }

    public void showMaze(MazeGrid mzGrid){
        cardLayout.show(cards, "MAZE");
        
        maze.loadMap(mzGrid);
    }

    public MainMenu getMenu(){
        return menu;
    }

    public Maze getMaze(){
        return maze;
    }
}
