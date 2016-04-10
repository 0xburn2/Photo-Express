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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class SearchSceneController {

  @FXML
  ChoiceBox tagBox = new ChoiceBox();

    public void initialize(URL url, ResourceBundle rb) {
        User temp = LoginSceneController.getLoggedInUser();
        tagBox.setItems(FXCollections.observableArrayList("choice 1", "choice 2"));
    }
}
