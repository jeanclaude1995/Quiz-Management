package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.OpenQuestion;
import fr.epita.quiz.datamodel.Question;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class OpenQuestionDAO {
    //create open question in database
    public void create(Scanner scan) {
        //insert questions into open question table
        System.out.println("Enter question Id");
        String id = scan.nextLine();
        System.out.println("Enter question topic");
        String topic = scan.nextLine();
        System.out.println("Enter question title");
        String title = scan.nextLine();
        System.out.println("Enter question answer");
        String answer = scan.nextLine();
        System.out.println("Enter Description(1|2|3):");
        String description = scan.nextLine();
        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            //if no table exists, create table
            String createQuery = "CREATE TABLE IF NOT EXISTS open_questions (sno Varchar(255) PRIMARY KEY, topic VARCHAR(255), question VARCHAR(255), answer VARCHAR(255), description VARCHAR(255))";
            stmt.executeUpdate(createQuery);
            String insertQuery = "INSERT INTO open_questions (sno,topic,question,answer,description) VALUES ("+id+",'"+topic+"','"+title+"','"+answer+"','"+description+"')";
            stmt.executeUpdate(insertQuery);
            System.out.println("Question Created successfully");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //update open question in database
    public void update(Scanner scan) {
        //update questions in open question table
        System.out.println("Enter question id");
        String id = scan.nextLine();
        System.out.println("Enter question topic");
        String topic = scan.nextLine();
        System.out.println("Enter question title");
        String title = scan.nextLine();
        System.out.println("Enter question answer");
        String answer = scan.nextLine();
        System.out.println("Enter Description:");
        String description = scan.nextLine();
        try {
            Connection con = connect();
            java.sql.Statement stmt = con.createStatement();
            String query = "UPDATE open_questions SET topic = '"+topic+"', question = '"+title+"', answer = '"+answer+"',description= '"+description+"' WHERE sno = '"+id+"'";
            stmt.executeUpdate(query);
            System.out.println("Question updated successfully");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //delete open question from database
    public void delete(Scanner scan) {
        //delete questions from open question table
        System.out.println("Enter question id");
        String id = scan.nextLine();
        try {
            String query = "DELETE FROM open_questions WHERE sno=?";
            Connection con = connect();
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Question deleted successfully");
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    //get open question from database
    public ArrayList<Question> getOpenQuestions(String topic, int qnPerTopic) throws SQLException {

        String SQL = "SELECT * FROM open_questions where topic ='"+topic+"'order by random() limit "+qnPerTopic;
        ArrayList<Question> questions = new ArrayList<Question>();    // ArrayList to store questions
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                OpenQuestion question = new OpenQuestion();
                //question.setDescription(rs.getString("hint"));
                question.setTitle(rs.getString("Question"));
                question.setTopic(rs.getString("topic"));
                question.setAnswer(rs.getString("answer"));
                questions.add(question);
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return questions;
    }
    //search open question in database
    public ArrayList<Question> searchOpenQuestions(String topic) throws SQLException {
        String SQL = "SELECT * FROM open_questions where topic ='"+topic+"'";
        ArrayList<Question> questions = new ArrayList<Question>();    // ArrayList to store questions
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SQL)) {
            while (rs.next()) {
                OpenQuestion question = new OpenQuestion();
                //question.setDescription(rs.getString("hint"));
                question.setTitle(rs.getString("Question"));
                question.setTopic(rs.getString("topic"));
                question.setAnswer(rs.getString("answer"));
                question.setDescription(rs.getString("description"));
                questions.add(question);
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        return questions;
    }
    private Connection connect() throws SQLException, IOException {//method to connect to database
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
