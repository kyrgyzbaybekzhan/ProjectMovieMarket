package com.company;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
    private static Connection connection=null;
    private static Statement statement=null;
    private static ResultSet resultSet = null;
    private static String url="jdbc:mysql://localhost/Users1?serverTimezone=Europe/Moscow&useSSL=false";
    private static String user="root";
    private static String password="";

    public static Connection ConnectDb(){
        try {
        Class.forName("com.mysql.jdbc.Driver");

        connection= DriverManager.getConnection(url,user,password);
        return connection;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }


    }

}
