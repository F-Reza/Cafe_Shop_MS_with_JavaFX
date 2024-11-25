/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DB;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
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
    private int getById;
    
    
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

    
    public void setGetById(int id) {
        this.getById = id;
        loadInvoiceDataById(getById);
    }
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
            db.closeConnection();

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
        
            String items = invoice.getItems();
            //System.out.println("----------> :" +items);
            String formattedItems = convertItemsFormat(items);
            //System.out.println("----------> :" +formattedItems);
            populateTableView(formattedItems);
        }
    }
    public static String convertItemsFormat(String items) {
        // Remove the square brackets
        items = items.replace("[", "").replace("]", "");

        // Split the string into parts
        String[] parts = items.split(", ");

        StringBuilder formattedItems = new StringBuilder();
        for (int i = 0; i < parts.length; i += 4) {
            // Construct each row with newline at the end
            formattedItems.append(parts[i]).append(", ")        // Item Name
                          .append(parts[i + 1]).append(", ")    // Rate
                          .append(parts[i + 2]).append(", ")    // Quantity
                          .append(parts[i + 3]).append("\n");   // Amount
        }
        // Remove the last newline character
        return formattedItems.toString().trim();
    }
    public void populateTableView(String items) {
        String[] rows = items.split("\n"); // Split by newline to get each item
        ObservableList<InvoiceItem> data = FXCollections.observableArrayList();

        int serialNumber = 1;
        for (String row : rows) {
            String[] fields = row.split(", "); // Split each row by comma

            // Ensure there are 4 fields per item (name, rate, qty, amount)
            if (fields.length == 4) {
                String itemName = fields[0];
                double rate = Double.parseDouble(fields[1]);
                int quantity = Integer.parseInt(fields[2]);
                double amount = Double.parseDouble(fields[3]);

                // Create a new InvoiceItem object and add it to the list
                data.add(new InvoiceItem(serialNumber++, itemName, rate, quantity, amount));
            }
        }

        // Set the data to the TableView
        invoice_ItemTableView.setItems(data);
    }
    public void getInvoiceItems() {
        invoice_SN.setCellValueFactory(new PropertyValueFactory<>("serialNumber"));
        invoice_ItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        invoice_ItemRate.setCellValueFactory(new PropertyValueFactory<>("rate"));
        invoice_ItemQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        invoice_ItemAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getInvoiceItems();
    }
    
    
}
