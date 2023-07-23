package Roots;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class UpdateAnswerRoot extends SelectQuestion {
	Label newAnswerLbl = new Label("new answer");
	Label newStatusLbl = new Label("new status");
	Button returnButton = new Button("Return to menu");
	
	TextField newAnswerTxt = new TextField();
	

	RadioButton trueRB = new RadioButton("true");
	RadioButton falseRB = new RadioButton("false");
	static ToggleGroup tg = new ToggleGroup();
	Button updateBtn = new Button("Update");

	

	public UpdateAnswerRoot() {
		super();
		stg.setTitle("Update answer");
		trueRB.setToggleGroup(tg);
		falseRB.setToggleGroup(tg);
		root.add(newAnswerLbl, 0, 4);
		root.add(newAnswerTxt, 1, 4);
		root.add(newStatusLbl, 1, 5);
		root.add(trueRB, 2, 5);
		root.add(falseRB, 3, 5);
		root.add(updateBtn, 1, 6);
		root.add(returnButton, 4, 6);
		idLbl.setVisible(false);
		idTxt.setVisible(false);
		newStatusLbl.setVisible(false);
		falseRB.setVisible(false);
		trueRB.setVisible(false);
		newAnswerLbl.setVisible(false);
		newAnswerTxt.setVisible(false);
		updateBtn.setVisible(false);
		stg.setScene(new Scene(root,600,630));
		stg.show();
		
	}

	public void showOpenUI() {
		// TODO Auto-generated method stub
		newAnswerLbl.setVisible(true);
		newAnswerTxt.setVisible(true);
		updateBtn.setVisible(true);
		newStatusLbl.setVisible(false);
		falseRB.setVisible(false);
		trueRB.setVisible(false);
		idLbl.setVisible(false);
		idTxt.setVisible(false);
	}

	public void showAmericanUI() {
		// TODO Auto-generated method stub
		idLbl.setVisible(true);
		idTxt.setVisible(true);
		newStatusLbl.setVisible(true);
		falseRB.setVisible(true);
		trueRB.setVisible(true);
		newAnswerLbl.setVisible(true);
		newAnswerTxt.setVisible(true);
		updateBtn.setVisible(true);
	}

	public Button getReturnBtn() {
		return returnButton;
	}
	public RadioButton getSelectedButton()
	{
		return (RadioButton)tg.getSelectedToggle();
	}

	public String getAnswerText() {
		// TODO Auto-generated method stub
	
		return newAnswerTxt.getText();
	}
	public static String getStatusString() {
		// TODO Auto-generated method stub
		RadioButton statusRB;
		statusRB=(RadioButton) tg.getSelectedToggle();	
		System.out.println("status radio button :"+statusRB);
		if (statusRB==null) {
			return "null";
		}
		return statusRB.getText();
	}

	public Button getUpdateBtn() {
		// TODO Auto-generated method stub
		return updateBtn;
	}

	public void hideUI() {
		// TODO Auto-generated method stub
		newAnswerLbl.setVisible(false);
		newAnswerTxt.setVisible(false);
		updateBtn.setVisible(false);
		newStatusLbl.setVisible(false);
		falseRB.setVisible(false);
		trueRB.setVisible(false);
		idLbl.setVisible(false);
		idTxt.setVisible(false);
		
		
	}

	

}
