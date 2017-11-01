package appData;

import java.util.ArrayList;

/**
 * Contains all of the Album functions and information
 * @author Peter Lambe and Le Liu - Photo Album
 */

public class Album implements java.io.Serializable {

    private static final long serialVersionUID = 932748968830155235L;
    private String name;
    private User user;
    private ArrayList<Photo> photos;

    public Album() {

    }

    public Album(String name, User user) {
        this.name = name;
        this.user = user;
        this.photos = new ArrayList<Photo>();

    }
    
    
    /**
	 * Gets the photos in an album
	 * @return ArrayList of Photos
	 */
    public ArrayList<Photo> getPhotos(){
    	return photos;
    }
    
    /**
	 * String version of size for GUI display
	 * @param JavaFX Event
	 * @return size of Album in String form 
	 */
    
    public String getSize(){
    	return String.valueOf(photos.size());
    }

    public String toString() {
        return name;
    }

    public void setName(String name) {
    	this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void renameAlbum(String name) {
        this.name = name;
    }
    
    /**
	 * Adds selected photo to the album
	 * @param photo
	 */

    public void addPhoto(Photo photo) {
        photos.add(photo);
        User.addToUserPhotoList(photo, user);
        Admin.updateUser(user);
    }
    
    public void printPhotos(){
    	for (Photo p : photos){
    		System.out.println(p);
    	}
    }
    
    
    /**
	 * Deletes the selected photo from the selected User's list
	 * @param photoId, User
	 */
    public void removePhoto(int photoID, User user){
    	
    	for (int i = 0; i < photos.size(); i++){
    		if (photos.get(i).getId() == photoID){
    			photos.remove(i);
    			Admin.updateUser(user);
    			System.out.println("removed from album too");
    		}
    	}
    	
    }

}
