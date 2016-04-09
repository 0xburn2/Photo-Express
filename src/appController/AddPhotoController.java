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

public class AddPhotoController {
        @FXML
	Button addPhotoButton;
	@FXML
	TextField photoUrlField;
        @FXML
	TextField captionField;
        @FXML
	TextField tagField;
        
        public void addPhoto(ActionEvent event) throws Exception {
            
	}
}
