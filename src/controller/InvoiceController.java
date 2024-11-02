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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import models.InvoiceDataModel;
import models.InvoiceItem;

/**
 *
 * @author F_Reza
 */
public class InvoiceController implements Initializable{
    private final DB db = new DB();
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    private String inv_date;
    private String inv_time;
    
    
    @FXML private AnchorPane invoice_DataForm;
    @FXML private Label invoice_ID;
    @FXML private Label invoice_Date;
    @FXML private Label invoice_Time;
    @FXML private Label invoice_OrderType;
    @FXML private Label invoice_ServedBy;
    @FXML private Label invoice_BillBy;
    @FXML private Label invoice_Subtotal;
    @FXML private Label invoice_Discount;
    @FXML private Label invoice_OtherCharge;
    @FXML private Label invoice_GrandTotal;
    @FXML private Label invoice_TotalQty;
    @FXML private Text  invoice_Note;
    
    @FXML private TableView<InvoiceItem> invoice_ItemTableView;
    @FXML private TableColumn<InvoiceItem, Integer> invoice_SN;
    @FXML private TableColumn<InvoiceItem, String> invoice_ItemName;
    @FXML private TableColumn<InvoiceItem, Double> invoice_ItemRate;
    @FXML private TableColumn<InvoiceItem, Integer> invoice_ItemQty;
    @FXML private TableColumn<InvoiceItem, Double> invoice_ItemAmount;
    
    
    public InvoiceDataModel getInvoiceById(int invID) {
        InvoiceDataModel invoice = null;
        String query = "SELECT * FROM invoices WHERE id = ?";

        try {
            db.getConnection();
            prepare = db.connection.prepareStatement(query);
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
            System.out.println(e.toString());
        }

        return invoice;
    }
    public void loadInvoiceDataById(int invID) {
    InvoiceDataModel invoice = getInvoiceById(invID);
        if (invoice != null) {
            
            long millis = invoice.getDate().getTime();
            SimpleDateFormat sdfD = new SimpleDateFormat("dd MMM yyyy");
            SimpleDateFormat sdfT = new SimpleDateFormat("hh:mm:aa");  
            Date resultdate = new Date(millis);
            inv_date = sdfD.format(resultdate.getTime());
            inv_time = sdfT.format(resultdate.getTime());
        
        
            invoice_ID.setText("Invoice ID: "+invoice.getInvID());
            invoice_Date.setText(inv_date);
            invoice_Time.setText(inv_time);
            invoice_OrderType.setText("Order Type: "+invoice.getOrderType());
            invoice_ServedBy.setText("Served By: "+invoice.getServedBy());
            invoice_BillBy.setText("Bill By: "+invoice.getBillBy());
            invoice_Subtotal.setText(String.format("Subtotal: %.2f Tk", invoice.getSubTotal()));
            invoice_Discount.setText("Discount: "+invoice.getDiscount());
            invoice_OtherCharge.setText("Others Charge: "+invoice.getOthersCharge());
            invoice_GrandTotal.setText(String.format("Grand Total: ৳ %.2f Tk", invoice.getGrandTotal()));
            invoice_TotalQty.setText("Total Qty: "+invoice.getTotalQty().toString());
            invoice_Note.setText("Note: "+invoice.getNote());
        
            String formattedOutput = "Pasta, 1, 8.49, 8.49\nPasta, 1, 8.49, 8.49\nPasta, 1, 8.49, 8.49\nPasta, 1, 8.49, 8.49\nPasta, 1, 8.49, 8.49\nPizza, 1, 3.99, 3.99\nPasta new, 1, 4.49, 4.49\nPizza new, 1, 3.99, 3.99";
            populateTableView(formattedOutput);

        }
    }
    
    
    //String formattedOutput = "Pasta, 1, 8.49, 8.49\nPizza, 1, 3.99, 3.99\nPasta new, 1, 4.49, 4.49\nPizza new, 1, 3.99, 3.99";
    //populateTableView(formattedOutput);
    
    public void populateTableView(String formattedOutput) {
        String[] rows = formattedOutput.split("\n");
        ObservableList<InvoiceItem> data = FXCollections.observableArrayList();

        int serialNumber = 1;
        for (String row : rows) {
            String[] fields = row.split(", ");
            String itemName = fields[0];
            int quantity = Integer.parseInt(fields[1]);
            double rate = Double.parseDouble(fields[2]);
            double amount = Double.parseDouble(fields[3]);

            // Create InvoiceItem object and add to the list
            data.add(new InvoiceItem(serialNumber++, itemName, rate, quantity, amount));
        }

        // Set the data to the TableView
        invoice_ItemTableView.setItems(data);
    }

    @FXML
    public void initialize() {
        invoice_SN.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        invoice_ItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        invoice_ItemRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        invoice_ItemQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        invoice_ItemAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadInvoiceDataById(20); 
        initialize();
    }
    
    
}