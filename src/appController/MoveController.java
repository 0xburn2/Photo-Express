package appController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import appData.Admin;
import appData.Album;
import appData.Photo;
import appData.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;

public class MoveController implements Initializable {
	
	@FXML
	ChoiceBox<Album> albumBox;
	
	Album albumToBeMovedTo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		User tempUser = LoginSceneController.getLoggedInUser();
		albumBox.setItems(FXCollections.observableArrayList(User.getUserAlbums(tempUser)));
		albumBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
		      if (newValue == null) {
		        return;
		      }
		      
		    albumToBeMovedTo = newValue;
		      
		});
		
	}

	public void movePhoto(ActionEvent event){
		User loggedIn = LoginSceneController.getLoggedInUser();
		Photo photo = PhotoDisplaySceneController.getSelectedPhoto();
		Album album = MainSceneController.getSelectedAlbum();
		
		int i = photo.getId();
		
		albumToBeMovedTo.addPhoto(photo);
		ArrayList<Album> temp = User.deletePhoto(i, loggedIn, album);
		loggedIn.setUserAlbums(temp);
		Admin.updateUser(loggedIn);
		Admin.updateUser(loggedIn);
		
		((Node) (event.getSource())).getScene().getWindow().hide();
		
	}
	
	
}
