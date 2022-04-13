package com.example.eventmanager.repositories;

import org.springframework.web.context.request.WebRequest;

import javax.xml.crypto.Data;
import java.sql.*;

public class UserRepo {
    private final Connection connection;

    public UserRepo() {
        this.connection = DatabaseConnection.getConnection();
    }

    public int validateUser(String usernameInput, String passwordInput) {
        String query = "SELECT * FROM EventManager.users " +
                "WHERE username = " + "'" + usernameInput + "'" + " AND password = " + "'" + passwordInput + "'" + ";";

        int userId = -1;
        String username = "temp";
        String password = "temp";

        try {
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                userId = rs.getInt(1);
                username = rs.getString(2);
                password = rs.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (username.equals(usernameInput) && password.equals(passwordInput))
            return userId;
         else
            return -1;
    }
}
