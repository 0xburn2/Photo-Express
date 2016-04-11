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
		User tempUser = LoginSceneController.getLoggedInUser();
		Photo tempPhoto = PhotoDisplaySceneController.getSelectedPhoto();
		Album album = MainSceneController.getSelectedAlbum();
		int i = tempPhoto.getId();
		ArrayList<Photo> temp = User.deletePhoto(i, tempUser, album);
		tempUser.setUserPhotos(temp);
		albumToBeMovedTo.addPhoto(tempPhoto);
		Admin.updateUser(tempUser);
		
		((Node) (event.getSource())).getScene().getWindow().hide();
		
	}
	
	
}
