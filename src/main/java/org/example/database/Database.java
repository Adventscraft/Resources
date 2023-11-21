package org.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {

    private static final String JDBC_URL = "jdbc:mysql://url:3306/db?autoReconnect=true";
    private static final String USERNAME = "";
    private static final String PASSWORD = "";

    private Connection connection;

    public void connect() {
        try {
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("Verbindung hergestellt");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Verbindung getrennt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet query(PreparedStatement preparedStatement) {
        try {
            if (connection == null || connection.isClosed()) {
                connect();
            }

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isConnectionValid() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
