package models;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class CartItem {
    private ItemsDataModel itemDataModel;
    private SimpleIntegerProperty quantity;
    private SimpleStringProperty itemName;
    private SimpleDoubleProperty unitPrice;
    private SimpleDoubleProperty totalPrice;
    private SimpleObjectProperty<Button> removeButton;

    public CartItem(ItemsDataModel itemsDataModel, int quantity) {
        this.itemDataModel = itemsDataModel;
        this.quantity = new SimpleIntegerProperty(quantity);
        this.itemName = new SimpleStringProperty(itemsDataModel.getItemsName());
        this.unitPrice = new SimpleDoubleProperty(itemsDataModel.getUnitPrice());
        this.totalPrice = new SimpleDoubleProperty(getTotalPrice());
        this.removeButton = new SimpleObjectProperty<>(new Button("X"));
    }

    public ItemsDataModel getItems() {
        return itemDataModel;
    }

    public String getItemName() { return itemName.get(); }
    public int getQuantity() { return quantity.get(); }
    public double getUnitPrice() { return unitPrice.get(); }
    public double getTotalPrice() { return itemDataModel.getUnitPrice() * quantity.get(); }
    public Button getRemoveButton() { return removeButton.get(); }

    public void setQuantity(int quantity) {
        this.quantity.set(quantity);
        this.totalPrice.set(getTotalPrice());
    }

    public void setRemoveButton(Button removeButton) {
        this.removeButton.set(removeButton);
    }
}
