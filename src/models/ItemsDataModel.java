/*
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
*/

package models;

import java.sql.Date;

/* 
    Created on : Sep 1, 2024, 5:46:33 PM
    Author     : F_Reza
*/
public class ItemsDataModel {

    private Integer id;
    private String itemsCode;
    private String itemsName;
    private String category;
    private String size;
    private Integer stock;
    private Double unitPrice;
    private String status;
    private String image;
    private Date date;
    private Integer quantity;

    public ItemsDataModel(Integer id, String itemsCode,
             String itemsName, String category, String size, Integer stock,
             Double unitPrice, String status, String image, Date date) {
        this.id = id;
        this.itemsCode = itemsCode;
        this.itemsName = itemsName;
        this.category = category;
        this.size = size;
        this.stock = stock;
        this.unitPrice = unitPrice;
        this.status = status;
        this.image = image;
        this.date = date;
    }
    
    public ItemsDataModel(Integer id, String itemsCode, String itemsName, 
            String category, String size, Integer quantity, 
            Double unitPrice, String image, Date date){
        this.id = id;
        this.itemsCode = itemsCode;
        this.itemsName = itemsName;
        this.category = category;
        this.size = size;
        this.unitPrice = unitPrice;
        this.image = image;
        this.date = date;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public String getItemsCode() {
        return itemsCode;
    }

    public String getItemsName() {
        return itemsName;
    }
    
    public String getCategory(){
        return category;
    }
    
    public String getSize(){
        return size;
    }


    public Integer getStock() {
        return stock;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
    
    public Integer getQuantity(){
        return quantity;
    }
}
