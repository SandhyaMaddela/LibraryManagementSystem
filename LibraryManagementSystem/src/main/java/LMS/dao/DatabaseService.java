package LMS.dao;
import java.sql.*;

public class DatabaseService {
    public static Connection conn;

    private static Connection createConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "Sanju@14");

        System.out.println("Database connection created successfully.");

        return conn;
    }

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (conn == null) {
            return createConn();
        }

        return conn;
    }
}
