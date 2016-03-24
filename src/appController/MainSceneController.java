package appController;

import appDesign.PhotoAlbum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MainSceneController {

	@FXML
	Button logoutButton;
	
	public void logout(ActionEvent event) throws Exception{
		PhotoAlbum.primaryStage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
}
