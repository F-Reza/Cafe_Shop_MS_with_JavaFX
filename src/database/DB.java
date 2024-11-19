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
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import models.InvoiceDataModel;
import models.ItemsDataModel;
import models.UserDataModel;

/**
 *
 * @author F_Reza
 */

public class DB {
    public Connection connection;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private final Image placeholderImage = new Image("file:../imgusers.jpg"); 

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
    
    public void closeConnection() throws SQLException {
        if(connection != null || !connection.isClosed()) {
            connection.close();
        }
    }
    
    public void getDateFormate() throws SQLException {
        long currentTimeInMillis = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd,yyyy HH:mm:aa");    
        Date resultdate = new Date(currentTimeInMillis);
        
        System.out.println(sdf.format(resultdate.getTime()));      
    }
    

    private void createTable() {
        //getConnection();

        String queryA = "CREATE TABLE IF NOT EXISTS admin ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL, "
                + "display_name TEXT NOT NULL, "
                + "question TEXT NOT NULL, "
                + "answer TEXT NOT NULL, "
                + "user_role TEXT NOT NULL, "
                + "status TEXT NOT NULL, "
                + "image TEXT NOT NULL, "
                + "date NUMERIC)";
        
        String queryB = "CREATE TABLE IF NOT EXISTS employees ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL, "
                + "user_role TEXT NOT NULL, "
                + "status TEXT NOT NULL, "
                + "date NUMERIC)";
        
        String queryC = "CREATE TABLE IF NOT EXISTS items ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "items_name TEXT NOT NULL, "
                + "category TEXT NOT NULL, "
                + "size TEXT, "
                + "unit_price REAL NOT NULL, "
                + "status TEXT NOT NULL, "
                + "image TEXT NOT NULL, "
                + "date NUMERIC)";
        
        String queryD = "CREATE TABLE IF NOT EXISTS invoices ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "inv_id TEXT NOT NULL, "
                + "items TEXT NOT NULL, "
                + "subtotal REAL NOT NULL, "
                + "discount REAL, "
                + "others_charge REAL, "
                + "grand_total REAL NOT NULL, "
                + "total_qty INTEGER NOT NULL, "
                + "note TEXT, "
                + "order_type TEXT, "
                + "served_by TEXT, "
                + "bill_by TEXT, "
                + "payment_status TEXT, "
                + "date NUMERIC)";

        String queryE = "CREATE TABLE IF NOT EXISTS expenses ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "ex_amount REAL NOT NULL, "
                + "ex_category TEXT NOT NULL, "
                + "ex_description TEXT NOT NULL, "
                + "ex_by TEXT NOT NULL, "
                + "ex_date NUMERIC)";

        
        try (PreparedStatement statement = connection.prepareStatement(queryA)) {
            statement.executeUpdate();

            // Check if the admin user already exists
            String checkQuery = "SELECT COUNT(*) FROM admin WHERE username = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, "a");
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next() && resultSet.getInt(1) == 0) {
                        // If admin doesn't exist, insert admin user
                        String insertQuery = "INSERT INTO admin (username, password, display_name, question, answer, user_role, status, image, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                            insertStatement.setString(1, "a"); //username
                            insertStatement.setString(2, "a"); //password123
                            insertStatement.setString(3, "Set Your Name"); //display_name
                            insertStatement.setString(4, "What is your favorite person?");
                            insertStatement.setString(5, "admin"); //answer
                            insertStatement.setString(6, "Admin"); //user_role
                            insertStatement.setString(7, "Active"); //status
                            insertStatement.setString(8, "..\\img\\users.jpg"); //image
                            insertStatement.setLong(9, System.currentTimeMillis()); //date

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
        try (PreparedStatement statementD = connection.prepareStatement(queryD)) {
            statementD.executeUpdate();
        } catch (Exception e) {
            logger.info(e.toString());
            //e.printStackTrace();
        }        
        try (PreparedStatement statementE = connection.prepareStatement(queryE)) {
            statementE.executeUpdate();
        } catch (Exception e) {
            logger.info(e.toString());
            //e.printStackTrace();
        }

        
    }
    
    
    //ADMIN GET DATA SECTION
    public UserDataModel getAdminUserData(int adminId) {
        UserDataModel user = null;
        String query = "SELECT * FROM admin WHERE id = ?";

        try {
            getConnection(); // Ensure connection is established
            prepare = connection.prepareStatement(query);
            prepare.setInt(1, adminId);
            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                user = new UserDataModel(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getString("display_name"),
                    resultSet.getString("question"),               
                    resultSet.getString("answer"),
                    resultSet.getString("user_role"),
                    resultSet.getString("status"),
                    resultSet.getString("image"),
                    new Date(resultSet.getLong("date")) // Assuming 'date' is stored in milliseconds
                );
            }

        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return user;
    }
    
    public ObservableList<ItemsDataModel> itemsDataList() {
        
        ObservableList<ItemsDataModel> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM items";
        
        getConnection();

        try {
            prepare = connection.prepareStatement(sql);
            result = prepare.executeQuery();

            ItemsDataModel itemData;

            while (result.next()) {

                itemData = new ItemsDataModel(
                        result.getInt("id"),
                        result.getString("items_name"),
                        result.getString("category"),
                        result.getString("size"),
                        result.getDouble("unit_price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(itemData);

            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    
    public InvoiceDataModel getInvoiceById(int invID) {
        InvoiceDataModel invoice = null;
        String query = "SELECT * FROM invoices WHERE id = ?";

        try {
            getConnection();
            prepare = connection.prepareStatement(query);
            prepare.setInt(1, invID);
            ResultSet resultSet = prepare.executeQuery();

            if (resultSet.next()) {
                invoice = new InvoiceDataModel(
                    resultSet.getInt("id"),
                    resultSet.getString("inv_id"),
                    resultSet.getString("items"),
                    resultSet.getDouble("subtotal"),
                    resultSet.getDouble("discount"),
                    resultSet.getDouble("others_charge"),
                    resultSet.getDouble("grand_total"),
                    resultSet.getInt("total_qty"),
                    resultSet.getString("note"),
                    resultSet.getString("order_type"),
                    resultSet.getString("served_by"),
                    resultSet.getString("bill_by"),
                    resultSet.getString("payment_status"),
                    resultSet.getDate("date")
                );
            }

        } catch (SQLException e) {
            logger.info(e.toString());
        }

        return invoice;
    }
    // Method to get the total count of invoices
    public int getTotalInvoice() {
        return getInvoiceSum("SELECT COUNT(*) FROM invoices");
    }
    public int getTotalPendingInvoice() {
        return getInvoiceSum("SELECT COUNT(*) FROM invoices WHERE payment_status = 'Pending'");
    } 
    public int getTotalCompleteInvoice() {
        return getInvoiceSum("SELECT COUNT(*) FROM invoices WHERE payment_status = 'Complete'");
    } 
    public double getTotalInvoicePendingAmount() {
        return getInvoiceSum("SELECT SUM(grand_total) FROM invoices WHERE payment_status = 'Pending'");
    }
    private int getInvoiceSum(String query) {
        int total = 0;
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            logger.info(e.toString());
        }
        return total;
    }
    // End Method to get the total count of invoices
    
    // Get Expenses Data Query Section
    
    public double getTodayExpenses1() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE DATE(datetime(ex_date / 1000, 'unixepoch')) = DATE('now')");
    }
    public double getYesterdayExpenses1() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE DATE(datetime(ex_date / 1000, 'unixepoch')) = DATE('now', '-1 day')");
    }
    
    public double getTodayExpenses() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE DATE(datetime(ex_date / 1000, 'unixepoch', 'localtime')) = DATE('now', 'localtime')");
    }
    public double getYesterdayExpenses() {
        return getExpenseSum("SELECT SUM(ex_amount) FROM expenses WHERE DATE(datetime(ex_date / 1000, 'unixepoch', 'localtime')) = DATE('now', '-1 day', 'localtime')");
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
