package appController;

import java.net.URL;
import java.util.ResourceBundle;

import appData.Photo;
import appData.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class EditPhotoController implements Initializable {

	@FXML
	TextField caption;
	
	@FXML
	TextField tags;
	
	public void editPhoto(ActionEvent event){
		
		
		
		User loggedIn = LoginSceneController.getLoggedInUser();
		Photo photo = PhotoDisplaySceneController.getSelectedPhoto();
		
		photo.editCaption(caption.getText(), loggedIn);
		Photo.tokenizeTags(tags.getText());
		((Node) (event.getSource())).getScene().getWindow().hide();
		
	}
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		Photo photo = PhotoDisplaySceneController.getSelectedPhoto();
		
        caption.setText(photo.getCaption());
        tags.setText(photo.getPreTags());
    } 
	
}
