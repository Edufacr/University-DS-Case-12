package gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImagePanel extends JPanel {
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
            pGraphics.setColor(line.getColor());
            pGraphics.drawLine((int)line.getStart().getX(),(int)line.getStart().getY(),(int)line.getEnd().getX(),(int)line.getEnd().getY());
        }
    }
    public void AddLine(Point pStart, Point pEnd,Color pColor){
        lines.add(new Line(pStart,pEnd,pColor));
    }
    public  void ChangeLabelColor(JLabel pLabel,Color pColor){
        pLabel.setBackground(pColor);
        repaint();
    }

}
