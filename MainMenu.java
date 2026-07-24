import java.awt.Font;

import javax.swing.*;

public class MainMenu extends JFrame{
    public MainMenu(){
        //Settings for The Main Frame
        this.setTitle("MCO2 - MAZE");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 600);
        

        //Items inside the Main Frame
        JLabel titleText = new JLabel("MCO2 - MAZE");
        titleText.setHorizontalAlignment(JLabel.CENTER);
        titleText.setFont(new Font("Impact", Font.PLAIN, 100));
        this.add(titleText);
        

        /*
        //Cool GIF Moving
        JLabel gif = new JLabel(new ImageIcon("Something.gif"));
        this.add(gif);

        //Changes Application Icon
        ImageIcon icon = new ImageIcon("Logo.png");
        this.setIconImage(icon.getImage());
         */

        this.setVisible(true);
    }
}