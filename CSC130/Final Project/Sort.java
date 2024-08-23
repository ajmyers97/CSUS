//Project:		CSC130 Final Project
//Module:		Sort.java
//Author:		Allen Myers
//Date:			May 13th, 2020
//Purpose:		Implement MergeSort algorithm
//				Split array into left and right halfs
//				Recursive sort the left and half sides of the arrays
//				Merge the two halves and return
//
package Main;																//part of the main package
import java.util.Arrays;													//utilities from Java Arrays, used for copyOfRange

public class Sort{
	public static void MergeSort(int []a){
		if (a.length >= 2) {												//only sort if array has at least two elements
			int left[]  = Arrays.copyOfRange(a, 0, a.length / 2);			//split into left half of array
			int right[] = Arrays.copyOfRange(a, a.length / 2, a.length);	//split into right half of array
			
			MergeSort(left);												// recursively sort the left half
			MergeSort(right);												// recursively sort the right half
			
			int i=0;														//initialize variables
			int j = 0;														//
			int k = 0;														//
				
			for (i=0; i < a.length; i++) {									//until reach end of array, increment
				if (k >= right.length ||(j < left.length && left[j] < right[k])) {
					a[i] = left[j];											//move left
					j++;													//increment
				}															//end if
				else {														
					a[i] = right[k];										//move right
					k++;													//increment 
				}															//end else
			}																//end for
		}																	//end if
	}																		//end MergeSort
}																			//end class