package model;

import java.util.ArrayList;
import java.util.HashMap;

public class Graph<T> {
	private ArrayList<GraphNode<T>> nodes;
	private HashMap<T, GraphNode<T>> directory;
	
	public Graph() {
		this.nodes = new ArrayList<GraphNode<T>>();
	}
	
	public void addNode(T pValue) {
		if (!directory.containsKey(pValue)) {
			GraphNode<T> node = new GraphNode<T>(pValue);
			nodes.add(node);
			directory.put(pValue, node);
		}
	}
	
	public void addEdge(T pValue1, T pValue2) {
		if (this.directory.containsKey(pValue1) && this.directory.containsKey(pValue2)) {
			GraphNode<T> node1 = directory.get(pValue1);
			GraphNode<T> node2 = directory.get(pValue2);
			node1.addEdge(node2);
			node2.addEdge(node1);
		}
	}
}
