package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class AdminLogin {
    public boolean getAdminData(Scanner scanner) throws SQLException, IOException {
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
        //authenticate students from student table in the database

        if (!authenticated) {
            // we leave
            System.out.println("not authenticated, bye!");
            return false;
        }

        System.out.println("credentials ok, welcome!");
        String userResponse = "";
        while (!"q".equals(userResponse)) {
            System.out.println("What operation would you like to do?");
            System.out.println("c. create a student?");
            System.out.println("u. update a student?");
            System.out.println("r. read all students?");
            System.out.println("d. delete a student?");
            System.out.println("q. operations on quiz");
            System.out.println("e. exit");
            System.out.print("type your choice : ");
            StudentDBDAO studentDBDAO = new StudentDBDAO();
            userResponse = scanner.nextLine();
            switch (userResponse) {
                case "c":
                    //create a student
                    System.out.println("please enter the Name: ");
                    String studentName = scanner.nextLine();
                    System.out.println("please enter the StudentId: ");
                    String studentId = scanner.nextLine();
                    studentDBDAO.create(new Student(studentId, studentName));
                    break;
                case "u":
                    //update a student
                    System.out.println("please enter the userName who need to update: ");
                    String studentName1 = scanner.nextLine();
                    System.out.println("please enter the newStudentId: ");
                    String studentId1 = scanner.nextLine();
                    studentDBDAO.update(new Student(studentId1, studentName1));
                    break;
                case "r":
                    //read all students
                    studentDBDAO.readAll();
                    break;

                case "d":
                    //delete a student
                    System.out.println("please enter the UserName that you want to delete: ");
                    String Name = scanner.nextLine();
                    studentDBDAO.delete(Name);

                    break;
                case "q":
                    break;
                 case "e":
                     System.out.println("bye!");
                     return false;
                default:
                    System.out.println("invalid option, retry");
                    break;
            }
        }
        return true;
    }
        private static Properties getProperties () {
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



