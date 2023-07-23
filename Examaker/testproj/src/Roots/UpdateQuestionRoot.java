package Roots;

import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;

public class UpdateQuestionRoot extends SelectQuestion {

	TextField newQuestionTxt = new TextField("new question");
	Button updateButton = new Button("Update");
	Button returnButton = new Button("Return to menu");

	public UpdateQuestionRoot() {

		super();
		stg.setTitle("Update answer");
		root.add(newQuestionTxt, 1, 1);
		root.add(updateButton, 1, 2);
		root.add(returnButton, 4, 6);
		updateButton.setVisible(false);
		newQuestionTxt.setVisible(false);
		stg.setScene(new Scene(root, 600, 630));
		stg.show();

	}

	public Button getReturnBtn() {
		return returnButton;
	}

	public Button getUpdateBtn() {
		return updateButton;
	}

	public String getNewQuestion() {
		// TODO Auto-generated method stub
		return newQuestionTxt.getText();
	}

	public void showUI() {
		updateButton.setVisible(true);
		newQuestionTxt.setVisible(true);
	}

	public void hideUI() {
		updateButton.setVisible(false);
		newQuestionTxt.setVisible(false);
	}

}
