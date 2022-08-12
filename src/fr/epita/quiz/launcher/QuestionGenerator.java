package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.datamodel.OpenQuestion;
import fr.epita.quiz.datamodel.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class QuestionGenerator {
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5433/Demo", "postgres","jean");
    }


    public ArrayList<Question> getallQuestions(String quizType,String topic, int qnPerTopic) throws SQLException {
        switch (quizType) {
            case "MCQ":
                return getMCQQuestions(topic, qnPerTopic);
           case "Open":
                return getOpenQuestions(topic, qnPerTopic);
//         case "Associative":
//              return getAssociativeQuestions(topic, qnPerTopic);
            default:
                return null;
        }


    }

    private ArrayList<Question> getMCQQuestions(String topic, int qnPerTopic) throws SQLException {

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
    private ArrayList<Question> getOpenQuestions(String topic, int qnPerTopic) throws SQLException {

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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return questions;
    }
//    private ArrayList<Question> getAssociativeQuestions(String topic, int qnPerTopic) throws SQLException {
//
//        String SQL = "SELECT * FROM fill_in_the_blank where topic ='"+topic+"'order by random() limit "+qnPerTopic;
//        ArrayList<Question> questions = new ArrayList<Question>();    // ArrayList to store questions
//        try (Connection conn = connect();
//             Statement stmt = conn.createStatement();
//             ResultSet rs = stmt.executeQuery(SQL)) {
//            while (rs.next()) {
//                OpenQuestion question = new OpenQuestion();
//                //question.setDescription(rs.getString("hint"));
//                question.setTitle(rs.getString("Question"));
//                question.setTopic(rs.getString("topic"));
//                question.setAnswer(rs.getString("answer"));
//                questions.add(question);
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        return questions;
//    }


}



