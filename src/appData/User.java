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

    /* 
    Fetch album of user
        User x = new User(Bob);
        ArrayList arr = new ArrayList();
        arr=x.ArrayList();  
    */
    public ArrayList<Album> userAlbum() {
        return listofAlbums;
    }
    
    /*
    Fetch photos of user
    */
    public ArrayList<Photo> userPhotos() {
        return photosinAlbum;
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
