/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birds;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
/**
 *
 * @author Abdelkader
 */
public class BirdsController implements Initializable {
    
    //FXMl Objects
    @FXML
    private MenuBar mainMenu;
    @FXML
    private Label birdNameLabel;            
    @FXML
    private Label birdDescText;             
    @FXML
    private AnchorPane ui;
    @FXML
    private ComboBox birdSizeBox;           
    @FXML
    private TextField birdSearchInput;      
    @FXML
    private ImageView pictureDisplayed;   
    @FXML
    private Button delete;                  
    @FXML
    private Button first;                  
    @FXML
    private Button next;                    
    @FXML
    private Button previous;                
    @FXML
    private Button last;                    
    @FXML
    private Button play;                  
    @FXML
    private Button stop;                  
    @FXML
    private Button find;                  
    @FXML
    private Label birdSearchLabel;                  
    @FXML
    private Label sizeSearchLabel;                  
    
    //Visual related
    Image temp;
    boolean ready;
    
    //Currents
    private String currentBirdName;
    private String currentBirdDesc;
    private String currentImageLocation;
    private String currentMp3Location;
    private String theFileName;
    
    //private OrderedDictionary instance;
    private final OrderedDictionary instance = new OrderedDictionary();
    private BirdRecord currentBirdRecord;
    
    //ComboBox options
    ObservableList<String> options = FXCollections.observableArrayList(
            "1", "2", "3"); 
    
    //Sound Player
    Media hit;
    MediaPlayer mediaPlayer;
    boolean playing = false;
    
    @FXML
    public void loadDictionary(){
        theFileName = "BirdsDatabase.txt";
        Scanner input;
        //BirdRecord temp;
        //DataKey tempKey;
        try {
            int size;
            String name;
            String description;
            BirdRecord temp;
            DataKey tempKey;
            int i=1;
            
            input = new Scanner(new File(theFileName));
            // ADD CODE HERE TO READ WORDS INTO THE DICTIONARY     
            while (input.hasNextLine()){  
                size = Integer.parseInt(input.nextLine());///
                name = input.nextLine();
                description = input.nextLine();
                //theDictionary.add(inString, correctWord);           ///insert equivalent
                //public DataKey(String bird, int classSize){
                tempKey = new DataKey(name,size);
                //public BirdRecord(DataKey birdInfo, String description, String soundFileName, String imageFileName){
                temp = new BirdRecord(tempKey ,  description,  System.getProperty("user.dir")+"/src/sounds/"+name+".mp3",  "images/"+name+".jpg");
                try{
                    instance.insert(temp);
                    System.out.println(temp.getDataKey().getBirdName());
                    //System.out.println("worked");
                }
                catch(DictionaryException ex){
                    displayAlert(ex.getLocalizedMessage());
                }
                i++;                                                                                //Counter for testing
            }                                                                       
        } catch (IOException e) {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }
        //get strings from word file
        //insert all into dictionary
        ready = true;
        
        first();
        //Show UI
        showUi(ready);
        
        //update elements
        //make elements visible
    }
    
    private void showUi(boolean ready){
        birdNameLabel.setVisible(ready);
        birdDescText.setVisible(ready);
        birdNameLabel.setVisible(ready);
        birdSizeBox.setVisible(ready);
        birdSearchInput.setVisible(ready);
        pictureDisplayed.setVisible(ready);
        delete.setVisible(ready);
        first.setVisible(ready);
        next.setVisible(ready);
        previous.setVisible(ready);
        last.setVisible(ready);
        play.setVisible(ready);
        stop.setVisible(ready);
        find.setVisible(ready);
        birdSearchLabel.setVisible(ready);
        sizeSearchLabel.setVisible(ready);
    }
    
