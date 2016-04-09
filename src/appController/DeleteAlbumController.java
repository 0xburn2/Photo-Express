package appController;

import appData.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.Node;

public class DeleteAlbumController {

	public void deleteAlbum(ActionEvent event) {

		Admin.deleteAlbum(MainSceneController.getSelectedAlbum().getName());
		((Node) (event.getSource())).getScene().getWindow().hide();

	}

	public void cancel(ActionEvent event) {

		((Node) (event.getSource())).getScene().getWindow().hide();

	}

}
