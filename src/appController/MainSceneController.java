package appController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import appData.Admin;
import appData.Album;
import appData.User;
import appDesign.PhotoAlbum;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSceneController implements Initializable {

	@FXML
	ListView<Album> listViewofAlbums = new ListView<Album>();
	@FXML
	TextField albumField;
	@FXML
	TextField numField;
	@FXML
	TextField lastPhotoField;
	@FXML
	TextField photoRangeField;
	@FXML
	Label title;

	private static Album selectedAlbum;
	
	public static Album getSelectedAlbum(){
		return selectedAlbum;
	}

	public void logout(ActionEvent event) throws Exception {
		PhotoAlbum.primaryStage.show();
		((Node) (event.getSource())).getScene().getWindow().hide();
	}

	public void addAlbum(ActionEvent event) throws Exception {

		createStage(event, "PhotoExpress - Create New Album", "/appDesign/CreateAlbumDialog.fxml", 526, 249);

	}

	public void rename(ActionEvent event) throws Exception {

		createStage(event, "PhotoExpress - Rename Album", "/appDesign/RenameDialog.fxml", 526, 249);

	}

	public void deleteAlbum(ActionEvent event) throws Exception {

		createStage(event, "PhotoExpress - Confirm Delete", "/appDesign/DeleteAlbumDialog.fxml", 503, 141);

	}

	public void openSearchStage(ActionEvent event) throws Exception {

		createStage(event, "PhotoExpress - Search", "/appDesign/SearchScene.fxml", 868, 534);

	}

	public void createStage(ActionEvent event, String windowTitle, String fxmlLocation, int x, int y) throws Exception {
		Parent root;
		Stage stage;
		try {
			root = FXMLLoader.load(getClass().getResource(fxmlLocation));
			stage = new Stage();
			stage.setTitle(windowTitle);
			stage.setScene(new Scene(root, x, y));
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(((Node) event.getSource()).getScene().getWindow());
			stage.getIcons().add(new Image("/appDesign/icon.png"));
			stage.show();

			// ((Node)(event.getSource())).getScene().getWindow().hide();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void listViewMouseDoubleClicked(MouseEvent e) {

		if (e.getClickCount() == 2) {
			Parent root;
			Stage stage;
			try {
				root = FXMLLoader.load(getClass().getResource("/appDesign/PhotoDisplayScene.fxml"));
				stage = new Stage();
				stage.setTitle("PhotoExpress - " + LoginSceneController.getLoggedInUser().getName() + "'s Photos");
				stage.setScene(new Scene(root, 868, 534));
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(((Node) e.getSource()).getScene().getWindow());
				stage.getIcons().add(new Image("/appDesign/icon.png"));
				stage.show();

				// ((Node)(event.getSource())).getScene().getWindow().hide();

			} catch (IOException x) {
				x.printStackTrace();
			}
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		User temp = LoginSceneController.getLoggedInUser();
		title.setText(temp.getName() + "'s Albums");
		listViewofAlbums.setItems(FXCollections.observableList(User.getUserAlbums(temp)));
		listViewofAlbums.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				return;
			}

			albumField.setDisable(true);
			numField.setDisable(true);
			lastPhotoField.setDisable(true);
			photoRangeField.setDisable(true);

			albumField.setText(newValue.getName());
			numField.setText(newValue.getSize());
			selectedAlbum = newValue;

		});

		listViewofAlbums.getSelectionModel().select(0);
	}

}
