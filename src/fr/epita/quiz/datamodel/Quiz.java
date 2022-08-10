package fr.epita.quiz.datamodel;

import fr.epita.quiz.launcher.QuestionGenerator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Quiz {
    String title = null;
    String[] topics = null;
    Integer numberOfQuestions = null;

    public ArrayList<Question> getQuestions( String quizeType,List<String> topics, int numberOfQuestions) throws SQLException {
        QuestionGenerator questionGenerator = new QuestionGenerator();
        int QuestionPerTopic = numberOfQuestions / topics.size();
        ArrayList<Question> result = new ArrayList();
        for (String topic : topics) {
            result.addAll(questionGenerator.getallQuestions(quizeType,topic, QuestionPerTopic));
        }
        return result;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }
}
