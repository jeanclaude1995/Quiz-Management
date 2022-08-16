package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class StudentLogin {
    public  boolean getStudentData(Scanner  scanner) throws SQLException, FileNotFoundException {
        // authentication, it returns a boolean, true if the authentication succeeded
        System.out.print("Welcome, please enter your name: ");
        String userName = scanner.nextLine();
        System.out.print("please enter your password: ");
        String password = scanner.nextLine();
        boolean authenticated = getStudent(userName, password);
        if (!authenticated) {
            System.out.println("not authenticated, bye!");
            return false;
        }
        System.out.println("credentials ok, welcome!");
        return true;
    }

    private boolean getStudent(String userName, String password) throws SQLException, FileNotFoundException {//check if the userName and password are available in the database
        //authenticate students from student table in the database
        String query = "SELECT * FROM students WHERE name = '" + userName + "' AND id = '" + password + "'";
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/Demo", "postgres","jean");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            return true;
        }else {
            return false;
        }
    }


}
