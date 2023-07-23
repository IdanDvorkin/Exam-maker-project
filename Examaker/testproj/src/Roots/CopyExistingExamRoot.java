package Roots;


import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CopyExistingExamRoot extends AbstractRoot {
	private Label selectExamLbl = new Label("Select Exam");
	private Button selectBtn = new Button("Select");
	private ComboBox allExamsCB = new ComboBox();
	Button returnButton = new Button("Return to menu");

	public CopyExistingExamRoot() {
		super();
		stg.setTitle("Copy exam from file");
		root.add(selectExamLbl, 0, 0);
		root.add(allExamsCB, 1, 0);
		root.add(selectBtn, 2, 0);
		root.add(returnButton, 4, 4);

	}

	public Button getReturnBtn() {
		return returnButton;
	}

	public Button getSelectBtn() {
		return selectBtn;
	}

	public ComboBox<String> getAllExamsCB() {
		return allExamsCB;
	}

	public String getSelectedExam() {
		// TODO Auto-generated method stub
		if (allExamsCB.getValue()!=null)
			return allExamsCB.getValue().toString();

		else
			return "null";

	}

	public void clearExamCB() {
		// TODO Auto-generated method stub
		allExamsCB.getItems().clear();
	}

	public Stage getStage() {
		// TODO Auto-generated method stub
		return stg;
	}

}
