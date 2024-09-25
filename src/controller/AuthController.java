/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import database.DB;
import utils.xValue;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author F_Reza
 */
public class AuthController implements Initializable {
    private DB db = new DB();
    private PreparedStatement prepare;
    private ResultSet result;
    
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private Alert alert;

    @FXML
    private TextField fp_answer;

    @FXML
    private Button fp_backBtn;

    @FXML
    private TextField fp_username;

    @FXML
    private Button fp_nextBtn;

    @FXML
    private ComboBox<?> fp_question;

    @FXML
    private AnchorPane fp_questionForm;

    @FXML
    private PasswordField np_confirmPassword;

    @FXML
    private Button np_backBtn;

    @FXML
    private Button np_changePassBtn;

    @FXML
    private AnchorPane np_newPassForm;

    @FXML
    private PasswordField np_password;

    @FXML
    private Hyperlink si_forgotPass;

    @FXML
    private Button si_loginBtn;

    @FXML
    private AnchorPane si_loginForm;

    @FXML
    private PasswordField si_password;

    @FXML
    private TextField si_username;

    @FXML
    private AnchorPane side_Form;

    @FXML
    private Button side_alreadyHave;

    @FXML
    private Button side_createBtn;

    @FXML
    private TextField su_answer;

    @FXML
    private PasswordField su_password;

    @FXML
    private ComboBox<?> su_question;

    @FXML
    private Button su_signupBtn;

    @FXML
    private AnchorPane su_signupForm;

    @FXML
    private TextField su_username;


    private String[] questionList = {
        "What is your favorite Color?",
        "What is your favorite food?",
        "What is your favorite person?"};

    public void loginBtn() {

        if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText("Enter Your Username and Password");
            //alert.setContentText("Please Username and Password.");
            //alert = new Alert(AlertType.ERROR);
            //alert.setTitle("Error Message");
            //alert.setHeaderText(null);
            //alert.setContentText("Incorrect Username/Password");
            alert.showAndWait();
        } else {

            String selctData = "SELECT username, password FROM users WHERE username = ? and password = ?";

            db.getConnection();

            try (PreparedStatement statement = db.connection.prepareStatement(selctData)) {

                //prepare = db.connection.prepareStatement(selctData);
                statement.setString(1, si_username.getText());
                statement.setString(2, si_password.getText());

                result = statement.executeQuery();
                // IF SUCCESSFULLY LOGIN, THEN PROCEED TO ANOTHER FORM WHICH IS OUR MAIN FORM 
                if (result.next()) {
                    // TO GET THE USERNAME THAT USER USED
                    xValue.username = si_username.getText();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    // LINK YOUR MAIN FORM
                    Parent root = FXMLLoader.load(getClass().getResource("/view/mainForm.fxml"));

                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setTitle("GoPoo Management System");
                    stage.setMinWidth(1384);
                    stage.setMinHeight(840);

                    stage.setScene(scene);
                    stage.show();

                    si_loginBtn.getScene().getWindow().hide();

                } else { // IF NOT, THEN THE ERROR MESSAGE WILL APPEAR
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.info(e.toString());
            }

        }

    }
    

