package Model;

import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable, Comparable<Question> {
	protected String question;
	protected int ID;

	public Question(String question, int id) {
		this.question = question;
		this.ID = id;

	}

	public Question() {
		// TODO Auto-generated constructor stub
		this.question = "";
		this.ID = 0;
	}

	public void setQuestion(String question) {
		this.question = question;

	}

	public String getAnswer() {
		if (this instanceof OpenQuestion)
			return ((OpenQuestion) this).getAnswer();
		else
			return ((AmericanQuestion) this).combineAnswers(((AmericanQuestion) this).getAllAnswers());

	}

	@Override
	public int compareTo(Question o) {
		String answerLength1, answerLength2;

		answerLength1 = this.getAnswer();
		answerLength2 = o.getAnswer();
		if (answerLength1.length() > answerLength2.length()) {
			return 1;
		} else
			return -1;
	}

	public int getID() {
		return ID;
	}

	public String getQuestion() {
		return this.question;
	}

	public void updateQ(String newQuestion) {
		this.question = newQuestion;
	}

	public void deleteAnswer(int QuestionID) {
		// TODO
	}

	public Question clone() {
		Question temp;
		String question = "";
		question = question.copyValueOf(this.getQuestion().toCharArray());
		temp = new Question(question, this.getID());

		return temp;

	}
	@Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        boolean checkIfComparingQuestions=obj instanceof Question&&this instanceof Question;
        if(obj.getClass()!=this.getClass()&&!checkIfComparingQuestions)
        {
        	return false;
        }
        Question user = (Question) obj;
        return this.question.equals(user.question); 
    }
	
    @Override
	    public int hashCode() {
            return Objects.hash(this.question);
	    }
	    
	


	
//	public boolean equals(String otherQuestion) {
//		if (this.question.equals(otherQuestion))
//			return true;
//		return false;
//	}
	

	public String toString() {
		String str = "";
		str += "ID: " + ID + " - " + "\nThe question is: " + question;
		return str;

	}

}
