package a4;

import static org.junit.Assert.assertEquals;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * CS146 Assignment 4  
 * This class is used for undirected graphs represented as adjacency lists
 * @author andreopo
 *
 * Name: Bill Li
 * 
 * Student ID: 014110824
 * 
 * Description of solution:
 * 
 * **********ASSIGNMENT 3**********
 * The implemented createAdjacencyList() method reads the file "3890.edges" and 
 * creates a graph from it, using the provided Node class to make a Node array.
 * 
 * The areFriends() method first calls createAdjacencyList() to make the graph, 
 * and then looks for B in the A element of adjacencyList. Returns true if B is
 * found and return false if not.
 * 
 * **********ASSIGNMENT 4**********
 * The BFStraversal() method implements a breadth first search to list the 
 * distance from each node to the given node. It is iterative and implements
 * a queue to do a level order search. When the same Node has been seen twice
 * it is then printed out with the given distance.
 * 
 * 
 */

public class NetworkAdjList {

	private static Node[] adjacencyList;
	
	public static void createAdjacencyList() {
		
		//creation of graph with 5000 possible nodes to account for file
		adjacencyList = new Node[5000];
		for(int i = 0; i < 5000; i++) {
			adjacencyList[i] = new Node(0);
		}
		
		// reading file
		//file reading element taken from https://www.baeldung.com/java-file-to-arraylist
		ArrayList<Integer> result = new ArrayList<Integer>();//= Files.readAllLines(Paths.get("nums.txt"));
		try {
			BufferedReader br = new BufferedReader(new FileReader("3980.edges"));
			while (br.ready()) {
				
				//process to turn each line into two ints
				String [] line = br.readLine().split(" ");
				int A = Integer.parseInt(line[0]);
				int B = Integer.parseInt(line[1]);
				
				//creation of nodes
				Node nodeA = new Node(A);
				Node nodeB = new Node(B);
				
				//adding Nodes to array
				//if node A doesnt exist in array, adds to array
				if(adjacencyList[A].getName() != nodeA.getName()) { 
					adjacencyList[A] = nodeA;
					nodeA.setNext(nodeB);
				}
				//else checks where node B needs to be and sets it accordingly
				else {
					Node temp = adjacencyList[A];
					while(temp.getNext() != null) {
						temp = temp.getNext();
					}
					temp.setNext(nodeB);
				}
			}
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//code to print the graph
		//for(Node i: adjacencyList) {
		//if(i.getName() != 0)
		//	System.out.print(i.getName());
		//Node temp = i;
		//	while(temp.getNext() != null) {
		//		System.out.print(" " + temp.getNext().getName());
		//		temp = temp.getNext();
		//	}
		//	System.out.println();
		//}
	}
	
	//checks if two users with ID A and B are friends
	public static boolean areFriends(int A, int B) {
		
		//first step create graph
		createAdjacencyList();
		
		//check for B in element A
		Node temp = adjacencyList[A];
		while(temp.getNext() != null) {
			if(temp.getNext().getName() == B)
				return true;
			temp = temp.getNext();
		}
		return false;
	}

	//BFS as implemented by CLRS
	public static void BFStraversal(int start) {
		int infinity = Integer.MAX_VALUE;
		
		//set distance of start to zero
		adjacencyList[start].setDistance(0);
		
		//created empty queue
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		//enqueue start Node
		queue.add(start);
		
		//while queue is not empty
		while(queue.isEmpty() == false) {
			
			//dequeue head of list
			Node temp = adjacencyList[queue.poll()];
			Node next = temp.getNext();
			
			while(next != null) {
				//test if we have seen Node. If so, give it a distance, add to queue and print
				if(adjacencyList[next.getName()].getDistance() == Integer.MAX_VALUE) {
					int distance = adjacencyList[temp.getName()].getDistance()+1;
					adjacencyList[next.getName()].setDistance(distance);
					queue.add(next.getName());
					//printing Node 
					System.out.println(next.getName() + " is at a distance of " + distance + " from " + start);
				}
				next = next.getNext();
			}
		}
	}

	public static void main(String[] args) {
		/**
		 * These test cases assume the file 3980.edges was used
		 */
		System.out.println("Testing...");
		assertEquals(areFriends(4038, 4014), true);
		System.out.println("1 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(3982, 4037), true);
		System.out.println("2 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4030, 4017), true);
		System.out.println("3 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4030, 1), false);
		System.out.println("4 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(1, 4030), false);
		System.out.println("5 of 7");

		System.out.println("Testing...");
		assertEquals(areFriends(4003, 3980), true);
		System.out.println("6 of 7");
		
		System.out.println("Testing...");
		assertEquals(areFriends(3985, 4038), false);
		System.out.println("7 of 7");
		System.out.println("Success!");
		
		BFStraversal(3980);
	}

}
