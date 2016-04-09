package appController;

import appData.Admin;
import appData.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.Node;

public class RenameAlbumController {

	@FXML
	TextField newAlbumName;
	
	public void renameAlbum(ActionEvent event){
		
		if (Admin.albumExists(newAlbumName.getText())){
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Album Name Taken");
            alert.setHeaderText(null);
            alert.setContentText("This album name is taken..");
            alert.showAndWait();
            return;
		}
		
		if (Admin.blankName(newAlbumName.getText())){
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Please Enter an Album Name");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid album name");
            alert.showAndWait();
            return;
		}
		
		
		Admin.renameAlbum(MainSceneController.selectedAlbum.getName(), newAlbumName.getText());
		((Node)(event.getSource())).getScene().getWindow().hide();
	
	}
	
}
