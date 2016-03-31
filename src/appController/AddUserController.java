package appController;

import java.io.FileNotFoundException;

import appData.Admin;
import appData.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AddUserController {

	@FXML
	Button button;
	
	@FXML
	TextField usernameField;
	
	public void createUser(ActionEvent event) throws Exception {
		
		if (Admin.usernameExists(usernameField.getText())){
			Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Username Taken");
            alert.setHeaderText(null);
            alert.setContentText("This username is taken. Please try another username.");
            alert.showAndWait();
            return;
		}
		
		Admin.addUser(usernameField.getText());
		Admin.serializeData();
		
		 
		 
		 ((Node) (event.getSource())).getScene().getWindow().hide();
		
		
		
	}
	
}
