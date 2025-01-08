package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:sqlite:students.db";

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL);
            System.out.println("Połączono z bazą danych SQLite!");
            return conn;
        } catch (SQLException e) {
            System.out.println("Nie udało się połączyć z bazą danych: " + e.getMessage());
            return null;
        }
    }
}
