/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_main_goppo_ms;

import database.DB;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
        Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml")); //mainForm authPage
        
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


//
//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.layout.AnchorPane;
//import javafx.stage.Stage;
//
//public class Main_App extends Application {
//
//    @Override
//    public void start(Stage primaryStage) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("IncomeChart.fxml"));
//            AnchorPane root = loader.load();
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Income Area Chart");
//            primaryStage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}