    public void regBtn() {
        db.getConnection();
        
        if (su_username.getText().isEmpty() 
            || su_password.getText().isEmpty()
            || su_question.getSelectionModel().getSelectedItem() == null 
            || su_answer.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else{
            
            String regData = "INSERT INTO users (username, password, question, answer, user_role, status, date) VALUES (?, ?, ?, ?, ?, ?, ?)";

            try {

                // CHECK IF THE USERNAME IS ALREADY RECORDED
                String checkUsername = "SELECT username FROM users WHERE username = '"
                        + su_username.getText() + "'";

                prepare = db.connection.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " is already taken");
                    alert.showAndWait();
                } else if (su_password.getText().length() < 6) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Password, atleast 6 characters are needed");
                    alert.showAndWait();
                } else {
                    prepare = db.connection.prepareStatement(regData);

                    prepare.setString(1, su_username.getText());
                    prepare.setString(2, su_password.getText());
                    prepare.setString(3, (String) su_question.getSelectionModel().getSelectedItem());
                    prepare.setString(4, su_answer.getText());
                    prepare.setString(5, "Undefined");
                    prepare.setBoolean(6, false);
                    prepare.setLong(7, System.currentTimeMillis());
    //                Date date = new Date();
    //                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    //                prepare.setString(7, String.valueOf(sqlDate));

                    prepare.executeUpdate();
                    logger.info("Data Inserted!");

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully registered Account!");
                    alert.showAndWait();

                    su_username.setText("");
                    su_password.setText("");
                    su_question.getSelectionModel().clearSelection();
                    su_answer.setText("");

                    TranslateTransition slider = new TranslateTransition();

                    slider.setNode(side_Form);
                    slider.setToX(0);
                    slider.setDuration(Duration.seconds(.5));

                    slider.setOnFinished((ActionEvent e) -> {
                        side_alreadyHave.setVisible(false);
                        side_createBtn.setVisible(true);
                    });

                    slider.play();
                }

            } catch (SQLException e) {
                logger.info(e.toString());
            }
        
        }
        

    }
    

    public void regQuestionList() {
        List<String> listQ = new ArrayList<>();

        for (String data : questionList) {
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        su_question.setItems(listData);
    }

    public void forgotPassQuestionList() {

        List<String> listQ = new ArrayList<>();

        for (String data : questionList) {
            listQ.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(listQ);
        fp_question.setItems(listData);

    }

    public void switchForgotPass() {
        fp_questionForm.setVisible(true);
        si_loginForm.setVisible(false);

        forgotPassQuestionList();
    }

    public void nextBtn() {

        if (fp_username.getText().isEmpty() || fp_question.getSelectionModel().getSelectedItem() == null
                || fp_answer.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String selectData = "SELECT username, question, answer FROM users WHERE username = ? AND question = ? AND answer = ?";
            db.getConnection();

            try {

                prepare = db.connection.prepareStatement(selectData);
                prepare.setString(1, fp_username.getText());
                prepare.setString(2, (String) fp_question.getSelectionModel().getSelectedItem());
                prepare.setString(3, fp_answer.getText());

                result = prepare.executeQuery();

                if (result.next()) {
                    np_newPassForm.setVisible(true);
                    fp_questionForm.setVisible(false);
                } else {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Information");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void changePassBtn() {

        if (np_password.getText().isEmpty() || np_confirmPassword.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else if (np_password.getText().length() < 6 || np_confirmPassword.getText().length() < 6) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid Password, atleast 6 characters are needed");
            alert.showAndWait();
        } else {

            if (np_password.getText().equals(np_confirmPassword.getText())) {
                String getDate = "SELECT date FROM users WHERE username = '"
                        + fp_username.getText() + "'";

                db.getConnection();

                try {

                    prepare = db.connection.prepareStatement(getDate);
                    result = prepare.executeQuery();

                    String date = "";
                    if (result.next()) {
                        date = result.getString("date");
                    }

                    String updatePass = "UPDATE users SET password = '"
                            + np_password.getText() + "', question = '"
                            + fp_question.getSelectionModel().getSelectedItem() + "', answer = '"
                            + fp_answer.getText() + "', date = '"
                            + date + "' WHERE username = '"
                            + fp_username.getText() + "'";

                    prepare = db.connection.prepareStatement(updatePass);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully changed Password!");
                    alert.showAndWait();

                    si_loginForm.setVisible(true);
                    np_newPassForm.setVisible(false);

                    // TO CLEAR FIELDS
                    clearData();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Not match");
                alert.showAndWait();
            }
        }
    }
    
    private void clearData() {
        // TO CLEAR FIELDS
        np_confirmPassword.setText("");
        np_password.setText("");
        fp_question.getSelectionModel().clearSelection();
        fp_answer.setText("");
        fp_username.setText("");
    }

    public void backToLoginForm() {
        si_loginForm.setVisible(true);
        fp_questionForm.setVisible(false);
        clearData();
    }

    public void backToQuestionForm() {
        fp_questionForm.setVisible(true);
        np_newPassForm.setVisible(false);
    }

    public void switchForm(ActionEvent event) {

        TranslateTransition slider = new TranslateTransition();

        if (event.getSource() == side_createBtn) {
            slider.setNode(side_Form);
            slider.setToX(300);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(true);
                side_createBtn.setVisible(false);

                fp_questionForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPassForm.setVisible(false);
                regQuestionList();
            });

            slider.play();
        } else if (event.getSource() == side_alreadyHave) {
            slider.setNode(side_Form);
            slider.setToX(0);
            slider.setDuration(Duration.seconds(.5));

            slider.setOnFinished((ActionEvent e) -> {
                side_alreadyHave.setVisible(false);
                side_createBtn.setVisible(true);

                fp_questionForm.setVisible(false);
                si_loginForm.setVisible(true);
                np_newPassForm.setVisible(false);
            });

            slider.play();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
