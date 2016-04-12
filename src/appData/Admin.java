package appData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import appController.AdminSceneController;
import appController.LoginSceneController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Admin class and control methods
 */

public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	static boolean adminSerCreated = false;
	/**
	 *
	 */

	static ArrayList<User> list = new ArrayList<User>();

	public static boolean isAdminSerCreated() {
		File f = new File("src/appData/userList.ser");
		if (f.exists() && !f.isDirectory()) {
			return true;
		}
		return false;
	}

	public static void adminSerCreated() {
		adminSerCreated = true;
	}

	/*
	 * One time usage
	 */
	public static ArrayList<User> getList() throws FileNotFoundException {
		try {
			return deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}

	public static boolean blankName(String name) {
		if (name.equals("")) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if a username exists
	 * @param Name
	 * @return True or false
	 */

	public static boolean usernameExists(String name) {
		try {
			list = deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (User i : list) {
			if (i.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates a new user
	 * @param Name
	 */

	public static void addUser(String name) {
		try {
			list = deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		User testUser = new User(name);
		list.add(testUser);
		serializeData();

	}
	
	/**
	 * Deletes a User of the specified name
	 * @param Name
	 */

	public static void deleteUser(String name) {
		try {
			list = deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			User temp = list.get(i);
			if (temp.getName().equals(name)) {
				list.remove(i);
			}
		}
		serializeData();
	}

	/**
	 * Update user function to be ran anytime a user is edited. Serializes it
	 * @param user to be updated
	 */
	public static void updateUser(User user) {
		try {
			list = deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < list.size(); i++) {
			User temp = list.get(i);
			if (temp.getName().equals(user.getName())) {
				list.remove(i);
			}
		}
		list.add(user);
		serializeData();
	}
	
	/**
	 * Checks if an album exists
	 * @param Name to check against
	 * @return True or false
	 */
	
	public static boolean albumExists(String albumName) {

		User temp = LoginSceneController.getLoggedInUser();
		ArrayList<Album> userAlbums = temp.listofAlbums;

		for (Album a : userAlbums) {

			if (a.getName().equals(albumName)) {
				return true;
			}

		}
		
		return false;

	}
	
	/**
	 * Renames an album
	 * @param Old album name, new album name
	 */

	public static void renameAlbum(String albumName, String newAlbumName) {

		User temp = LoginSceneController.getLoggedInUser();
		ArrayList<Album> userAlbums = temp.listofAlbums;

		for (Album a : userAlbums) {

			if (a.getName().equals(albumName)) {
				a.setName(newAlbumName);
				updateUser(temp);
			}

		}

	}
	
	/**
	 * Deletes an album of specified name
	 * @param Name
	 */
	
	public static void deleteAlbum(String albumName) {

		User temp = LoginSceneController.getLoggedInUser();
		ArrayList<Album> userAlbums = temp.listofAlbums;

		for (int i = 0; i < userAlbums.size(); i++) {

			if (userAlbums.get(i).getName().equals(albumName)) {
				userAlbums.remove(userAlbums.get(i));
				updateUser(temp);
			}

		}

	}
	
	/**
	 * Serializes the user list
	 */

	public static void serializeData() {
		try {
			FileOutputStream userListOut = new FileOutputStream("src/appData/userList.ser");
			ObjectOutputStream out = new ObjectOutputStream(userListOut);
			out.writeObject(list);
			out.close();
			userListOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	/**
	 * Deserializes the userlist for editing
	 */

	public static ArrayList<User> deSerializeData() throws FileNotFoundException {

		ArrayList<User> list = new ArrayList<User>();
		try {
			FileInputStream fis = new FileInputStream("src/appData/userList.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			list = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return null;
		} catch (ClassNotFoundException c) {
			System.out.println("Class not found");
			c.printStackTrace();
			return null;
		}

		return list;
	}

}
