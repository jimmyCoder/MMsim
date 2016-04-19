/**
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;


public class LRU 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Boolean b = new Boolean(false);
		Scanner in = new Scanner(System.in);
		int setSize = 0; 
		
		/**
		 * setSize is set by the user, and should normally be 8-15. 
		 * Some room is left for experimentation. 
		 */
		
		System.out.println("Please type the size of the working set:");
		try
		{
			setSize = in.nextInt();
			if(setSize > 4 && setSize < 21)
				b = true;
			else
				System.out.println("The size of the working set should be between 8-15");
		}catch(Exception e)
		{
			System.out.println("The size of the working set has to be an integer preferably between 8-15. Try again ");
		}
		
		System.out.println("Thanks");
		in.close();
		/**
		 *create a program list  
		 * add n random integers less than 100 to the list 
		 */
		List<Integer> program = new ArrayList<Integer>();
		//for(int i =0;i<15;i++){
			//program.add((int)(Math.random()*100));
			int[] array = {1,2,3,4,5,6,7,10,9,10};
			for(int i =0;i<array.length;i++){
				program.add((array[i]));
		}
		System.out.println(program.toString());
		int pageFaults = lru(program, setSize);
		System.out.println("For program size: " + program.size() + ",  "+  pageFaults +" page faults occured with a resident set size of:  " +setSize);
		

	}

	/**
	 * lru method that accept a list and resident set size as parameter 
	 * make a priority queue of the resident set
	 * if a page is in the resident set, check if its equal with the new page, and if it is, increment its useCount and set flag to true
	 * if page is not flagged, increment pages.if resident set is greater than the set size,take out the least recently used, create a new page and put it in the resident set 
	 */
	private static int lru(shortProgram program, int setSize) 
	{
		PriorityQueue<Page> resSet = new PriorityQueue<Page>();
		int pageFaults=0;
		boolean flag;
		for(int i=0; i< program.size(); i++){
			flag=false;
			for(Page p : resSet){
				if(p.equals(program.getIndex(i))) {
					p.access();
					flag=true;
					break;
				}
			}
			if(!flag){
				pageFaults++;
				if(resSet.size()>=setSize){
					//take out the least recently used page and put a new one in
					resSet.poll();
					
				}
				Page p = new Page(program.getIndex(i));
				resSet.add(p);
			}
		}
		return pageFaults;
	}

}