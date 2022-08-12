package fr.epita.quiz.services.data.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentQuiz {
    public String getQuizTypeFromStudent(Scanner scan) {
        //get quiz type from the student
        System.out.println("a. MCQ \nb. Open \nc. Associative\n");
        System.out.println("Enter the quiz type: ");
        String quizType = scan.nextLine();
            switch (quizType) {
                case "a":
                    System.out.println("You have selected MCQ Question");
                    return "MCQ";
                case "b":
                    System.out.println("You have selected Open Question");
                    return "Open";
                case "c":
                    System.out.println("You have selected Associative Question");
                    return "Associative";
                default:
                    System.out.println("You have selected wrong quiz type");
                    return null;
            }

    }
    public int getTotalQuestionsCountFromStudent(Scanner scanner) {
        //scanner to get the total questions count from the student
        System.out.println("Please enter the total questions count: ");
        String totalQuestionsCount = scanner.nextLine();
        return Integer.parseInt(totalQuestionsCount);
    }
    public ArrayList<String> getAllTopicsFromStudent(Scanner scanner) {
        //scanner to get the topics from the student
        System.out.println("Please enter the topics in Uppercase with space one the same line: ");
        String topics = scanner.nextLine();
        String[] topicsArray = topics.split(" ");
        ArrayList<String> topicsList = new ArrayList<String>();
        for (String topic : topicsArray) {
            topicsList.add(topic);
        }
        return topicsList;
    }
}
