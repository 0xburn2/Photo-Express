package appData;

import java.util.ArrayList;

public class User implements java.io.Serializable {

    String username;
    ArrayList<Album> listofAlbums;
    ArrayList<Photo> photosinAlbum;

    public User() {

    }

    public User(String username) {
        this.username = username;
        listofAlbums = null;
        photosinAlbum = null;
    }

    public String toString() {
        return username;
    }

<<<<<<< HEAD
    
    public String getName(){
    	return username;
    }
    
    
=======
    /*
    Fetch album of user
        User x = new User(Bob);
        ArrayList arr = new ArrayList();
        arr=x.ArrayList();
    */
>>>>>>> 7377c2ba3cf17e013aadd2ee07abb8b2f528892d
    public ArrayList<Album> userAlbum() {
        return listofAlbums;
    }

    /*
    Fetch photos of user
    */
    public ArrayList<Photo> userPhotos() {
        return photosinAlbum;
    }

    /*
    Add Album to user
    */
    public void addAlbum(Album album) {
        // should maybe check for dup albums
        listofAlbums.add(album);
    }

    /**
     * removes an album from the user's list of albums given the album's name
     */
    public void removeAlbum(String albumName) {
        for (Album album : listofAlbums) {
           if (album.getName().equals(albumName)) { // need album name in Album.java
               listofAlbums.remove(album);
          }
        }
    }

    /*
    Rename the album
    */
    public void renameAlbum(String oldName, String newName) {
        for (Album album : listofAlbums) {
          if (album.getName().equals(oldName)) { // need album name in Album.java
                album.setName(newName);
            }
        }
    }
}

    /*
    Serialization of album
        Move to where we want the serialization to happen.
        - screen transition?
        - or just any changes to album get instantly serialized?
    */
//      try
//      {
//         FileOutputStream albumListOut = new FileOutputStream("/albumList.ser");
//         ObjectOutputStream out = new ObjectOutputStream(albumListOut);
//         out.writeObject(listofAlbums);
//         out.close();
//         albumListOut.close();
//         System.out.printf("Serialized data is saved in /albumList.ser");
//      }catch(IOException i)
//      {
//          i.printStackTrace();
//      }
