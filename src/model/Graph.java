package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ArrayDeque;

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
	
	public void clearVisits() {
		for (GraphNode<T> node : this.nodes) {
			node.resetVisit();
		}
	}
	
	public void clear() {
		this.nodes.clear();
		this.directory.clear();
	}
	
	public void addEdge(T pValue1, T pValue2) {
		if (this.directory.containsKey(pValue1) && this.directory.containsKey(pValue2)) {
			GraphNode<T> node1 = directory.get(pValue1);
			GraphNode<T> node2 = directory.get(pValue2);
			node1.addEdge(node2);
			node2.addEdge(node1);
		}
	}
	
	public ArrayList<T> getPath(T pValue1, T pValue2){
		if (this.directory.containsKey(pValue1) && this.directory.containsKey(pValue2)) {
			GraphNode<T> node1 = directory.get(pValue1);
			GraphNode<T> node2 = directory.get(pValue2);
			ArrayList<T> invPath = new ArrayList<T>();
			ArrayDeque<GraphNode<T>> queue = new ArrayDeque<GraphNode<T>>();
			
			queue.addLast(node1);
			
			while(!queue.isEmpty()) {
				GraphNode<T> current = queue.removeFirst();
				current.visit();
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
			invPath = generatePath(invPath, node2);
			ArrayList<T> path = new ArrayList<T>();
			for (int invPathIndex = invPath.size() - 1; invPathIndex >= 0; invPathIndex--) {
				path.add(invPath.get(invPathIndex));
			}
			return path;
			
		}
		return null;
	}
	
	public T getHome() {
		return this.nodes.get(0).getContents();
	}
	
	private ArrayList<T> generatePath(ArrayList<T> pArray, GraphNode<T> pNode){
		if (pNode == null) {
			return pArray;
		} else {
			pArray.add(pNode.getContents());
			return generatePath(pArray, pNode.getLast());
		}
	}
	
	
	public void print() {
		for (GraphNode<T> node : this.nodes) {
			System.out.println("Nodo: " + node.getContents());
			System.out.print("Adyacentes: ");
			for (GraphNode<T> adjNode : node.getAdjacentNodes()) {
				System.out.print("" + adjNode.getContents() + " ");
			}
			System.out.println();
			System.out.println();
		}
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
		
		ArrayList<String> path = g.getPath("A", "E");
	
		System.out.println(path);

	}
}
