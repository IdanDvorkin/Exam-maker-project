package Listeners;

import java.util.Vector;

import Model.Answer;
import Model.Question;
import Model.QuestionPool;
import Roots.AddQuestionRoot;
import Roots.CopyExistingExamRoot;
import Roots.CopyLastExamRoot;
import Roots.CreateAutomaticExamRoot;
import Roots.CreateManualExamRoot;
import Roots.UpdateAnswerRoot;
import Roots.UpdateQuestionRoot;
import javafx.scene.control.ComboBox;

public interface UIEventesListener {

	void addOpenQuestionToUI(String question, String answer);

	void deleteAnswerUI(int answerChoice, int deletedId) throws Exception;

	void addAnswers(String answer, ComboBox<Answer> allAnswers, AddQuestionRoot root);

	QuestionPool getModel();

	String getAllQuestions();

	void updateAmericanAnswer(Question selectedQuestion, String answerText, String answerIdText, String statusString);

	void save() throws Exception;

	void updateAnswer(Question selectedQuestion, String answerText, String answerIdText, String statusString,
			UpdateAnswerRoot uaRoot);

	void addQuestionToManual(Question selectedQuestion, ComboBox selectedAnswerCB) throws Exception;

	void addQuestionToComboBoxUI(Question selectedQuestion, ComboBox selectedAnswerCB, CreateManualExamRoot cmeRoot)
			throws Exception;

	void createAutomaticExam(int numOfQuestions, CreateAutomaticExamRoot root) throws Exception;

	void createExamFile(ComboBox<Question> examCB);

	QuestionPool getLastExam();

	void fillLastExamCB(QuestionPool lastExam, CopyLastExamRoot cleRoot);

	void copyLastExam();

	void updateQuestionUI(Question question, String newQuestion, UpdateQuestionRoot uqRoot);

	void updateOpenAnswer(Question selectedQuestion, String answerText, UpdateAnswerRoot uaRoot);

	void getExamNames(CopyExistingExamRoot ceeRoot);

	void copySelectedExamFromFile(String selectedExam, CopyExistingExamRoot root);

	void clearAnswers(AddQuestionRoot addQuestionsRoot);
	

}
