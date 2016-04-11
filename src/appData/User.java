package appData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import appController.LoginSceneController;
import java.util.logging.Level;
import java.util.logging.Logger;

public class User implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 5256634808584478017L;
    String username;
    ArrayList<Album> listofAlbums;
    ArrayList<Photo> photosinAlbum = new ArrayList<Photo>();

    public User() {

    }

    public User(String username) {
        this.username = username;
        listofAlbums = new ArrayList<Album>();
        photosinAlbum = new ArrayList<Photo>();
    }

    public String toString() {
        return username;
    }

    public String getName() {
        return username;
    }

    public static ArrayList<Album> getUserAlbums(User user) {
        ArrayList<User> userList = new ArrayList<User>();
        try {
            userList = Admin.deSerializeData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Album> temp = new ArrayList<Album>();

        for (User i : userList) {
            if (i.getName().equals(user.getName())) {
                temp = i.listofAlbums;
            }
        }

        return temp;
    }

    public static void addToUserPhotoList(Photo photo, User user){
    	User i = user;
    	i.photosinAlbum.add(photo);
    }

    /*
     Fetch photos of user
     */
    public static ArrayList<Photo> getUserPhotos(User user) {

    	ArrayList<User> userList = new ArrayList<User>();
        try {
            userList = Admin.deSerializeData();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<Photo> temp = new ArrayList<Photo>();

        for (User i : userList){
        	if (i.getName().equals(user.getName())){
        		temp = i.photosinAlbum;
        	}
        }


        return temp;
    }

    public static void createAlbum(String name, User user) {
        Album tempAlbum = new Album(name, user);
        user.listofAlbums.add(tempAlbum);
        Admin.updateUser(user);
        //printing out all the albums for testing purpose
        for (Album album : user.listofAlbums) {
            System.out.println(album.getName());
        }
    }

    /*
     *Get all possible tags from the user's photos. No repeating tags
     *Return an arraylist a unique tags
     */
    public static ArrayList<Tag> getAllTags(User user){
      ArrayList<Tag> allTags = new ArrayList<Tag>();
        for (Photo photo : getUserPhotos(user)) {
          ArrayList<Tag> photoTags = Photo.getTags(photo);
          for(Tag tag : photoTags){
            if(!allTags.contains(tag)){
              allTags.add(tag);
            }
          }
        }
        return allTags;
    }

    public static ArrayList<String> getAllDates(User user){
      ArrayList<String> allDates = new ArrayList<String>();
      String dateString;
        for (Photo photo : getUserPhotos(user)) {
          dateString = photo.getSimpleDateString();
        }
        return allDates;
    }

}
