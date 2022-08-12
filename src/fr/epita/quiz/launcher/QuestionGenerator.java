package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.datamodel.OpenQuestion;
import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.data.dao.MCQDAO;
import fr.epita.quiz.services.data.dao.OpenQuestionDAO;

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
                MCQDAO mcqDAO = new MCQDAO();
                return mcqDAO.getMCQQuestions(topic, qnPerTopic);
           case "Open":
                OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                return openQuestionDAO.getOpenQuestions(topic, qnPerTopic);
//         case "Associative":
//              return getAssociativeQuestions(topic, qnPerTopic);
            default:
                return null;
        }


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



