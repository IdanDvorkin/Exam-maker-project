package Roots;



import Model.Answer;
import Model.Question;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class CreateManualExamRoot extends SelectQuestion {
	private Button addOpenBtn = new Button("Add");
	private Button addAmericanBtn = new Button("Add");
	private Button transferBtn = new Button("transfer");
	private Button createBtn = new Button("Create");
	private ComboBox allAnswersCB = new ComboBox();
	private ComboBox selectedAnswersCB = new ComboBox();
	private ComboBox examCB = new ComboBox<Question>();
	private Label examLbl = new Label("exam");
	private Label chosenAnswersLbl = new Label("The chosen answers");
	private Label chooseAnswerLbl = new Label("Choose answers from the list");
	Button returnButton = new Button("Return to menu");
	
	public CreateManualExamRoot() {
		super();
		stg.setTitle("Create manual exam");
		root.add(addOpenBtn, 2, 1);
		root.add(allAnswersCB, 0, 4);
		root.add(transferBtn, 1, 4);
		root.add(selectedAnswersCB, 2, 4);
		root.add(addAmericanBtn, 6, 4);
		root.add(examLbl, 0, 5);
		root.add(examCB, 0, 6);
		root.add(createBtn, 1, 6);
		root.add(chosenAnswersLbl, 2, 3);
		root.add(chooseAnswerLbl, 0, 3);
		root.add(returnButton, 4, 6);
		chooseAnswerLbl.setVisible(false);
		chosenAnswersLbl.setVisible(false);
		allAnswersCB.setVisible(false);
		addOpenBtn.setVisible(false);
		selectedAnswersCB.setVisible(false);
		transferBtn.setVisible(false);
		addAmericanBtn.setVisible(false);
		examCB.setVisible(false);
		examLbl.setVisible(false);
		createBtn.setVisible(false);
		
		stg.setScene(new Scene(root,800,930));
		stg.show();
		
	}
	public Button getReturnBtn() {
		return returnButton;
	}
	public Button getAddBtn() {
		return addOpenBtn;
	}
	public void showAddBtn() {
		addOpenBtn.setVisible(true);
		selectedAnswersCB.setVisible(false);
		allAnswersCB.setVisible(false);
		transferBtn.setVisible(false);
		addAmericanBtn.setVisible(false);
		
		
	}
	public void showAmericanUI() {
		// TODO Auto-generated method stub
		chooseAnswerLbl.setVisible(true);
		chosenAnswersLbl.setVisible(true);
		allAnswersCB.setVisible(true);
		addOpenBtn.setVisible(false);
		selectedAnswersCB.setVisible(true);
		transferBtn.setVisible(true);
		addAmericanBtn.setVisible(true);
	}
	public void showExamUI() {
		examCB.setVisible(true);
		examLbl.setVisible(true);
		createBtn.setVisible(true);
		
	}
	public ComboBox getAllAnswersCB() {
		return allAnswersCB;
	}
	public ComboBox getSelectedAnswerCB() {
		return selectedAnswersCB;
	}
	public void transferAnswer(Answer answer) {
		for (int i = 0; i < allAnswersCB.getItems().size(); i++) {
			
			if (allAnswersCB.getItems().get(i).equals(answer)) {
				allAnswersCB.getItems().remove(i);
				selectedAnswersCB.getItems().add(answer);
			}
		}
	}
	public Button getCreateBtn() {
		return createBtn;
	}
	public Button getTransferBtn() {
		return transferBtn;
	}
	public ComboBox getExamCB() {
		return examCB;
	}
	public Answer getSelectedAnswer() {
		return (Answer)allAnswersCB.getValue();
	}
	public Button getAmericanBtn() {
		return addAmericanBtn;
	}
	public void clearComboBox(ComboBox cb) {
		// TODO Auto-generated method stub
		
		cb.getItems().clear();
	}
	public void addQuestionToComboBox(Question selectedQuestion) {
		// TODO Auto-generated method stub
		examCB.getItems().add(selectedQuestion);
	}
	public void remove(Question selectedQuestion) {
		// TODO Auto-generated method stub
		for (int i = 0; i < cmAllQuestions.getItems().size(); i++) {
			if (cmAllQuestions.getItems().get(i).equals(selectedQuestion)) {
				cmAllQuestions.getItems().remove(i);
			}
		}
		}
	public void hideUI() {
		// TODO Auto-generated method stub
		
		allAnswersCB.setVisible(false);
		addOpenBtn.setVisible(false);
		selectedAnswersCB.setVisible(false);
		transferBtn.setVisible(false);
		addAmericanBtn.setVisible(false);
	}
	public void close() {
		// TODO Auto-generated method stub
		stg.close();
		
	}


}
