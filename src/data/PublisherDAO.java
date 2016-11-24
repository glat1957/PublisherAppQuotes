// Giorgio Latour
// Publisher App for Quotations
// IHRTLUHC
package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PublisherDAO {

    Connection connection;
    Statement stmt;
    String currentUser;
    PreparedStatement pstmt;

    public Boolean logIn(String username, String password) {
        connectDB();

        // Determine if user exists and log in.
        Boolean success = false;
        currentUser = username;
        try {
            String logInQuerySQL = "select username from users where username= ?"
                    + "and password= ?";

            pstmt = connection.prepareStatement(logInQuerySQL);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rset = pstmt.executeQuery();

            if (rset.next()) {
                success = true;
            }

        } catch (SQLException ex) {
            return success;
        }
        return success;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public ArrayList<String> getCategories() {
        ArrayList<String> categoriesList = new ArrayList<>();
        try {
            // Get categories from database.
            String getCategoriesSQL = "select categoryname from categories";
            ResultSet categories = stmt.executeQuery(getCategoriesSQL);

            // Add the category names to an array list. The categories are stored
            // in a column so we have to move to the next row after adding each 
            // category to the arraylist.
            while (categories.next()) {
                categoriesList.add(categories.getString(1));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return categoriesList;
    }

    public void publishQuote(String quote, String username, String category) {
        try {
            String publishQuoteSQL = "insert into quotes (quote, username, category)"
                    + " values(?, ?, ?)";
            pstmt = connection.prepareStatement(publishQuoteSQL);
            pstmt.setString(1, quote);
            pstmt.setString(2, username);
            pstmt.setString(3, category);

            pstmt.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void connectDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/quotations?user=root&password=cmsc250");
            stmt = connection.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
