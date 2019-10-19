package gui;

import java.awt.*;

public class Line {
    private Point start;
    private Point end;
    private Color color;

    public Line(Point start, Point end, Color color) {
        this.start = start;
        this.end = end;
        this.color = color;
    }

    Point getStart() {
        return start;
    }

    Point getEnd() {
        return end;
    }

    Color getColor() {
        return color;
    }
}
