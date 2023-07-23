package Roots;

import Model.Question;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CopyLastExamRoot extends AbstractRoot {
	private Label lastExamLbl = new Label("Last exam :");
	private ComboBox lastExamCB = new ComboBox();
	private Button copyBtn = new Button("copy");
	Button returnButton = new Button("Return to menu");
	public CopyLastExamRoot() {
		super();
		stg.setTitle("Copy exam from memory");
		root.add(lastExamLbl, 0, 0);
		root.add(lastExamCB, 1, 0);
		root.add(copyBtn, 2, 0);
		root.add(returnButton, 4, 4);
	}
	public Button getReturnBtn() {
		return returnButton;
	}
	public Button getCopyBtn() {
		return copyBtn;
	}
	public ComboBox<Question> getLastExamCB() {
		return lastExamCB;
	}
	public Stage getStage() {
		// TODO Auto-generated method stub
		return stg;
	}
	

}
