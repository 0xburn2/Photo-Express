package appController;

import appData.Admin;
import appData.Album;
import appData.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CreateAlbumController {
	
	@FXML
	TextField albumTextField;

	public void createAlbum(ActionEvent event){
		
		if (Admin.albumExists(albumTextField.getText())){
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Album Already Exists");
            alert.setHeaderText(null);
            alert.setContentText("This album already exists.");
            alert.showAndWait();
            return;
		}
		
		if (Admin.blankName(albumTextField.getText())){
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Please Enter an Album Name");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid album name");
            alert.showAndWait();
            return;
		}
		
		User.createAlbum(albumTextField.getText(), LoginSceneController.getLoggedInUser());
		User temp = LoginSceneController.getLoggedInUser();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	
}
