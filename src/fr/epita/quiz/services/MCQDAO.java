package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.datamodel.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;

public class MCQDAO {
    public void create(Scanner scan) {//insert questions into MCQ question table
        //insert questions into MCQ question table
        System.out.println("Enter question Id");
        String id = scan.nextLine();
        System.out.println("Enter question topic");
        String topic = scan.nextLine();
        System.out.println("Enter the question ");
        String title = scan.nextLine();
        System.out.println("Enter question answer");
        String answer = scan.nextLine();
        System.out.println("Enter choice 2");     //choice 1
        String choices_2 = scan.nextLine();
        System.out.println("Enter choice 3");     //choice 2
        String choices_3 = scan.nextLine();
        System.out.println("Enter choice 4");     //choice 3
        String choices_4 = scan.nextLine();
        System.out.println("Enter Description(1|2|3):");
        String description = scan.nextLine();
        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            //if no table exists, create table
            String createQuery = "CREATE TABLE IF NOT EXISTS mcq (sno VARCHAR(255) PRIMARY KEY, topic VARCHAR(255), question VARCHAR(255), answer VARCHAR(255), choice1 VARCHAR(255), choice2 VARCHAR(255), choice3 VARCHAR(255), description VARCHAR(255))";
            stmt.executeUpdate(createQuery);
            String query = "INSERT INTO mcq (sno,topic,question,answer,choice2,choice3,choice4,description) VALUES ("+id+",'"+topic+"','"+title+"','"+answer+"','"+choices_2+"','"+choices_3+"','"+choices_4+"','"+description+"')";
            stmt.executeUpdate(query);
            System.out.println("Question created successfully");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }
    //update questions in MCQ question table
    public void update(Scanner scan) {//update questions in MCQ question table
        //update questions in MCQ question table
        System.out.println("Enter question id: ");
        String id = scan.nextLine();
        System.out.println("Enter question topic");
        String topic = scan.nextLine();
        System.out.println("Enter question title");
        String title = scan.nextLine();
        System.out.println("Enter question answer");
        String answer = scan.nextLine();
        System.out.println("Enter choice 2");     //choice 1
        String choices_2 = scan.nextLine();
        System.out.println("Enter choice 3");     //choice 2
        String choices_3 = scan.nextLine();
        System.out.println("Enter choice4: ");     //choice 3
        String choices_4 = scan.nextLine();
        System.out.println("Enter Description(1|2|3):");
        String description = scan.nextLine();
        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            String query = "UPDATE mcq SET topic='"+topic+"',question='"+title+"',answer='"+answer+"',choice2='"+choices_2+"',choice3='"+choices_3+"',choice4='"+choices_4+"',description='"+description+"' WHERE sno='"+id+"'";
            stmt.executeUpdate(query);
            System.out.println("Question updated successfully");
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //delete questions from MCQ question table
    public void delete(Scanner scan) {//delete questions from MCQ question table
        //delete questions from MCQ question table
        System.out.println("Enter question id");
        String id = scan.nextLine();
        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            String query = "DELETE FROM mcq WHERE sno='"+id+"'";
            stmt.executeUpdate(query);
            System.out.println("Question deleted successfully");
            con.close();
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //get all questions from MCQ question table
    public ArrayList<Question> getMCQQuestions(String topic, int qnPerTopic) throws SQLException {//get all questions from MCQ question table

        String SQL = "SELECT * FROM mcq where topic ='"+topic+"'order by random() limit "+qnPerTopic;

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
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return questions;

    }
    //search questions from MCQ question table by topics
    public ArrayList<Question> searchMCQ(String topic) throws SQLException {//search questions from MCQ question table by topics
        String SQL = "SELECT * FROM mcq where topic ='"+topic+"'";
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
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return questions;
    }

    private Connection connect() throws SQLException, IOException {//connect to database
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
