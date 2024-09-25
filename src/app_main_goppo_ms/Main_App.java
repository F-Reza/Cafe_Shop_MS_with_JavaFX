/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_main_goppo_ms;

import database.DB;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author F_Reza
 */
public class Main_App extends Application {
    private DB db = new DB();
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/authPage.fxml")); //mainForm
        
        Scene scene = new Scene(root);
        
        stage.setTitle("GoPpo Management System");
        stage.setMinHeight(450);
        stage.setMinWidth(616); 
        
        stage.setScene(scene);
        stage.show();
        
        db.getConnection();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