    private void updateBird(BirdRecord bird){
        currentBirdName = bird.getDataKey().getBirdName();
        currentBirdDesc = bird.getAbout();
        currentImageLocation = bird.getImage();
        currentMp3Location = bird.getSound();
        updateVisual();
    }
    private void updateVisual(){
        //Image Update
        temp = new Image(currentImageLocation);
        //Image temp = new Image("images/Mute Swan.jpg");
        pictureDisplayed.setImage(temp);
        //Title Text Update
        birdNameLabel.setText(currentBirdName);
        //Description Text Update
        birdDescText.setText(currentBirdDesc);
        //Soundfile prep update
        //resource = getClass().getResource(currentMp3Location);
        //media = new Media(currentMp3Location);
        //mediaPlayer = new MediaPlayer(media);
    }
    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/birds/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }
    
    @FXML
    public void first(){
        try{
        if ( playing == true){
            mediaPlayer.stop();
            playing =  false;
        }  
        currentBirdRecord = instance.smallest();
        //Update bird & visual
        updateBird(currentBirdRecord);
        }catch(DictionaryException ex){
            displayAlert(ex.getMessage());   //for errors
        }
    }
    @FXML
    public void last(){
        try{
        if ( playing == true){
            mediaPlayer.stop();
            playing =  false;
        }  
        currentBirdRecord = instance.largest();
        //Update bird & visual
        updateBird(currentBirdRecord);
        }catch(DictionaryException ex){
           displayAlert(ex.getMessage());   //for errors
        }
    }
    @FXML
    public void next(){
        if(ready ==false){
           displayAlert("Dictionary is empty");
        }
        try{
        if ( playing == true){
            mediaPlayer.stop();
            playing =  false;
        }  
        //currentBirdRecord = instance.largest();
        currentBirdRecord = instance.successor(currentBirdRecord.getDataKey());
        //Update bird & visual
        updateBird(currentBirdRecord);
        }catch(DictionaryException ex){
            displayAlert(ex.getMessage());   //for errors
        }
    }
    @FXML
    public void previous(){
        if(ready ==false){
           displayAlert("Dictionary is empty");
        }
        if ( playing == true){
            mediaPlayer.stop();
            playing =  false;
        }   
        try{
        //currentBirdRecord = instance.largest();
        currentBirdRecord = instance.predecessor(currentBirdRecord.getDataKey());
        //Update bird & visual
        updateBird(currentBirdRecord);
        }catch(DictionaryException ex){
            displayAlert(ex.getMessage());   //for errors
        }
    }
    @FXML
    public void delete(){
        if(ready ==false){
           displayAlert("Dictionary is empty");
        }
        if ( playing == true){
            mediaPlayer.stop();
            playing =  false;
        }  
        try{
             //get current index shown. (search or record) then delete matching birdRecord from tree
            if(currentBirdRecord.getDataKey().compareTo(instance.largest().getDataKey())==0){       //if last, we will pull/show the predecessor
                BirdRecord tempBird = new BirdRecord();
                tempBird = instance.predecessor(currentBirdRecord.getDataKey());
                updateBird(tempBird);
                instance.remove(instance.largest().getDataKey());
                currentBirdRecord = tempBird;
                //updateBird(currentBirdRecord);
                if(instance.isEmpty()==true){
                    showUi(false);
                } 
                return;
            }
            else{
                //delete current
                //show successor,
                BirdRecord tempBird = new BirdRecord();
                tempBird = instance.successor(currentBirdRecord.getDataKey());
                updateBird(tempBird);
                instance.remove(currentBirdRecord.getDataKey());
                currentBirdRecord = tempBird;
                //updateBird(currentBirdRecord);
                if(instance.isEmpty()==true){
                    showUi(false);
                } 
                return;
            }
        }catch(DictionaryException ex){
            displayAlert(ex.getMessage());   //for errors
        }
        
    }
    @FXML
    public void find(){
        if(ready ==false){
           displayAlert("Dictionary is empty");
        }
        if ( playing == true){
            mediaPlayer.stop();
            playing =  false;
        }  
        int a=1;
        if(birdSizeBox.getValue()=="1")
            a=1;
        if(birdSizeBox.getValue()=="2")
            a=2;
        if(birdSizeBox.getValue()=="3")
            a=3;
        String b = birdSearchInput.getText();
        //public DataKey(String bird, int classSize)
        DataKey toFind = new DataKey( b,a );           //
        //return bird record
        //find matching key
        try{
            currentBirdRecord = instance.find(toFind);
        }
        catch(DictionaryException ex){
            displayAlert(ex.getMessage());   //for errors
        }
        //update visual
        updateBird(currentBirdRecord);
        //  CLEAR INPUT TEXT
        birdSearchInput.clear();
        
    }
    @FXML
    public void play(){
        if(ready ==false){
           displayAlert("Dictionary is empty");
        }
       
        hit = new Media(new File(currentMp3Location).toURI().toString());
        mediaPlayer = new MediaPlayer(hit);
        mediaPlayer.play();
        playing = true;
    }
    
    @FXML
    public void stop(){
        if(ready ==false){
           displayAlert("Dictionary is empty");
        }
        if ( playing == true){
            mediaPlayer.stop();
            playing =  false;
        }   
        
    }
    @FXML
    public void exit() {
        if ( playing == true){
            mediaPlayer.stop();
            playing =  false;
        }   
        Stage stage = (Stage) mainMenu.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // ui.visibleProperty(false);
       ready = false;
       birdSizeBox.setItems(options);
       birdSizeBox.setValue("1");
       
       //hide UI
       showUi(ready);
    }

}
