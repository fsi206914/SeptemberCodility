// you can also use imports, for example:
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.*;

class Solution {
    
    public static HashSet<Integer> forwardSet;
    public static ArrayList< HashSet<Integer> > backward;
        
    
    public static int Number = 0;
	public static int GlobalLength;
	public static int CurrNumber = 0;
    public static int match(int[]A, int forw, int back){

		CurrNumber = 0;
    	if(backward.get( GlobalLength- back -1).size() >  forwardSet.size() )
            return 1;
    	if(backward.get( GlobalLength- back -1).size() <  forwardSet.size() )
            return 2; 

   	    Iterator iter = forwardSet.iterator();
        while(iter.hasNext() ){
       		Object obj = iter.next();
        	if(backward.get(GlobalLength-back - 1).contains ( (Integer)obj ) == false ) return 3;
        }
        
        while(back>=0){
            if( backward.get(GlobalLength - back - 1).size() == forwardSet.size()){
                Number++; back--;
				CurrNumber++;
            }
            else break;
        }
        return 1;
    }
    
    public void copyHashSet( HashSet<Integer> src, HashSet<Integer> dest){
    
    Iterator iter = src.iterator();
        while(iter.hasNext() ){
        Object object = iter.next();
        dest.add( (Integer)object  );
        }
    
    }
    
    public static void print_hashSet(HashSet<Integer> test){
	Iterator iter = test.iterator();
	//System.out.println("access print hashset :"+ test.size());
	while(iter.hasNext() ){
	System.out.println("access print hashset");
        Object object = iter.next();
        System.out.print( (Integer)object + "  ");
        }
        System.out.println( "  ");

    }

    public int solution(int[] A) {
    
        int length = A.length;
		GlobalLength = length;
        if(length ==1 )return 1;
        
        backward = new ArrayList< HashSet<Integer> >();
 		forwardSet = new HashSet<Integer>();       
        int forw_point = 0; int back_point = length-1;
        int state;
        backward.add( new HashSet<Integer>());
		forwardSet.add(A[0]);
        backward.get(0).add(A[length-1]);
        
        for(int i=length-1-1; i>=0; i--){
        	backward.add( new HashSet<Integer>());
			copyHashSet(backward.get( length -i - 2 ), backward.get( length -i - 1)  );
			backward.get( backward.size()-1 ).add(A[i]);
        }
       
		boolean notAdd = false; 
        while(forw_point < length && back_point >=0 ){
			if(Number>1000000000) return 1000000000;

			if(forw_point > 0 && notAdd ){
			//if(forw_point > 0 ){

				Number += CurrNumber;
				state = 1;
			}
			else{
        	state = match(A, forw_point, back_point);
        	}
                if(state == 1){
				forw_point++;
				if(forw_point >=length) break;
				if(forwardSet.contains(A[forw_point])  ) {notAdd = true;   }
                else{
				forwardSet.add(A[forw_point]);
				notAdd = false;
				}

                continue;
            	}
            
                if(state == 2){
			    	back_point--;
					notAdd = false; 
 			    	if(back_point <0) break;
               	    continue;
            	}          

            
                if(state == 3){
            	back_point--; 
                    
				forw_point++;
				if(forw_point >=length) break;
				if(forwardSet.contains(A[forw_point])  ) {notAdd = true;   }
                else{
				forwardSet.add(A[forw_point]);
				notAdd = false;
				}

                continue;
            	}    
        }
        return Number;
    
    }

public static void main(String[] args){

	int A[] = {4,4,7,4,1,3,2,7,6,1,2,4};
	//int A[] = {3,5,7,3,3,5};
	Solution Solu = new Solution();
	System.out.println(Solu.solution(A));

}
}

