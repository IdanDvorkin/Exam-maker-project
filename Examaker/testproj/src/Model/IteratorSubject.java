package Model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;






public class IteratorSubject {
	    private Iterator<Question> iterator;
	    private List<Observer> observers;

	    public  IteratorSubject(Iterator<Question> iterator) {
	        this.iterator = iterator;
	        observers = new ArrayList<>();
	    }

	    public void register(ButtonReact buttonReact) {
	        observers.add(buttonReact);
	    }
	    public void register(LabelReact labelReact) {
	        observers.add(labelReact);
	    }
	    public void register(ButtonRestore buttonRestore) {
	        observers.add(buttonRestore);
	    }
	    public void register(LabelRestore labelRestore) {
	        observers.add(labelRestore);
	    }
	    
	    

	    public void unregister(Observer observer) {
	        observers.remove(observer);
	    }

		public void notifyObservers() {
			for (int i = 0; i < observers.size(); i++) {
				observers.get(i).update();
			}
			
		}

}
