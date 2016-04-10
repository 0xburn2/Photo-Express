package appController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import appData.Admin;
import appData.Album;
import appData.Tag;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class SearchSceneController implements Initializable {

  @FXML
  ChoiceBox<Tag> tagBox = new ChoiceBox<Tag>();

    public void initialize(URL url, ResourceBundle rb) {
    	
        User temp = LoginSceneController.getLoggedInUser();
        System.out.println("hi");
        System.out.println(User.getAllTags(temp));
        
        
        tagBox.setItems(FXCollections.observableArrayList(User.getAllTags(temp)));
        tagBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue == null) {
				return;
			}

		});


    }
}
