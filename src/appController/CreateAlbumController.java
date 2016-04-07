package appController;

import appData.Album;
import appData.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class CreateAlbumController {
	
	@FXML
	TextField albumTextField;

	public void createAlbum(ActionEvent event){
		System.out.println("TESTING");
		User.createAlbum(albumTextField.getText(), LoginSceneController.getLoggedInUser());
		User temp = LoginSceneController.getLoggedInUser();
		User.printAlbumList(temp);
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	
}
