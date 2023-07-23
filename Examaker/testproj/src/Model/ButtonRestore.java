package Model;

import javafx.scene.control.Button;

public class ButtonRestore implements Observer {
	 private Button button;
	    private IteratorSubject subject;

	public ButtonRestore(Button observerBtn, IteratorSubject subject) {
		  this.button = observerBtn;
	        this.subject = subject;
	    }

	@Override
	public void update() {
		  button.setVisible(true);
		
	}

}
