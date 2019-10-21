package gui;

import utilities.IConstants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImagePanel extends JPanel implements IConstants {
    private Image image;
    private ArrayList<Line> lines;
    void setImage(Image image) {
        this.image = image;
    }
    ImagePanel(){
        super();
        lines = new ArrayList<Line>();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
        PaintLines(g);
    }
    private void PaintLines(Graphics pGraphics){
        for (Line line:lines ){
            paintLine(line.getStart(),line.getEnd(),line.getColor(),pGraphics);
        }
    }
    public void paintLine(Point pStart, Point pEnd,Color pColor,Graphics pGraphics){
        Graphics2D g = (Graphics2D) pGraphics;
        g.setColor(pColor);
        g.setStroke(new BasicStroke(STROKE_WIDTH));
        g.drawLine((int)pStart.getX(),(int)pStart.getY(),(int)pEnd.getX(),(int)pEnd.getY());
    }
    public void AddLine(Point pStart, Point pEnd,Color pColor){
        lines.add(new Line(pStart,pEnd,pColor));
    }
    public  void ChangeLabelColor(JLabel pLabel,Color pColor){
        pLabel.setBackground(pColor);
        repaint();
    }

}
