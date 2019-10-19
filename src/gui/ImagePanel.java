package gui;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {
    Image image;

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,0,0,null);
    }
}
