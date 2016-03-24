package appData;

import java.util.ArrayList;

public class User implements java.io.Serializable {

	String username;
	ArrayList<Album> listofAlbums;
	ArrayList<Photo> photosinAlbum;
	
	public User(){
		
	}
	
	public User(String username){
		this.username = username;
		listofAlbums = null;
		photosinAlbum = null;
	}
	
	public String toString(){
		return username;
	}
	
	
	
}
