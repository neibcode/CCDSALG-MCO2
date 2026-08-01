import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JPanel{
    final Dimension appDimension = new Dimension(600, 600);
    JButton startButton, loadButton, exitButton;
    public MainMenu(){
        this.setLayout(null);
        this.setBackground(Color.black);
        
        //Title
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(true);
        titlePanel.setBounds(0, 0, appDimension.width, appDimension.height/3);
        //titlePanel.setBackground(Color.yellow);
        titlePanel.setOpaque(false);
        this.add(titlePanel);

        ImageIcon logo = new ImageIcon("Logo.png");
        float scaleFactor = 0.11f;
        Image scaledLogo = logo.getImage().getScaledInstance((int)(3840 * scaleFactor), (int)(2160 * scaleFactor), Image.SCALE_SMOOTH);
        JLabel title = new JLabel(new ImageIcon(scaledLogo));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title);
        

        //Buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBounds(0, appDimension.height/3, appDimension.width, (appDimension.height/3) * 2);
        buttonsPanel.setBorder(new EmptyBorder(60, appDimension.width/4, 80, appDimension.width/4));
        buttonsPanel.setLayout(new GridLayout(3, 1, 0, 20));
        //buttonsPanel.setBackground(Color.red);
        buttonsPanel.setOpaque(false);
        this.add(buttonsPanel);
        
        //Start Button
        startButton = new JButton("START MAZE");
        startButton.setForeground(Color.white);
        startButton.setActionCommand("START");
        this.disableStartButton();
        buttonsPanel.add(startButton);
        //startButton.addActionListener(this);

        //Load Button
        loadButton = new JButton("LOAD MAZE");
        loadButton.setBackground(Color.red);
        loadButton.setForeground(Color.white);
        loadButton.setActionCommand("LOAD");
        buttonsPanel.add(loadButton);
        //loadButton.addActionListener(this);

        //Exit Button
        exitButton = new JButton("EXIT MAZE");
        exitButton.setBackground(Color.red);
        exitButton.setForeground(Color.white);
        exitButton.setActionCommand("EXIT");
        buttonsPanel.add(exitButton);
        //exitButton.addActionListener(this);
    }

    public void addMenuListener(ActionListener listenerForMenu){

        startButton.addActionListener(listenerForMenu);
        loadButton.addActionListener(listenerForMenu);
        exitButton.addActionListener(listenerForMenu);
        
    }

    public void enableStartButton(){
        startButton.setBackground(Color.red);
        startButton.setEnabled(true);
    }

    public void disableStartButton(){
        startButton.setBackground(new Color(255, 0, 0, 128));
        startButton.setEnabled(false);
    }
}