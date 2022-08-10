package fr.epita.quiz.datamodel;

public class Choice {
    String Title=null;
    Boolean isCorrect=null;

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }
}
