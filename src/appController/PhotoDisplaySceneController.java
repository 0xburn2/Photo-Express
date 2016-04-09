package appController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import appData.Admin;
import appData.Album;
import appData.Photo;
import appData.User;
import appDesign.PhotoAlbum;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PhotoDisplaySceneController implements Initializable {

	@FXML
	Label title;

	@FXML
	TilePane tilePane = new TilePane();
	
	@FXML
	ScrollPane scrollPane = new ScrollPane();

	public void addPhoto(ActionEvent event) throws Exception {

		createStage(event, "PhotoExpress - Create New Photo", "/appDesign/AddPhotoDialog.fxml", 527, 341);

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
		User temp = LoginSceneController.getLoggedInUser();
		title.setText(MainSceneController.getSelectedAlbum().getName());
		loadImages(MainSceneController.getSelectedAlbum().getPhotos());
	}
	
	public void loadImages(ArrayList<Photo> photos) {

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
			Image image = new Image(new FileInputStream(photo.getPhotoFile()), 25, 0, true, true);
			imageView = new ImageView(image);
			imageView.setFitWidth(25);
			try {
				imageView = new ImageView();
				image = new Image(new FileInputStream(photo.getPhotoFile()));
				imageView.setImage(image);
				imageView.setStyle("-fx-background-color: BLACK");
				imageView.setFitHeight(PhotoAlbum.primaryStage.getHeight() - 10);
				imageView.setPreserveRatio(true);
				imageView.setSmooth(true);
				imageView.setCache(true);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		return imageView;
	}

}
