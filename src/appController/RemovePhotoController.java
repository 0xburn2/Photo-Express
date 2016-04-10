package appController;

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
		
		//User.deletePhoto(photo);
		
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	
}
