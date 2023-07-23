package Model;

import java.io.Serializable;

public class AmericanQuestion extends Question implements Serializable, Cloneable {

	protected Set<Answer> allAnswers;

	public AmericanQuestion(String question, Set<Answer> listOfAnswers, int id) {
		super(question, id);
		this.allAnswers = listOfAnswers;

	}

	public AmericanQuestion(Question question, Set<Answer> answers) {
		super(question.getQuestion(), question.getID());
		allAnswers = answers;

	}

	public void setAnswers(Set<Answer> allAnswers) {
		this.allAnswers = allAnswers;
	}

	public Answer getAnswer(int id) {
		return allAnswers.get(id);
	}

	public Set<Answer> getAllAnswers() {
		return this.allAnswers;
	}

	public int getSize() {
		return this.allAnswers.getCurrentSize();
	}

	public void deleteAnswer(int QuestionID) {

	}

	public String combineAnswers(Set<Answer> allAnswers) {
		String combinedAnswers = "";

		for (int i = 0; i < allAnswers.getCurrentSize(); i++) {
			combinedAnswers += allAnswers.get(i).getAnswer();
		}
		return combinedAnswers;
	}

	public void deleteAmericanAnswer(int index) throws Exception {
		Set<Answer> temp = this.allAnswers.copy();
		for (int i = index; i < this.allAnswers.getCurrentSize() - 1; i++) {
			temp.set(i, this.allAnswers.get(i + 1));

		}
		temp.set(temp.getCurrentSize() - 1, null);
		temp.decreaseCurrentSize();
		this.allAnswers = temp;
	}

	public void updateAmericanAnswer(int answerChoice, String newAnswer, boolean newStatus) {
		this.allAnswers.get(answerChoice ).setAnswer(newAnswer, newStatus);

	}

	public AmericanQuestion clone() {
		Set<Answer> temp = new Set<Answer>();
		temp = getAllAnswers().clone();
		return new AmericanQuestion(super.clone(), temp);

	}

	public String toString() {
		StringBuffer sb = new StringBuffer(super.toString());
		sb.append("\nThe Answers are: \n\n");

		for (int i = 0; i < allAnswers.getCurrentSize(); i++) {
			sb.append("(" + (int) (i + 1) + ") :" + this.allAnswers.get(i) + "\n");

		}
		return sb.toString();
	}

}
