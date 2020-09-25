package a4;


/**
 * CS146 Assignment 3 Node class 
 * This class is used for undirected graphs represented as adjacency lists
 * @author andreopo
 * 
 * This Node class is also used in assignment 4
 *
 */
public class Node {
	private int name;
	private int distance = Integer.MAX_VALUE;
	public boolean visited = false;
	
	public int getName() {
		return name;
	}
	
	public void visit() {
		visited = true;
	}
	
	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	public int getDistance() {
		return distance;
	}

	public void setName(int name) {
		this.name = name;
	}

	private Node next;
	
	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Node(int s) {
		name = s;
		next = null;
		distance = Integer.MAX_VALUE;
		visited = false;
	}

	public Node(int s, Node n) {
		name = s;
		next = n;
		distance = Integer.MAX_VALUE;
		visited = false;
	}
}

