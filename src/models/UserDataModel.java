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


import java.util.Date;


public class UserDataModel {
    private int id;
    private String username;
    private String password;
    private String question;
    private String answer;
    private String userRole;
    private Boolean status;
    private Date date;
    
    // Corrected Constructor
    public UserDataModel (int id, String username, String password, String question, String answer, String userRole, Boolean status, Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.question = question; 
        this.answer = answer;
        this.userRole = userRole;
        this.status = status; 
        this.date = date;
    }

    public int getId(){
        return id;
    }
    public String getUserName(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public String getQuestion(){
        return question;
    }
    public String getAnswer(){
        return answer;
    }
    public String getUserRole(){
        return userRole;
    }
    public Boolean getStatus(){
        return status;
    }
    public Date getDate(){
        return date;
    }
    

    
}
