package Listeners;

public interface EventsListener {

	void addedOpenQuestionToModelEvent(String question, String answer);

	void wrongInput(String string);

	void questionExists();

}
