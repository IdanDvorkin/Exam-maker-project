package Roots;

import Model.Question;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public abstract class AbstractRoot {
	protected GridPane root = new GridPane();
	protected Stage stg = new Stage();
	

	public AbstractRoot() {
		root.setPadding(new Insets(5));

		root.setHgap(5);
		root.setVgap(5);


	
		
	}
	public GridPane getRoot() {
		return root;
	}
	public void show() {
		stg.setScene(new Scene(root, 500, 400));
		stg.show();
	}

}
