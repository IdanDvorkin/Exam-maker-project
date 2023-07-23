package Roots;
import java.awt.Color;

import javafx.scene.control.Button;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class TurnSetToMyArrayList extends SelectQuestion{

	GridPane showQuestionsRoot = new GridPane();
	Label allQuestionsLbl = new Label("Set to MyArrayList ");
	Label ObserverLbl = new Label("Print here-----> ");
	Button closeBtn = new Button("Close");
	Button removeAllBtn = new Button("Remove All");
	Button removeBtn = new Button("Remove one");
	Button ObserverBtn=new Button("Print using Iterator");
	Button createBtn=new Button("Create iterator");
	Button ObserverBtnRestored=new Button("Restore and print memento");
	Label ObserverLblRestored = new Label("Print here-----> ");

	
	public TurnSetToMyArrayList() {

		super();
		stg.setTitle("All questions");
		chooseQuestionLbl.setVisible(false);
		ObserverLbl.setStyle("-fx-text-fill: green; -fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-background-color: #cccccc;");
		ObserverLblRestored.setStyle("-fx-text-fill: green; -fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-background-color: #cccccc;");
		ObserverLbl.setVisible(false);
		ObserverBtn.setVisible(false);
		ObserverBtnRestored.setVisible(false);
		ObserverLblRestored.setVisible(false);
		selectBtn.setVisible(false);
		root.add(createBtn, 0, 3);
		root.add(ObserverBtn, 1, 4);
		root.add(ObserverLbl, 0, 4);
		root.add(ObserverLblRestored, 0, 5);
		root.add(ObserverBtnRestored, 1, 5);
		root.add(allQuestionsLbl, 0, 0);
		root.add(removeAllBtn, 1, 2);
		root.add(removeBtn, 1, 3);
		root.add(closeBtn, 6, 6);

	}
	
	public Button getRemoveAllBtn() {
		return removeAllBtn;
	}
	
	public Button getCloseBtn() {
		return closeBtn;
	}
	public Button getRemoveBtn() {
		return removeBtn;
	}
	public Label getObserverLbl()
	{
		return ObserverLbl;
	}
	public Label getObserverLblRestored()
	{
		return ObserverLblRestored;
	}
	public Button getObserverBtn()
	{
		return ObserverBtn;
	}
	public Button getCreateBtn()
	{
		return createBtn;
	}
	public Button getObserverBtnRestored() {
		return ObserverBtnRestored;
	}


	




}
