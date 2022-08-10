package fr.epita.quiz.services.data.dao;

import fr.epita.quiz.datamodel.Choice;
import fr.epita.quiz.datamodel.MCQQuestion;
import fr.epita.quiz.datamodel.OpenQuestion;
import fr.epita.quiz.datamodel.Question;

import java.io.*;
import java.util.Scanner;

public class QuizEvaluation {
    public Boolean displayAndGetAnswer(PrintWriter pw,Integer position,Question question) throws IOException {
        if (question instanceof MCQQuestion) {
            System.out.println(position + ") " +question.getTitle());
            //System.out.println(question.getDescription());
            return displayAndGetAnswersForMCQ((MCQQuestion) question,pw);
        }
        else if(question instanceof OpenQuestion) {
            System.out.println(position + ") " +question.getTitle());
            //System.out.println(question.getDescription());
            return displayAndGetAnswersForOpen((OpenQuestion) question);
        }
        return null;
    }
    private Boolean displayAndGetAnswersForMCQ(MCQQuestion mcqQuestion,PrintWriter pw) throws IOException {
        for (Choice choice : mcqQuestion.getChoices()) {
            Integer position = mcqQuestion.getChoices().indexOf(choice)+1;
            System.out.println("  ("+position + ") " + choice.getTitle());
            pw.println("  ("+position + ") " + choice.getTitle());
        }
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        String answer = scanner.nextLine();
        switch (answer) {
            case "1":
                return mcqQuestion.getChoices().get(0).getCorrect();
            case "2":
                return mcqQuestion.getChoices().get(1).getCorrect();
            case "3":
                return mcqQuestion.getChoices().get(2).getCorrect();
            case "4":
                return mcqQuestion.getChoices().get(3).getCorrect();
            default:
                return false;
        }

        }
    private Boolean displayAndGetAnswersForOpen(OpenQuestion openQuestion){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your answer: ");
        String answer = scanner.nextLine();
        return answer.equals(openQuestion.getAnswer());
    }

}



