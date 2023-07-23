package Roots;

import Model.Question;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SelectQuestion extends AbstractRoot {
	protected ComboBox cmAllQuestions = new ComboBox();
	protected Button selectBtn = new Button("Select");
	Label idLbl = new Label("answer id");
	TextField idTxt = new TextField();
	protected Label chooseQuestionLbl = new Label("choose question");
	protected Stage stg = new Stage();
	
	
	public SelectQuestion()  {
		super();
		
		
		root.add(chooseQuestionLbl, 0, 0);
		root.add(cmAllQuestions, 1, 0);
		root.add(idLbl, 0, 3);
		root.add(idTxt, 1, 3);
		root.add(selectBtn, 2, 0);
		idTxt.setVisible(false);
		idLbl.setVisible(false);

		
	}
	public Stage getStage()
	{
		return this.stg;
	}
	public ComboBox<Question> getComboBox() {
		return cmAllQuestions;
	}
	public void clearComboBox() {
		cmAllQuestions.getItems().clear();
	}
	public Question getSelectedQuestion() {
		// TODO Auto-generated method stub
		return (Question) cmAllQuestions.getValue();
	}
	public Button getSelectBtn() {
		return selectBtn;
	}


	public String getAnswerIdText() {

		if (idTxt.getText()=="") {
			return "0";
		}
		return idTxt.getText();
	}

}
