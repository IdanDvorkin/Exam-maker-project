package Model;

import java.util.Iterator;

import Roots.SelectQuestion;

public class PrintMyArrayListCommand implements Model.Command {

	private MyArrayList<Question> list;
	private SelectQuestion root;

	public PrintMyArrayListCommand(MyArrayList<Question> list, SelectQuestion root) {
		this.list = list;
		this.root = root;
	}

	@Override
	public void execute() {
		Iterator<Question> itr = list.iterator();
		while (itr.hasNext()) {
			Question current = itr.next();
			if (current != null) {
				System.out.println(current);
				root.getComboBox().getItems().add(current);
			}
		}
	}
}
