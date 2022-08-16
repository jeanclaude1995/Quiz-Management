package fr.epita.quiz.tests;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import static java.lang.System.getProperties;

public class TestDBConnection {

    public static void main(String[] args) throws SQLException, IOException {
        //testSimpleConnectionAndSelect();

        // when
        /*?currentSchema=public to be appended to the connection url to specify the schema*/

        Connection connection = connect();


        String createTableQuery = "CREATE TABLE IF NOT EXISTS STUDENTS( id varchar(30), name varchar(255))";

        connection.prepareStatement(createTableQuery).execute();

        String insertQuery = "INSERT INTO STUDENTS(id, name) values ('thomas@epita.fr', 'thomas')";

        connection.prepareStatement(insertQuery).execute();
        connection.close();

    }

    private static void testSimpleConnectionAndSelect() throws SQLException, IOException {
        //given


        // when
        Connection connection = connect();
        String schema = connection.getSchema();
        System.out.println(schema);

        String sqlQuery = "select * from customers";

        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            System.out.println(resultSet.getString("customer_id"));
        }

        //then

        //cleanup
        connection.close();
    }
    public static Connection connect() throws IOException, SQLException {
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

