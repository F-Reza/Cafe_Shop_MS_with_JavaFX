/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author F_Reza
 */

public class EmployeeDataModel {

    private int id;
    private String username;
    private String password;
    private String userRole;
    private String status;
    private Date date;

    // Corrected Construc
    public EmployeeDataModel(
            int id, 
            String username, 
            String password, 
            String userRole, 
            String status, 
            Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.status = status;
        this.date = date;
    }

    // Corrected getter methods to match field names
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }
}

