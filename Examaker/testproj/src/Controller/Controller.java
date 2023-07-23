package Controller;

import java.io.FileNotFoundException;
import java.util.Vector;

import javax.swing.JOptionPane;
import Listeners.EventsListener;
import Listeners.UIEventesListener;
import Model.AmericanQuestion;
import Model.Answer;
import Model.Question;
import Model.QuestionPool;
import Model.Set;
import Roots.AddQuestionRoot;
import Roots.CopyExistingExamRoot;
import Roots.CopyLastExamRoot;
import Roots.CreateAutomaticExamRoot;
import Roots.CreateManualExamRoot;
import Roots.UpdateAnswerRoot;
import Roots.UpdateQuestionRoot;
import View.JavaFX;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;


public class Controller implements EventsListener, UIEventesListener {
	private QuestionPool model;
	private JavaFX view;

	public Controller(QuestionPool model, JavaFX view) {
		this.model = model;
		this.view = view;

		this.model.registerListener(this);
		this.view.registerListener(this);
	}

	public void addOpenQuestionToUI(String question, String answer) {
		model.addQuestion(question, answer);
		System.out.println("THE MODEL IS: " + model);
	}

	@Override
	public void addedOpenQuestionToModelEvent(String question, String answer) {
		// TODO Auto-generated method stub
		view.addOpenQuestionToUI(question, answer);

	}

	@Override
	public String getAllQuestions() {
		// TODO Auto-generated method stub

		return model.toString();
	}

	@Override
	public void deleteAnswerUI(int answerChoice, int deletedId) throws Exception {
		// TODO Auto-generated method stub
		model.deleteAnswer(deletedId, answerChoice + 1);
		System.out.println("NEW MODEL : " + model);
	}

