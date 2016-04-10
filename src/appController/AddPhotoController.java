package appController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.Desktop;

import appData.Admin;
import appData.Photo;
import appData.User;
import appDesign.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.image.ImageView;
import javafx.scene.control.ScrollPane;

public class AddPhotoController {
	@FXML
	Button addPhotoButton;
	@FXML
	TextField photoUrlField;
	@FXML
	TextField captionField;
	@FXML
	TextField tagField;

	File image;
	String imagePath;
	Photo photo;

	public void addPhoto(ActionEvent event) throws Exception {

		Photo photo = new Photo(imagePath, tagField.getText(), captionField.getText(), image);
		this.photo = photo;

		MainSceneController.getSelectedAlbum().addPhoto(photo);
		((Node) (event.getSource())).getScene().getWindow().hide();

	}

	public void browsePhotos(ActionEvent event) throws Exception {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg");
		fileChooser.getExtensionFilters().add(extFilter);

		File file = fileChooser.showOpenDialog(PhotoAlbum.primaryStage);
		if (file != null) {
			imagePath = file.getPath();
			image = file;
			photoUrlField.setText(imagePath);
		}
	}

}
