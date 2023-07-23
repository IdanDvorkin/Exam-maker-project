package Roots;

import javafx.scene.control.Button;

public class MainRoot extends AbstractRoot {
	Button btnShowAllQuestions = new Button("Show All Questions");
	Button btnAddNewQuestion = new Button("Add New Question");
	Button btnUpdateQuestion = new Button("Update Question");
	Button btnUpdateAnswer = new Button("Update Answer");
	Button btnDeleteAnswer = new Button("Delete Answer");
	Button btnCreateManualExam = new Button("Create Manual Exam");
	Button btnCreateAutomaticExam = new Button("Create Automatic Exam");
	Button btnCopyLastExam = new Button("Copy Last Exam");
	Button btnCopyExistingExam = new Button("Copy Existing Exam ");
	Button btnVectorToList = new Button("Turn Vector To List ");
	Button btnListToSet = new Button("Turn List To Set ");
	Button btnMyArrayList = new Button("Turn Set To MyArrayList ");
	Button btnArrayList = new Button("Java ArrayList and Iterator ");

	public MainRoot() {
		super();
		root.add(btnShowAllQuestions, 0, 0);
		root.add(btnAddNewQuestion, 0, 1);
		root.add(btnUpdateQuestion, 0, 2);
		root.add(btnUpdateAnswer, 0, 3);
		root.add(btnDeleteAnswer, 0, 4);
		root.add(btnCreateManualExam, 0, 5);
		root.add(btnCreateAutomaticExam, 0, 6);
		root.add(btnCopyExistingExam, 0, 7);
		root.add(btnCopyLastExam, 0, 8);
		root.add(btnVectorToList, 0, 9);
		root.add(btnListToSet, 0,10);
		root.add(btnMyArrayList, 1,0);
		root.add(btnArrayList, 1,1);
		
	}

	public Button getShowAllQuestionsBtn() {
		return btnShowAllQuestions;
	}

	public Button getAddNewQuestionBtn() {
		return btnAddNewQuestion;
	}

	public Button getUpdateQuestionBtn() {
		return btnUpdateQuestion;
	}

	public Button getUpdateAnswerBtn() {
		return btnUpdateAnswer;
	}

	public Button getDeleteAnswerBtn() {
		return btnDeleteAnswer;
	}

	public Button getCreateManualExamBtn() {
		return btnCreateManualExam;
	}

	public Button getCreateAutomaticExamBtn() {
		return btnCreateAutomaticExam;
	}

	public Button getCopyExistingExamBtn() {
		return btnCopyExistingExam;
	}

	public Button getCopyLastExamBtn() {
		return btnCopyLastExam;
	}
	public Button getVectorToListBtn()
	{
		return btnVectorToList;
	}
	public Button getListToSetBtn()
	{
		return btnListToSet;
	}
	public Button getbtnMyArrayList()
	{
		return btnMyArrayList;
	}
	public Button getbtnArrayList()
	{
		return btnArrayList;
	}


}
