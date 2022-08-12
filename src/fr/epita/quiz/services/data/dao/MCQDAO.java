package fr.epita.quiz.services.data.dao;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.datamodel.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MCQDAO {
    //insert MCQ question into the quiz table
    public void create(Scanner scan) {
        //insert questions into MCQ question table
        System.out.println("Enter question Id");
        String id = scan.nextLine();
        System.out.println("Enter question topic");
        String topic = scan.nextLine();
        System.out.println("Enter question title");
        String title = scan.nextLine();
        System.out.println("Enter question answer");
        String answer = scan.nextLine();
        System.out.println("Enter choice 1");     //choice 1
        String choices_1 = scan.nextLine();
        System.out.println("Enter choice 2");     //choice 2
        String choices_2 = scan.nextLine();
        System.out.println("Enter choice 3");     //choice 3
        String choices_3 = scan.nextLine();
        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            //if no table exists, create table
            String createQuery = "CREATE TABLE IF NOT EXISTS quiz (sno VARCHAR(255) PRIMARY KEY, topic VARCHAR(255), question VARCHAR(255), answer VARCHAR(255), choice1 VARCHAR(255), choice2 VARCHAR(255), choice3 VARCHAR(255))";
            stmt.executeUpdate(createQuery);
            String query = "INSERT INTO quiz (sno,topic,question,answer,choices_1,choices_2,choices_3) VALUES ("+id+",'"+topic+"','"+title+"','"+answer+"','"+choices_1+"','"+choices_2+"','"+choices_3+"')";
            stmt.executeUpdate(query);
            System.out.println("Question created successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //update questions in MCQ question table
    public void update(Scanner scan) {
        //update questions in MCQ question table
        System.out.println("Enter question id: ");
        String id = scan.nextLine();
        System.out.println("Enter question topic");
        String topic = scan.nextLine();
        System.out.println("Enter question title");
        String title = scan.nextLine();
        System.out.println("Enter question answer");
        String answer = scan.nextLine();
        System.out.println("Enter choice 1");     //choice 1
        String choices_1 = scan.nextLine();
        System.out.println("Enter choice 2");     //choice 2
        String choices_2 = scan.nextLine();
        System.out.println("Enter choice3: ");     //choice 3
        String choices_3 = scan.nextLine();
        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            String query = "UPDATE quiz SET topic='"+topic+"',question='"+title+"',answer='"+answer+"',choices_1='"+choices_1+"',choices_2='"+choices_2+"',choices_3='"+choices_3+"' WHERE sno='"+id+"'";
            stmt.executeUpdate(query);
            System.out.println("Question updated successfully");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //delete questions from MCQ question table
    public void delete(Scanner scan) {
        //delete questions from MCQ question table
        System.out.println("Enter question id");
        String id = scan.nextLine();
        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            String query = "DELETE FROM mcq_question WHERE sno='"+id+"'";
            stmt.executeUpdate(query);
            System.out.println("Question deleted successfully");
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //get all questions from MCQ question table
    public ArrayList<Question> getMCQQuestions(String topic, int qnPerTopic) throws SQLException {

        String SQL = "SELECT * FROM quiz where topic ='"+topic+"'order by random() limit "+qnPerTopic;

        ArrayList<Question> questions = new ArrayList<Question>();    // ArrayList to store questions
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                MCQQuestion question = new MCQQuestion();
                question.setDescription(rs.getString("description"));
                question.setTitle(rs.getString("Question"));
                question.setTopic(rs.getString("topic"));
                Choice answer = new Choice();
                answer.setTitle(rs.getString("answer"));
                answer.setCorrect(true);
                Choice choice2 = new Choice();
                choice2.setTitle(rs.getString("choice2"));
                choice2.setCorrect(false);
                Choice choice3 = new Choice();
                choice3.setTitle(rs.getString("choice3"));
                choice3.setCorrect(false);
                Choice choice4 = new Choice();
                choice4.setTitle(rs.getString("choice4"));
                choice4.setCorrect(false);
                ArrayList<Choice> choices = new ArrayList<>();
                choices.add(answer);
                choices.add(choice2);
                choices.add(choice3);
                choices.add(choice4);
                Collections.shuffle(choices);
                question.setChoices(choices);
                questions.add(question);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return questions;

    }
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5433/Demo", "postgres","jean");
    }
}
