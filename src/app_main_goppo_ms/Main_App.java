/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app_main_goppo_ms;

import controller.AuthController;
import database.DB;
import java.util.prefs.Preferences;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.xValue;

/**
 *
 * @author F_Reza
 */
public class Main_App extends Application {
    private DB db = new DB();

    private static final String LOGIN_SAVE_KEY = "logInSave";
    private static final String LOGIN_USER_KEY = "logUserName";

    @Override
    public void start(Stage stage) throws Exception {
        boolean isLoggedIn = checkSavedLogin();

        Parent root;

        if (isLoggedIn) {
            Preferences preferences = Preferences.userNodeForPackage(AuthController.class);
            String logUserName = preferences.get(LOGIN_USER_KEY, "Unknown User");
            xValue.username =logUserName;
            
            root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));
            
            Scene scene = new Scene(root);
            
            stage.setTitle("GoPpo Management System");
            stage.setMinWidth(1384);
            stage.setMinHeight(840);
            stage.setScene(scene);
            stage.show();
            
            
        } else {
            root = FXMLLoader.load(getClass().getResource("/view/authPage.fxml"));
            Scene scene = new Scene(root);
            
            stage.setTitle("GoPpo Management System");
            stage.setMinHeight(450);
            stage.setMaxHeight(450);
            stage.setMinWidth(616);
            stage.setMaxWidth(616);
            stage.setScene(scene);
            stage.show();
        }
        
        // Establish database connection
        db.getConnection();
    }

    private boolean checkSavedLogin() {
        Preferences preferences = Preferences.userNodeForPackage(AuthController.class);
        return preferences.getBoolean(LOGIN_SAVE_KEY, false);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

