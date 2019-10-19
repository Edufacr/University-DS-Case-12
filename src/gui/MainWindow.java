package gui;

import controller.MapManager;
import utilities.IConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MainWindow extends JFrame implements Observer, IConstants {
    private boolean done;
    private JPanel mainPanel;
    private MapManager manager;
    private Hashtable<JLabel,Point> hashtable;
    private MouseAdapter panelListener;
    private MouseAdapter inNodeListener;
    private BufferedImage image;
    public MainWindow(){
        //Frame
        super(WINDOW_NAME);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setPreferredSize(new Dimension(WINDOW_WIDTH,WINDOW_HEIGHT));

        hashtable = new Hashtable<JLabel,Point>();
        CreateManager();
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
        mainPanel.addMouseListener(panelListener);
    }
    
    private void loadImage() {
    	try {                
            this.image = ImageIO.read(new File(IMAGE_PATH));
         } catch (IOException ex) {
              this.image = null;
         }
    }
    
    private void CreateListeners(){
        panelListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                //manager.addPoint(x,y);
            }
        };
        inNodeListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Point point = hashtable.get((JLabel)e.getSource());
                //manager.addEdge(point);
            }
        };
    }
    private void CreateManager(){
        manager = new MapManager();
        manager.addObserver(this);
    }
    @Override
    public void update(Observable pObservable, Object pObjectPoint) {
        Point point = (Point) pObjectPoint;
        JLabel label = new JLabel();
        label.setBounds((int)point.getX()-15,(int)point.getY()-15,(int)point.getX()+15,(int)point.getY()+15);
        label.addMouseListener(inNodeListener);
        hashtable.put(label,point);
        add(label);
    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        main.setVisible(true);
    }



}
