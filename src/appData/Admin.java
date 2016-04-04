package appData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import appController.AdminSceneController;

public class Admin implements Serializable {

	static ArrayList<User> list = new ArrayList<User>();
	
	public static ArrayList<User> getList() throws FileNotFoundException {
		try {
			return deSerializeData();
		} catch (FileNotFoundException e){
			e.printStackTrace();
            return null;
		}
		
	}
	
	public static boolean usernameExists(String name){
		try {
			list = deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (User i : list){
			if (i.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public static void addUser(String name){
		try {
			list = deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		User testUser = new User(name);
		list.add(testUser);
		serializeData();
		
	}
	
	public static void deleteUser(String name){
		try {
			list = deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (User i : list){
			if (i.getName().equals(name)){
				list.remove(i);
			}
		}
		
		serializeData();
		
	}
	
	public static void serializeData(){
	 try  {
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
	
	public static ArrayList<User> deSerializeData() throws FileNotFoundException {
		
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
