package app_main_goppo_ms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CardItemController {
    @FXML
    private Label itemNameLabel; 

    public void setData(String itemName) {
        itemNameLabel.setText(itemName);
    }
}
