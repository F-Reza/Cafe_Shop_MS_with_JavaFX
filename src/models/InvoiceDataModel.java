/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;

/**
 *
 * @author F_Reza
 */
public class InvoiceDataModel {
    
    private final Integer id;
    private final String invID;
    private final String items;
    private final Double subTotal;
    private final Double discount;
    private final Double othersCharge;
    private final Double grandTotal;
    private final Integer totalQty;
    private final String note;
    private final String orderType;
    private final String servedBy;
    private final String billBy;
    private final String paymentStatus;
    private final Date date;

    public InvoiceDataModel(Integer id, String invID, String items, 
            Double subTotal, Double discount, Double othersCharge,Double grandTotal, 
            Integer totalQty, String note, String orderType, String servedBy, String billBy, 
            String paymentStatus, Date date) {
        
        this.id = id;
        this.invID = invID;
        this.items = items;
        this.subTotal = subTotal;
        this.discount = discount;
        this.othersCharge = othersCharge;
        this.grandTotal = grandTotal;
        this.totalQty = totalQty;
        this.note = note;
        this.orderType = orderType;
        this.servedBy = servedBy;
        this.billBy = billBy;
        this.paymentStatus = paymentStatus;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }
    public String getInvID() {
        return invID;
    }
    public String getItems(){
        return items;
    }
    public Double getSubTotal() {
        return subTotal;
    }
    public Double getDiscount() {
        return discount;
    }
    public Double getOthersCharge() {
        return othersCharge;
    }
    public Double getGrandTotal() {
        return grandTotal;
    }
    public Integer getTotalQty() {
        return totalQty;
    }
    public String getNote() {
        return note;
    }
    public String getOrderType() {
        return orderType;
    }
    public String getServedBy() {
        return servedBy;
    }
    public String getBillBy() {
        return billBy;
    }
    public String getPaymentStatus() {
        return paymentStatus;
    }
    public Date getDate() {
        return date;
    }
    
}
