package Model;

import java.util.Scanner;

public interface Menuable {

	void ExitTheProgram(QuestionPool questionPool);

	void Save(QuestionPool questionPool);

	void updateAnswer(QuestionPool questionPool, Scanner s);

	void updateQuestion(QuestionPool questionPool, Scanner s);

	void deleteAnswer(QuestionPool questionPool);

	void createManualExam(QuestionPool questionPool);

	void createAutomaticExam(QuestionPool questionPool);

	void copyExistingExam(QuestionPool questionPool);

	void addNewQuestion(QuestionPool questionPool, Scanner s);

	void showAllQuestions(QuestionPool questionPool);
	
	void turnVectorIntoList(QuestionPool questionPool);

}
