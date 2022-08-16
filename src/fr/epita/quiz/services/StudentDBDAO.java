package fr.epita.quiz.services;

import fr.epita.quiz.datamodel.Student;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class StudentDBDAO {



    public void create(Student student) throws SQLException, IOException {
        Connection connection = getConnection();

        String createTableQuery = "CREATE TABLE IF NOT EXISTS STUDENTS(studentId varchar(255), name varchar(255))";
        connection.prepareStatement(createTableQuery).execute();
        String insertQuery = "INSERT INTO STUDENTS(id, name) values (?, ?)";
        PreparedStatement ps = connection.prepareStatement(insertQuery);
        ps.setString(1, student.getId());
        ps.setString(2, student.getName());
        ps.execute();
        connection.close();
        System.out.println("Student created successfully");
    }
    //update student in database
    public void update(Student student) throws SQLException, IOException {
        Connection connection = getConnection();
        String updateQuery = "UPDATE STUDENTS SET  id = ? WHERE  name = ? ";
        PreparedStatement ps = connection.prepareStatement(updateQuery);
        ps.setString(2, student.getName());
        ps.setString(1, student.getId());
        ps.execute();
        connection.close();
        System.out.println("Student updated successfully");
    }

    public void readAll() throws SQLException, IOException {
        Connection connection = getConnection();

        String sqlQuery = "select * from STUDENTS";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

       ArrayList<Student> students = new ArrayList<Student>();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            String id = resultSet.getString("id");
            students.add(new Student(id, name));
        }
        connection.close();
        System.out.println("Students read successfully");
        for(Student student : students){
            System.out.println("password:"+student.getId() + " " +"userName:"+student.getName());
       }
    }

    public void delete(String  username) throws SQLException, IOException {
        Connection connection = getConnection();
        String sqlQuery = "DELETE FROM STUDENTS WHERE name = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setString(1, username);
        preparedStatement.execute();
        connection.close();
    }

    private Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        FileInputStream in = new FileInputStream("./database.properties");
        props.load(in);
        in.close();
        String url = props.getProperty("dbUrl");
        String user = props.getProperty("dbName");
        String password = props.getProperty("dbPassword");
        return DriverManager.getConnection(url,user,password);
    }


}
