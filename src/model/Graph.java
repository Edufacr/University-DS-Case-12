package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;
import java.util.Stack;

public class Graph<T> {
	private ArrayList<GraphNode<T>> nodes;
	private HashMap<T, GraphNode<T>> directory;
	
	public Graph() {
		this.nodes = new ArrayList<GraphNode<T>>();
		this.directory = new HashMap<T, GraphNode<T>>();
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
	
	public Stack<T> getPath(T pValue1, T pValue2){
		if (this.directory.containsKey(pValue1) && this.directory.containsKey(pValue2)) {
			GraphNode<T> node1 = directory.get(pValue1);
			GraphNode<T> node2 = directory.get(pValue2);
			Stack<T> path = new Stack<T>();
			ArrayDeque<GraphNode<T>> queue = new ArrayDeque<GraphNode<T>>();
			
			queue.addLast(node1);
			
			while(!queue.isEmpty()) {
				GraphNode<T> current = queue.removeFirst();
				for (GraphNode<T> adjNode : current.getAdjacentNodes()) {
					if (!adjNode.isVisited()) {
						adjNode.visit();
						adjNode.setLast(current);
						queue.addLast(adjNode);
					}
					if (adjNode.equals(node2)) {
						queue.clear();
						break;
					}
				}
			}
			
			GraphNode<T> current = node2;
			do {
				path.push(current.getContents());
				current = current.getLast();
			} while(current.getLast() != null);
			
			return path;
			
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		Graph<String> g = new Graph<String>();
		g.addNode("A");
		g.addNode("B");
		g.addNode("C");
		g.addNode("D");
		g.addNode("E");
		
		g.addEdge("A", "C");
		g.addEdge("A", "D");
		g.addEdge("B", "C");
		g.addEdge("B", "E");
		
		Stack<String> path = g.getPath("A", "D");
		
		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.pop());
		}
	}
}
