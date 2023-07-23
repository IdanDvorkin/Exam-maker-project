package View;

import javax.swing.JOptionPane;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.function.Predicate;
import Listeners.UIEventesListener;
import Model.AmericanQuestion;
import Model.IteratorSubject;
import Model.LabelReact;
import Model.MyArrayList;
import Model.OpenQuestion;
import Model.Question;
import Model.QuestionPool;
import Model.MyArrayList.Memento;
import Roots.ShowAllQuestionsRoot;
import Roots.TurnSetToMyArrayList;
import Roots.UpdateQuestionRoot;
import Roots.DeleteAnswerRoot;
import Roots.SelectQuestion;
import Roots.UpdateAnswerRoot;
import Roots.AddQuestionRoot;
import Roots.CopyExistingExamRoot;
import Roots.CopyLastExamRoot;
import Roots.CreateAutomaticExamRoot;
import Roots.CreateManualExamRoot;
import Roots.MainRoot;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class JavaFX implements AbstractView {
	private Vector<UIEventesListener> allListeners = new Vector<UIEventesListener>();

	public JavaFX(Stage stgMain) {
		stgMain.setTitle("Exams");
		stgMain.setHeight(400);
		stgMain.setWidth(400);
		Stage stgShowAllQuestions = new Stage();
		stgShowAllQuestions.setTitle("All questions");
		ShowAllQuestionsRoot showAllQuestionsRoot = new ShowAllQuestionsRoot();

		stgShowAllQuestions.setScene(new Scene(showAllQuestionsRoot.getRoot(), 700, 700));
		MainRoot mainRoot = new MainRoot();
		mainRoot.getShowAllQuestionsBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				fillComboBoxWithQuestions(showAllQuestionsRoot);
				showAllQuestionsRoot.getCloseBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						stgShowAllQuestions.close();
					}
				});

				stgShowAllQuestions.show();

			}
		});
		Stage stg2 = new Stage();
		stg2.setTitle("Add Question");
		AddQuestionRoot addQuestionsRoot = new AddQuestionRoot();
		stg2.setScene(new Scene(addQuestionsRoot.getRoot(), 500, 400));

		mainRoot.getAddNewQuestionBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				addQuestionsRoot.getAddOpenQuestionBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent action) {

						for (UIEventesListener l : allListeners) {
							l.addOpenQuestionToUI(addQuestionsRoot.getAddOpenTxt().getText(),
									addQuestionsRoot.getAddOpenAnswerTxt().getText());
							try {
								l.save();
							} catch (Exception e) {

								e.printStackTrace();
							}

						}

					}
				});
				addQuestionsRoot.getAddAnswersBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						if (addQuestionsRoot.getAnswer() != "") {
							AddQuestionRoot.addAnswersToComboBox(AddQuestionRoot.getAnswer(),
									AddQuestionRoot.getStatusString());
						} else
							JOptionPane.showMessageDialog(null, "Input can't be empty");

					}
				});
				addQuestionsRoot.getReturnToMainBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						for (UIEventesListener uiEventesListener : allListeners) {
							uiEventesListener.clearAnswers(addQuestionsRoot);
						}
						stg2.close();
					}
				});

				stg2.show();

			}
		});
		addQuestionsRoot.getAddAmericanQuestionBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				for (UIEventesListener uiEventesListener : allListeners) {
					uiEventesListener.addAnswers(addQuestionsRoot.getAddAmericanQuestionTxt().getText(),
							AddQuestionRoot.getAllAnswers(), addQuestionsRoot);
					try {
						uiEventesListener.save();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});

		mainRoot.getUpdateQuestionBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				UpdateQuestionRoot uqRoot = new UpdateQuestionRoot();
				fillComboBoxWithQuestions(uqRoot);
				uqRoot.getSelectBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						if (uqRoot.getSelectedQuestion() != null)
							uqRoot.showUI();

						else
							JOptionPane.showMessageDialog(null, "Please select a question");

					}

				});

				uqRoot.getUpdateBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						for (UIEventesListener uiEventesListener : allListeners) {
							uiEventesListener.updateQuestionUI(uqRoot.getSelectedQuestion(), uqRoot.getNewQuestion(),
									uqRoot);
						}
						uqRoot.hideUI();
						System.out.println("THE NEW MODEL : " + getModel());
					}
				});
				uqRoot.getReturnBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						uqRoot.getStage().close();

					}
				});

			}

		});

		mainRoot.getUpdateAnswerBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				UpdateAnswerRoot uaRoot = new UpdateAnswerRoot();
				fillComboBoxWithQuestions(uaRoot);
				uaRoot.getSelectBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if (uaRoot.getSelectedQuestion() != null) {
							if (uaRoot.getSelectedQuestion() instanceof OpenQuestion) {
								uaRoot.showOpenUI();

							} else {
								uaRoot.showAmericanUI();

							}
						}

						else
							JOptionPane.showMessageDialog(null, "Please select a question");
					}
				});
				uaRoot.getUpdateBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub

						for (UIEventesListener uiEventesListener : allListeners) {
							uiEventesListener.updateAnswer(uaRoot.getSelectedQuestion(), uaRoot.getAnswerText(),
									uaRoot.getAnswerIdText(), UpdateAnswerRoot.getStatusString(), uaRoot);
						}
						uaRoot.hideUI();
