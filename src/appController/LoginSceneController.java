package appController;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginSceneController {
	
	
	
	@FXML
    private Button loginButton;

	public void login(ActionEvent event) throws Exception {   
		Parent root; 
		try {
	            root = FXMLLoader.load(getClass().getResource("/appDesign/MainScene.fxml"));
	            Stage stage = new Stage();
	            stage.setTitle("Photo Album");
	            stage.setScene(new Scene(root, 784, 437));
	            stage.show();

	            //hide this current window (if this is whant you want
	            ((Node)(event.getSource())).getScene().getWindow().hide();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
	}
}
