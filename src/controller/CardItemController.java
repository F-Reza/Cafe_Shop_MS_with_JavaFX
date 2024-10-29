/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DB;
import models.ItemsDataModel;
//import controller.MainFormController;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author F_Reza
 */
public class CardItemController implements Initializable {
    private DB db = new DB();
    private PreparedStatement prepare;
    private ResultSet result;

    @FXML private ImageView card_ImageView;
    @FXML private Button card_itemAddBtn;
    @FXML private AnchorPane card_itemForm;
    @FXML private Label card_itemName;
    @FXML private Label card_itemPrice;
    @FXML private Label card_itemSize;
    @FXML private Spinner<Integer> card_itemSpinner;

    private ItemsDataModel itemData;
    private Image image;
    private String category;
    private String item_date;
    private String item_image;
    
    private SpinnerValueFactory<Integer> spin;
    private Alert alert;

    public void setData(ItemsDataModel itemData) {
        this.itemData = itemData;

        item_image = itemData.getImage();
        item_date = String.valueOf(itemData.getDate());
        category = itemData.getCategory();      
        card_itemName.setText(itemData.getItemsName());
        card_itemPrice.setText("৳" + String.valueOf(itemData.getUnitPrice()));
        card_itemSize.setText(itemData.getSize());
        String path = "File:" + itemData.getImage();
        image = new Image(path, 130, 130, false, true);
        card_ImageView.setImage(image);
        pr = itemData.getUnitPrice();
    }

    private double pr;
    private double totalP;

    public void addBtn() {
        MainFormController mForm = new MainFormController();
        //mForm.customerID();
        db.getConnection();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

}
