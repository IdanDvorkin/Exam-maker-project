package Model;

import java.util.Iterator;

import Roots.SelectQuestion;

public class RemoveFromMyArrayListCommand implements Model.Command {
    private MyArrayList<Question> myList;
    private SelectQuestion root;

    public RemoveFromMyArrayListCommand(MyArrayList<Question> myList, SelectQuestion root) {
        this.myList = myList;
        this.root = root;
    }

	@Override
	public void execute() {
		
		Iterator<Question> itr = myList.iterator();
		itr.next();
		itr.remove();
		root.clearComboBox();

		while (itr.hasNext()) {
			Question current = itr.next();
			if (current != null) {
				System.out.println(current);
				root.getComboBox().getItems().add(current);
			}

		}
		
	}
}