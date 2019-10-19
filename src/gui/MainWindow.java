package gui;

import utilities.IConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MainWindow extends JFrame implements Observer, IConstants {
    private boolean done;
    private JPanel mainPanel;
    private BufferedImage image;
    
    public MainWindow(){
        //Frame
        super(WINDOW_NAME);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));

        //MainPanel
        CreateMainPanel();
        //Button

        //add
        add(mainPanel);

    }
    private void CreateMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(WINDOW_WIDTH*MAINPANEL_WIDTHRATIO,WINDOW_HEIGHT*MAINPANEL_HEIGHTRATIO));
        //Aqui carga la foto
        loadImage();
        JLabel imageLabel = new JLabel(new ImageIcon(this.image.getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_DEFAULT)));
        mainPanel.add(imageLabel);
        
        
        
    }
    
    private void loadImage() {
    	try {                
            this.image = ImageIO.read(new File(IMAGE_PATH));
         } catch (IOException ex) {
              this.image = null;
         }
    }
    
    @Override
    public void update(Observable observable, Object o) {

    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        main.setVisible(true);
    }



}
