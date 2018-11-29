import java.util.ArrayList;

public class MusicStore {
    //ADD YOUR CODE BELOW HERE
	
	// need an arraylist for artists and year because all songs that belong the same artist and same year have to be returned
    private MyHashTable<String,Song> database_title;
    private MyHashTable<String,ArrayList<Song>> database_artist;
    private MyHashTable<Integer,ArrayList<Song>> database_year;
    
    //ADD YOUR CODE ABOVE HERE
    
    
    public MusicStore(ArrayList<Song> songs) {
        //ADD YOUR CODE BELOW HERE

    	this.database_title = new MyHashTable<String,Song>(songs.size());	// by title
    	this.database_artist = new MyHashTable<String,ArrayList<Song>>(songs.size());	// by artist
    	this.database_year = new MyHashTable<Integer,ArrayList<Song>>(songs.size());	// by year
    	
    	// populate arraylists
    	for(Song item: songs)
    	{
    		addSong(item);
    	}
        //ADD YOUR CODE ABOVE HERE
    }
    
    
    /**
     * Add Song s to this MusicStore
     */
    public void addSong(Song s) {
        // ADD CODE BELOW HERE
        
    	// check if song is valid
    	if(s == null || s.getTitle() == null || s.getArtist() == null || s.getYear() < 0) {
    		throw new IllegalArgumentException();
    	}
  
    	// hash table by title
    	this.database_title.put(s.getTitle(), s);
    	
    	// for hash table by artist
    	// check if song with artist already exists since implementation of put() overwrites the old value
    	if(this.database_artist.get(s.getArtist()) != null) {
    		
    		// hashpair with same key already exists
    		
    		// retrieve arraylist
    		ArrayList<Song> temp = this.database_artist.get(s.getArtist());
    		
    		// add song to arraylist of songs with same artist
    		temp.add(s);
    		
    		// call put() to overwrite arraylist of songs for the same artist
    		// this way num of entries is updated
    		this.database_artist.put(s.getArtist(), temp);
    	} else {
    		
    		// the bucket for that artist is empty, directly call put()
    		ArrayList<Song> arr = new ArrayList<Song>();		// create arraylist
    		arr.add(s);											// add song to arraylist
    		this.database_artist.put(s.getArtist(), arr);		// call put() to store in hash table	
    	}
    	
    	// for hash table by year
    	// check if song with specific year already exists since implementation of put() overwrites the old value
    	if(this.database_year.get(s.getYear()) != null) {
    		
    		// hashpair with same key already exists
    		
    		// retrieve arraylist and add song to arraylist of songs with same year
    		ArrayList<Song> temp = this.database_year.get(s.getYear());
    		temp.add(s);
    		
    		// call put() to overwrite arraylist of songs for the same year
    		// this way num of entries is updated
    		this.database_year.put(s.getYear(), temp);
    	} else {
    		
    		// the bucket for that artist is empty, directly call put()
    		ArrayList<Song> arr = new ArrayList<Song>();		// create arraylist
    		arr.add(s);											// add song to arraylist
    		this.database_year.put(s.getYear(), arr);		// call put() to store in hash table	
    	}
    	
    	return;
    	
        // ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for Song by title and return any one song 
     * by that title 
     */
    public Song searchByTitle(String title) {
        //ADD CODE BELOW HERE
        
    	// check for valid input
    	if(title == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	return this.database_title.get(title);		// returns Song object or null if not found
    	
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicStore for song by `artist' and return an 
     * ArrayList of all such Songs.
     */
    public ArrayList<Song> searchByArtist(String artist) {
        //ADD CODE BELOW HERE
        
    	// check validity of input
    	if(artist == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	return this.database_artist.get(artist);		// returns an arraylist of songs associated with the artist
    	
        //ADD CODE ABOVE HERE
    }
    
    /**
     * Search this MusicSotre for all songs from a `year'
     *  and return an ArrayList of all such  Songs  
     */
    public ArrayList<Song> searchByYear(Integer year) {
        //ADD CODE BELOW HERE

    	// check validity of input
    	if(year.compareTo(new Integer(0)) < 0 || year == null) {
    		throw new IllegalArgumentException();
    	}
    	
    	return this.database_year.get(year);		// returns an arraylist of songs associated with the year    	
    	
        //ADD CODE ABOVE HERE
        
    }
}
