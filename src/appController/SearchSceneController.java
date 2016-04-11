package appController;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import appData.Admin;
import appData.Album;
import appData.Photo;
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
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class SearchSceneController implements Initializable {

  @FXML
  ChoiceBox<Tag> tagBox = new ChoiceBox<Tag>();

  ArrayList<Photo> correctPhotos;

  public void initialize(URL url, ResourceBundle rb) {

    User tempUser = LoginSceneController.getLoggedInUser();
    System.out.println("hi");
    System.out.println(User.getAllTags(tempUser));

    tagBox.setItems(FXCollections.observableArrayList(User.getAllTags(tempUser)));
    tagBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
     if (newValue == null) {
      return;
    }
     System.out.println(newValue);
      correctPhotos = new ArrayList<Photo>();
     //Get all the user's photos
     for(Photo photo : tempUser.getUserPhotos(tempUser)){
         System.out.println(photo.getCaption());
      for(Tag tag : photo.getTags(photo)){
          System.out.println("tag is " + tag.getValue());
        if(tag.getValue().equals(newValue.getValue())){
            System.out.println("tag match");
          if(!correctPhotos.contains(photo)){
              correctPhotos.add(photo);
          }
        }
      }
     }
     for(Photo photo: correctPhotos){
         System.out.println(photo.getCaption());
     }
  });
  }
}
