package appController;

import java.io.IOException;
import appDesign.PhotoAlbum;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSceneController {

	@FXML
	Button logoutButton;
	@FXML
	Button addNewAlbumButton;
	@FXML
	Button deleteButton;
	@FXML
	Button searchButton;

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

		createStage(event, "PhotoExpress - Confirm Delete", "/appDesign/DeleteDialog.fxml", 503, 141);

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

}
