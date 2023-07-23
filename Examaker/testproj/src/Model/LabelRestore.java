package Model;

import javafx.scene.control.Label;

public class LabelRestore implements Observer {
	 private Label label;
	    private IteratorSubject subject;

	public LabelRestore(Label observerLbl, IteratorSubject subject) {
		  this.label =observerLbl;
	        this.subject = subject;
	}

	@Override
	public void update() {
		 label.setVisible(true);
		
	}

}
