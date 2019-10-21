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
import java.util.Iterator;
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
    private Hashtable<JLabel,Point> jLabelPointHashtable;
    private Hashtable<Point,JLabel> pointJLabelHashtable;
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

        jLabelPointHashtable = new Hashtable<JLabel,Point>();
        pointJLabelHashtable = new Hashtable<Point,JLabel>();
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
                if(!done){
                    int x = e.getX();
                    int y = e.getY();
                    manager.addPoint(x,y);
                }
            }
        };
        inNodeListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(done){
                    mainPanel.ChangeLabelColor(pointJLabelHashtable.get(manager.getLast()),Color.BLACK);
                	manager.setLast(jLabelPointHashtable.get((JLabel)e.getSource()));
                    mainPanel.ChangeLabelColor((JLabel)e.getSource(),Color.green);
                }
                else{
                    Point point = jLabelPointHashtable.get((JLabel)e.getSource());
                    addLine(point,manager.addEdge(point),Color.BLUE);
                }

            }
        };
        this.bFinished = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		done = true;
        		mainPanel.ChangeLabelColor(pointJLabelHashtable.get(manager.getHome()),Color.RED);
			};
        };
        
        this.bGenPath = new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		path = manager.getPath();
        		(new Thread(new Runnable(){
     			   public void run(){
     			    	try {
     			    		for (int pointIndex = 0; pointIndex < path.size()-1; pointIndex++) {
     			    			mainPanel.paintLine(path.get(pointIndex), path.get(pointIndex+1), Color.red,mainPanel.getGraphics());
     			    			Thread.sleep(SLEEP_TIME);
     		        		}
     			    		
     			    	}catch (Exception ex) {
     						System.out.println("F");
     					}	
     			   }
     			})).start();
			};
        };
    }
    private void AddLabel(Point pPoint){
        JLabel label = new JLabel("");
        label.setOpaque(true);
        label.setBackground(Color.BLACK);
        label.setBounds((int)pPoint.getX()-(NODE_RADIUS/2),(int)pPoint.getY()-(NODE_RADIUS/2),NODE_RADIUS,NODE_RADIUS);
        label.addMouseListener(inNodeListener);
        jLabelPointHashtable.put(label,pPoint);
        pointJLabelHashtable.put(pPoint,label);
        mainPanel.add(label);
    }
    private void CreateManager(){
        manager = new MapManager();
        manager.addObserver(this);
    }
    private void addLine(Point pStart, Point pEnd,Color pColor){
        mainPanel.AddLine(pStart,pEnd,pColor);
        mainPanel.repaint();
    }
    @Override
    public void update(Observable pObservable, Object pObjectPoint) {
        ArrayList<Point> list = (ArrayList<Point>) pObjectPoint;
        Point point = list.get(0);
        Point endPoint = list.get(1);
        AddLabel(point);
        if(endPoint != null){
            addLine(point,endPoint,Color.BLUE);
        }
        else{
            mainPanel.repaint();
        }

    }

    public static void main(String[] args) {
        MainWindow main = new MainWindow();
        
    }



}
