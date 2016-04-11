package appController;

import java.util.ArrayList;

import appData.Admin;
import appData.Album;
import appData.Photo;
import appData.User;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class RemovePhotoController {

	public void cancel(ActionEvent event){
		
		((Node) (event.getSource())).getScene().getWindow().hide();
		
	}
	
	public void deletePhoto(ActionEvent event){
		
		User loggedIn = LoginSceneController.getLoggedInUser();
		Photo photo = PhotoDisplaySceneController.getSelectedPhoto();
		Album album = MainSceneController.getSelectedAlbum();
		
		int photoId = photo.getId();
		ArrayList<Photo> temp = User.deletePhoto(photoId, loggedIn, album);
		loggedIn.setUserPhotos(temp);
		Admin.updateUser(loggedIn);
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	
}
