package Roots;

import javax.swing.JOptionPane;

import View.JavaFX;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;

public class AddQuestionRoot extends AbstractRoot {
	Button btnAddOpenQuestion = new Button("Add open Question");
	Button btnAddAmericanQuestion = new Button("Add american Question");
	Button btnAddAnswers= new Button("Add Answer");
	Button returnToMain = new Button("Return to main menu");
	Label addOpenQuestionLbl = new Label("Open question :");
	Label openQuestionLbl = new Label("Question :");
	Label americanQuestionLbl = new Label("Question :");
	Label OpenAnswerLbl = new Label("Answer");
	Label americanAnswerLbl = new Label("Answer");
	Label allAnswersLbl = new Label("all answers");
	Label statusLbl = new Label("Status");		
	TextField statusTxt= new TextField();
	TextField addOpenQuestionTxt= new TextField();
	TextField addOpenAnswerTxt= new TextField();
	Label addAmericanQuestionLbl = new Label("American question :");
	TextField addAmericanQuestionTxt= new TextField();
	static TextField addAmericanAnswerTxt= new TextField();
	static ComboBox allAnswers = new ComboBox();
	RadioButton trueRB = new RadioButton("true");
	RadioButton falseRB = new RadioButton("False");
	static ToggleGroup tg = new ToggleGroup();
    
	
	public AddQuestionRoot() {
super();
		trueRB.setToggleGroup(tg);
		falseRB.setToggleGroup(tg);
		root.add(addOpenQuestionLbl, 0, 0);
		root.add(openQuestionLbl,0, 1);
		root.add(addOpenQuestionTxt,1, 1);
		root.add(OpenAnswerLbl, 0, 2);
		root.add(addOpenAnswerTxt,1, 2);
		root.add(btnAddOpenQuestion, 1, 3);
		root.add(addAmericanQuestionLbl, 0, 4);
		root.add(americanQuestionLbl,0, 5);
		root.add(addAmericanQuestionTxt,1, 5);
		root.add(americanAnswerLbl, 0, 6);
		root.add(addAmericanAnswerTxt, 1, 6);
		root.add(btnAddAnswers, 3, 7);
		root.add(statusLbl, 0, 7);
		root.add(trueRB, 1, 7);
		root.add(falseRB, 1, 8);
		root.add(allAnswersLbl, 0, 9);
		root.add(allAnswers, 1, 9);
		root.add(btnAddAmericanQuestion, 1,10);
		root.add(returnToMain, 1, 11);
	
	}
	public Button getAddOpenQuestionBtn() {
		return btnAddOpenQuestion;
	}
	public Button getAddAmericanQuestionBtn() {
		return btnAddAmericanQuestion;
	}
	public Button getAddAnswersBtn() {
		return btnAddAnswers;
	}
	public Button getReturnToMainBtn() {
		return returnToMain;
	}
	public TextField getAddOpenTxt() {
		return addOpenQuestionTxt;
	}
	public TextField getAddOpenAnswerTxt() {
		return addOpenAnswerTxt;
	}
	public static void addAnswersToComboBox(String answer, String statusString) {
		// TODO Auto-generated method stub
		boolean status=false;
		
		if (statusString=="true") {
			status=true;
			
		}
		allAnswers.getItems().add(answer+" ["+status+"]");
	}
	public TextField getAddAmericanQuestionTxt() {
		// TODO Auto-generated method stub
		return addAmericanQuestionTxt;
	}
	public static ComboBox getAllAnswers() {
		// TODO Auto-generated method stub
		return allAnswers;
	}
	public static String getAnswer() {
		// TODO Auto-generated method stub
		return addAmericanAnswerTxt.getText();
	}
	public static String getStatusString() {
		// TODO Auto-generated method stub
		RadioButton statusRB;
		statusRB=(RadioButton) tg.getSelectedToggle();	
		if(statusRB!=null) {
			return statusRB.getText();
		}
		else
			return "null";
	
		}
			
		
		
	}
	

