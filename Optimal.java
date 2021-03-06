import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

public class Optimal
{
    public static int Optimal(shortProgram program, int resSetMaxSize )
	{
		int pageFaults =0;
		Set<Integer> resSet = new HashSet<Integer>(resSetMaxSize, (float)2.0);
		Set<Integer> elimSet = new HashSet<Integer>(resSetMaxSize, (float)2.0);	
		
		program.shuffle();
		program.shuffle();
		//program.show();
		/**
		 * outer loop in which we go through the pages required by our 'program'
		 * y is a counter for how many slots are filled in resident set
		 */
		int resSetCurrSize = 0;
		for(int i=0; i< program.size(); i++)
		{ 
			  
			if ( !resSet.contains(program.getIndex(i)))
			{
				pageFaults++;

				if (resSetCurrSize <= resSetMaxSize)
				{
					resSet.add(program.getIndex(i));
					resSetCurrSize++;
				}
				else // a page needs to be replaced
				{
					Iterator<Integer> iter = resSet.iterator();

					while (iter.hasNext())	
						elimSet.add(iter.next());
					
					int j=i+1;	
					
					while(j < program.size() && elimSet.size()>1)
					{
						if(elimSet.contains(program.getIndex(j)))
							elimSet.remove(program.getIndex(j));
						j++;					
					}
					
					iter = elimSet.iterator();
					resSet.remove(iter.next());
					elimSet.clear();
					resSet.add(program.getIndex(i));
				}
			}	
		}
		return pageFaults;	
	}
}
