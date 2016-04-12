package appController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import appData.Album;
import appData.Photo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Controls the Slideshow function
 * @author Peter Lambe and Le Liu - Photo Album 40
 */

public class SlideshowSceneController implements Initializable {

	int counter = 0;

	@FXML
	ImageView slideshowImageView = new ImageView();

	Image image = null;
	Album album = MainSceneController.getSelectedAlbum();
	ArrayList<Photo> photos = album.getPhotos();

	public void increaseCounter(ActionEvent event) {
		
		if (counter == (photos.size() - 1)){
			counter = -1;
		}
		
		counter++;

		try {
			image = new Image(new FileInputStream(photos.get(counter).getPhotoFile()), 200, 0, true, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		slideshowImageView.setImage(image);

	}

	public void decreaseCounter(ActionEvent event) {

		if (counter == 0){
			counter = (photos.size());
		}
		
		counter--;

		try {
			image = new Image(new FileInputStream(photos.get(counter).getPhotoFile()), 200, 0, true, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		slideshowImageView.setImage(image);

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		try {
			image = new Image(new FileInputStream(photos.get(counter).getPhotoFile()), 200, 0, true, true);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		slideshowImageView.setImage(image);

	}

}
