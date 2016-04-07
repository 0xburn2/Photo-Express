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
    static ArrayList<Photo> photosinAlbum;

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

    /*
     Fetch photos of user
     */
    public ArrayList<Photo> getUserPhotos() {
        return photosinAlbum;
    }

//   public static void createAlbum(String name, User user) {
//    	try {
//    		user.listofAlbums = deSerializeAlbumList(user);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//    	
//    	Album temp = new Album(name, user);
//        user.listofAlbums.add(temp);
//
////        System.out.print(temp);
//       //printing out all the albums for testing purpose
//       for (Album album : user.listofAlbums) {
//           System.out.println(album.getName());
//       }
//
//        serializeAlbumList(user);
//        
//        // Deserialize and print out albums
//        try {
//            ArrayList<Album> tempAlbum = deSerializeAlbumList(user);
//            for (Album album : tempAlbum) {
//                System.out.println(album.getName());
//            }
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void createAlbum(String name, User user) {
        Album tempAlbum = new Album(name, user);
        user.listofAlbums.add(tempAlbum);
        Admin.updateUser(user);
        //printing out all the albums for testing purpose
        for (Album album : user.listofAlbums) {
            System.out.println(album.getName());
        }
    }

    public static void deleteAlbum(String name, User user) {
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
        for (Album j : temp) {
            if (j.getName().equals(name)) {
                temp.remove(j);
            }
        }

        Admin.updateUser(user);
        //printing out all the albums for testing purpose
        for (Album album : user.listofAlbums) {
            System.out.println(album.getName());
        }
    }

}
