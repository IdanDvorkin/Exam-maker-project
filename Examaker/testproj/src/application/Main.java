package application;

import java.io.IOException;

import Controller.Controller;

import View.JavaFX;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Model.QuestionPool questionPool = new Model.QuestionPool();

		try {
			questionPool.loadQuestionPool();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		JavaFX theView1 = new JavaFX(new Stage());
		Controller c1 = new Controller(questionPool, theView1);
	}

	public static void main(String[] args) {

		launch(args);
	}
}
