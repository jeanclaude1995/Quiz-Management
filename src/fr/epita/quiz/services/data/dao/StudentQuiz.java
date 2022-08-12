package fr.epita.quiz.services.data.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentQuiz {
    public String getQuizTypeFromStudent(Scanner scan) {
        //get quiz type from the student
        System.out.println("a. MCQ \nb. Open \nc. Associative\n");
        System.out.print("Enter the quiz type: ");
        String quizType = scan.nextLine();
            switch (quizType) {
                case "a":
                    System.out.println("You have selected MCQ Question");
                    return "MCQ";
                case "b":
                    System.out.println("You have selected Open Question");
                    return "Open";
                case "c":
                    System.out.println("You have selected Associative Question");
                    return "Associative";
                default:
                    System.out.println("You have selected wrong quiz type");
                    return null;
            }

    }
    public int getTotalQuestionsCountFromStudent(Scanner scanner) {
        //scanner to get the total questions count from the student
        System.out.print("Please enter the total questions count: ");
        String totalQuestionsCount = scanner.nextLine();
        return Integer.parseInt(totalQuestionsCount);
    }
    public ArrayList<String> getAllTopicsFromStudent(String quizType,Scanner scanner) {
        //scanner to get the topics from the student
        //connect to database and get the topics from the table

        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            String openQnTopics = "SELECT DISTINCT  topic FROM open_questions";
            String mcqQnTopics = "select DISTINCT topic from quiz";
            if(quizType=="Open"){
                ResultSet rs = stmt.executeQuery(openQnTopics);
                //display the Result set
                while (rs.next()) {
                    System.out.println(rs.getString("topic"));
                }
                }
            else if(quizType=="MCQ") {
                ResultSet rs = stmt.executeQuery(mcqQnTopics);
                //display the Result set
                while (rs.next()) {
                    System.out.println(rs.getString("topic"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print("Please enter the topics from the above list with space one the same line: ");
        String topics = scanner.nextLine();
        String[] topicsArray = topics.split(" ");
        ArrayList<String> topicsList = new ArrayList<String>();
        for (String topic : topicsArray) {
            topicsList.add(topic);
        }
        return topicsList;
    }
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5433/Demo", "postgres","jean");
    }        //connect to database and get the topics from the table


}
