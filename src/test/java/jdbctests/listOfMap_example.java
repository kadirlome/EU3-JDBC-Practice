package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMap_example {


    String dbUrl = "jdbc:oracle:thin:@3.93.69.110:1521:xe";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void MetaDataExample() throws SQLException {
        //create coonection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select * from departments");

        //get the resultset object metadata
        ResultSetMetaData rsmetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        Map<String,Object> row1 = new HashMap<>();
        row1.put("first_name","Steven");
        row1.put("last_name","King");
        row1.put("salary",24000);
        row1.put("job_id","AD_PRES");

        System.out.println(row1.toString());

        Map<String,Object> row2 = new HashMap<>();
        row2.put("first_name","Neena");
        row2.put("last_name","kochhar");
        row2.put("salary",17000);
        row2.put("job_id","AD_VP");

        System.out.println(row2.get("Salary"));

        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name fdirectly from the list
        System.out.println(queryData.get(0).get("last_name"));

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }



    @Test
    public void MetaDataExample2() throws SQLException {
        //create coonection
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        //create statement object
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet = statement.executeQuery("select first_name,last_name,salary,job_id from employees where rownum <6");

        //get the resultset object metadata
        ResultSetMetaData rsmetadata = resultSet.getMetaData();

        //list for keeping all rows a map
        List<Map<String,Object>> queryData = new ArrayList<>();

        resultSet.next();
        Map<String,Object> row1 = new HashMap<>();
        row1.put(rsmetadata.getColumnName(1),resultSet.getString(1));
        row1.put(rsmetadata.getColumnName(2),resultSet.getString(2));
        row1.put(rsmetadata.getColumnName(3),resultSet.getString(3));
        row1.put(rsmetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row1.toString());

        resultSet.next();
        Map<String,Object> row2 = new HashMap<>();
        row2.put(rsmetadata.getColumnName(1),resultSet.getString(1));
        row2.put(rsmetadata.getColumnName(2),resultSet.getString(2));
        row2.put(rsmetadata.getColumnName(3),resultSet.getString(3));
        row2.put(rsmetadata.getColumnName(4),resultSet.getString(4));

        System.out.println(row2.toString());
        System.out.println(row2.get("Salary"));

        queryData.add(row1);
        queryData.add(row2);

        //get the steven last name fdirectly from the list
        System.out.println(queryData.get(0).get("last_name"));

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();

    }
}
