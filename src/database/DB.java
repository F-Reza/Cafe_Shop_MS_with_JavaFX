/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 *
 * @author F_Reza
 */

public class DB {
    public Connection connection;
    private PreparedStatement prepare;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private final Image placeholderImage = new Image("file:item_image_GoPpo.jpeg"); 

    public void getConnection() {

        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:sqlite:db/GoPpo_MS.db"); //"org.sqlite.JDBC" "jdbc:sqlite:"
                logger.info("Conneted to database!");
                createTable();
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
    }

    private void createTable() {
        //getConnection();

        String queryA = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL, "
                + "question TEXT NOT NULL, "
                + "answer TEXT NOT NULL, "
                + "user_role TEXT NOT NULL, "
                + "status BLOB NOT NULL, "
                + "date NUMERIC)";
        
        String queryB = "CREATE TABLE IF NOT EXISTS items ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "items_code TEXT NOT NULL, "
                + "items_name TEXT NOT NULL, "
                + "category TEXT NOT NULL, "
                + "size TEXT, "
                + "stock INTEGER NOT NULL, "
                + "unit_price REAL NOT NULL, "
                + "status TEXT NOT NULL, "
                + "image TEXT NOT NULL, "
                + "date NUMERIC)";


        String queryC = "CREATE TABLE IF NOT EXISTS expenses ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "ex_amount REAL NOT NULL, "
                + "ex_category TEXT NOT NULL, "
                + "ex_description TEXT NOT NULL, "
                + "ex_by TEXT NOT NULL, "
                + "ex_date NUMERIC)";

        
        try (PreparedStatement statement = connection.prepareStatement(queryA)) {
            statement.executeUpdate();

            // Check if the admin user already exists
            String checkQuery = "SELECT COUNT(*) FROM users WHERE username = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, "a");
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) == 0) {
                        // If admin doesn't exist, insert admin user
                        String insertQuery = "INSERT INTO users (username, password, question, answer, user_role, status, date) VALUES (?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                            insertStatement.setString(1, "a");
                            insertStatement.setString(2, "a"); //password123
                            insertStatement.setString(3, "What is your favorite person?");
                            insertStatement.setString(4, "admin");
                            insertStatement.setString(5, "Admin");
                            insertStatement.setBoolean(6, true);
                            insertStatement.setLong(7, System.currentTimeMillis());

                            insertStatement.executeUpdate();
                            System.out.println("-> Initial admin user created.\n------------------------------------------------");
                        }
                    } else {
                        System.out.println("-> Admin user already exists.\n------------------------------------------------");
                    }
                }
            }

        } catch (SQLException e) {
            logger.info(e.toString());
            //e.printStackTrace();
        }
        
        try (PreparedStatement statementB = connection.prepareStatement(queryB)) {
            statementB.executeUpdate();
        } catch (Exception e) {
            logger.info(e.toString());
            //e.printStackTrace();
        }
        try (PreparedStatement statementC = connection.prepareStatement(queryC)) {
            statementC.executeUpdate();
        } catch (Exception e) {
            logger.info(e.toString());
            //e.printStackTrace();
        }

        
    }
    
    public void getDateFormate() throws SQLException {
        long currentTimeInMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:aa");    
        Date resultdate = new Date(currentTimeInMillis);
        
        System.out.println(sdf.format(resultdate.getTime()));      
    }
    
    
    
    private void closeConnection() throws SQLException {
        if(connection != null || !connection.isClosed()) {
            connection.close();
        }
    }
    
    
    // Get Expenses Data Query Section
    
    public double getTodayExpenses() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE DATE(datetime(ex_date / 1000, 'unixepoch')) = DATE('now')");
    }

    public double getYesterdayExpenses() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE DATE(datetime(ex_date / 1000, 'unixepoch')) = DATE('now', '-1 day')");
    }

    public double getThisWeekExpenses() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE strftime('%Y-%W', datetime(ex_date / 1000, 'unixepoch')) = strftime('%Y-%W', 'now')");
    }

    public double getThisMonthExpenses() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE strftime('%Y-%m', datetime(ex_date / 1000, 'unixepoch')) = strftime('%Y-%m', 'now')");
    }

    public double getThisYearExpenses() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE strftime('%Y', datetime(ex_date / 1000, 'unixepoch')) = strftime('%Y', 'now')");
    }

    public double getTotalExpenses() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses");
    }

    private double getExpenseSum(String query) {
        double total = 0.0;
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return total;
    }
    
    
    
    public double getExpensesForDate(long selectedDateMillis) {
    // Convert selectedDateMillis to the start and end of the day (milliseconds)
    long dayStartMillis = selectedDateMillis;
    long dayEndMillis = selectedDateMillis + (24 * 60 * 60 * 1000) - 1;

    String query = "SELECT SUM(ex_amount) FROM expenses WHERE ex_date BETWEEN ? AND ?";
    double total = 0.0;

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setLong(1, dayStartMillis);
        pstmt.setLong(2, dayEndMillis);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Add your logger here if you wish
    }
    return total;
    }
    
    
    public double getExpensesForDateRange(long startMillis, long endMillis) {
    String query = "SELECT SUM(ex_amount) FROM expenses WHERE ex_date BETWEEN ? AND ?";
    double total = 0.0;

    try (PreparedStatement pstmt = connection.prepareStatement(query)) {
        pstmt.setLong(1, startMillis);
        pstmt.setLong(2, endMillis);

        try (ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getDouble(1);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();  // You can use a logger instead if preferred
    }
    return total;
    }

    
       
    //End Expenses Data Query Section
    
    

}
