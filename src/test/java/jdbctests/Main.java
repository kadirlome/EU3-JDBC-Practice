package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:oracle:thin:@3.93.69.110:1521:xe";
        String dbUsername = "hr";
        String dbPassword = "hr";

        //create coonection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement();
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from address");

        //move pointer to first row
       //+ resultSet.next();

//        //getting informations with column name
//        System.out.println(resultSet.getString("region_name"));
//        //getting informaton with column index (starts from 1)
//        System.out.println(resultSet.getString(2));
//
//        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString("region_name"));
//
//        //move printer the second row
        resultSet.next();
//
//        System.out.println(resultSet.getInt(1)+" - "+resultSet.getString("region_name"));
//

        System.out.println(resultSet.getString("phone"));


        while(resultSet.next()){
            System.out.println(resultSet.getString(1)+" - "+resultSet.getString(2));
        }







        //close akk connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
