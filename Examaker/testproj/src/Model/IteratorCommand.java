package Model;

import java.util.Iterator;
import java.util.List;

import Roots.TurnSetToMyArrayList;

public class IteratorCommand implements Command {
	  private IteratorSubject subject;
	  private TurnSetToMyArrayList turnSetToMyArrayListRoot;

	  public IteratorCommand(MyArrayList<Question> myList,TurnSetToMyArrayList turnSetToMyArrayListRoot) {
	    Iterator<Question> itr = myList.iterator();
	    this.subject = new IteratorSubject(itr);
	    this.turnSetToMyArrayListRoot=turnSetToMyArrayListRoot;
	  }

	  @Override
	  public void execute() {
	    subject.register(new Model.LabelReact(turnSetToMyArrayListRoot.getObserverLbl(), subject));
	    subject.register(new Model.ButtonReact(turnSetToMyArrayListRoot.getObserverBtn(), subject));
	    subject.register(new Model.ButtonRestore(turnSetToMyArrayListRoot.getObserverBtnRestored(), subject));
	    subject.register(new Model.LabelRestore(turnSetToMyArrayListRoot.getObserverLblRestored(), subject));
        subject.notifyObservers();
	   
	  }
	}