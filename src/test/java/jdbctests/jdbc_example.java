package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {

    String dbUrl = "jdbc:oracle:thin:@3.93.69.110:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void test1() throws SQLException {
        //create coonection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //how to find how many rows we have for the query
        //go to last row
        resultSet.last();
        //get the row count
        int rowCount = resultSet.getRow();
        System.out.println(rowCount);
        //we need move before first row to get full list since we are at he last row right now.
        resultSet.beforeFirst();

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }


        resultSet = statement.executeQuery("select * from address");

        while(resultSet.next()){
            System.out.println(resultSet.getString(2));

        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }



    @Test
    public void MetaData() throws SQLException {
        //create coonection
        Connection connection = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");


        //get the database related data inside the dbMetaData object
        DatabaseMetaData dbMetadata = connection.getMetaData();

        System.out.println("User =" + dbMetadata.getUserName());
        System.out.println("Database Product Name = " + dbMetadata.getDatabaseProductName());
        System.out.println("Database Product Version = " + dbMetadata.getDatabaseProductVersion());
        System.out.println("Driver Name = " + dbMetadata.getDriverName());
        System.out.println("Driver Version = " + dbMetadata.getDriverVersion());



        //get the resultset object metadata
        ResultSetMetaData rsmetadata = resultSet.getMetaData();

        //how many colums we have ?
        int colCount = rsmetadata.getColumnCount();
        System.out.println(colCount);

        //print all the columns name dynamically
        for(int i=1;i<=colCount;i++){
            System.out.println(rsmetadata.getColumnName(i));
        }



//        //Columns name
//        System.out.println(rsmetadata.getColumnName(1));
//        System.out.println(rsmetadata.getColumnName(2));




        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }



}