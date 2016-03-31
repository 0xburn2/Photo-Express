package appData;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Admin implements Serializable {

	static ArrayList<User> list = new ArrayList<User>();
	
	
	
	
	
	public static void addUser(String name){
		User testUser = new User(name);
		list.add(testUser);
	}
	
	public static void serializeData(){
	 try  {
		 addUser("Pete");
      FileOutputStream userListOut = new FileOutputStream("src/appData/userList.ser");
      ObjectOutputStream out = new ObjectOutputStream(userListOut);
      out.writeObject(list);
      out.close();
      userListOut.close();
   } catch(IOException i)
   {
       i.printStackTrace();
   }
	}
	
	public static ArrayList<User> deSerializeData(){
		 ArrayList<User> list = new ArrayList<User>();
	        try {
	            FileInputStream fis = new FileInputStream("src/appData/userList.ser");
	            ObjectInputStream ois = new ObjectInputStream(fis);
	            list = (ArrayList) ois.readObject();
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
