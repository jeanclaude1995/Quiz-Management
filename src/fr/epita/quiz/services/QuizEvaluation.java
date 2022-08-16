package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.datamodel.OpenQuestion;
import fr.epita.quiz.datamodel.Question;

import java.io.*;
import java.sql.*;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;

public class QuizEvaluation {
    public Boolean displayAndGetAnswer(PrintWriter pw,Integer position,Question question) throws IOException, SQLException {
        if (question instanceof MCQQuestion) {
            System.out.println(position + ") " +question.getTitle());
            //System.out.println(question.getDescription());
            return displayAndGetAnswersForMCQ((MCQQuestion) question,pw);
        }
        else if(question instanceof OpenQuestion) {
            System.out.println(position + ") " +question.getTitle());
            //System.out.println(question.getDescription());
            return displayAndGetAnswersForOpen((OpenQuestion) question,pw);
        }
        return null;
    }
    private Boolean displayAndGetAnswersForMCQ(MCQQuestion mcqQuestion,PrintWriter pw) throws IOException, SQLException {
        for (Choice choice : mcqQuestion.getChoices()) {
            Integer position = mcqQuestion.getChoices().indexOf(choice)+1;
            System.out.println("  ("+position + ") " + choice.getTitle());
            pw.println("  ("+position + ") " + choice.getTitle());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        String answer = scanner.nextLine();
        pw.println("Your answer: "+answer);
        String questionTitle = mcqQuestion.getTitle();
        String answerQuery= "SELECT answer FROM mcq WHERE question = '"+questionTitle+"'";
        Connection con = connect();
        PreparedStatement stmt = con.prepareStatement(answerQuery);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            pw.println("Correct answer: "+rs.getString("answer"));
        }
        switch (answer) {
            case "1":
                return mcqQuestion.getChoices().get(0).getCorrect();
            case "2":
                return mcqQuestion.getChoices().get(1).getCorrect();
            case "3":
                return mcqQuestion.getChoices().get(2).getCorrect();
            case "4":
                return mcqQuestion.getChoices().get(3).getCorrect();
            default:
                return false;
        }
        }
    private Boolean displayAndGetAnswersForOpen(OpenQuestion openQuestion,PrintWriter pw) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        String answer = scanner.nextLine();
        pw.println("Your answer: "+answer);
        pw.println("Correct answer: "+openQuestion.getAnswer());
        return answer.equals(openQuestion.getAnswer());
    }
    private Connection connect() throws SQLException, IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("./database.properties");
        props.load(in);
        in.close();
        String url = props.getProperty("dbUrl");
        String user = props.getProperty("dbName");
        String password = props.getProperty("dbPassword");
        return DriverManager.getConnection(url,user,password);
    }

}



