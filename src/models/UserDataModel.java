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
    private String displayName;
    private String question;
    private String answer;
    private String userRole;
    private String status;
    private String image;
    private Date date;
    
    // Corrected Constructor
    public UserDataModel (
            int id, 
            String username, 
            String password, 
            String displayName, 
            String question,
            String answer, 
            String userRole, 
            String status,
            String image, 
            Date date) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.displayName = displayName;
        this.question = question; 
        this.answer = answer;
        this.userRole = userRole;
        this.status = status;
        this.image = image;
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
    public String getDisplayName(){
        return displayName;
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
    public String getStatus(){
        return status;
    }
    public String getImage(){
        return image;
    }
    public Date getDate(){
        return date;
    }
    
    

    
}


