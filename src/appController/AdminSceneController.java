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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;

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
    private AdminSceneController adminController;
    private AddUserController addUserController;

    public ListView<User> getUserList() {

        return listViewofUsers;

    }

    public void refreshList() throws FileNotFoundException {
        System.out.println("refreshing");
        for (User user : Admin.getList()) {
            System.out.println(user.getName());
        }
        try {
            listViewofUsers.setItems(FXCollections.observableList(Admin.getList()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        listViewofUsers.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }

            //deletedUserName = newValue.getName();
        }
        );
        listViewofUsers.getSelectionModel().select(0);
    }

    public void logout(ActionEvent event) throws Exception {
        PhotoAlbum.primaryStage.show();
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void addUser(ActionEvent event) throws Exception {

//            URL location = getClass().getResource("/appDesign/AdminPanelScene.fxml");
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(location);
//            adminController = fxmlLoader.getController();
//
//            location = getClass().getResource("/appDesign/CreateNewUserDialog.fxml");
//            fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(location);
//            addUserController = fxmlLoader.getController();
//
//            addUserController.setAdminController(adminController);
        createStage(event, "PhotoExpress - Create New User", "/appDesign/CreateNewUserDialog.fxml", 526, 249);

//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("/appDesign/CreateNewUserDialog.fxml"));
//            //AnchorPane frame = fxmlLoader.load();
//            AddUserController c = (AddUserController) fxmlLoader.getController();
//            // Add this too:
//            c.usersList = listViewofUsers;
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

}
