package fr.epita.quiz.launcher;

import fr.epita.quiz.datamodel.Question;
import fr.epita.quiz.services.MCQDAO;
import fr.epita.quiz.services.OpenQuestionDAO;

import java.sql.*;
import java.util.ArrayList;

public class QuestionGenerator {
    public Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5433/Demo", "postgres", "jean");
    }


    public ArrayList<Question> getallQuestions(String quizType, String topic, int qnPerTopic) throws SQLException {
        switch (quizType) {
            case "MCQ":
                MCQDAO mcqDAO = new MCQDAO();
                return mcqDAO.getMCQQuestions(topic, qnPerTopic);
            case "Open":
                OpenQuestionDAO openQuestionDAO = new OpenQuestionDAO();
                return openQuestionDAO.getOpenQuestions(topic, qnPerTopic);
            default:
                return null;
        }


    }
}

