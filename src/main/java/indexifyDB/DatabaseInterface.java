package indexifyDB;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

//This file contains all the methods that are needed from the main program by sending queries to the SQLite database
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
     * Insert a new user into the users table with the credentials listed below
     *
     * @param username  User's preferred username, must be unique
     * @param password  Password for user, must be <= 50 chars
     * @param securityQuestion  Determines which security question user selected
     * @param answer    Answer to the selected security question
     */
    public boolean newUser(String username, String password, int securityQuestion, String answer) {
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
            return false;
        }
        return true;
    }


    /**
     * This is a helper function that checks if the info passed in is valid or not for resetting a password
     *
     * @param username  Possible username for user
     * @param answer    Users given answer for security question
     * @return true if a user exists in the database with these credentials, return false if bad info passed
     */
    private boolean verifyUserAnswer(String username, String answer) {
        String sql = "SELECT * FROM users WHERE username = ? AND answer = ?";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, answer);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    /**
     * Change the password for an existing user using their security question as verification
     *
     * @param username  User that wants their password change
     * @param newPassword   User's new password of choice
     * @param answer    Security question answer to verify user's identity
     */
    public boolean resetPassword(String username, String newPassword, String answer) {
        if (!verifyUserAnswer(username, answer)) return false;  //If user and answer or not in the database, bad input received so exit
        String sql = "UPDATE users SET password = ? WHERE username = ? AND answer = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPassword);
            pstmt.setString(2, username);
            pstmt.setString(3, answer);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * Adds a course for the user into the database
     *
     * @param username  User that is adding a new course
     * @param courseName    Name of said course
     * @return  false if process is unsuccessful
     */
    public boolean addCourse(String username, String courseName) {
        String sql = "INSERT INTO courses VALUES(?,?)";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, courseName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     *  Similar to the verifyUserAnswer helper function, just check to see if the data is dirty or not for the coursename
     *
     * @param username The user that is renaming/deleting the course
     * @param courseName The course that they would like to enter
     * @return false if no row fits the criteria
     */
    private boolean verifyUserCourse(String username, String courseName) {
        String sql = "SELECT * FROM courses WHERE username = ? AND courseName = ?";
        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, courseName);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return false;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }


    /**
     * This function renames a user's course
     *
     * @param username  The user that wants to rename their course
     * @param oldCourseName The original name of the course
     * @param newCourseName The name that the course will be switched to
     * @return false if process fails for some reason
     */
    public boolean renameCourse(String username, String oldCourseName, String newCourseName) {
        if (!verifyUserCourse(username, oldCourseName)) return false;   //If data is not found in db, exit the function
        String sql = "UPDATE courses SET courseName = ? WHERE username = ? AND courseName = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newCourseName);
            pstmt.setString(2, username);
            pstmt.setString(3, oldCourseName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * This function deletes a user's course
     *
     * @param username The user that wants to delete the course
     * @param courseName The name of course to be deleted
     * @return false if process fails for any reason
     */
    public boolean deleteCourse(String username, String courseName) {
        if (!verifyUserCourse(username, courseName)) return false;  //If data is not found in db, exit the function
        String sql = "DELETE FROM courses WHERE username = ? AND courseName = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, courseName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }


    /**
     * This function checks the username and password to see if that user exists in the db
     *
     * @param username Proposed user
     * @param password User's password
     * @return true if user and password work
     */
    public boolean login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (!rs.next()) return false;   //If no results return with username and password
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }


    /**
     * Grabs all the courses that a user has
     *
     * @param username The user that we are grabbing the courses from
     * @return a list of all the courses
     */
    public ArrayList<String> getCourses(String username) {
        String sql = "SELECT * FROM courses WHERE username = ?";
        ArrayList<String> allCourses = new ArrayList<String>();

        try (Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                allCourses.add(rs.getString("courseName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return allCourses;
    }

    /* Tests for functions
    public static void main(String[] args) {

        DatabaseInterface app = new DatabaseInterface();
        // insert three new rows
        /*
        app.newUser("H2Shami", "bruhgamermode", 1, "Qazi");
        app.newUser("Shamirock", "naruto4ever", 2, "Pheobe");
        app.newUser("a1shami", "12345678", 3, "Tracy");
        if (app.login("H2Shami", "gamerode")) System.out.println("Login Success");
        else System.out.println("Login Failed");

        //Attempt password Change with incorrect security question answer
        //if(app.resetPassword("H2Shami", "notgamermode", "Q")) System.out.println("Password Change Success");
        //else System.out.println("Password Change Failed");
        //app.resetPassword("H2Shami", "notgamermode", "Qazi");

        //Adding courses
        if(app.addCourse("H2Shami", "CS151")) System.out.println("Course Added");
        else System.out.println("Course not Added");
        if(app.addCourse("H2Shami", "CMPE148")) System.out.println("Course Added");
        else System.out.println("Course not Added");
        if(app.addCourse("H2Shami", "CMPE102")) System.out.println("Course Added");
        else System.out.println("Course not Added");
        //Renaming Courses
        if(app.renameCourse("H2Shami", "CMPE131", "CMPE")) System.out.println("Course name Changed");
        else System.out.println("Course name not Changed");
        if(app.renameCourse("H2Shami", "CMPE148", "CMPE131")) System.out.println("Course name Changed");
        else System.out.println("Course name not Changed");
        if(app.renameCourse("H2Shami", "CS151", "CS157A")) System.out.println("Course name Changed");
        else System.out.println("Course name not Changed");
        //Delete Course
        if(app.deleteCourse("H2Shami", "CS")) System.out.println("Course Deleted");
        else System.out.println("Course not Deleted");
        if(app.deleteCourse("H2Shami", "CS157A")) System.out.println("Course Deleted");
        else System.out.println("Course not Deleted");
        ArrayList<String> courses = new ArrayList<String>();
        courses = app.getCourses("H2Shami");
        for (String i: courses) System.out.println(i);
    }
     */
}
