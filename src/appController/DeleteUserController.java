package appController;

import java.util.ConcurrentModificationException;

import appData.Admin;
import appData.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class DeleteUserController {

	@FXML
	Button cancelButton;

	
	public void cancel(ActionEvent event){
		((Node) (event.getSource())).getScene().getWindow().hide();
		
	}
	
	public void delete(ActionEvent event){
		try {
			Admin.deleteUser(AdminSceneController.getDeletedUser());
		} catch (ConcurrentModificationException e){
			System.out.println("sdf");
		}
		
		
	}
	
	
	
}
