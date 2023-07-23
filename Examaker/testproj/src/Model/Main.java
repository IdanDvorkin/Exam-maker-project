package Model;

import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Vector;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main implements Menuable {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		int menuOption = 0;
		Scanner s = new Scanner(System.in);
		QuestionPool questionPool = new QuestionPool();
		Main main = new Main();
		int numOfOptions = 11;

		try {
			questionPool.loadQuestionPool();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}

		while (menuOption != numOfOptions) {
			System.out.println("Please select an option :");
			System.out.println("1- Show all questions");
			System.out.println("2- Add new question");
			System.out.println("3- Update question");
			System.out.println("4- Update answer");
			System.out.println("5. Delete answer");
			System.out.println("6- Create manual exam");
			System.out.println("7- Create automatic exam");
			System.out.println("8- copy Existing exam from file");
			System.out.println("9- copy Existing exam from memory");
			System.out.println("10- Turn the question's vector into a List");
			System.out.println("11- Exit the program");
	

			menuOption = isInputOk(1, numOfOptions);

			switch (menuOption) {

			case 1: {

				main.showAllQuestions(questionPool);

			}

				break;
			case 2: {
				main.addNewQuestion(questionPool, s);
				main.Save(questionPool);
			}
				break;

			case 3: {
				main.updateQuestion(questionPool, s);
				main.Save(questionPool);

			}
				break;

			case 4: {
				main.updateAnswer(questionPool, s);
				main.Save(questionPool);

			}
				break;

			case 5: {
				main.deleteAnswer(questionPool);
				main.Save(questionPool);

			}

				break;

			case 6: {
				main.createManualExam(questionPool);
				
			}

				break;

			case 7: {
				main.createAutomaticExam(questionPool);

			}
				break;
			case 8: {
				main.copyExistingExam(questionPool);

			}
			case 9: {
				try {
					questionPool.copyLastExam();
	
					
					System.out.println("last exam has been copied");
				}
					catch(NullPointerException e) {
						System.out.println("\nmust create an exam first\n");
					}
				 catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
				break;
			case 10:{
				main.turnVectorIntoList(questionPool);
			}

			case 11: {
				main.Save(questionPool);

			}
	
				break;
			}

		}
		System.out.println("Thank you for using the menu, Goodbye");

	}

	
	public void turnVectorIntoList(QuestionPool questionPool) {
		// TODO Auto-generated method stub
		
	}

	private static boolean isInputOk2() {
		// TODO Auto-generated method stub
		boolean isInputOk = false;
		Scanner s = new Scanner(System.in);
		boolean input = false;

		while (!isInputOk) {
			try {

				input = s.nextBoolean();

				isInputOk = true;
			} catch (InputMismatchException e) {
				System.out.println("Input must be 'true' or 'false'");
				s.nextLine();// resets buffer
			}
		}
		return input;
	}

	private static int isInputOk(int minAnswers, int maxAnswers) {
		// TODO Auto-generated method stub
		boolean isInputOk = false;
		int input = -1;
		Scanner s = new Scanner(System.in);
		while (!isInputOk)
			try {
				input = s.nextInt();
				if (input < minAnswers || input > maxAnswers) {
					throw new Exception("Input must be between " + (int) (minAnswers) + "-" + (int) (maxAnswers));
				}
				isInputOk = true;

			} catch (InputMismatchException e) {
				System.out.println("Please enter an integer");
				s.nextLine();// resets buffer
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		return input;
	}

	private static String isInputOk() {
		// TODO Auto-generated method stub
		boolean isInputOk = false;
		Scanner s = new Scanner(System.in);
		String str = "";
		while (!isInputOk)
			try {

				str = s.nextLine();
				if (str == "") {
					throw new Exception("*** Input can't be empty ***");
				}
				isInputOk = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		return str;
	}

	@Override
	public void showAllQuestions(QuestionPool questionPool) {
		// TODO Auto-generated method stub
		System.out.println(questionPool);
	}

	@Override
	public void addNewQuestion(QuestionPool questionPool, Scanner s) {
		// TODO Auto-generated method stub
		int addQuestionMenu = 0;
		System.out.println("______________________________\n");

		while (addQuestionMenu != 3) {
			System.out.println("1 -Add open question");
			System.out.println("2 -Add american question");
			System.out.println("3 -back to main menu");

			addQuestionMenu = s.nextInt();
			switch (addQuestionMenu) {

			case 1: {
				String questionContent = "", answerContent = "";
				s.nextLine();// resets buffer
				System.out.println("Enter the question: ");
				questionContent = isInputOk();
				System.out.println("Enter the answer: ");
				answerContent = isInputOk();

				if(!questionPool.addQuestion(questionContent, answerContent)) {
					System.out.println("Question already exists");
				}
				else
				System.out.println(questionPool);

			}
				break;
			case 2: {
				int numOfAnswers, maxAnswers = 10;
				String answer = "";
				String question = "";
				boolean status;
				Set<Answer> answers;

				System.out.println("__________\n");

				System.out.println("Enter the number of answers maximum 10");
				numOfAnswers = isInputOk(0, maxAnswers);

				answers = new Set<Answer>();

				System.out.println("Enter your question");
				s.nextLine();// resets buffer
				question = isInputOk();
				for (int i = 0; i < numOfAnswers; i++) {
					System.out.println("Enter your " + (i + 1) + " answer:");
					answer = isInputOk();
					System.out.println("Enter the status for " + (int) (i + 1) + " answer:");
					status = isInputOk2();
					try {
						answers.add(new Answer(answer, status));
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				}
				if (!questionPool.addQuestion(question, answers)) {
					System.out.println("Question already exists");
				} else

					System.out.println(questionPool);
			}
			}
		}
	}

	@Override
	public void updateQuestion(QuestionPool questionPool, Scanner s) {
		// TODO Auto-generated method stub
		int updatedID;

		System.out.println("______________________________\n");
		String newQuestion = "";
		
		System.out.println(questionPool);
		System.out.println("Enter the ID of the question you want to update: ");
		updatedID = isInputOk(0, questionPool.getCount() - 1);
		System.out.println("Enter the new question: ");
		newQuestion = isInputOk();
		questionPool.updateQuestion(newQuestion, updatedID);
		System.out.println(questionPool);
	}

	@Override
	public void updateAnswer(QuestionPool questionPool, Scanner s) {
		// TODO Auto-generated method stub
		int updatedID, answerChoice = 0;
		boolean newStatus = false;
		String updatedAnswer = "";

		System.out.println(questionPool);
		System.out.println("Please enter the id of the question you would like to update its answer");
		updatedID = isInputOk(0, questionPool.getCount() - 1);
		if (questionPool.isAmericanQuestion(updatedID)) {

			System.out.println(questionPool.getQuestionByID(updatedID));
			System.out.println("Which answer do you you want to update: ");
			answerChoice = isInputOk(1, ((AmericanQuestion) (questionPool.getQuestionByID(updatedID))).getSize());
			System.out.println("What is the status of the new answer");

			newStatus = isInputOk2();

		}
		System.out.println("Enter your new answer:");
		updatedAnswer = isInputOk();
		questionPool.updateAnswer(updatedID, updatedAnswer, answerChoice - 1, newStatus);
		System.out.println(questionPool);
	}

	@Override
	public void deleteAnswer(QuestionPool questionPool) {
		// TODO Auto-generated method stub
		int deletedID, answerChoice = 0;

		System.out.println(questionPool);
		System.out.println("Please enter the id of the question you would like to delete its answer");

		deletedID = isInputOk(0, questionPool.getCount() - 1);
		try {
			if (questionPool.isAmericanQuestion(deletedID)) {

				while (questionPool.getNumOfAnswersOfAmericanQuestionById(deletedID) == 1) {

					System.out.println("This question has only 1 answer,please enter new ID");

					deletedID = isInputOk(0, questionPool.getCount() - 1);
				}

				System.out.println(questionPool.getQuestionByID(deletedID));
				System.out.println("Which answer do you you want to delete: ");

				answerChoice = isInputOk(1, ((AmericanQuestion) (questionPool.getQuestionByID(deletedID))).getSize());

				questionPool.deleteAnswer(deletedID, answerChoice - 1);
				System.out.println(questionPool);

			} else
				throw new Exception("Can't delete answer of an open question\n");
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Override
	public void createManualExam(QuestionPool questionPool) {
		// TODO Auto-generated method stub
		int numOfQuestions, questionId, answerId, numOfAnswers;

		System.out.println("Please enter the number of questions in the exam:");
		numOfQuestions = isInputOk(1, questionPool.getCount());
		questionPool.examConstructor();
	//	QuestionPool manualExam = new QuestionPool();

		for (int i = 0; i < numOfQuestions; i++) {
			System.out.println("id of question:");
			questionId = isInputOk(0, questionPool.getCount() - 1);
			
			if (questionPool.isAmericanQuestion(questionId)) {
				System.out.println("This is an american question, how many answers would you like?");
				numOfAnswers = isInputOk(1, ((AmericanQuestion) (questionPool.getQuestionByID(questionId))).getSize());
				for (int j = 0; j < numOfAnswers; j++) {

					System.out.println(questionPool.getQuestionByID(questionId));
					System.out.println("Please enter id of the answer you would like to add");

					answerId = isInputOk(1, ((AmericanQuestion) (questionPool.getQuestionByID(questionId))).getSize());
					System.out.println("Added");
					try {
						questionPool.setAnswerForManualExam(questionId, answerId - 1);
					} catch (InputMismatchException e) {
						System.out.println(e.getMessage());
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				}
				try {
					questionPool.exam.addQuestion(questionPool.getQuestionByID(questionId).getQuestion(),questionPool.getAnswersForManualExam());
					questionPool.resetAnswerForManualExam();
				} catch (InputMismatchException e) {
					System.out.println(e.getMessage());
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			} else {

				questionPool.exam.addQuestion(questionPool.getContentOfQuestionById(questionId),
						questionPool.getContentOfAnswerBydId(questionId));
				System.out.println("Added");

			}
			

		}

		System.out.println(questionPool.getExam());
		try {
			questionPool.saveToText();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void createAutomaticExam(QuestionPool questionPool) {
		// TODO Auto-generated method stub
		int numOfQuestions, numOfValidQuestions, numOfAnswers = 4;
		System.out.println(questionPool);
		numOfQuestions = questionPool.getCount() + 1;

		Vector<Integer> questionsIndex = new Vector<Integer>();

		numOfValidQuestions = questionPool.numOfValidQuestions(numOfAnswers);
		System.out.println("Please enter number of questions, " + numOfValidQuestions + " valid options");

		numOfQuestions = isInputOk(1, numOfValidQuestions);
		try {
			questionsIndex = QuestionPool.generateNewQuestionNumbers(numOfValidQuestions, questionPool, numOfAnswers,
					numOfQuestions);
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

//			
		try {
			questionPool.buildExam(questionsIndex);
		}

		catch (Exception e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
		try {
			questionPool.Sort();

		} catch (StringIndexOutOfBoundsException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(questionPool.getExam());

		try {
			questionPool.saveToText();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void copyExistingExam(QuestionPool questionPool) {
		// TODO Auto-generated method stub
		questionPool.loadExam();
	}

	@Override
	public void Save(QuestionPool questionPool) {
		// TODO Auto-generated method stub
		try {
			questionPool.save();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void ExitTheProgram(QuestionPool questionPool) {
		// TODO Auto-generated method stub
		try {
			questionPool.save();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}