	@Override
	public void addAnswers(String question, ComboBox allAnswers, AddQuestionRoot root) {
		// TODO Auto-generated method stub
		if (question.isEmpty() || allAnswers.getItems().isEmpty()) {
			if (allAnswers.getItems().isEmpty())
				view.wrongInput("Please add an answer first");

			else
				view.wrongInput("Input can't be empty");
		} else {
			Set<Answer> answers = new Set<Answer>();
			String combinedAnswer;
			String answer;
			String statusString;
			boolean status;
			int endIndex;
			for (int i = 0; i < allAnswers.getItems().size(); i++) {
				combinedAnswer = allAnswers.getItems().get(i).toString();
				endIndex = combinedAnswer.indexOf('[');
				answer = combinedAnswer.substring(0, endIndex - 1);

				status = convertStringToBoolean(combinedAnswer.substring(endIndex + 1, combinedAnswer.length() - 1));
				try {
					answers.add(new Answer(answer, status));
					System.out.println("THE ANSWER IS : " + answers.get(i));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			model.addQuestion(question, answers);
			view.clearAnswerCB(root);
			System.out.println("CURRENT QUESTIONPOOL : " + model);

		}
	}

	public boolean convertStringToBoolean(String str) {
		if (str.equals("true"))
			return true;

		else if (str.equals("false"))
			return false;
		else
			System.out.println("THE STRING IS :" + str);
		return false;
	}

	@Override
	public void updateQuestionUI(Question question, String newQuestion, UpdateQuestionRoot uqRoot) {
		// TODO Auto-generated method stub
		if (newQuestion != "") {
			model.updateQuestion(newQuestion, question.getID());
			view.updateQuestionUI(model, uqRoot);
		} else
			view.wrongInput("Input Can't be empty");

	}

	@Override
	public QuestionPool getModel() {
		// TODO Auto-generated method stub
		return model;
	}

	@Override
	public void clearAnswers(AddQuestionRoot root) {
		view.clearAnswerCB(root);
	}

	@Override
	public void updateOpenAnswer(Question selectedQuestion, String answerText, UpdateAnswerRoot uaRoot) {
		// TODO Auto-generated method stub
		model.updateAnswer(selectedQuestion.getID(), answerText, 0, false);
		view.updateAnswerUI(uaRoot);
	}

	@Override
	public void updateAmericanAnswer(Question selectedQuestion, String answerText, String answerIdText,
			String statusString) {
		// TODO Auto-generated method stub
		boolean status;
		status = convertStringToBoolean(statusString);
		model.updateAnswer(selectedQuestion.getID(), answerText, Integer.parseInt(answerIdText), status);
		System.out.println("NEW MODEL: " + model);

	}

	@Override
	public void save() throws Exception {
		// TODO Auto-generated method stub
		model.save();
	}

	@Override
	public void addQuestionToManual(Question selectedQuestion, ComboBox selectedAnswerCB) throws Exception {
		// TODO Auto-generated method stub
		if (model.exam == null) {
			model.examConstructor();
		}
		if (selectedAnswerCB == null)

			model.exam.addQuestion(selectedQuestion);

		else {
			System.out.println("selectedQuestion " + selectedQuestion);
			Set<Answer> answers = convertListToSet(selectedAnswerCB.getItems());
			model.addAnswers(answers);
			model.exam.addQuestion(selectedQuestion.getQuestion(), answers);

		}
	}

	public <Answer> Set<Answer> convertListToSet(ObservableList<Answer> list) {
		Set<Answer> set = new Set<Answer>();
		for (int i = 0; i < list.size(); i++) {
			try {
				set.add((Answer) (list.get(i)));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return set;
	}

	@Override
	public void addQuestionToComboBoxUI(Question selectedQuestion, ComboBox selectedAnswerCB,
			CreateManualExamRoot cmeRoot) throws Exception {
		// TODO Auto-generated method stub
		Set<Answer> answers = convertListToSet(selectedAnswerCB.getItems());
		model.addAnswers(answers);
		AmericanQuestion aq = new AmericanQuestion(selectedQuestion, answers);
		view.addQuestionToExamUI(aq, cmeRoot);
	}

	@Override
	public void createAutomaticExam(int numOfQuestions, CreateAutomaticExamRoot root) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("options : " + model.numOfValidQuestions(4));
		if (numOfQuestions > model.numOfValidQuestions(4)) {
			JOptionPane.showMessageDialog(null, "number must be between 1-" + model.numOfValidQuestions(4));

		} else {
			model.createAutomaticExam(numOfQuestions);
			model.saveToText();
			System.out.println("AUTO EXAM : " + model.exam);
			view.addExamToUI(model.exam, root);
		}
	}

	@Override
	public void createExamFile(ComboBox examCB) {
		// TODO Auto-generated method stub
		try {
			model.saveToText();
			view.saved();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public QuestionPool getLastExam() {
		// TODO Auto-generated method stub
		return model.exam;
	}

	@Override
	public void fillLastExamCB(QuestionPool lastExam, CopyLastExamRoot cleRoot) {
		// TODO Auto-generated method stub
		view.fillLastExamCB(lastExam, cleRoot);
	}

	@Override
	public void copyLastExam() {
		// TODO Auto-generated method stub
		try {
			if (model.exam != null) {
				model.copyLastExam();
				view.copiedLastExam();
			} else
				JOptionPane.showMessageDialog(null, "Create an exam first");

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateAnswer(Question selectedQuestion, String answerText, String answerIdText, String statusString,
			UpdateAnswerRoot uaRoot) {
		// TODO Auto-generated method stub

		model.updateAnswer(selectedQuestion.getID(), answerText, Integer.parseInt(answerIdText),
				convertStringToBoolean(statusString));
		view.updateAnswerUI(uaRoot);
		System.out.println("NEW QUESTION : " + selectedQuestion);

	}

	@Override
	public void wrongInput(String string) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, string);
	}

	@Override
	public void getExamNames(CopyExistingExamRoot ceeRoot) {
		// TODO Auto-generated method stub
		view.clearExamComboBox(ceeRoot);
		view.fillExamListComboBox(ceeRoot, model.getAllExamsFromFile());
	}

	@Override
	public void copySelectedExamFromFile(String selectedExam, CopyExistingExamRoot root) {
		// TODO Auto-generated method stub
		model.loadExam(selectedExam, root);
		view.updateAnswerUI(root);
		System.out.println("selected exam : " + selectedExam);
	}

	@Override
	public void questionExists() {
		// TODO Auto-generated method stub
		view.questionExists();
	}
}
