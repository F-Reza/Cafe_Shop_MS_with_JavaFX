/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DB;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import models.InvoiceDataModel;


/**
 *
 * @author F_Reza
 */
public class CullectBillController implements Initializable{
    private final DB db = new DB();
    private PreparedStatement prepare;
    private Statement statement;
    private Alert alert;
    private String inv_date;
    int xID = 0;
    String invID = "";
    String xBillAmount = "";
    
    @FXML private AnchorPane orderBillCard;
    @FXML private Button card_completeBtn;
    @FXML private Label card_dateTime, card_invoiceID, card_paybleAmount;
    
    public void setData(InvoiceDataModel invData) {
        long millis = invData.getDate().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy -- hh:mm:aa");  
        Date resultdate = new Date(millis);
        inv_date = sdf.format(resultdate.getTime());
        //inv_date = String.valueOf(invData.getDate()); 
        card_dateTime.setText(inv_date);
        card_invoiceID.setText(invData.getInvID());
        card_paybleAmount.setText(String.format("৳ %.2f Tk", invData.getGrandTotal()));
        
        xID = invData.getId();
        invID = invData.getInvID();
        xBillAmount = String.format("%.2f Tk", invData.getGrandTotal());
    }
    
    public void completePaymentInvoice() {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to Complete Bill Payment "+xBillAmount+"\nWhose Invoice ID:-> "+invID+" ?");
    Optional<ButtonType> option = alert.showAndWait();

        // If the user confirms the deletion
        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            updatePaymentStatus(xID);
            System.out.println("Action: Done! "+xID); 
        } else {
            System.out.println("Action Canceled!");
        }
    }
    public void updatePaymentStatus(int id) {
        db.getConnection();
        String sql = "UPDATE invoices SET payment_status = ? WHERE id = ?";
        try {
            //db.getConnection();
            prepare = db.connection.prepareStatement(sql);
            prepare.setString(1, "Complete");
            prepare.setInt(2, id);

            prepare.executeUpdate();
            System.out.println("Payment status updated successfully");
            
        } catch (SQLException e) {
            //e.printStackTrace();
            System.out.println("Error: " +e);
        }
    }
    
    public void completeBtn() {
        completePaymentInvoice();
        
        // Refresh the bill list in MainFormController after completing payment
        if (mainFormController != null) {
            mainFormController.billDisplayCard();  // Refresh the list
        }
    }
  
    private MainFormController mainFormController;
    public void setMainFormController(MainFormController mainFormController) {
        this.mainFormController = mainFormController;
    } 
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }
    
}


//try {
//    db.closeConnection();
//    System.out.println("Database connection closed on View Invoice close.");
//} catch (SQLException e) {
//    System.out.println("Error closing database connection: " + e.getMessage());
//    e.printStackTrace();
//}