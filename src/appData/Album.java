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
        Admin.updateUser(user);
    }

    public void deletePhoto(int photoId) {
        for (Photo photo : photos) {
            if (photo.getId().equals(photoId)) { // need album name in Album.java
                photos.remove(photo);
            }
        }
        Admin.updateUser(user);
    }

}
