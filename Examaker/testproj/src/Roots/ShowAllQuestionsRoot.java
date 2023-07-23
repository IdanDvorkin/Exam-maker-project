package Roots;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ShowAllQuestionsRoot extends SelectQuestion {
	GridPane showQuestionsRoot = new GridPane();
	Label allQuestionsLbl = new Label("All questions: ");

	Button closeBtn = new Button("Close");



	public ShowAllQuestionsRoot() {

		super();
		stg.setTitle("All questions");
		chooseQuestionLbl.setVisible(false);
		selectBtn.setVisible(false);
		root.add(allQuestionsLbl, 0, 0);
		root.add(closeBtn, 6, 6);

	}

	public Button getCloseBtn() {
		return closeBtn;
	}

}
