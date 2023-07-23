package Model;

import java.util.Comparator;

public class OrderCompare implements Comparator<Question> {

	@Override
	public int compare(Question o1, Question o2) {
		
		return (o1.getQuestion().toUpperCase().compareTo(o2.getQuestion().toUpperCase())*-1);
	}		
}