//						if (!uaRoot.getAnswerIdText().isBlank() && !uaRoot.getAnswerText().isBlank()) {
//							if (Integer.parseInt(uaRoot.getAnswerIdText()) > 0 && Integer.parseInt(
//									uaRoot.getAnswerIdText()) < getNumOfAnswers(uaRoot.getSelectedQuestion())) {
//								if (uaRoot.getSelectedButton() != null) {
//									for (UIEventesListener uiEventesListener : allListeners) {
//										uiEventesListener.updateAnswer(uaRoot.getSelectedQuestion(),
//												uaRoot.getAnswerText(), uaRoot.getAnswerIdText(),
//												UpdateAnswerRoot.getStatusString(), uaRoot);
//
//									}
//									uaRoot.hideUI();
//
//								} else {
//									JOptionPane.showMessageDialog(null, "Please select true or false");
//								}
//							} else {
//								
//								JOptionPane.showMessageDialog(null, "Please select an id from 1 to "
//										+ getNumOfAnswers(uaRoot.getSelectedQuestion()));
//							}
//						} else {
//							JOptionPane.showMessageDialog(null, "Input cant be empty");
//						}

					}

					private int getNumOfAnswers(Question selectedQuestion) {
						// TODO Auto-generated method stub
						return ((AmericanQuestion) (getModel().getQuestionByID(selectedQuestion.getID())))
								.getAllAnswers().getCurrentSize();
					}
				});
				uaRoot.getReturnBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						uaRoot.getStage().close();

					}
				});
			}
		});

		mainRoot.getDeleteAnswerBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				DeleteAnswerRoot daRoot = new DeleteAnswerRoot();
				fillComboBoxWithQuestions(daRoot);
				daRoot.getSelectBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						if (daRoot.getSelectedQuestion() == null)
							JOptionPane.showMessageDialog(null, "Please select a question");
						else {
							if (daRoot.getSelectedQuestion() instanceof AmericanQuestion) {
								daRoot.showUI();
								if (daRoot.getSelectedQuestion() instanceof AmericanQuestion) {
									daRoot.getDeleteBtn().setOnAction(new EventHandler<ActionEvent>() {

										@Override
										public void handle(ActionEvent arg0) {
											// TODO Auto-generated method stub
											for (UIEventesListener uiEventesListener : allListeners) {
												try {
													uiEventesListener.deleteAnswerUI(
															Integer.parseInt(daRoot.getAnswerIdText()) - 1,
															daRoot.getSelectedQuestion().getID());
													System.out.println("deleted");
													daRoot.getComboBox().getItems().clear();
													fillComboBoxWithQuestions(daRoot);
													daRoot.hideUI();
												} catch (NumberFormatException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
											}
										}
									});

								}
							} else
								JOptionPane.showMessageDialog(null, "Can't delete answers of open question");
						}

					}
				});
				daRoot.getReturnBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						daRoot.getStage().close();

					}
				});

			}

		});

		mainRoot.getCreateManualExamBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CreateManualExamRoot cmeRoot = new CreateManualExamRoot();
				fillComboBoxWithQuestions(cmeRoot);
				cmeRoot.getReturnBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						cmeRoot.getStage().close();

					}
				});
				cmeRoot.getSelectBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						cmeRoot.clearComboBox(cmeRoot.getSelectedAnswerCB());
						cmeRoot.clearComboBox(cmeRoot.getAllAnswersCB());

						if (cmeRoot.getSelectedQuestion() != null) {

							if (cmeRoot.getSelectedQuestion() instanceof OpenQuestion) {
								cmeRoot.showAddBtn();
								cmeRoot.getAddBtn().setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										// TODO Auto-generated method stub
										cmeRoot.showExamUI();
										for (UIEventesListener uiEventesListener : allListeners) {
											try {
												uiEventesListener.addQuestionToManual(cmeRoot.getSelectedQuestion(),
														null);
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											cmeRoot.addQuestionToComboBox(cmeRoot.getSelectedQuestion());
										}
										cmeRoot.remove(cmeRoot.getSelectedQuestion());
										cmeRoot.hideUI();
									}

								});
							} else {
								cmeRoot.showAmericanUI();

								fillComboBoxWithAnswers(cmeRoot);
								cmeRoot.getTransferBtn().setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										// TODO Auto-generated method stub
										cmeRoot.transferAnswer(cmeRoot.getSelectedAnswer());

									}

								});
								cmeRoot.getAmericanBtn().setOnAction(new EventHandler<ActionEvent>() {

									@Override
									public void handle(ActionEvent arg0) {
										// TODO Auto-generated method stub

										if (!cmeRoot.getSelectedAnswerCB().getItems().isEmpty()) {
											cmeRoot.showExamUI();
											for (UIEventesListener uiEventesListener : allListeners) {

												try {
													uiEventesListener.addQuestionToManual(cmeRoot.getSelectedQuestion(),
															cmeRoot.getSelectedAnswerCB());
												} catch (Exception e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												try {
													uiEventesListener.addQuestionToComboBoxUI(
															cmeRoot.getSelectedQuestion(),
															cmeRoot.getSelectedAnswerCB(), cmeRoot);
												} catch (Exception e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}

											}
											cmeRoot.remove(cmeRoot.getSelectedQuestion());
											cmeRoot.hideUI();
										} else
											JOptionPane.showMessageDialog(null, "Please select answers");

									}
								});

							}
						} else
							JOptionPane.showMessageDialog(null, "Please select a question");
						cmeRoot.getCreateBtn().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								// TODO Auto-generated method stub
								for (UIEventesListener uiEventesListener : allListeners) {
									uiEventesListener.createExamFile(cmeRoot.getExamCB());
								}
								cmeRoot.close();
							}
						});

					}

					private void fillComboBoxWithAnswers(CreateManualExamRoot cmeRoot) {
						// TODO Auto-generated method stub
						AmericanQuestion aq = (AmericanQuestion) (cmeRoot.getSelectedQuestion());
						for (int i = 0; i < aq.getSize(); i++) {
							cmeRoot.getAllAnswersCB().getItems().add(aq.getAnswer(i));
						}

					}

				});

			}
		});
		mainRoot.getCreateAutomaticExamBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CreateAutomaticExamRoot caeRoot = new CreateAutomaticExamRoot();
				caeRoot.show();
				caeRoot.getCreateBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						try {
//							if (caeRoot.getNumOfQuestionTxt().getText() != "") {
							for (UIEventesListener uiEventesListener : allListeners) {
								uiEventesListener.createAutomaticExam(
										Integer.parseInt(caeRoot.getNumOfQuestionTxt().getText()), caeRoot);
								// uiEventesListener.createExamFile(caeRoot.getAutoExamComboBox());

							}
//							} else
//								JOptionPane.showMessageDialog(null, "Must enter a number");

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Must enter a number");
						}
					}
				});
				caeRoot.getReturnBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						caeRoot.getStage().close();

					}
				});

			}
		});

		mainRoot.getCopyExistingExamBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				CopyExistingExamRoot ceeRoot = new CopyExistingExamRoot();
				ceeRoot.show();
				fillAllExamsCB(ceeRoot);
				ceeRoot.getSelectBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub

						if (ceeRoot.getSelectedExam() != "null") {
							for (UIEventesListener uiEventesListener : allListeners) {
								uiEventesListener.copySelectedExamFromFile(ceeRoot.getSelectedExam(), ceeRoot);
							}
							JOptionPane.showMessageDialog(null, "exam copied");
						} else
							JOptionPane.showMessageDialog(null, "please select an exam");

					}
				});
				ceeRoot.getReturnBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						ceeRoot.getStage().close();

					}
				});

			}

		});

		mainRoot.getCopyLastExamBtn().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CopyLastExamRoot cleRoot = new CopyLastExamRoot();
				cleRoot.show();
				for (UIEventesListener uiEventesListener : allListeners) {
					uiEventesListener.fillLastExamCB(uiEventesListener.getLastExam(), cleRoot);
				}
				cleRoot.getCopyBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						// TODO Auto-generated method stub
						for (UIEventesListener uiEventesListener : allListeners) {
							uiEventesListener.copyLastExam();
						}
					}
				});
				cleRoot.getReturnBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						cleRoot.getStage().close();

					}
				});

			}

		});

		mainRoot.getVectorToListBtn().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage stgShowAllQuestions = new Stage();
				stgShowAllQuestions.setTitle("Sorted list of questions");
				ShowAllQuestionsRoot showAllQuestionsRoot = new ShowAllQuestionsRoot();
				stgShowAllQuestions.setScene(new Scene(showAllQuestionsRoot.getRoot(), 500, 500));
				printSortedList(turnToList(showAllQuestionsRoot), showAllQuestionsRoot);
				showAllQuestionsRoot.getCloseBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						stgShowAllQuestions.close();
					}
				});

				stgShowAllQuestions.show();

			}

		});

		mainRoot.getListToSetBtn().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage stgShowAllQuestions = new Stage();
				stgShowAllQuestions.setTitle("Questions without duplicates");
				ShowAllQuestionsRoot showAllQuestionsRoot = new ShowAllQuestionsRoot();
				stgShowAllQuestions.setScene(new Scene(showAllQuestionsRoot.getRoot(), 500, 500));
				turnToSet(turnToList(showAllQuestionsRoot), showAllQuestionsRoot);
				printSet(turnToSet(turnToList(showAllQuestionsRoot), showAllQuestionsRoot), showAllQuestionsRoot);
				showAllQuestionsRoot.getCloseBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						stgShowAllQuestions.close();
					}

				});
				stgShowAllQuestions.show();
			}

			private void printSet(Set<Question> s, SelectQuestion root) {
				Iterator<Question> itr = s.iterator();
				while (itr.hasNext()) {
					Question current = itr.next();
					System.out.println(current);
					root.getComboBox().getItems().add(current);

				}
			}

		});
		mainRoot.getbtnMyArrayList().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage stgShowAllQuestions = new Stage();
				stgShowAllQuestions.setTitle("Set turned to MyArrayList");
				TurnSetToMyArrayList TurnSetToMyArrayListRoot = new TurnSetToMyArrayList();
				stgShowAllQuestions.setScene(new Scene(TurnSetToMyArrayListRoot.getRoot(), 500, 500));
				turnSetIntoMyArrayList(turnToSet(turnToList(TurnSetToMyArrayListRoot), TurnSetToMyArrayListRoot),
						TurnSetToMyArrayListRoot);
				Model.MyArrayList<Question> myList = turnSetIntoMyArrayList(
						turnToSet(turnToList(TurnSetToMyArrayListRoot), TurnSetToMyArrayListRoot),
						TurnSetToMyArrayListRoot);
				Memento memento=myList.saveStateToMemento();
				TurnSetToMyArrayListRoot.clearComboBox();
				

				TurnSetToMyArrayListRoot.getCreateBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						Model.Command command = new Model.IteratorCommand(myList, TurnSetToMyArrayListRoot);
						command.execute();
						
					}

				});
				TurnSetToMyArrayListRoot.getObserverBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						
						TurnSetToMyArrayListRoot.clearComboBox();
					    Model.Command command = new Model.PrintMyArrayListCommand(myList, TurnSetToMyArrayListRoot);
					    
					    command.execute();
					}

				});
				TurnSetToMyArrayListRoot.getRemoveAllBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {

						removeMyArrayList(removeMyArrayList(myList.iterator(), TurnSetToMyArrayListRoot),
								TurnSetToMyArrayListRoot);
					}

				});
				TurnSetToMyArrayListRoot.getRemoveBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
				
						Model.Command command = new Model.RemoveFromMyArrayListCommand(myList, TurnSetToMyArrayListRoot);
						command.execute();
					}

				});
				TurnSetToMyArrayListRoot.getCloseBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						stgShowAllQuestions.close();
					}

				});
				TurnSetToMyArrayListRoot.getObserverBtnRestored().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						myList.restoreStateFromMemento(memento);
						TurnSetToMyArrayListRoot.clearComboBox();
                        Model.Command command = new Model.PrintMyArrayListCommand(myList, TurnSetToMyArrayListRoot);
					    
					    command.execute();
					
					}
				});
				stgShowAllQuestions.show();
			}
		});
		mainRoot.getbtnArrayList().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage stgShowAllQuestions = new Stage();
				stgShowAllQuestions.setTitle("Set turned to MyArrayList");
				TurnSetToMyArrayList TurnSetToMyArrayListRoot = new TurnSetToMyArrayList();
				stgShowAllQuestions.setScene(new Scene(TurnSetToMyArrayListRoot.getRoot(), 500, 500));
				ArrayList<Question> list = new ArrayList<>(
						turnToSet(turnToList(showAllQuestionsRoot), showAllQuestionsRoot));
				Iterator<Question> itr = list.iterator();
				while (itr.hasNext()) {
					Question current = itr.next();
					System.out.println(current);
					TurnSetToMyArrayListRoot.getComboBox().getItems().add(current);

				}
				TurnSetToMyArrayListRoot.getRemoveBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						Iterator<Question> itr = list.iterator();
						itr.next();
						itr.remove();
						TurnSetToMyArrayListRoot.clearComboBox();
						while (itr.hasNext()) {
							Question current = itr.next();

							System.out.println(current);
							TurnSetToMyArrayListRoot.getComboBox().getItems().add(current);
						}

					}
				});
				TurnSetToMyArrayListRoot.getRemoveAllBtn().setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						Iterator<Question> itr = list.iterator();
						removeAllWithJava(removeAllWithJava(itr, TurnSetToMyArrayListRoot), TurnSetToMyArrayListRoot);

					}

					private Iterator<Question> removeAllWithJava(Iterator<Question> itr, SelectQuestion root) {

						boolean flag = true;
						root.clearComboBox();

						while (itr.hasNext()) {
							Question current = itr.next();
							if (flag) {
								itr.remove();
								flag = false;
							}
							if (current != null) {
								System.out.println(current);
								root.getComboBox().getItems().add(current);
							}

						}
						return itr;
					}

				});

				TurnSetToMyArrayListRoot.getCloseBtn().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {

						stgShowAllQuestions.close();
					}

				});
				stgShowAllQuestions.show();
			}

		});

		stgMain.setScene(new Scene(mainRoot.getRoot(), 250, 290));
		stgMain.show();

	}

	private void removeFromMyArrayList(MyArrayList<Question> myList, SelectQuestion root) {
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

	private Iterator<Question> removeMyArrayList(Iterator<Question> itr, SelectQuestion root) {

		boolean flag = true;
		root.clearComboBox();

		while (itr.hasNext()) {
			Question current = itr.next();
			if (flag) {
				itr.remove();
				flag = false;
			}
			if (current != null) {
				System.out.println(current);
				root.getComboBox().getItems().add(current);
			}

		}
		return itr;
	}

	public MyArrayList<Question> turnSetIntoMyArrayList(Set list, SelectQuestion root) {
		Model.MyArrayList<Question> myList = new Model.MyArrayList<Question>();
		Iterator<Question> listItr = list.iterator();

		while (listItr.hasNext()) {
			myList.add(listItr.next());
		}
		Iterator<Question> itr = myList.iterator();
		while (itr.hasNext()) {
			Question current = itr.next();
			if (current != null) {
				System.out.println(current);
				root.getComboBox().getItems().add(current);
			}

		}
		return myList;

	}

	private void PrintMyArrayList(MyArrayList<Question> list, SelectQuestion root) {
		Iterator<Question> itr = list.iterator();
		while (itr.hasNext()) {
			Question current = itr.next();
			if (current != null) {
				System.out.println(current);
				root.getComboBox().getItems().add(current);
			}

		}
	}

	public Set<Question> turnToSet(List<Question> list, SelectQuestion root) {
		Set<Question> s = new LinkedHashSet<>(list);
		root.clearComboBox();
		Iterator<Question> itr = s.iterator();

		return s;

	}

	public List<Question> turnToList(SelectQuestion root) {
		List<Question> list = Collections.list(getModel().getAllQuestions().elements());
		Model.OrderCompare compare = new Model.OrderCompare();
		Collections.sort(list, compare);
		return list;
	}

	public void printSortedList(List<Question> list, SelectQuestion root) {
		Model.OrderCompare compare = new Model.OrderCompare();
		list.sort(compare);
		root.clearComboBox();
		Iterator<Question> itr = list.iterator();

		while (itr.hasNext()) {
			Question current = itr.next();
			System.out.println(current);
			root.getComboBox().getItems().add(current);
		}
	}

	public void registerListener(UIEventesListener newListener) {
		allListeners.add(newListener);
	}

	public void addOpenQuestionToUI(String question, String answer) {
		JOptionPane.showMessageDialog(null, "" + question + "," + answer + " added");

	}

	private void fillComboBoxWithQuestions(SelectQuestion root) {
		// TODO Auto-generated method stub
		root.clearComboBox();
		for (int i = 0; i < getNumOfQuestions(); i++) {
			System.out.println(getModel().getQuestionByID(i));
			root.getComboBox().getItems().add(getModel().getQuestionByID(i));
		}

	}

	private int getNumOfQuestions() {
		// TODO Auto-generated method stub
		return getModel().getCount();
	}

	public QuestionPool getModel() {
		for (UIEventesListener uiEventesListener : allListeners) {
			return uiEventesListener.getModel();
		}
		return null;

	}

	public void addQuestionToExamUI(AmericanQuestion aq, CreateManualExamRoot cmeRoot) {
		// TODO Auto-generated method stub
		cmeRoot.addQuestionToComboBox(aq);
	}

	public void addExamToUI(QuestionPool exam, CreateAutomaticExamRoot root) {
		// TODO Auto-generated method stub
		root.getAutoExamComboBox().getItems().clear();
		for (int i = 0; i < exam.getCount(); i++) {
			root.getAutoExamComboBox().getItems().add(exam.getAllQuestions().get(i));
		}
		JOptionPane.showMessageDialog(null, "Automatic exam has been created");
	}

	public void saved() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, " Exam Created ");

	}

	public void fillLastExamCB(QuestionPool lastExam, CopyLastExamRoot cleRoot) {
		// TODO Auto-generated method stub
		if (lastExam != null) {
			for (int i = 0; i < lastExam.getCount(); i++) {
				cleRoot.getLastExamCB().getItems().add(lastExam.getAllQuestions().get(i));
			}
		} else
			JOptionPane.showMessageDialog(null, "Create an exam first");

	}

	public void copiedLastExam() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "Last exam has been copied");
	}

	public void updateQuestionUI(QuestionPool model, UpdateQuestionRoot uqRoot) {
		// TODO Auto-generated method stub
		uqRoot.clearComboBox();
		fillComboBoxWithQuestions(uqRoot);

	}

	public void updateAnswerUI(UpdateAnswerRoot uaRoot) {
		// TODO Auto-generated method stub

		fillComboBoxWithQuestions(uaRoot);
		JOptionPane.showMessageDialog(null, "Exam copied");
	}

	public void wrongInput(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, msg);

	}

	public void fillExamListComboBox(CopyExistingExamRoot ceeRoot, Vector<String> allExamsFromFile) {
		// TODO Auto-generated method stub
		for (int i = 0; i < allExamsFromFile.size(); i++) {
			ceeRoot.getAllExamsCB().getItems().add(allExamsFromFile.get(i));

		}
	}

	private void fillAllExamsCB(CopyExistingExamRoot ceeRoot) {
		// TODO Auto-generated method stub
		for (UIEventesListener uiEventesListener : allListeners) {
			uiEventesListener.getExamNames(ceeRoot);
		}
	}

	public void updateAnswerUI(CopyExistingExamRoot root) {
		// TODO Auto-generated method stub
		fillAllExamsCB(root);
	}

	public void clearExamComboBox(CopyExistingExamRoot ceeRoot) {
		// TODO Auto-generated method stub
		ceeRoot.clearExamCB();
	}

	public void questionExists() {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, "question already exists");
	}

	public void clearAnswerCB(AddQuestionRoot root) {
		// TODO Auto-generated method stub
		root.getAllAnswers().getItems().clear();
	}

}