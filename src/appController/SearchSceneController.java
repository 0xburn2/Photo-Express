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
import java.io.FileInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.TilePane;
import javafx.stage.Modality;

public class SearchSceneController implements Initializable {

  @FXML
  ChoiceBox<String> tagValueBox = new ChoiceBox<String>();
  @FXML
  ChoiceBox<String> tagTypeBox = new ChoiceBox<String>();
  @FXML
  ChoiceBox<String> startDateBox = new ChoiceBox<String>();
  @FXML
  ChoiceBox<String> endDateBox = new ChoiceBox<String>();

  @FXML
  TilePane tilePane = new TilePane();

  @FXML
  ScrollPane scrollPane = new ScrollPane();

  @FXML
  ImageView bigImageView;

  @FXML
  Label tags;
  @FXML
  Label caption;
  @FXML
  Label dateTaken;

  private static Photo selectedPhoto;

  public static Photo getSelectedPhoto(){
    return selectedPhoto;
  }

  public void loadImages(ArrayList<Photo> photos) {
    tilePane = new TilePane();
    tilePane.setPadding(new Insets(15, 15, 15, 15));
    tilePane.setHgap(15);

    for (Photo p : photos) {
      ImageView imageView;
      imageView = createImageView(p);
      tilePane.getChildren().addAll(imageView);
    }

     scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // Horizontal
     scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED); // Vertical scroll bar
     scrollPane.setFitToWidth(true);
     scrollPane.setContent(tilePane);
   }

   private ImageView createImageView(Photo photo) {
    // DEFAULT_THUMBNAIL_WIDTH is a constant you need to define
    // The last two arguments are: preserveRatio, and use smooth (slower)
    // resizing

    ImageView imageView = null;
    try {
      Image image = new Image(new FileInputStream(photo.getPhotoFile()), 75, 0, true, true);
      imageView = new ImageView(image);
      imageView.setFitWidth(75);

      imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent mouseEvent) {

          if(mouseEvent.getButton().equals(MouseButton.PRIMARY)){
            selectedPhoto = photo;
            bigImageView.setImage(image);
            caption.setText("Caption: " + photo.getCaption());
            tags.setText("Tags: " + photo.getPreTags());
            dateTaken.setText("Date Taken: " + photo.getDateString());
          }
        }
      });


    } catch (FileNotFoundException ex) {
      ex.printStackTrace();
    }
    return imageView;
  }

  static ArrayList<Photo> correctPhotos;

  String startDate = "";
  String endDate = "";
  String tagValue = "";
  String tagType = "";

  public static ArrayList<Photo> getCorrectPhotos(){
    return correctPhotos;
  }

  public void createNewAlbum(ActionEvent event) throws Exception {

    createStage(event, "PhotoExpress - Create New Album", "/appDesign/CreateAlbumFromPhoto.fxml", 526, 249);

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

  public void initialize(URL url, ResourceBundle rb) {

    User tempUser = LoginSceneController.getLoggedInUser();
    System.out.println("hi");
    System.out.println(User.getAllTags(tempUser));

    tagValueBox.setItems(FXCollections.observableArrayList(User.getAllValues(tempUser)));
    tagValueBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == null) {
        return;
      }
      System.out.println(newValue);
       tagValue = newValue;
      correctPhotos = new ArrayList<Photo>();
      correctPhotos = findPhotos(tempUser, tagValue, tagType, "", "");
      loadImages(correctPhotos);
      for (Photo photo : correctPhotos) {
        System.out.println(photo.getCaption());
      }
    });

    tagTypeBox.setItems(FXCollections.observableArrayList(User.getAllTypes(tempUser)));
    tagTypeBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
      if (newValue == null) {
        return;
      }
      System.out.println(newValue);
       tagType = newValue;
      correctPhotos = new ArrayList<Photo>();
      correctPhotos = findPhotos(tempUser, tagValue, tagType, "", "");
      loadImages(correctPhotos);
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
      correctPhotos = findPhotos(tempUser, "", "", startDate, endDate);
      loadImages(correctPhotos);
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
      correctPhotos = findPhotos(tempUser, "", "", startDate, endDate);
      loadImages(correctPhotos);
      for (Photo photo : correctPhotos) {
        System.out.println(photo.getCaption());
      }
    });
  }

  public static ArrayList<Photo> findPhotos(User tempUser, String tagValue, String tagType, String startDate, String endDate) {
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

    if (!(tagValue.equals("")) && !(tagType.equals(""))) {
      for (Photo photo : User.getUserPhotos(tempUser)) {
        for (Tag tag : photo.getTags(photo)) {
          System.out.println("tag is " + tag.getValue());
          if (tag.getValue().equals(tagValue) && tag.getType().equals(tagType)) {
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
