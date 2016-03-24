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

public class AdminSceneController {
	

		@FXML
		Button logoutButton;
		@FXML
		Button addButton;
		@FXML 
		Button deleteButton;
		
		@FXML
		public void logout(ActionEvent event) throws Exception {
			PhotoAlbum.primaryStage.show();
			((Node)(event.getSource())).getScene().getWindow().hide();
		}
		
		@FXML
		public void addUser(ActionEvent event) throws Exception {
			Parent addUserDialogRoot;
			Stage addUserDialogStage;
			try {
				addUserDialogRoot = FXMLLoader.load(getClass().getResource("/appDesign/CreateNewUserDialog.fxml"));
				addUserDialogStage = new Stage();
				addUserDialogStage.setTitle("PhotoExpress - Create New User");
				addUserDialogStage.setScene(new Scene(addUserDialogRoot, 526, 249));
				addUserDialogStage.getIcons().add(new Image("/appDesign/icon.png"));
				addUserDialogStage.show();

		          // ((Node)(event.getSource())).getScene().getWindow().hide();

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}
		
		@FXML
		public void deleteUser(ActionEvent event) throws Exception {
			Parent deleteUserDialogroot;
			Stage deleteUserDialogStage;
			try {
				deleteUserDialogroot = FXMLLoader.load(getClass().getResource("/appDesign/DeleteDialog.fxml"));
				deleteUserDialogStage = new Stage();
				deleteUserDialogStage.setTitle("PhotoExpress - Confirm Delete");
				deleteUserDialogStage.setScene(new Scene(deleteUserDialogroot, 503, 141));
				deleteUserDialogStage.getIcons().add(new Image("/appDesign/icon.png"));
				deleteUserDialogStage.show();

		          // ((Node)(event.getSource())).getScene().getWindow().hide();

		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		}

}
