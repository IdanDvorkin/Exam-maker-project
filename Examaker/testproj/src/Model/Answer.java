package Model;

import java.io.Serializable;

public class Answer implements Serializable,Cloneable {

	private String solution;
	private boolean status;

	public Answer(String solution, boolean status) {
		this.solution = solution;
		this.status = status;
	}

	public void setAnswer(String newSolution, boolean newStatus) {
		this.solution = newSolution;
		this.status = newStatus;
	}

	public boolean equals(String otherSolution) {
		if (this.solution.equals(otherSolution))
			return true;
		return false;
	}

	public boolean equals(Answer otherAnswer) {
		if (this.solution.equals(otherAnswer))
			return true;
		return false;
	}

	public String toString() {
		String str = "";
		str += solution + " [" + status + "]";
		return str;
	}

	public boolean getStatus() {
		// TODO Auto-generated method stub
		return this.status;

	}

	public String getAnswer() {
		// TODO Auto-generated method stub
		return solution;
	}

	public void setStatus(boolean newStatus) {
		this.status = newStatus;

	}


}
