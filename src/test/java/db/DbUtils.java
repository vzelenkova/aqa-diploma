package db;

import java.sql.*;

public class DbUtils {
    private static String url = System.getProperty("db.url");
    private static String user = System.getProperty("db.user");
    private static String password = System.getProperty("db.password");

    public static void clearTables() throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM payment_entity");
            stmt.executeUpdate("DELETE FROM credit_request_entity");
        }
    }

    public static String getLastStatus(String table) throws SQLException {
        String query = String.format("SELECT status FROM %s ORDER BY created DESC LIMIT 1", table);
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            if (rs.next()) return rs.getString("status");
        }
        return null;
    }
}
