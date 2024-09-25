/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author F_Reza
 */

public class ExpenseDataModel {
    
    private int id;
    private Double exAmount;
    private String exCategory;
    private String exDescription;
    private String exBy;
    private Date exDate;
    
    // Corrected Constructor
    public ExpenseDataModel (int id, Double exAmount, String exCategory, String exDescription, String exBy, Date exDate) {
        this.id = id;
        this.exAmount = exAmount;
        this.exCategory = exCategory;
        this.exDescription = exDescription;
        this.exBy = exBy;
        this.exDate = exDate;
    }

    public int getId(){
        return id;
    }
    public Double getExAmount(){
        return exAmount;
    }
    public String getExCategory(){
        return exCategory;
    }
    public String getExDescription(){
        return exDescription;
    }
    public String getExBy(){
        return exBy;
    }
    public Date getExDate(){
        return exDate;
    }


    
}
