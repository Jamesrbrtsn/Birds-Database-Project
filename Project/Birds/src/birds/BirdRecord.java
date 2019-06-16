package birds;

/**
 * This class represents a bird record in the database. Each record consists of two
 * parts: a DataKey and the data associated with the DataKey.
 */

//Completed Functionality - James 03 24
public class BirdRecord {
    //Variables
    public DataKey birdData;   //Bird data
    public String about;       //description storage
    public String sound; //filename
    public String image; //filename
    
    // default constructor
    public BirdRecord() {
        
    }
     // Other constructors
    public BirdRecord(DataKey birdInfo, String description, 
            String soundFileName, String imageFileName){
        birdData = birdInfo;
        about = description;
        sound = soundFileName;
        image = imageFileName;
    }

    public DataKey getDataKey(){
        return birdData;
    }
    public String getAbout(){
        return about;
    }
    public String getSound(){
        return sound;
    }
    public String getImage(){
        return image;
    }
    
}
