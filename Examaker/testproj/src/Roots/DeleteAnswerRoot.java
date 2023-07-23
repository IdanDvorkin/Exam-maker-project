package Roots;

import javafx.scene.Scene;
import javafx.scene.control.Button;

public class DeleteAnswerRoot extends SelectQuestion {
	Button deleteBtn = new Button("Delete");
	Button returnButton = new Button("Return to menu");
	
	
	public DeleteAnswerRoot() {
		super();
		stg.setTitle("Delete answer");
		root.add(deleteBtn, 1, 6);
		root.add(returnButton, 4, 6);
		idLbl.setVisible(false);
		idTxt.setVisible(false);
		deleteBtn.setVisible(false);
		stg.setScene(new Scene(root,600,630));
		stg.show();
			
	}
	public Button getReturnBtn() {
		return returnButton;
	}
	public Button getDeleteBtn() {
		return deleteBtn;
	}
	public void showUI() {
		idLbl.setVisible(true);
		idTxt.setVisible(true);
		deleteBtn.setVisible(true);
		
	}
	public void hideUI() {
		idLbl.setVisible(false);
		idTxt.setVisible(false);
		deleteBtn.setVisible(false);
	}
	

	

}
