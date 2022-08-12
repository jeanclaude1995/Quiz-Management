package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.services.data.dao.*;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) throws SQLException, IOException {
        Scanner scan = new Scanner(System.in);
        boolean isStudentValid = new StudentLogin().getStudentData(scan);
        if (!isStudentValid) return;
        StudentQuiz studentQuiz = new StudentQuiz();
        Quiz questionGenerator = new Quiz();
        String quizType = studentQuiz.getQuizTypeFromStudent(scan);
        //String quizType = "Open";
        if (quizType != null) {
            //CRUD operations for quiz table
            System.out.println("1. Create");
            System.out.println("2. Read");
            System.out.println("3. Update");
            System.out.println("4. Delete");
            System.out.println("5. Exit");
            System.out.print("Enter your choice");
            String choice = scan.nextLine();
            int choiceInt = Integer.parseInt(choice);
            //int choiceInt = 3;
            switch (choiceInt) {
                case 1:
                    if (quizType.equals("MCQ")) {
                        MCQDAO mcqDAO = new MCQDAO();
                        mcqDAO.create(scan);
                    } else if(quizType.equals("Open")){
                        OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                        openQuestionDAO.create(scan);
                    }
                    break;
                case 2:
                    ArrayList<String> topics = studentQuiz.getAllTopicsFromStudent(quizType,scan);
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
                    if(quizType.equals("MCQ")){
                        MCQDAO mcqDAO = new MCQDAO();
                        mcqDAO.update(scan);
                    }else if(quizType.equals("Open")){
                        OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                        openQuestionDAO.update(scan);
                    }
                    break;
                case 4:
                    if(quizType.equals("MCQ")){
                        MCQDAO mcqDAO = new MCQDAO();
                        mcqDAO.delete(scan);
                    }else if(quizType.equals("Open")){
                        OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                        openQuestionDAO.delete(scan);
                    }
                    break;
                case 5:
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
//        String quizType = studentQuiz.getQuizTypeFromStudent(scan);
//        if (quizType != null) {
//            ArrayList<String> topics = studentQuiz.getAllTopicsFromStudent(scan);
//            int numberOfQuestions = studentQuiz.getTotalQuestionsCountFromStudent(scan);
//            ArrayList<Question> questions = questionGenerator.getQuestions(quizType,topics, numberOfQuestions);
//            QuizEvaluation quizEvaluation = new QuizEvaluation();
//            int score = 0;
//            int position = 0;
//            File file  = new File("D://Code2.txt");
//            FileOutputStream outputStream = new FileOutputStream(file);
//            PrintWriter pw = new PrintWriter(outputStream);
//            pw.println("Quiz Type: " + quizType+ "QUESTIONS");
//            for (Question question : questions) {
//                position++;
//                pw.println(position + ". " + question.getTitle());
//                boolean isCorrect = quizEvaluation.displayAndGetAnswer(pw,position,question);
//                if (isCorrect) score++;
//            }
//            pw.flush();
//            pw.close();
//            System.out.println("Your score is: " + score);
//
//
//        }
        }

    }
}
