package birds;

//Completed Functionality - James 03 24
public class DataKey {
    
        //Variables
        //Bird Sizes; Large 3, Medium 2, Small 1
        private int birdSize;
        private String birdName;
    
	// default constructor
	public DataKey() {
            
	}
        
	// other constructors
        public DataKey(String bird, int classSize){
            birdSize = classSize;      
            birdName = bird;
        }
        
	/**
	 * Returns 0 if this DataKey is equal to k, returns -1 if this DataKey is smaller
	 * than k, and it returns 1 otherwise. 
         * @param k
	 */
	public int compareTo(DataKey k) {
            if (birdSize == k.getBirdSize()){
                return birdName.compareTo(k.getBirdName());     //comapre for strings
                //
                //
                /* Old
                if (birdName.compareTo(k.getBirdName())==-1){
                    /* s1.compareTo(s2) {String.compareTo method}
                    The result is a negative integer if this String object lexicographically
                    precedes the argument string. The result is a positive integer if this 
                    String object lexicographically follows the argument string. The result
                    is zero if the strings are equal; compareTo returns 0 exactly when the 
                    equals(Object) method would return true//*
                    return -1;                                  //is smaller size & preceds lexi order
                }
                if(birdName.equals(k.getBirdName())){
                    return 0;                               //is equal, same name and size
                }*/
            }
            if (birdSize < k.getBirdSize()){
               return -1;                                    //   Inverted                    
            }
            return 1;                                                        
	}
        
        public String getBirdName(){
            return birdName;
        }
        
        public int getBirdSize(){
            return birdSize;
        }
}
