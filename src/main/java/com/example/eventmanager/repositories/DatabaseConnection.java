package com.example.eventmanager.repositories;

import java.sql.*;

public class DatabaseConnection {
    private static Connection conn;

        static {
            try{
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/EventManager","root","keatest");
                /*PreparedStatement psts = conn.prepareStatement("SELECT * from users");
                ResultSet resultSet = psts.executeQuery();*/

            }catch(SQLException e){
                System.out.println("Cannot connect to database");
                e.printStackTrace();
            }
        }


    public static Connection getConnection() {
            return conn;
    }
}

