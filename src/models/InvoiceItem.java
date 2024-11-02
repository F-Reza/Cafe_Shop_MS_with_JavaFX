/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author F_Reza
 */
public class InvoiceItem {
    private int serialNumber;
    private String itemName;
    private double rate;
    private int quantity;
    private double amount;

    // Constructor
    public InvoiceItem(int serialNumber, String itemName, double rate, int quantity, double amount) {
        this.serialNumber = serialNumber;
        this.itemName = itemName;
        this.rate = rate;
        this.quantity = quantity;
        this.amount = amount;
    }

    // Getters and setters
    public int getSerialNumber() { return serialNumber; }
    public String getItemName() { return itemName; }
    public double getRate() { return rate; }
    public int getQuantity() { return quantity; }
    public double getAmount() { return amount; }
}