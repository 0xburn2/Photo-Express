package appController;

import appData.Photo;
import appData.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class EditPhotoController {

	@FXML
	TextField caption;
	
	@FXML
	TextField tags;
	
	public void editPhoto(ActionEvent event){
		User loggedIn = LoginSceneController.getLoggedInUser();
		Photo photo = PhotoDisplaySceneController.getSelectedPhoto();
		photo.editCaption(caption.getText(), loggedIn);
		((Node) (event.getSource())).getScene().getWindow().hide();
		
	}
	
}
