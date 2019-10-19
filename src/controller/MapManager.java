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
	
	public void setLast(Point pPoint) {
		this.last = pPoint;
	}

	public Point addPoint(int pX, int pY) {
		Point point = new Point(pX, pY);
		this.graph.addNode(point);
		
		
		if (this.last != null) {
			this.graph.addEdge(this.last, point);
			this.graph.addEdge(point, this.last);
		}
		
		this.last = point;
		ArrayList<Point> list = new ArrayList<Point>();
		list.add(point);
		list.add(last);
		setChanged();
		notifyObservers(list);
		return point;
	}
	
	public void addEdge(Point pPoint) {
		this.graph.addEdge(this.last, pPoint);
		this.graph.addEdge(pPoint, this.last);
		
	}
	
	public ArrayList<Point> getPath(){
		Point source = this.graph.getHome();
		ArrayList<Point> path = this.graph.getPath(source, last);
		return path;
	} 
	
}
