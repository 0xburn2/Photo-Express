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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.scene.control.ChoiceBox;

public class SearchSceneController implements Initializable {

    @FXML
    ChoiceBox<Tag> tagBox = new ChoiceBox<Tag>();
    @FXML
    ChoiceBox<String> startDateBox = new ChoiceBox<String>();
    @FXML
    ChoiceBox<String> endDateBox = new ChoiceBox<String>();

    ArrayList<Photo> correctPhotos;

    String startDate = "";
    String endDate = "";
    Tag tag;

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
            tag = newValue;
            correctPhotos = new ArrayList<Photo>();
            //  //Get all the user's photos
            // for(Photo photo : tempUser.getUserPhotos(tempUser)){
            //      //System.out.println(photo.getCaption());
            //   for(Tag tag : photo.getTags(photo)){
            //     System.out.println("tag is " + tag.getValue());
            //     if(tag.getValue().equals(newValue.getValue())){
            //       System.out.println("tag match");
            //       if(!correctPhotos.contains(photo)){
            //         correctPhotos.add(photo);
            //       }
            //     }
            //   }
            // }
            correctPhotos = findPhotos(tempUser, tag, "", "");
            for (Photo photo : correctPhotos) {
                System.out.println(photo.getCaption());
            }
        });

        startDateBox.setItems(FXCollections.observableArrayList(User.getAllDates(tempUser)));
        startDateBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            System.out.println(newValue);
            startDate = newValue;
            correctPhotos = new ArrayList<Photo>();
            correctPhotos = findPhotos(tempUser, null, startDate, endDate);

            for (Photo photo : correctPhotos) {
                System.out.println(photo.getCaption());
            }
        });

        endDateBox.setItems(FXCollections.observableArrayList(User.getAllDates(tempUser)));
        endDateBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            System.out.println(newValue);
            endDate = newValue;
            correctPhotos = findPhotos(tempUser, null, startDate, endDate);

            for (Photo photo : correctPhotos) {
                System.out.println(photo.getCaption());
            }
        });
    }

    public static ArrayList<Photo> findPhotos(User tempUser, Tag tagLookFor, String startDate, String endDate) {
        ArrayList<Photo> correctPhotos = new ArrayList<Photo>();
        System.out.println("All photos----");

        LocalDate startDateFormatted;
        LocalDate endDateFormatted;

        if (startDate.equals("")) {
            System.out.println("startDate not set");
        }

        if (endDate.equals("")) {
            System.out.println("endDate not set");
        }

        if (tagLookFor != null) {
            for (Photo photo : User.getUserPhotos(tempUser)) {
                for (Tag tag : photo.getTags(photo)) {
                    System.out.println("tag is " + tag.getValue());
                    if (tag.getValue().equals(tagLookFor.getValue())) {
                        System.out.println("tag match");
                        if (!correctPhotos.contains(photo)) {
                            correctPhotos.add(photo);
                        }
                    }
                }
            }
            return correctPhotos;
        }

        if (!(endDate.equals("")) && !(startDate.equals(""))) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            startDateFormatted = LocalDate.parse(startDate, formatter);
            System.out.println(startDateFormatted); // 2010-01-02

            formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
            endDateFormatted = LocalDate.parse(endDate, formatter);
            System.out.println(startDateFormatted); // 2010-01-02

            for (Photo testPhoto : User.getUserPhotos(tempUser)) {
                formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
                LocalDate photoDateFormatted = LocalDate.parse(testPhoto.getSimpleDateString(), formatter);
                System.out.println("Photo Date" + photoDateFormatted); // 2010-01-02) 

                if (photoDateFormatted.isEqual(startDateFormatted) || photoDateFormatted.isEqual(endDateFormatted) || (photoDateFormatted.isAfter(startDateFormatted)) && (photoDateFormatted.isBefore(endDateFormatted))) {
                    if (!correctPhotos.contains(testPhoto)) {
                        correctPhotos.add(testPhoto);
                    }
                }
            }
        }

//        if (startDate.equals("") && !(endDate.equals(""))) {
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
//            endDateFormatted = LocalDate.parse(endDate, formatter);
//            System.out.println(endDateFormatted); // 2010-01-02
//
//            for (Photo testPhoto : correctPhotos) {
//                formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy", Locale.ENGLISH);
//                LocalDate photoDateFormatted = LocalDate.parse(testPhoto.getSimpleDateString(), formatter);
//                System.out.println("Photo Date" + photoDateFormatted); // 2010-01-02
//                if (!(endDateFormatted.equals(photoDateFormatted))) {
//                    correctPhotos.remove(testPhoto);
//                }
//            }
//        }
        return correctPhotos;
    }
}
