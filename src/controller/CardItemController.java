/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import models.ItemsDataModel;
//import controller.MainFormController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author F_Reza
 */
public class CardItemController implements Initializable {

    @FXML private ImageView card_ImageView;
    @FXML private AnchorPane card_itemForm;
    @FXML private Label card_itemName;
    @FXML private Label card_itemPrice;
    @FXML private Label card_itemSize;

    private ItemsDataModel itemData;
    private Image image;
    private String category;
    private String item_date;
    private String item_image;
    

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
    }

    public void addBtn() {
        System.out.println("Click Add Btn");
        MainFormController mForm = new MainFormController();
        //mForm.addToCart(itemData);
        //mForm.customerID();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //
    }

}
