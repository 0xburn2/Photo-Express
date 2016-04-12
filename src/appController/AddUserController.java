package appController;

import java.io.FileNotFoundException;

import appData.Admin;
import appData.User;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AddUserController {

    @FXML
    Button button;

    @FXML
    TextField usernameField;


    public ListView<User> usersField;

    @FXML
    public void createUser(ActionEvent event) throws Exception {

        if (Admin.usernameExists(usernameField.getText())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Username Taken");
            alert.setHeaderText(null);
            alert.setContentText("This username is taken. Please try another username.");
            alert.showAndWait();
            return;
        }

        if (Admin.blankName(usernameField.getText())) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Please Enter a Username");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid username.");
            alert.showAndWait();
            return;
        }

        Admin.addUser(usernameField.getText());
        Admin.serializeData();

        ((Node) (event.getSource())).getScene().getWindow().hide();

    }

}
