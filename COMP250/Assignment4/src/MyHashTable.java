import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashTable<K,V> implements Iterable<HashPair<K,V>>{
    // num of entries to the table
    private int numEntries;
    // num of buckets 
    private int numBuckets;
    // load factor needed to check for rehashing 
    private static final double MAX_LOAD_FACTOR = 0.75;
    // ArrayList of buckets. Each bucket is a LinkedList of HashPair
    private ArrayList<LinkedList<HashPair<K,V>>> buckets; 
    
    // constructor
    public MyHashTable(int initialCapacity) {
        // ADD YOUR CODE BELOW THIS
        
    	int size = initialCapacity;
    	
    	// to avoid mod 0 exception
    	if(size == 0) {
    		size += 1;
    	}
    	
    	this.numEntries = 0;
    	this.numBuckets = size;
    	this.buckets = new ArrayList<LinkedList<HashPair<K,V>>>(size);
    	
    	// populate arraylist
    	for(int i = 0; i < size; i++) {
    		this.buckets.add(new LinkedList<HashPair<K,V>>());
    	}
    	
        //ADD YOUR CODE ABOVE THIS
    }
    
    public int size() {
        return this.numEntries;
    }
    
    public int numBuckets() {
        return this.numBuckets;
    }
    
    /**
     * Returns the buckets variable. Useful for testing  purposes.
     */
    public ArrayList<LinkedList< HashPair<K,V> > > getBuckets(){
        return this.buckets;
    }
    /**
     * Given a key, return the bucket position for the key. 
     */
    public int hashFunction(K key) {
        int hashValue = Math.abs(key.hashCode())%this.numBuckets;
        return hashValue;
    }
    /**
     * Takes a key and a value as input and adds the corresponding HashPair
     * to this HashTable. Expected average run time  O(1)
     */
    public V put(K key, V value) {
        //  ADD YOUR CODE BELOW HERE
        
    	// check validity of input
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	// if the corresponding bucket is empty, create new HashPair<K,V> and add it in
    	if((this.buckets.get(this.hashFunction(key))).isEmpty()) {
    		this.buckets.get(this.hashFunction(key)).add(new HashPair<K,V>(key, value));
    		this.numEntries++;		// update num of entries
    		
    		// check current load factor and rehashing
    		if(((double)this.numEntries)/this.numBuckets > MAX_LOAD_FACTOR) {
    			rehash();							
    		}
    		
    		return null;	// no matching hashpair found in bucket
    	}
    	
    	// call hashfunction on key, and mod with numBuckets to find corresponding bucket
    	// iterating through entries of a bucket since the buckets themselves are linkedlists
    	for(HashPair<K,V> item: this.buckets.get(this.hashFunction(key))) {
    		
    		// if a hashpair with the key already exists
    		if ((item.getKey()).equals(key)){
    			V oldValue = item.getValue();		// the previous value
    			item.setValue(value);				// update value
    			return oldValue;					// return old value
    		}
    	}
    	
    	// no match for current key, create new HashPair<K,V> and add to bucket
    	(this.buckets.get(this.hashFunction(key))).add(new HashPair<K,V>(key, value));
    	this.numEntries++;		// new entry in hash table
    	
    	// check current load factor and rehashing
		if(((double)this.numEntries)/this.numBuckets > MAX_LOAD_FACTOR) {
			rehash();							
		}
    	
    	return null;
    	
        //  ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Get the value corresponding to key. Expected average runtime = O(1)
     */
    
    public V get(K key) {
        //ADD YOUR CODE BELOW HERE
        
    	// Case where key is null
    	if(key == null){
    		throw new IllegalArgumentException();
    	}
    	
    	// check if bucket is empty
    	if(this.buckets.get(this.hashFunction(key)).isEmpty()){
    		return null;		// nothing in bucket
    	}
    	
    	// for hashpairs in the corresponding bucket
    	for(HashPair<K,V> item: this.buckets.get(this.hashFunction(key))) {
    		
    		// the hashpair with key is found 
    		if((item.getKey()).equals(key)) {
    			return item.getValue();			// return value of item
    		}
    	}
  
    	return null;		// hashpair not found
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Remove the HashPair corresponding to key . Expected average runtime O(1) 
     */
    public V remove(K key) {
        //ADD YOUR CODE BELOW HERE
    	
    	// check validity of key
    	if(key == null) {
    		throw new IllegalArgumentException();
    	}

    	// check whether corresponding bucket is empty
    	if(this.buckets.get(this.hashFunction(key)).isEmpty()){
    		return null;	// bucket has no hashpairs so key not found
    	}
    	
    	// bucket is not empty, iterate through the linked list
    	int index = -1;
    	for(HashPair<K,V> item: this.buckets.get(this.hashFunction(key))){
    		
    		index++;	// item's index
    		
    		// if the hashpair with key is found, remove hashpair
    		if((item.getKey()).equals(key)){
    			this.buckets.get(this.hashFunction(key)).remove(index);
    			this.numEntries--;		// removed an entry
    			return item.getValue();
    		}
    	}
    	
    	return null;	// hashpair not found
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    // Method to double the size of the hashtable if load factor increases
    // beyond MAX_LOAD_FACTOR.
    // Made public for ease of testing.
    
    public void rehash() {
        //ADD YOUR CODE BELOW HERE
        
    	// double capacity
    	MyHashTable<K,V> newtable = new MyHashTable<K,V>(this.numBuckets * 2);
    	
    	// iterate through all buckets in arraylist
    	for(LinkedList<HashPair<K,V>> list: this.buckets) {
    		
    		// iterate through linked list
    		for(HashPair<K,V> item: list) {
    			
    			// add hashpair to new hash table
    			newtable.put(item.getKey(), item.getValue());
    		}
    	}
    	
    	this.buckets = newtable.buckets;
    	this.numBuckets = this.numBuckets * 2;			// doubled capacity

    	return;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Return a list of all the keys present in this hashtable.
     */
    
    public ArrayList<K> keys() {
        //ADD YOUR CODE BELOW HERE

    	// each entry has a key
    	ArrayList<K> keys = new ArrayList<K>(this.numEntries);
    	
    	// iterate through hash table
    	for(LinkedList<HashPair<K,V>> list: this.buckets) {
    		for(HashPair<K,V> item: list) {
    			keys.add(item.getKey());		// add key to arraylist
    		}
    	}
    	
    	return keys;
    	
        //ADD YOUR CODE ABOVE HERE
    }
    
    /**
     * Returns an ArrayList of unique values present in this hashtable.
     * Expected average runtime is O(n)
     */
    public ArrayList<V> values() {
        //ADD CODE BELOW HERE
    	
    	ArrayList<V> values = new ArrayList<V>();	// stores unique values
    	MyHashTable<V,K> valuetable = new MyHashTable<V,K>(this.numBuckets);	// hash by value
    	
    	// iterate through hash table and add values to new valuetable, sorted by value
    	for(LinkedList<HashPair<K,V>> list: this.buckets) {
    		for(HashPair<K,V> item: list) {
    			valuetable.put(item.getValue(), item.getKey());
    		}
    	}
    
    	// iterate through valuetable and add all values (which are values but used as keys)
	    for(LinkedList<HashPair<V,K>> list: valuetable.buckets) {
			for(HashPair<V,K> item: list) {
				values.add(item.getKey());
			}
		}
    	
    	return values;
    	
        //ADD CODE ABOVE HERE
    }
    
    @Override
    public MyHashIterator iterator() {
        return new MyHashIterator();
    }
    
    
    private class MyHashIterator implements Iterator<HashPair<K,V>> {
        private LinkedList<HashPair<K,V>> entries;
        
        private MyHashIterator() {
            //ADD YOUR CODE BELOW HERE
            
        	entries = new LinkedList<HashPair<K,V>>();
        	// iterate through hash table and add all hashpairs into entries
        	for(LinkedList<HashPair<K,V>> list: MyHashTable.this.getBuckets()) {
        		for(HashPair<K,V> item: list) {
        			entries.add(item);
        		}
        	}
     	
            //ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public boolean hasNext() {
            //ADD YOUR CODE BELOW HERE
            
        	return (!entries.isEmpty());	// check if linked list is empty

        	//ADD YOUR CODE ABOVE HERE
        }
        
        @Override
        public HashPair<K,V> next() {
            //ADD YOUR CODE BELOW HERE
        	
            return entries.removeFirst();		// sends out the first and removes the element
           
            //ADD YOUR CODE ABOVE HERE
        }
        
    }
}
