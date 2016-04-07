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
    ArrayList<Album> listofAlbums = new ArrayList<Album>();
    static ArrayList<Photo> photosinAlbum = new ArrayList<Photo>();
    boolean firstTimeLoggingIn = true;

    public User() {

    }

    public User(String username) {
        this.username = username;
        listofAlbums = new ArrayList<Album>();
        photosinAlbum = new ArrayList<Photo>();
    }
    
    public static boolean firstTimeLoggingIn(User user){
    	return user.firstTimeLoggingIn;
    }
    
    public static void serFileCreated(User user){
    	user.firstTimeLoggingIn = false;
    }

    public String toString() {
        return username;
    }
    
    public String getName(){
    	return username;
    }
    
    
    public static ArrayList<Album> getUserAlbums(User user) {
    	ArrayList<Album> temp = new ArrayList<Album>();
		try {
			temp = deSerializeAlbumList(user);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		serializeAlbumList(user);
		
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

   
    public static void serializeAlbumList(User user){
   	 try  {
         FileOutputStream userListOut = new FileOutputStream("src/appData/albumListFor" + user.getName() + ".ser");
         ObjectOutputStream out = new ObjectOutputStream(userListOut);
         out.writeObject(user.listofAlbums);
         out.close();
         userListOut.close();
      } catch(IOException i)
      {
          i.printStackTrace();
      }
   	}
   	
   	public static ArrayList<Album> deSerializeAlbumList(User user) throws FileNotFoundException {
   		
   		 ArrayList<Album> list = new ArrayList<Album>();
   	        try {
   	            FileInputStream fis = new FileInputStream("src/appData/albumListFor" + user.getName() + ".ser");
   	            ObjectInputStream ois = new ObjectInputStream(fis);
   	            list = (ArrayList<Album>) ois.readObject();
   	            ois.close();
   	            fis.close();
   	         }catch(IOException ioe){
   	             ioe.printStackTrace();
   	             return null;
   	          }catch(ClassNotFoundException c){
   	             System.out.println("Class not found");
   	             c.printStackTrace();
   	             return null;
   	          }
   	        
   	        return list;
   	}
    
}

   