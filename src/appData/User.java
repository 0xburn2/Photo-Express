package appData;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import appController.LoginSceneController;
import appController.MainSceneController;

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

	public static ArrayList<Album> removeAlbum(ArrayList<Album> albumList, Album album) {
		for (int i = 0; i < albumList.size(); i++) {
			if (albumList.get(i).getName().equals(album.getName())) {
				albumList.remove(i);
			}
		}

		return albumList;
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

	public void setUserAlbums(ArrayList<Album> list) {
		listofAlbums = list;
	}

	public static void addToUserPhotoList(Photo photo, User user) {
		User i = user;
		if (!i.photosinAlbum.contains(photo)) {
			i.photosinAlbum.add(photo);
		}
	}

	/*
	 * Fetch photos of user
	 */
	public static ArrayList<Photo> getUserPhotos(User user) {

		ArrayList<User> userList = new ArrayList<User>();
		try {
			userList = Admin.deSerializeData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Photo> temp = new ArrayList<Photo>();

		for (User i : userList) {
			if (i.getName().equals(user.getName())) {
				temp = i.photosinAlbum;
			}
		}

		return temp;
	}

	public void setUserPhotos(ArrayList<Photo> list) {
		photosinAlbum = list;
	}

	public static void createAlbum(String name, User user) {
		Album tempAlbum = new Album(name, user);
		user.listofAlbums.add(tempAlbum);
		Admin.updateUser(user);
		// printing out all the albums for testing purpose
		for (Album album : user.listofAlbums) {
			System.out.println(album.getName());
		}
	}

	public static void createAlbumFromPhotos(String name, User user, ArrayList<Photo> temp) {
		Album tempAlbum = new Album(name, user);
		for (Photo photo : temp) {
			tempAlbum.addPhoto(photo);
		}
		user.listofAlbums.add(tempAlbum);
		Admin.updateUser(user);
		// printing out all the albums for testing purpose
		for (Album album : user.listofAlbums) {
			System.out.println(album.getName());
		}
	}

	/*
	 * Get all possible tags from the user's photos. No repeating tags Return an
	 * arraylist a unique tags
	 */
	public static ArrayList<Tag> getAllTags(User user) {
		ArrayList<Tag> allTags = new ArrayList<Tag>();
		for (Photo photo : getUserPhotos(user)) {
			ArrayList<Tag> photoTags = Photo.getTags(photo);
			for (Tag tag : photoTags) {
				if (!allTags.contains(tag)) {
					allTags.add(tag);
				}
			}
		}
		return allTags;
	}

	public static ArrayList<String> getAllDates(User user) {
		ArrayList<String> allDates = new ArrayList<String>();
		String dateString;
		for (Photo photo : getUserPhotos(user)) {
			dateString = photo.getSimpleDateString();
			if (!allDates.contains(dateString))
				allDates.add(dateString);
		}
		return allDates;
	}

	public static String getSmallestDate(User user) {

		ArrayList<String> allDates = getAllDates(user);
		String smallestDate = allDates.get(0);
		for (int i = 0; i < allDates.size() - 1; i++) {
			if (allDates.get(i + 1).compareTo(allDates.get(i)) < 0) {
				smallestDate = allDates.get(i + 1);
			}
		}

		return smallestDate;

	}

	public static String getLargestDate(User user) {

		ArrayList<String> allDates = getAllDates(user);
		String largestDate = allDates.get(0);
		for (int i = 0; i < allDates.size() - 1; i++) {
			if (allDates.get(i + 1).compareTo(allDates.get(i)) > 0) {
				largestDate = allDates.get(i + 1);
			}
		}

		return largestDate;

	}

	public static ArrayList<Album> deletePhoto(int photoId, User user, Album album) {

		ArrayList<Photo> photoList = album.getPhotos();
		ArrayList<Album> albumList = getUserAlbums(user);

		for (int j = 0; j < photoList.size(); j++) {
			Photo photo = photoList.get(j);
			if (photo.getId() == (photoId)) {
				System.out.println(album.getSize());
				albumList = removeAlbum(albumList, album);
				album.removePhoto(photoId, user);
				albumList.add(album);
				Admin.updateUser(user);
				System.out.println("photo deleted");
				System.out.println(album.getSize());

			}
		}

		return albumList;

	}

	public static ArrayList<String> getAllValues(User user) {
		ArrayList<String> allValues = new ArrayList<String>();
		for (Photo photo : getUserPhotos(user)) {
			ArrayList<Tag> photoTags = Photo.getTags(photo);
			for (Tag tag : photoTags) {
				if (!allValues.contains(tag.getValue())) {
					allValues.add(tag.getValue());
				}
			}
		}
		return allValues;
	}

	public static ArrayList<String> getAllTypes(User user) {
		ArrayList<String> allTypes = new ArrayList<String>();
		for (Photo photo : getUserPhotos(user)) {
			ArrayList<Tag> photoTags = Photo.getTags(photo);
			for (Tag tag : photoTags) {
				if (!allTypes.contains(tag.getType())) {
					allTypes.add(tag.getType());
				}
			}
		}
		return allTypes;
	}
}
