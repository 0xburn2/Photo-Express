package appController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import appData.User;
import appDesign.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import appData.Admin;
import appData.User;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Controls the Admin Scene functions
 */

public class AdminSceneController implements Initializable {

    @FXML
    Button logoutButton;
    @FXML
    Button addButton;
    @FXML
    Button deleteButton;
    @FXML
    public ListView<User> listViewofUsers;

    static String deletedUserName;

    public ListView<User> getUserList() {
        return listViewofUsers;
    }

    public void logout(ActionEvent event) throws Exception {
        PhotoAlbum.primaryStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void addUser(ActionEvent event) throws Exception {
      FXMLLoader fxmlLoader = new FXMLLoader();
        Pane p = fxmlLoader.load(getClass().getResource("/appDesign/CreateNewUserDialog.fxml").openStream());
        AddUserController addUserController = (AddUserController) fxmlLoader.getController();
      // Add this too:
      addUserController.usersField = listViewofUsers;
        createStage(event, "PhotoExpress - Create New User", "/appDesign/CreateNewUserDialog.fxml", 526, 249);

    }

    @FXML
    public void deleteUser(ActionEvent event) throws Exception {
        createStage(event, "PhotoExpress - Confirm Delete", "/appDesign/DeleteUserDialog.fxml", 503, 141);

    }

    public static String getDeletedUser() {
        String temp = deletedUserName;
        return temp;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listViewofUsers.setItems(FXCollections.observableList(Admin.getList()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        listViewofUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }

            deletedUserName = newValue.getName();
        }
        );
        listViewofUsers.getSelectionModel().select(0);
    }

    @FXML
    public void refreshList() throws FileNotFoundException {
        //System.out.println("refreshing");
        for (User user : Admin.getList()) {
            System.out.println(user.getName());
        }
        listViewofUsers.setItems(FXCollections.observableList(Admin.getList()));

        listViewofUsers.getSelectionModel().select(0);
        //System.out.println("finish refresh");
    }

}
