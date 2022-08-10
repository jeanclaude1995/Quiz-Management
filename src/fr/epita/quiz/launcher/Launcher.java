package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.datamodel.Quiz;
import fr.epita.quiz.services.data.dao.QuizEvaluation;
import fr.epita.quiz.services.data.dao.StudentLogin;
import fr.epita.quiz.services.data.dao.StudentQuiz;

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
        if (quizType != null) {
            ArrayList<String> topics = studentQuiz.getAllTopicsFromStudent(scan);
            int numberOfQuestions = studentQuiz.getTotalQuestionsCountFromStudent(scan);
            ArrayList<Question> questions = questionGenerator.getQuestions(quizType,topics, numberOfQuestions);
            QuizEvaluation quizEvaluation = new QuizEvaluation();
            int score = 0;
            int position = 0;
            File file  = new File("D://Code2.txt");
            FileOutputStream outputStream = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(outputStream);
            pw.println("Quiz Type: " + quizType+ "QUESTIONS");
            for (Question question : questions) {
                position++;
                pw.println(position + ". " + question.getTitle());
                boolean isCorrect = quizEvaluation.displayAndGetAnswer(pw,position,question);
                if (isCorrect) score++;
            }
            pw.flush();
            pw.close();
            System.out.println("Your score is: " + score);


        }
    }


}
