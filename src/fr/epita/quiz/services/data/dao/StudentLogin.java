package fr.epita.quiz.services.data.dao;

import fr.epita.quiz.datamodel.Student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class StudentLogin {
    public  boolean getStudentData(Scanner  scanner) throws SQLException, FileNotFoundException {
        // authentication, it returns a boolean, true if the authentication succeeded
       // Scanner  = new Scanner(System.in);
        Properties properties = getProperties();
        if (properties == null) return false;

        String defaultUserName = properties.getProperty("userName");
        String defaultPassword = properties.getProperty("password");

        System.out.print("Welcome, please enter your name: ");
        String userName = scanner.nextLine();
        System.out.print("please enter your password: ");
        String password = scanner.nextLine();

        boolean authenticated = userName.equals(defaultUserName)
                && password.equals(defaultPassword);

        if (!authenticated) {
            // we leave
            System.out.println("not authenticated, bye!");
            return false;
        }

        System.out.println("credentials ok, welcome!");
        return true;

//        String userResponse = "";
//        while (!"q".equals(userResponse)) {
//            System.out.println("What operation would you like to do?");
//            System.out.println("c. create a student?");
//            System.out.println("u. update a student?");
//            System.out.println("d. delete a student?");
//            System.out.println("q. quit");
//            System.out.println("type your choice (c|u|d|q)");
//
//            userResponse = scanner.nextLine();
//            switch (userResponse) {
//                case "c":
//                    //create a student
//                    ArrayList<Student> students = new ArrayList<>();
//                    System.out.println("please enter the Name: ");
//                    String studentName = scanner.nextLine();
//                    System.out.println("please enter the StudentId: ");
//                    String studentId = scanner.nextLine();
//                    Student student = new Student(studentName, studentId);
//                    students.add(student);
//                    StudentCSVDAO studentCSVDAO = new StudentCSVDAO();
//                    studentCSVDAO.create(student);
//                    break;
//                case "u":
//                    //update
//                    break;
//                case "d":
//                    //delete
//                    break;
//                case "q":
//                    //quit
//                    break;
//                default:
//                    System.out.println("invalid option, retry");
//                    break;
//            }
    }


    //TODO how to deal with resources in that method?
    private static Properties getProperties() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("./credentials.properties"));
        } catch (IOException e) {
            System.out.println("Sorry, the program is not finding the required files, check your setup " +
                    "(authentication is not possible)");
            return null;
        }
        return properties;
    }

}
