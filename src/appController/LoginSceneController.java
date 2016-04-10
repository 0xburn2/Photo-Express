package appController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import appData.Admin;
import appData.Album;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import appData.User;

public class LoginSceneController {

	@FXML
	private Button loginButton;

	@FXML
	TextField usernameField;

	static User loggedInUser = new User();

	public static User getLoggedInUser(){

		return loggedInUser;

	}

	public void login(ActionEvent event) throws Exception {
		if (usernameField.getText().equalsIgnoreCase("admin")) {

			if (Admin.isAdminSerCreated() == false){
				System.out.println("Creating ser file");
				Admin.serializeData();
			}

			Parent adminSceneRoot;
			Stage adminSceneStage;
			try {
				adminSceneRoot = FXMLLoader.load(getClass().getResource("/appDesign/AdminPanelScene.fxml"));
				adminSceneStage = new Stage();
				adminSceneStage.setTitle("PhotoExpress - Admin Panel");
				adminSceneStage.setScene(new Scene(adminSceneRoot, 582, 437));
				adminSceneStage.getIcons().add(new Image("/appDesign/icon.png"));
				adminSceneStage.show();

				((Node) (event.getSource())).getScene().getWindow().hide();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			if (Admin.isAdminSerCreated() == false){
				Alert alert = new Alert(AlertType.INFORMATION);
	            alert.setTitle("No Users in Database");
	            alert.setHeaderText(null);
	            alert.setContentText("There are currently no users in the database. Please log in to the admin panel to create some users.");
	            alert.showAndWait();
	            return;
			}
			ArrayList<User> list = Admin.deSerializeData();

			for (User i : list){
				if (usernameField.getText().equalsIgnoreCase(i.getName())){
					loggedInUser = i;

					Parent mainSceneRoot;
					Stage mainSceneStage;
					try {
						mainSceneRoot = FXMLLoader.load(getClass().getResource("/appDesign/MainScene.fxml"));
						mainSceneStage = new Stage();
						mainSceneStage.setTitle("PhotoExpress");
						mainSceneStage.setScene(new Scene(mainSceneRoot, 784, 437));
						mainSceneStage.getIcons().add(new Image("/appDesign/icon.png"));
						mainSceneStage.show();

						((Node) (event.getSource())).getScene().getWindow().hide();
						return;
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			//Admin.serializeData();

			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("User Not Found");
            alert.setHeaderText(null);
            alert.setContentText("User does not exist. Please try logging in again.");
            alert.showAndWait();
            return;



		}

	}
}
