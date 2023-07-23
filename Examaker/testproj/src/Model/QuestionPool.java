package Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Vector;
import Listeners.EventsListener;
import Roots.CopyExistingExamRoot;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class QuestionPool implements Cloneable {
	protected int count = 0;
	protected Vector<Question> allQuestions;
	public QuestionPool exam;
	protected Set<Answer> answersOfAmericanQuestion;
	private Vector<EventsListener> listeners;

	public QuestionPool() {
		this.allQuestions = new Vector<Question>();
		this.answersOfAmericanQuestion = new Set<Answer>();
		listeners = new Vector<EventsListener>();

	}

	public QuestionPool(int numOfQuestions) {
		// TODO Auto-generated constructor stub
		allQuestions = new Vector<Question>(numOfQuestions);

	}

	public Vector<Question> getAllQuestions() {
		return this.allQuestions;
	}

	public QuestionPool getExam() {
		return this.exam;
	}

	public int getCount() {
		return this.count;
	}

	public boolean addQuestion(String question, String answer) {
		if (!question.isEmpty() && !answer.isEmpty()) {
			if (!isQuestionExist(question, "OpenQuestion")) {
				allQuestions.add(new OpenQuestion(question, answer, this.count));
				this.count++;
				fireAddQuestionEvenet(question, answer);
				return true;
			} else {
				fireQuestionExists();
				return false;
			}
		} else {
			for (EventsListener l : listeners) {
				l.wrongInput("Input can't be empty");
			}

			return false;
		}

	}

	private void fireQuestionExists() {
		// TODO Auto-generated method stub
		for (EventsListener l : listeners) {
			l.questionExists();
		}
	}

	private void fireAddQuestionEvenet(String question, String answer) {
		// TODO Auto-generated method stub
		for (EventsListener l : listeners) {
			l.addedOpenQuestionToModelEvent(question, answer);

		}

	}

	public boolean addQuestion(String question, Set<Answer> listOfAnswers) {
		// isFull();
		if (!isQuestionExist(question, "AmericanQuestion")) {
			if (!question.isEmpty() && listOfAnswers.getCurrentSize() > 0) {
				allQuestions.add(new AmericanQuestion(question, listOfAnswers, count));
				this.count++;
				return true;
			} else {
				for (EventsListener l : listeners) {
					l.wrongInput("Input can't be empty");
				}
			}
		} else {
			fireQuestionExists();

		}
		return false;

	}

	public boolean addQuestion(Question question) {
		// TODO Auto-generated method stub
		if (this.isAmericanQuestion(question)) {
			AmericanQuestion aq = (AmericanQuestion) question;
			addQuestion(question.getQuestion(), aq.getAllAnswers());
			return true;
		} else {
			OpenQuestion oq = (OpenQuestion) question;
			addQuestion(question.getQuestion(), oq.getAnswer());
			return true;
		}
	}

	public void updateQuestion(String newQuestion, int questionID) {
		if (isQuestionExists(newQuestion)) {
			fireQuestionExists();
		} else
			getQuestionByID(questionID).updateQ(newQuestion);
	}

	public Question getQuestionByID(int questionID) {

		for (int i = 0; i <= allQuestions.size(); i++) {
			if (this.allQuestions.elementAt(i).getID() == questionID) {
				return allQuestions.elementAt(i);
			}
		}
		return null;
	}

	public void deleteAnswer(int deletedID, int answerChoice) throws Exception {

		AmericanQuestion aq = (AmericanQuestion) getQuestionByID(deletedID);
		System.out.println("aq.getAllAnswers()" + aq.getAllAnswers());
		System.out.println("aq.getAllAnswers().getCurrentSize()" + aq.getAllAnswers().getCurrentSize());

		if (aq.getAllAnswers().getCurrentSize() >= answerChoice) {
			System.out.println(aq.getAllAnswers().getCurrentSize() > answerChoice);
			aq.deleteAmericanAnswer(answerChoice);
		} else {
			fireWrongInput(aq.getAllAnswers().getCurrentSize());
			System.out.println(aq.getAllAnswers().getCurrentSize() > answerChoice);
		}

	}

	private void fireWrongInput(int count) {
		// TODO Auto-generated method stub
		for (EventsListener l : listeners) {
			l.wrongInput("please enter number between 1-" + count);
		}
	}

	public void updateAnswer(int updatedID, String updatedAnswer, int answerChoice, boolean newStatus) {
		if (typeOfQuestion(updatedID).equals("OpenQuestion")) {
			OpenQuestion oq = (OpenQuestion) getQuestionByID(updatedID);
			oq.setAnswer(updatedAnswer);
		}
		if (typeOfQuestion(updatedID).equals("AmericanQuestion")) {
			AmericanQuestion aq = (AmericanQuestion) this.getQuestionByID(updatedID);
			aq.updateAmericanAnswer(answerChoice, updatedAnswer, newStatus);

		}
	}

	public void setAnswer(int questionIndex, Set<Answer> allAnswers, QuestionPool questionPool) throws Exception {
		this.addAnswers(allAnswers);
		this.addQuestion(questionPool.getQuestionByID(questionIndex).getQuestion(), allAnswers);

	}

	public void setAnswerForManualExam(int questionId, int answerId) throws Exception {
		this.answersOfAmericanQuestion.add(this.getAnswerById(questionId, answerId));
	}

	public Set<Answer> getAnswersForManualExam() {
		Set<Answer> temp = new Set<Answer>();
		try {
			temp = this.answersOfAmericanQuestion.copy();
			this.addAnswers(temp);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}

	public void resetAnswerForManualExam() {
		this.answersOfAmericanQuestion.clear();
	}

	public boolean isQuestionExist(String question, String type) {
		// TODO Auto-generated method stub
		for (int i = 0; i < allQuestions.size(); i++) {
			if (question.equals(allQuestions.elementAt(i).getQuestion())) {
				if (type.equals("OpenQuestion")) {
					if (allQuestions.elementAt(i) instanceof OpenQuestion) {
						return true;
					}

				} else if (type.equals("AmericanQuestion")) {
					if (allQuestions.elementAt(i) instanceof AmericanQuestion) {
						return true;
					}
				}

			}

		}
		return false;

	}

	public boolean isQuestionExists(String question) {
		for (int i = 0; i < allQuestions.size(); i++) {
			if (question.equals(allQuestions.get(i).getQuestion())) {
				return true;
			}
		}
		return false;
	}

	public void addAnswers(Set<Answer> answers) throws Exception {
		answers.add(new Answer("There are 0 correct answers", isZeroAnswers(answers)));
		answers.add(new Answer("There are more than 1 correct answer", isMoreThanOne(answers)));
	}

	private boolean isZeroAnswers(Set<Answer> answers) {
		// TODO Auto-generated method stub
		if (numOfCorrectAnswers(answers) == 0) {
			return true;
		}
		return false;
	}

	private boolean isMoreThanOne(Set<Answer> answers) {
		// TODO Auto-generated method stub
		if (numOfCorrectAnswers(answers) > 1) {
			for (int i = 0; i < answers.getCurrentSize(); i++) {
				answers.get(i).setStatus(false);
			}
			return true;
		}
		return false;
	}

	private int numOfCorrectAnswers(Set<Answer> answers) {
		int counter = 0;
		for (int i = 0; i < answers.getCurrentSize(); i++) {
			if (answers.get(i).getStatus() == true) {
				counter++;
			}

		}
		return counter;
	}

	public void createAutomaticExam(int numOfQuestions) throws Exception {

		Vector<Integer> questionsIndex = new Vector<Integer>();
		questionsIndex = generateNewQuestionNumbers(numOfValidQuestions(4), this, 4, numOfQuestions);
		buildExam(questionsIndex);

	}

	public void buildExam(Vector<Integer> questionsIndex) throws Exception {
		this.exam = new QuestionPool();

		for (int i = 0; i < questionsIndex.size(); i++) {
			if (this.getQuestionByID(questionsIndex.elementAt(i)) instanceof OpenQuestion) {
				exam.addQuestion(this.getQuestionByID(questionsIndex.elementAt(i)).getQuestion(),
						((OpenQuestion) this.getQuestionByID(questionsIndex.elementAt(i))).getAnswer());

			} else {
				Vector<Answer> newAnswers;
				int numOfAnswers = 4;

				Set<Answer> randomAnswers = new Set<Answer>();
				AmericanQuestion aq = (AmericanQuestion) this.getQuestionByID(questionsIndex.elementAt(i));
				if (optionalAnswers(aq.getAllAnswers()).size() >= numOfAnswers - 1) {
					newAnswers = optionalAnswers(aq.getAllAnswers());

					for (int j = 0; j < numOfAnswers; j++) {
						randomAnswers.add(
								new Answer(newAnswers.elementAt(j).getAnswer(), newAnswers.elementAt(j).getStatus()));

					}
					this.addAnswers(randomAnswers);
					exam.addQuestion(this.getQuestionByID(questionsIndex.elementAt(i)).getQuestion(), randomAnswers);

				}
			}

		}

	}

	public Vector<Answer> optionalAnswers(Set<Answer> set) {
		Vector<Answer> temp = new Vector<Answer>(set.getCurrentSize());
		int counter = 0;

		for (int i = 0; i < set.getCurrentSize(); i++) {
			if (set.get(i).getStatus() == true && counter == 0) {
				temp.add(set.get(i));
				counter++;
			}
			if (!(set.get(i).getStatus() == true && counter >= 0))
				temp.add(set.get(i));

		}
		return correctAnswers(temp);

	}

	public Vector<Answer> correctAnswers(Vector<Answer> temp) {
		Vector<Answer> fixedAnswers;
		fixedAnswers = new Vector<Answer>();
		for (int i = 0; i < temp.size(); i++) {
			fixedAnswers.add(temp.elementAt(i));
		}
		return fixedAnswers;
	}

	public boolean isAmericanQuestion(Question question) {
		// TODO Auto-generated method stub
		if (this.typeOfQuestion(question).equals("AmericanQuestion"))
			return true;
		return false;
	}

	public boolean isAmericanQuestion(int id) {
		if (this.typeOfQuestion(id).equals("AmericanQuestion"))
			return true;
		return false;

	}

	public String typeOfQuestion(int questionID) {
		if (getQuestionByID(questionID) instanceof OpenQuestion)
			return "OpenQuestion";
		return "AmericanQuestion";
	}

	public String typeOfQuestion(Question question) {
		if (question instanceof OpenQuestion)
			return "OpenQuestion";
		return "AmericanQuestion";
	}

	public int getNumOfAnswersOfAmericanQuestionById(int id) {
		AmericanQuestion aq = (AmericanQuestion) this.getQuestionByID(id);
		return aq.getAllAnswers().getCurrentSize();

	}

//	

	public void Sort() throws Exception {
		// TODO Auto-generated method stub
		for (int i = 1; i < this.exam.allQuestions.size(); i++) {
			for (int j = i; j > 0 && (this.exam.allQuestions.elementAt(j - 1)
					.compareTo(this.exam.allQuestions.elementAt(j)) > 0); j--) {
				Question temp = new Question();
				temp = exam.allQuestions.elementAt(j);
				exam.allQuestions.set(j, exam.allQuestions.elementAt(j - 1));
				exam.allQuestions.set(j - 1, temp);

			}

		}

	}

	public static Vector<Integer> generateNewQuestionNumbers(int numOfValidQuestions, QuestionPool questionPool,
			int numOfAnswers, int numOfQuestions) throws Exception {
		Vector<Integer> idOfValidQuestions = new Vector<Integer>();
		Vector<Integer> chosenQustions = new Vector<Integer>();
		int minRange, maxRange;
		int generatedNumber;

		minRange = 0;
		maxRange = questionPool.getCount() - 1;

		for (int i = 0; i < questionPool.allQuestions.size(); i++) {
			if (!questionPool.isAmericanQuestion(i)) {
				idOfValidQuestions.add(i);
			} else {
				if (((AmericanQuestion) questionPool.allQuestions.get(i)).getSize() >= numOfAnswers) {
					idOfValidQuestions.add(i);
				}

			}
		}
		for (int i = 0; i < numOfQuestions; i++) {
			generatedNumber = generateNumber(minRange, maxRange);
			for (int j = 0; j < idOfValidQuestions.size(); j++) {
				if (idOfValidQuestions.get(j) == generatedNumber) {
					if (generatedNumber == minRange || generatedNumber == maxRange) {
						if (generatedNumber == minRange) {
							minRange++;
						} else
							maxRange--;

					}

					chosenQustions.add(idOfValidQuestions.get(j));
					idOfValidQuestions.remove(j);
					i++;
				}

			}
			i--;

		}

		return chosenQustions;

	}

	private static int generateNumber(int min, int max) {
		Random rnd = new Random();
		int num;
		num = rnd.nextInt((max - min) + 1) + min;
		// num=rnd.nextInt(min,max);
		return num;
	}

	public void saveToText() throws FileNotFoundException {

		LocalDate today = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd");
		Question thisQuestion;
		String date;
		File answers, questions;
		int fileCounter = 0;

		File folder = new File(System.getProperty("user.dir"));
		File[] listOfAllFiles = folder.listFiles();
		date = today.format(dtf);

		for (int i = 0; i < listOfAllFiles.length; i++) {
			if (listOfAllFiles[i].getName().contains(date)) {

				fileCounter++;
			}
		}
		fileCounter /= 2; // counts exam and solution which unneccesary
		if (fileCounter != 0) {

			answers = new File("solution_" + today.format(dtf) + "(" + fileCounter + ")" + ".txt");
			questions = new File("exam_" + today.format(dtf) + "(" + fileCounter + ")" + ".txt");
		} else {
			answers = new File("solution_" + today.format(dtf) + ".txt");
			questions = new File("exam_" + today.format(dtf) + ".txt");
		}
		PrintWriter pwa = new PrintWriter(answers);
		PrintWriter pwq = new PrintWriter(questions);
		pwq.print("Exam\n");
		pwa.print("Solution\n");

		for (int i = 0; i < this.exam.allQuestions.size(); i++) {
			thisQuestion = this.exam.allQuestions.elementAt(i);
			pwq.print("\n[" + (int) (i + 1) + "] " + thisQuestion.getQuestion() + "\n");
			if (this.isAmericanQuestion(thisQuestion)) {
				pwq.print("\n" + printAnswersWithoutSolution(thisQuestion) + "\n");
				pwa.print("\n[" + (int) (i + 1) + "] The answer is: " + printCorrectAnswer(thisQuestion));
			}

			else {
				pwa.print("\n[" + (int) (i + 1) + "] The answer is: " + thisQuestion.getAnswer());
			}
		}

		pwq.close();
		pwa.close();

	}

	private String printCorrectAnswer(Question thisQuestion) {
		// TODO Auto-generated method stub
		AmericanQuestion aq = (AmericanQuestion) thisQuestion;
		for (int i = 0; i < aq.getSize(); i++) {
			if (aq.getAnswer(i).getStatus() == true) {
				return aq.getAnswer(i).getAnswer();
			}
		}
		return "";
	}

	private String printAnswersWithoutSolution(Question thisQuestion) {
		// TODO Auto-generated method stub
		String str = "";
		AmericanQuestion aq = (AmericanQuestion) thisQuestion;
		for (int i = 0; i < aq.getSize(); i++) {
			str += "(" + (int) (i + 1) + ") " + aq.getAnswer(i).getAnswer() + "\n";
		}

		return str;
	}

	public Answer getAnswerById(int questionId, int id) {
		// TODO Auto-generated method stub
		AmericanQuestion aq = (AmericanQuestion) this.getQuestionByID(questionId);
		return aq.getAnswer(id);
	}

	public String getContentOfQuestionById(int questionId) {
		// TODO Auto-generated method stub
		OpenQuestion temp = (OpenQuestion) this.getQuestionByID(questionId);
		return temp.getQuestion();
	}

	public String getContentOfAnswerBydId(int questionId) {
		// TODO Auto-generated method stub
		OpenQuestion temp = (OpenQuestion) this.getQuestionByID(questionId);
		return temp.getAnswer();
	}

	public void loadQuestionPool() throws FileNotFoundException, IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Vector<Question> questionsList = new Vector<Question>();
		ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("questionPool.dat"));

		questionsList = (Vector<Question>) inFile.readObject();
		for (Question question : questionsList) {
			this.addQuestion(question);
		}

		inFile.close();
	}

	public void save() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("questionPool.dat"));

		outFile.writeObject(this.allQuestions);

		outFile.close();
	}

	public void loadExam() {
		// TODO Auto-generated method stub
		String name = "";
		int counter = 0;
		int ListOfExamsIndex = 1;
		int examChoice;
		Scanner s = new Scanner(System.in);

		File folder = new File(System.getProperty("user.dir"));
		File[] listOfAllFiles = folder.listFiles();
		File[] listOfExams = new File[listOfAllFiles.length];
		for (int i = 0; i < listOfAllFiles.length; i++) {
			name = listOfAllFiles[i].getName();
			if (name.contains("exam")) {
				listOfExams[counter++] = listOfAllFiles[i];
				System.out.println((ListOfExamsIndex++) + ": " + name);

				listOfExams[counter++] = getSolutionForExam(name, listOfAllFiles);

			}
		}
		System.out.println("Which exam would you like copy");
		examChoice = s.nextInt();
		int indexOfExam, indexOfSolution;
		String pathOfSolution = "";
		String pathOfExam = "";
		String examDate = "";
		int choice, numOfExisted = 0;
		File answers;
		File questions;

		choice = (examChoice * 2) - 2;
		indexOfExam = Arrays.asList(listOfAllFiles).indexOf(listOfExams[choice]);
		indexOfSolution = Arrays.asList(listOfAllFiles)
				.indexOf(getSolutionForExam(listOfExams[choice].getName(), listOfAllFiles));

		if (listOfAllFiles[indexOfExam].getName().contains("(")) {
			examDate = listOfAllFiles[indexOfExam].getName().substring(
					listOfAllFiles[indexOfExam].getName().length() - 17,
					listOfAllFiles[indexOfExam].getName().length() - 7);
		} else {
			examDate = listOfAllFiles[indexOfExam].getName().substring(
					listOfAllFiles[indexOfExam].getName().length() - 14,
					listOfAllFiles[indexOfExam].getName().length() - 4);

		}

		pathOfSolution = "solution_" + examDate;
		pathOfExam = "exam_" + examDate;

		for (int i = 0; i < listOfAllFiles.length; i++) {
			if (listOfAllFiles[i].getName().contains(pathOfExam)) {
				numOfExisted++;
			}
		}
		if (numOfExisted == 0) {
			answers = new File(pathOfSolution + ".txt");
			questions = new File(pathOfExam + ".txt");
		} else {
			answers = new File(pathOfSolution + "(" + numOfExisted + ")" + ".txt");
			questions = new File(pathOfExam + "(" + numOfExisted + ")" + ".txt");
		}

		try {
			Files.copy(listOfAllFiles[indexOfExam].toPath(), questions.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
			Files.copy(listOfAllFiles[indexOfSolution].toPath(), answers.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println();
		}

	}

	public void loadExam(String examName, CopyExistingExamRoot root) {
		// TODO Auto-generated method stub
		String name = "";
		int counter = 0;
		int ListOfExamsIndex = 1;
		int examChoice = 0;
		Scanner s = new Scanner(System.in);

		File folder = new File(System.getProperty("user.dir"));
		File[] listOfAllFiles = folder.listFiles();
		File[] listOfExams = new File[listOfAllFiles.length];
		for (int i = 0; i < listOfAllFiles.length; i++) {
			name = listOfAllFiles[i].getName();
			if (name.contains("exam")) {
				listOfExams[counter++] = listOfAllFiles[i];
				System.out.println((ListOfExamsIndex++) + ": " + name);

				listOfExams[counter++] = getSolutionForExam(name, listOfAllFiles);

			}
		}
		System.out.println("Which exam would you like copy");
//		examChoice = s.nextInt();
		int indexOfExam, indexOfSolution;
		String pathOfSolution = "";
		String pathOfExam = "";
		String examDate = "";
		int choice, numOfExisted = 0;
		File answers;
		File questions;

		for (int i = 0; i < listOfExams.length; i++) {
			if (listOfExams[i] != null) {
				if (examName.equals(listOfExams[i].getName())) {
					examChoice = i;

				}
			}
		}

		indexOfExam = Arrays.asList(listOfAllFiles).indexOf(listOfExams[examChoice]);
		indexOfSolution = Arrays.asList(listOfAllFiles)
				.indexOf(getSolutionForExam(listOfExams[examChoice].getName(), listOfAllFiles));

		if (listOfAllFiles[indexOfExam].getName().contains("(")) {
			examDate = listOfAllFiles[indexOfExam].getName().substring(
					listOfAllFiles[indexOfExam].getName().length() - 17,
					listOfAllFiles[indexOfExam].getName().length() - 7);
		} else {
			examDate = listOfAllFiles[indexOfExam].getName().substring(
					listOfAllFiles[indexOfExam].getName().length() - 14,
					listOfAllFiles[indexOfExam].getName().length() - 4);

		}

		pathOfSolution = "solution_" + examDate;
		pathOfExam = "exam_" + examDate;

		for (int i = 0; i < listOfAllFiles.length; i++) {
			if (listOfAllFiles[i].getName().contains(pathOfExam)) {
				numOfExisted++;
			}
		}
		if (numOfExisted == 0) {
			answers = new File(pathOfSolution + ".txt");
			questions = new File(pathOfExam + ".txt");
		} else {
			answers = new File(pathOfSolution + "(" + numOfExisted + ")" + ".txt");
			questions = new File(pathOfExam + "(" + numOfExisted + ")" + ".txt");
		}

		try {
			Files.copy(listOfAllFiles[indexOfExam].toPath(), questions.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
			System.out.println(" exam copied");
			Files.copy(listOfAllFiles[indexOfSolution].toPath(), answers.toPath(), StandardCopyOption.COPY_ATTRIBUTES);
			System.out.println("solution copied");
			// fireUpdateAllExamUI(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println();
		}

	}

//	private void fireUpdateAllExamUI(CopyExistingExamRoot root) {
//		// TODO Auto-generated method stub
//		for (EventsListener l : listeners) {
//		//	l.updateAllExamUI(root);
//		}
//	}

	private File getSolutionForExam(String name, File[] listOfAllFiles) {
		// TODO Auto-generated method stub
		String date = "";
		date = name.substring(5);
		for (int i = 0; i < listOfAllFiles.length; i++) {
			if (listOfAllFiles[i].getName().contains("solution_" + date)) {
				return listOfAllFiles[i];
			}
		}

		return null;
	}

	public int numOfValidQuestions(int numOfAnswers) {
		// TODO Auto-generated method stub
		int counter = 0;

		for (int i = 0; i < this.allQuestions.size(); i++) {
			if (!isAmericanQuestion(i)) {
				counter++;
			} else {
				if (((AmericanQuestion) this.allQuestions.get(i)).getSize() >= numOfAnswers) {
					counter++;
				}

			}
		}
		return counter;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("");
		for (Question question : allQuestions) {
			sb.append("\n" + question).append(" \n___________________");

		}
		return sb.toString();
	}

	public void examConstructor() {
		// TODO Auto-generated method stub
		this.exam = new QuestionPool();

	}

	public QuestionPool clone() {
		QuestionPool temp = new QuestionPool();
		for (int i = 0; i < this.getCount(); i++) {
			temp.addQuestion(this.allQuestions.get(i).clone());

		}

		return temp;

	}

	public void copyLastExam() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		QuestionPool temp = new QuestionPool();
		temp = (QuestionPool) this.getExam().clone();
//		System.out.println("temp: "+temp);   proof that .clone copies content
//		exam=new QuestionPool();
//		System.out.println("exam: "+exam);
//		System.out.println("temp: "+temp);

	}

	public void registerListener(EventsListener listener) {
		listeners.add(listener);
	}

	public Vector<String> getAllExamsFromFile() {
		String name = "";

		Vector<String> examNames = new Vector<String>();
		File folder = new File(System.getProperty("user.dir"));
		File[] listOfAllFiles = folder.listFiles();
		// File[] listOfExams = new File[listOfAllFiles.length];
		for (int i = 0; i < listOfAllFiles.length; i++) {
			name = listOfAllFiles[i].getName();
			if (name.contains("exam")) {
				examNames.add(listOfAllFiles[i].getName());

			}
		}
		return examNames;
	}
	

}
