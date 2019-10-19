package controller;

import java.util.ArrayList;
import java.util.Observable;
import java.awt.Graphics;
import model.*;

public class MapManager extends Observable{
	private Graph<Graphics> graph;
	//private GUI gui;
	
	public MapManager() {
		this.graph = new Graph<Graphics>();
	}
	
	public void addPoint(Graphics pFirst) {
		this.graph.addNode(pFirst);
		setChanged();
		notifyObservers();
	}
	
	public void addPoint(Graphics pPrevious, Graphics pNew) {
		this.graph.addNode(pNew);
		this.graph.addEdge(pPrevious, pNew);
		this.graph.addEdge(pNew, pPrevious);
		setChanged();
		notifyObservers();
	}
	
	public ArrayList<Graphics> getPath(Graphics pSource, Graphics pDestination){
		setChanged();
		notifyObservers();
		return this.graph.getPath(pSource, pDestination);
	} 
	
}
