package Model;

import java.io.Serializable;

public class OpenQuestion extends Question implements Serializable, Cloneable {

	private String answer;

	public OpenQuestion(String question, String answer, int id) {
		super(question, id);
		this.answer = answer;
	}

	public OpenQuestion(Question question, String answer) {
		super(question.getQuestion(), question.getID());
		this.answer = answer;
	}

	public void setAnswer(String newAnswer) {
		this.answer = newAnswer;
	}

	public void deleteOpenAnswer(int QuestionID) {
		this.answer = "";
	}

	public OpenQuestion clone() {
		String answer = "";

		answer = this.getAnswer();
		OpenQuestion temp = new OpenQuestion(super.clone(), answer);

		return temp;

	}

	public String getAnswer() {
		return this.answer;
	}

	public String toString() {

		return super.toString() + "\nThe answer is: " + answer;
	}

}
