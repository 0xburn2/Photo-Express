package appData;

import java.util.ArrayList;

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
    
    public ArrayList<Photo> getPhotos(){
    	return photos;
    }
    
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
