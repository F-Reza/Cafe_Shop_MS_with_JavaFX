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




//import javafx.application.Application;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Scene;
//import javafx.scene.control.ScrollPane;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//
//
//public class Main_App extends Application {
//
//    private GridPane gridPane;
//
//    @Override
//    public void start(Stage primaryStage) throws IOException {
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainLayout.fxml"));
//        ScrollPane scrollPane = loader.load();
//
//        // Get the GridPane from the loaded FXML
//        gridPane = (GridPane) loader.getNamespace().get("gridPane");
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//        
//        
//        Scene scene = new Scene(scrollPane, 800, 600);
//
//        // Add listeners to the width and height properties
//        scene.widthProperty().addListener((obs, oldVal, newVal) -> updateGrid(newVal.doubleValue()));
//        scene.heightProperty().addListener((obs, oldVal, newVal) -> populateGrid(newVal.doubleValue()));
//
//        primaryStage.setTitle("Dynamic Card Grid with ScrollPane Example");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        // Populate initial cards
//        populateGrid(scene.getHeight());
//    }
//
//    private void populateGrid(double height) {
//        int columns = calculateColumns(gridPane.getScene().getWidth()); // Get current column count
//        gridPane.getChildren().clear(); // Clear existing cards
//
//        for (int i = 0; i < 100; i++) { // Populate a maximum of 20 cards
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardItem.fxml"));
//                gridPane.add(loader.load(), i % columns, i / columns); // Add cards to grid
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void updateGrid(double width) {
//        int columns = Math.max(1, (int) (width / 150)); // Calculate columns based on width
//        gridPane.getChildren().clear(); // Clear existing cards
//
//        for (int i = 0; i < 100; i++) { // Re-add cards based on new column count
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("cardItem.fxml"));
//                gridPane.add(loader.load(), i % columns, i / columns);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private int calculateColumns(double width) {
//        return Math.max(1, (int) (width / 150)); // Each card is approximately 150px wide
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}
