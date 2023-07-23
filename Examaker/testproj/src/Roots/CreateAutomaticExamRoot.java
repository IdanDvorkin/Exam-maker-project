package Roots;

import Model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CreateAutomaticExamRoot extends AbstractRoot {
	private Label numOfQuestionsLbl = new Label("number of question:");
	private Label examQuestionsLbl = new Label("all questions: ");
	private TextField numOfQuestionsTxt = new TextField();
	private ComboBox autoExamCB = new ComboBox();
	private Button   createBtn = new Button("Create");
	Button returnButton = new Button("Return to menu");
	
	public CreateAutomaticExamRoot() {
		super();
		stg.setTitle("Create automatic exam");
		root.add(numOfQuestionsLbl, 0, 0);
		root.add(numOfQuestionsTxt, 1, 0);
		root.add(createBtn, 2, 0);
		root.add(examQuestionsLbl, 0, 1);
		root.add(autoExamCB, 0, 2);
		root.add(returnButton, 4, 6);
	}
	public Button getReturnBtn() {
		return returnButton;
	}
	public ComboBox getAutoExamComboBox() {
		return autoExamCB;
	}
	public TextField getNumOfQuestionTxt() {
		return numOfQuestionsTxt;
	}
	public Button getCreateBtn() {
		return createBtn;
	}
	public Stage getStage() {
		// TODO Auto-generated method stub
		return stg;
	}

}
