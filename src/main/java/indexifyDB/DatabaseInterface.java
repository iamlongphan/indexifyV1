package indexifyDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author sqlitetutorial.net
 */
public class DatabaseInterface {

    /**
     * Connect to the test.db database
     *
     * @return the Connection object
     */
    private Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:C:/sqlite/db/indexify.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    /**
     * Insert a new row into the users table
     *
     * @param username
     * @param password
     * @param securityQuestion
     * @param answer
     */
    public void newUser(String username, String password, int securityQuestion, String answer) {
        String sql = "INSERT INTO users(username,password, question, answer) VALUES(?,?,?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setInt(3, securityQuestion);
            pstmt.setString(4, answer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void print() {
        String sql = "SELECT * FROM users";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        DatabaseInterface app = new DatabaseInterface();
        // insert three new rows
        app.newUser("H2Shami", "gamermode", 1, "Qazi");
        app.newUser("Shamirock", "naruto4ever", 2, "Pheobe");
        app.newUser("a1shami", "12345678", 3, "Tracy");

        app.print();
    }

}
