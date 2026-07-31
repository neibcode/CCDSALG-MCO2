import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Maze extends JPanel{
    //Runnable returnButton;
    JButton replayButton, mainMenuButton;
    final Dimension appDimension = new Dimension(600, 600);
    
    public Maze(int rows, int columns){//Runnable returnButton,
        //Window settings
        this.setLayout(null);
        this.setBackground(Color.black);

        //Load the maze

        //Maze Panel
        JPanel mazePanel = new JPanel();
        mazePanel.setOpaque(true);
        mazePanel.setBounds(0, 0, appDimension.width, (appDimension.height/4)*3);
        mazePanel.setBackground(Color.blue);
        this.add(mazePanel);


        //Button Panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, (appDimension.height/4)*3, appDimension.width, appDimension.height/4);
        buttonsPanel.setBorder(new EmptyBorder(15, appDimension.width/12, 60, appDimension.width/12));
        buttonsPanel.setLayout(new GridLayout(1, 2, 30, 0));
        buttonsPanel.setOpaque(false);

        //LOAD Button
        mainMenuButton = new JButton("MAIN MENU");
        mainMenuButton.setBackground(Color.YELLOW);
        //mainMenuButton.setForeground(Color.white);
        mainMenuButton.setActionCommand("MENU");
        buttonsPanel.add(mainMenuButton);
        //loadButton.addActionListener(this);

        //REPLAY Button
        replayButton = new JButton("REPLAY MAZE");
        replayButton.setBackground(Color.YELLOW);
        //replayButton.setForeground(Color.white);
        replayButton.setActionCommand("REPLAY");
        buttonsPanel.add(replayButton);
        //startButton.addActionListener(this);


        this.add(buttonsPanel);
    }

    public void addMazeListener(ActionListener listenerForMaze){

        replayButton.addActionListener(listenerForMaze);
        mainMenuButton.addActionListener(listenerForMaze);
        
    }
}
