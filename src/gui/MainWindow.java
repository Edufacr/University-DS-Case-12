package gui;

import utilities.IConstants;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class MainWindow extends JFrame implements Observer, IConstants {
    private boolean done;
    private JPanel mainPanel;
    MainWindow(){
        //Frame
        super(WINDOW_NAME);
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
    }
    @Override
    public void update(Observable observable, Object o) {

    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        main.setVisible(true);
    }



}
