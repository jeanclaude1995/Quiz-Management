package fr.epita.quiz.datamodel;


import java.util.ArrayList;
import java.util.List;

public class MCQQuestion extends Question {
    ArrayList<Choice> choices;


    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }
}
