//Project:		CSC130 Final Project
//Module:		Sum.java
//Author:		Allen Myers
//Date:			May 13th, 2020
//Purpose:		Implement ThreeSum algorithm using a HashMap
//				Return three values which equal to a sum as requested in Main
//
package Main;																//part of Main package
import java.util.Map;														//import the Java API Map Utility
import java.util.HashMap;													//import the Java API Hashmap Utility

public class Sum{															//create the sum class
    public static int[] threeSum(int[] a, int sum) {						//feed array and sum into threeSum
		Map<Integer, Integer> mapSum = new HashMap<>();						//create new HashMap: mapSum
		mapSum.put(0, a[0]);												//move the values from array into mapSum
		int i = 1;															//initialize incementors: i=1 
		int j = 2;															//initialize incementors: j=2
		
		while(i<a.length) {													//
			while(j<a.length) {												//
				if (mapSum.containsValue(sum - a[i] - a [j])) {				//
		        	return new int[] {(sum - a[i] - a[j]), a[i], a[j]};		//
				}															//
	        	j++;														//
			}																//
			i++;															//
			mapSum.put(i, a[i]);											//
		}																	//end while
		return null;														//return value back to main
	}																		//end threeSum
}																			//end class