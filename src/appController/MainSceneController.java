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
	
	public void logout(ActionEvent event) throws Exception{
		PhotoAlbum.primaryStage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	
	public void addAlbum(ActionEvent event) throws Exception {
		Parent addAlbumDialogRoot;
		Stage addAlbumDialogStage;
		try {
			addAlbumDialogRoot = FXMLLoader.load(getClass().getResource("/appDesign/CreateAlbumDialog.fxml"));
			addAlbumDialogStage = new Stage();
			addAlbumDialogStage.setTitle("PhotoExpress - Create New Album");
			addAlbumDialogStage.setScene(new Scene(addAlbumDialogRoot, 526, 249));
			addAlbumDialogStage.getIcons().add(new Image("/appDesign/icon.png"));
			addAlbumDialogStage.show();

	          // ((Node)(event.getSource())).getScene().getWindow().hide();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void rename(ActionEvent event) throws Exception {
		Parent root;
		Stage stage;
		try {
			root = FXMLLoader.load(getClass().getResource("/appDesign/RenameDialog.fxml"));
			stage = new Stage();
			stage.setTitle("PhotoExpress - Rename Album");
			stage.setScene(new Scene(root, 526, 249));
			stage.getIcons().add(new Image("/appDesign/icon.png"));
			stage.show();

	          // ((Node)(event.getSource())).getScene().getWindow().hide();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void deleteAlbum(ActionEvent event) throws Exception {
		Parent root;
		Stage stage;
		try {
			root = FXMLLoader.load(getClass().getResource("/appDesign/DeleteDialog.fxml"));
			stage = new Stage();
			stage.setTitle("PhotoExpress - Confirm Delete");
			stage.setScene(new Scene(root, 503, 141));
			stage.getIcons().add(new Image("/appDesign/icon.png"));
			stage.show();

	          // ((Node)(event.getSource())).getScene().getWindow().hide();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	public void openSearchStage(ActionEvent event) throws Exception {
		Parent root;
		Stage stage;
		try {
			root = FXMLLoader.load(getClass().getResource("/appDesign/SearchScene.fxml"));
			stage = new Stage();
			stage.setTitle("PhotoExpress - Search");
			stage.setScene(new Scene(root, 868, 534));
			stage.getIcons().add(new Image("/appDesign/icon.png"));
			stage.show();

	          // ((Node)(event.getSource())).getScene().getWindow().hide();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
}
