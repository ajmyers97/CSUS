/* This is my tester file to make sure you have completed the assignment correctly.
 * CSC 130 - Final Project
 * Matthew W. Phillips 2020 CSUS*/

package Main;

import java.util.Arrays;

public class Main{
	public static void main(String[] args){
		// Do NOT modifiy! This file provided just so you can see how I will grade the assignment. 
		// If you modify, your changes here will NOT be present during my test since I will use my version.
			/* BST STUFF */
		int[] a = {6, 4, 8, 3, 5, 7, 9};	// Tester array to insert into BST
		BST bst = new BST();
		for(int i = 0; i < a.length; i++)
			bst.insert(a[i]);			// Insert values here...
		System.out.print("toString:");
		p(bst.toString());				// Original inorder traversal for reference of how to display data
		System.out.print("preorderToString:");
		p(bst.preorderToString());		// Test preorder traversal
		System.out.print("postorderToString:");
		p(bst.postorderToString());		// Test postorder traversal
		System.out.print("levelorderToString:");
		p(bst.levelorderToString());	// Test levelorder traversal
			/* GRAPH STUFF */
		Graph gr = new Graph(6);
		gr.addWeightedEdge(0, 1, 1);	// Add various weighted edges...
		gr.addWeightedEdge(0, 2, 1);
		gr.addWeightedEdge(1, 2, 1);
		gr.addWeightedEdge(1, 3, 1);
		gr.addWeightedEdge(2, 4, 2);
		gr.addWeightedEdge(3, 5, 3);
		gr.addWeightedEdge(4, 5, 4);
		//System.out.print("ShortestPath:");
		p(gr.shortestPath(2, 5));		// Display the shortest path of nodes between the two vertices
			/* SORT STUFF */
		int[]b = {6, 4, 8, 3, 5, 7, 9};
		Sort.MergeSort(b);
		System.out.print("MergeSort:");
		p(Arrays.toString(b));
			/* 3 SUM STUFF */
		int[] c = {6, 4, 8, 3, 5, 7, 9};
		int[] d = Sum.threeSum(c, 13);
		System.out.print("threeSum:");
		p(Arrays.toString(d));
	}
	
	public static <E> void p(E item){
		System.out.println(item);
	}
}