package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.awt.Point;
import model.*;

public class MapManager extends Observable{
	private Graph<Point> graph;
	private Point last;
	//private GUI gui;
	
	public MapManager() {
		this.graph = new Graph<Point>();
	}
	
	public Point addPoint(int pX, int pY) {
		Point point = new Point(pX, pY);
		this.graph.addNode(point);
		
		
		if (this.last != null) {
			this.graph.addEdge(this.last, point);
			this.graph.addEdge(point, this.last);
		}
		
		this.last = point;
		setChanged();
		notifyObservers();
		return point;
	}
	
	public void addEdge(Point pPoint) {
		this.graph.addEdge(this.last, pPoint);
		this.graph.addEdge(pPoint, this.last);
	}
	
	public ArrayList<Point> getPath(Point pSource, Point pDestination){
		setChanged();
		notifyObservers();
		return this.graph.getPath(pSource, pDestination);
	} 
	
}
