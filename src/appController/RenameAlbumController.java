package appController;

import appData.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.Node;

public class RenameAlbumController {

	@FXML
	TextField newAlbumName;
	
	public void renameAlbum(ActionEvent event){
		
		Admin.renameAlbum(MainSceneController.selectedAlbum.getName(), newAlbumName.getText());
		((Node)(event.getSource())).getScene().getWindow().hide();
		
	}
	
}
