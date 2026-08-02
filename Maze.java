import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Maze extends JPanel{

    //Maze Settings
    private int rowCount;
    private int columnCount;
    private int tileSize;

    private Image characterImage;
    private Image characterUp;
    private Image characterDown;
    private Image characterLeft;
    private Image characterRight;
    private Image goalImage;
    JButton replayButton, mainMenuButton;
    final Dimension appDimension = new Dimension(600, 600);

    private class Block{
        int x;
        int y;
        int width;
        int height;
        Image image;

        int startX;
        int startY;

        Block(Image image, int x, int y, int width, int height){
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;

            this.startX = x;
            this.startY = y;
        }
    }

    HashSet<Block> walls;
    Block character;
    Block goal;
    
    //Maze Constructor
    public Maze(){
        //Window settings

        this.setLayout(null);
        this.setBackground(Color.black);
        this.tileSize = 225/9;

        //Button Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, (appDimension.height/4)*3, appDimension.width, appDimension.height/4);
        buttonsPanel.setBorder(new EmptyBorder(15, appDimension.width/12, 60, appDimension.width/12));
        buttonsPanel.setLayout(new GridLayout(1, 2, 30, 0));
        buttonsPanel.setOpaque(false);

        //LOAD Button
        mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.setBackground(Color.YELLOW);
        mainMenuButton.setActionCommand("MENU");
        buttonsPanel.add(mainMenuButton);

        //REPLAY Button
        replayButton = new JButton("REPLAY MAZE");
        replayButton.setBackground(Color.YELLOW);
        replayButton.setActionCommand("REPLAY");
        buttonsPanel.add(replayButton);

        characterUp = new ImageIcon("characterUp.png").getImage();
        characterDown = new ImageIcon("characterDown.png").getImage();
        characterLeft = new ImageIcon("characterLeft.png").getImage();
        characterRight = new ImageIcon("characterRight.png").getImage();
        goalImage = new ImageIcon("goal.png").getImage();
        this.add(buttonsPanel);
    }

    public void addMazeListener(ActionListener listenerForMaze){

        replayButton.addActionListener(listenerForMaze);
        mainMenuButton.addActionListener(listenerForMaze);
        
    }

    //Updates the hash and items inside?
    public void loadMap(MazeGrid mazeGrid){
        characterImage = characterRight;

        this.rowCount = mazeGrid.getRows();
        this.columnCount = mazeGrid.getCols();
        System.out.println("ROW: " + rowCount + ", COL: " + columnCount);

        walls = new HashSet<Block>();

        int baseX = (int)(appDimension.width / 2) - (tileSize * (columnCount / 2));
        int baseY = (int) (appDimension.height / 3) - (tileSize * (rowCount / 2));

        for(int r = 0; r < rowCount; r++){
           for(int c = 0; c < columnCount; c++){
                Cell tile = mazeGrid.getCell(r, c);

                int x = baseX + (c * tileSize);
                int y = baseY + (r * tileSize);

                if(tile == Cell.WALL){
                    Block wall = new Block(null, x, y, tileSize, tileSize);
                    walls.add(wall);
                } else if(tile == Cell.START){
                    character = new Block(null, x, y, tileSize, tileSize);
                } else if(tile == Cell.GOAL){
                    goal = new Block(null, x, y, tileSize, tileSize);
                }
            }
        }

        System.out.println("WALLS: " + walls.size());
        System.out.println(character);
        System.out.println(goal);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.drawImage(characterImage, character.x, character.y, character.width, character.height, null);

        g.setColor(Color.red);
        g.drawImage(goalImage, goal.x, goal.y, goal.width, goal.height, null);

        for(Block wall : walls){
            g.setColor(Color.blue);
            g.fillRect(wall.x, wall.y, wall.width, wall.height);
        }
    }

    public void moveCharacter(String direction){
        switch (direction) {
            case "UP":
                character.y -= tileSize;
                characterImage = characterUp;
                break;
            case "DOWN":
                character.y += tileSize;
                characterImage = characterDown;
                break;
            case "LEFT":
                character.x -= tileSize;
                characterImage = characterLeft;
                break;
            case "RIGHT":
                character.x += tileSize;
                characterImage = characterRight;
                break;
        
            default:
                break;
        }
        repaint();
    }
}
