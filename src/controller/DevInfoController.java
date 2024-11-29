package controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class DevInfoController implements Initializable {
    private Alert alert;
    @FXML private FontAwesomeIcon instaBtn, facebookBtn, githubBtn, twitterBtn, whatsAppBtn, youtubeBtn;
    @FXML private Label currentYear;
    
    private void openURL(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                desktop.browse(new URI(url));
            } else {
                throw new UnsupportedOperationException("Desktop is not supported on this platform.");
            }
        } catch (IOException | URISyntaxException e) {
            showError("Failed to open URL: " + url);
        }
    }
    
    private void showError(String message) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    
    @FXML
    private void clkWhatsAppBtn() {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Message");
        alert.setHeaderText(null);
        alert.setContentText("+8801830996044");
        alert.showAndWait();
        
        String phoneNumber = "+8801830996044";
        String url = "https://wa.me/" + phoneNumber.replace("+", "").trim(); 
        openURL(url);
    }
    
    @FXML
    private void clkGithubBtn() {
        openURL("https://github.com/NextdigitOfficial/");
    }
    
    @FXML
    private void clkFacebookBtn() {
        openURL("https://www.facebook.com/NextDigitOfficial/");
    }
    
    @FXML
    private void clkInstaBtn() {
        openURL("https://www.instagram.com/nextdigitofficial/");
    }

    @FXML
    private void clkTwitterBtn() {
        openURL("https://twitter.com/Next_Digit/");
    }

    @FXML
    private void clkYoutubeBtn() {
        openURL("https://www.youtube.com/@nextdigitofficial/");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int year = LocalDate.now().getYear();
        currentYear.setText(">2024 - "+year+"<"); 
    }
}
