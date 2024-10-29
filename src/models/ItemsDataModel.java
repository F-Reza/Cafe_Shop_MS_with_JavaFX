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
    private String itemsName;
    private String category;
    private String size;
    private Double unitPrice;
    private String status;
    private String image;
    private Date date;

    public ItemsDataModel(Integer id, String itemsName, String category, String size, 
            Double unitPrice, String status, String image, Date date) {
        this.id = id;
        this.itemsName = itemsName;
        this.category = category;
        this.size = size;
        this.unitPrice = unitPrice;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    public Integer getId() {
        return id;
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
    
}
