package com.lomgfei.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
    public static Connection getConnection() throws SQLException {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://106.54.242.90:3306/crm1";
        Connection conn = null;
        String name = "web01";
        String passswd = "web01";
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, name, passswd);
            if (!conn.isClosed()) {
                System.out.println("Succeeded connecting to the Database!");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
