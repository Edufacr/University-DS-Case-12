package gui;

import controller.MapManager;
import utilities.IConstants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Hashtable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

public class MainWindow extends JFrame implements Observer, IConstants {
    private boolean done;
    private ImagePanel mainPanel;
    private JPanel buttonPanel;
    private MapManager manager;
    private Hashtable<JLabel,Point> hashtable;
    private ArrayList<Point> path;
    private MouseAdapter panelListener;
    private MouseAdapter inNodeListener;
    private ActionListener bFinished;
    private ActionListener bGenPath;
    private BufferedImage image;
    
    public MainWindow(){
        //Frame
        super(WINDOW_NAME);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        hashtable = new Hashtable<JLabel,Point>();
        CreateListeners();
        CreateManager();
        CreateMainPanel();
        CreateButtonPanel();
        
        add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);
        setVisible(true);
        

    }
    private void CreateMainPanel(){
        mainPanel = new ImagePanel();
        mainPanel.setLayout(null);
        mainPanel.setPreferredSize(new Dimension(WINDOW_WIDTH*MAINPANEL_WIDTHRATIO,WINDOW_HEIGHT*MAINPANEL_HEIGHTRATIO));

        loadImage();
        mainPanel.setImage(image.getScaledInstance(WINDOW_WIDTH, WINDOW_HEIGHT, Image.SCALE_DEFAULT));
        mainPanel.addMouseListener(panelListener);
    }
    
    private void CreateButtonPanel() {
    	//Buttons
        JButton finished = new JButton("Finish Adding");
        finished.setSize(15,  4);
        finished.addActionListener(bFinished);
        
        JButton path = new JButton("Get Path");
        path.setSize(10, 4);
        path.addActionListener(bGenPath);
        
        this.buttonPanel = new JPanel();
        this.buttonPanel.setSize(20, 10);
        //this.buttonPanel.
        this.buttonPanel.add(finished);
        this.buttonPanel.add(path);
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
                System.out.println("AddNode");
                int x = e.getX();
                int y = e.getY();
                manager.addPoint(x,y);
            }
        };
        inNodeListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("AddEdge");
                Point point = hashtable.get((JLabel)e.getSource());
                manager.addEdge(point);
            }
        };
        this.bFinished = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		done = true;
			};
        };
        
        this.bGenPath = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
//        		path = manager.getPath();
//        		this.paintPath();
			};
        };
    }
    private void CreateManager(){
        manager = new MapManager();
        manager.addObserver(this);
    }
    @Override
    public void update(Observable pObservable, Object pObjectPoint) {
        System.out.println("Update");
        Point point = (Point) pObjectPoint;
        JLabel label = new JLabel("");
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setBounds((int)point.getX()-NODE_RADIUS,(int)point.getY()-NODE_RADIUS,NODE_RADIUS,NODE_RADIUS);
        label.addMouseListener(inNodeListener);
        hashtable.put(label,point);
        mainPanel.add(label);
        label.setVisible(true);
        mainPanel.repaint();
    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        
    }



}
