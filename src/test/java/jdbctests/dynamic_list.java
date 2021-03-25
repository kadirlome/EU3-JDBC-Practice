package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {


    String dbUrl = "jdbc:oracle:thin:@3.93.69.110:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";


    @Test
    public void Test1() throws SQLException {
        //create coonection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from countries");

        //get the resultset object metadata
        ResultSetMetaData rsmetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String, Object>> queryData = new ArrayList<>();

        int colNum = rsmetadata.getColumnCount();

        //loop throught each  cloumn
        while (resultSet.next()){
            Map<String,Object> row = new HashMap<>();

            //loop for each row
            for (int i = 1; i <colNum; i++) {

                row.put(rsmetadata.getColumnName(i),resultSet.getObject(i));

            }

            //add your map to your list
            queryData.add(row);
        }


        //print the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());

        }


        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }

}
