package appController;

import java.io.FileNotFoundException;
import java.util.ConcurrentModificationException;

import appData.Admin;
import appData.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

/**
 * Controls the Controller that deletes users
 */

public class DeleteUserController {

	@FXML
	Button cancelButton;

	
	public void cancel(ActionEvent event){
		((Node) (event.getSource())).getScene().getWindow().hide();
		
	}
	
	public void delete(ActionEvent event){
		
		Admin.deleteUser(AdminSceneController.getDeletedUser());
		((Node) (event.getSource())).getScene().getWindow().hide();
		//try {
		//	AdminSceneController.getUserList().setItems(FXCollections.observableList(Admin.getList()));
	//	} catch (FileNotFoundException e){
	//		e.printStackTrace();
	//	}
	}
	
	
	
}
