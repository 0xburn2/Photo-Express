/**
 * Handles the controller for Remove Photo
 *
 */

package appController;

import java.util.ArrayList;

import appData.Admin;
import appData.Album;
import appData.Photo;
import appData.User;
import javafx.event.ActionEvent;
import javafx.scene.Node;



public class RemovePhotoController {
	
	/**
	 * Closes dialog...
	 * @event JavaFX Event
	 * @return .....
	 */

	public void cancel(ActionEvent event){
		
		((Node) (event.getSource())).getScene().getWindow().hide();
		
	}
	
	/**
	 * Deletes photo
	 * @event JavaFX Event
	 * 
	 */
	
	public void deletePhoto(ActionEvent event){
		
		User loggedIn = LoginSceneController.getLoggedInUser();
		Photo photo = PhotoDisplaySceneController.getSelectedPhoto();
		Album album = MainSceneController.getSelectedAlbum();
		
		int photoId = photo.getId();
		ArrayList<Album> temp = User.deletePhoto(photoId, loggedIn, album);
		loggedIn.setUserAlbums(temp);
		Admin.updateUser(loggedIn);
		((Node) (event.getSource())).getScene().getWindow().hide();
	}
	
}
