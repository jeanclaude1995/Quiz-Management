package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.*;
import fr.epita.quiz.services.*;


import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println("1. Admin");
        System.out.println("2. Student");
        System.out.println("3. exit");
        System.out.print("Enter your choice: ");
        Scanner scan = new Scanner(System.in);
        String choice = scan.nextLine();
        switch (choice) {
            case "1":
                boolean isAdminValid = new AdminLogin().getAdminData(scan);
                if (isAdminValid) {
                    StudentQuiz studentQuiz = new StudentQuiz();
                    Quiz questionGenerator = new Quiz();
                    String quizType = studentQuiz.getQuizTypeFromStudent(scan);
                    //String quizType = "Open";
                    if (quizType != null) {
                        //CRUD operations for quiz table
                        String ch = "";
                        while (!"6".equals(ch)) {
                            System.out.println("What operation would you like to do?");
                            System.out.println("1. Create question");
                            System.out.println("2. Demo quiz");
                            System.out.println("3. Update question");
                            System.out.println("4. Delete question");
                            System.out.println("5. Search question");
                            System.out.println("6. Exit");
                            System.out.print("Enter your choice: ");
                            ch = scan.nextLine();
                            int choiceInt = Integer.parseInt(ch);
                            switch (choiceInt) {
                                case 1:
                                    if (quizType.equals("MCQ")) {
                                        MCQDAO mcqDAO = new MCQDAO();
                                        mcqDAO.create(scan);
                                    } else if (quizType.equals("Open")) {
                                        OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                                        openQuestionDAO.create(scan);
                                    }
                                    break;
                                case 2:
                                    ArrayList<String> topics = studentQuiz.getAllTopicsFromStudent(quizType, scan);
                                    int numberOfQuestions = studentQuiz.getTotalQuestionsCountFromStudent(scan);
                                    ArrayList<Question> questions = questionGenerator.getQuestions(quizType, topics, numberOfQuestions);
                                    QuizEvaluation quizEvaluation = new QuizEvaluation();
                                    Integer score = 0;
                                    Integer position = 0;
                                    //getting file path from the user and write the questions to the file
                                    System.out.print("Enter the file path to write the questions in text file(sample: D://Code2.txt) : ");
                                    String filePath = scan.nextLine();
                                    File file = new File(filePath);
                                    //File file = new File("D://Code2.txt"); //file name is hardcoded
                                    FileOutputStream outputStream = new FileOutputStream(file); //
                                    PrintWriter pw = new PrintWriter(outputStream);
                                    pw.println("Quiz Type: " + quizType + "QUESTIONS");
                                    for (Question question : questions) {
                                        position++;
                                        pw.println(position + ". " + question.getTitle());
                                        boolean isCorrect = quizEvaluation.displayAndGetAnswer(pw, position, question);

                                        if (isCorrect) score++;
                                    }
                                    pw.flush();
                                    pw.close();
                                    System.out.println("Your score is: " + score);
                                    break;
                                case 3:
                                    if (quizType.equals("MCQ")) {
                                        MCQDAO mcqDAO = new MCQDAO();
                                        mcqDAO.update(scan);
                                    } else if (quizType.equals("Open")) {
                                        OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                                        openQuestionDAO.update(scan);
                                    }
                                    break;
                                case 4:
                                    if (quizType.equals("MCQ")) {
                                        MCQDAO mcqDAO = new MCQDAO();
                                        mcqDAO.delete(scan);
                                    } else if (quizType.equals("Open")) {
                                        OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                                        openQuestionDAO.delete(scan);
                                    }
                                    break;
                                case 5:
                                    ArrayList<String> topics1 = studentQuiz.getAllTopicsFromStudent(quizType, scan);
                                    Integer position2 = 0;
                                    if (quizType.equals("MCQ")) {
                                        MCQDAO mcqDAO = new MCQDAO();
                                        for (String topic : topics1) {
                                            ArrayList<Question> questions1 = mcqDAO.searchMCQ(topic);
                                            for (Question question : questions1) {
                                                position2++;
                                                if (question instanceof MCQQuestion) {
                                                    MCQQuestion mcqQuestion = (MCQQuestion) question;
                                                    System.out.println( position2+ ") " +mcqQuestion.getTitle());
                                                    Integer position1 = 0;
                                                    for (Choice choice1 : mcqQuestion.getChoices()) {
                                                        position1++;
                                                        position1 = mcqQuestion.getChoices().indexOf(choice1) + 1;
                                                        System.out.println("  (" + position1 + ") " + choice1.getTitle());
                                                    }
                                                }
                                            }
                                        }
                                    } else if (quizType.equals("Open")) {
                                        OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                                        for (String topic : topics1) {
                                            ArrayList<Question> questions1 = openQuestionDAO.searchOpenQuestions(topic);
                                            for (Question question : questions1) {
                                                position2++;
                                                if (question instanceof OpenQuestion) {
                                                    OpenQuestion openQuestion = (OpenQuestion) question;
                                                    System.out.println(position2 + ") " +question.getTitle());
                                                    System.out.println("Answer: " + openQuestion.getAnswer());
                                                }

                                            }
                                        }
                                    }
                                    break;
                                case 6:
                                    System.out.println("Exiting");
                                    break;
                                default:
                                    System.out.println("Invalid choice");
                                    break;
                            }
                        }

                    }
                }
                    break;
                    case "2":
                        boolean isStudentValid = new StudentLogin().getStudentData(scan);
                        if (isStudentValid) {
                            StudentQuiz studentQuiz = new StudentQuiz();
                            Quiz questionGenerator = new Quiz();
                            String quizType = studentQuiz.getQuizTypeFromStudent(scan);
                            if (quizType != null) {
                                        ArrayList<String> topics = studentQuiz.getAllTopicsFromStudent(quizType, scan);
                                        int numberOfQuestions = studentQuiz.getTotalQuestionsCountFromStudent(scan);
                                        ArrayList<Question> questions = questionGenerator.getQuestions(quizType, topics, numberOfQuestions);
                                        QuizEvaluation quizEvaluation = new QuizEvaluation();
                                        Integer score = 0;
                                        Integer position = 0;
                                        //getting file path from the user and write the questions to the file
                                        System.out.print("Enter the file path to write the questions in text file(sample: D://Code2.txt) : ");
                                        String filePath = scan.nextLine();
                                        File file = new File(filePath);
                                        //File file = new File("D://Code2.txt"); //file name is hardcoded
                                        FileOutputStream outputStream = new FileOutputStream(file); //
                                        PrintWriter pw = new PrintWriter(outputStream);
                                        pw.println("Quiz Type: " + quizType + "QUESTIONS");
                                        for (Question question : questions) {
                                            position++;
                                            pw.println(position + ". " + question.getTitle());
                                            boolean isCorrect = quizEvaluation.displayAndGetAnswer(pw, position, question);
                                            if (isCorrect) score++;
                                        }
                                        pw.println("\n\nYour Final score is: " + score);
                                        pw.flush();
                                        pw.close();
                                        System.out.println("Your score is: " + score);
                                        break;

                                }
                            }
                        break;
                    case "3":
                        System.out.println("Exiting");
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
        }
    }


