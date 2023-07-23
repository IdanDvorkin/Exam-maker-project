package View;

import Listeners.UIEventesListener;

public interface AbstractView {
	void registerListener(UIEventesListener listener);
	void addOpenQuestionToUI(String question,String answer);
//	void removePassengerFromUI(int id);

}
