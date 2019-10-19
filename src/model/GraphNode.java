package model;

import java.util.ArrayList;

public class GraphNode<T> {
	private T contents;
	private boolean visited;
	private ArrayList<GraphNode<T>> adjacentNodes;
	
	public GraphNode(T pContents) {
		this.contents = pContents;
		this.adjacentNodes = new ArrayList<GraphNode<T>>();
	}

	public T getContents() {
		return this.contents;
	}

	public ArrayList<GraphNode<T>> getAdjacentNodes() {
		return adjacentNodes;
	}
	
	public boolean isVisited() {
		return this.visited;
	}
	
	public void visit() {
		this.visited = true;
	}
	
	public void resetVisit() {
		this.visited = false;
	}
	
	public GraphNode<T> getAdjacent(T pValue) {
		for (GraphNode<T> node : this.adjacentNodes) {
			if (node.getContents().equals(pValue)) {
				return node;
			}
		}
		return null;
	}
	
	public void addEdge(GraphNode<T> pNode) {
		if (!this.adjacentNodes.contains(pNode)) {
			this.adjacentNodes.add(pNode);
		}
	}
}