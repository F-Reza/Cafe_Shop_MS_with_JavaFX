/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import database.DB;
import models.ExpenseDataModel;
import models.ItemsDataModel;
import utils.xValue;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import models.EmployeeDataModel;
import models.UserDataModel;

/**
 *
 * @author F_Reza
 */
public class MainFormController implements Initializable {
    private final DB db = new DB();
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Alert alert;
    
    //// SET Variable
    private static int id;
    private static String path;
    private static String imagePath;
    private Image image;
    private static final String IMAGE_DIR = "Images";
    private static String getEmpDate;
    
    //All Form Section Start
    @FXML
    private AnchorPane main_Form;
    @FXML
    private AnchorPane dashboadrForm;
    @FXML
    private AnchorPane itemsForm;
    @FXML
    private AnchorPane posMenuForm;
    @FXML
    private AnchorPane invoicesForm;
    @FXML
    private AnchorPane expensesForm;
    @FXML
    private AnchorPane reportForm;
    @FXML
    private AnchorPane usersForm;
    @FXML
    private AnchorPane settingsForm;
    //End

    //All Nav Section Start
    @FXML
    private Button dashboardBtn;
    @FXML
    private Button itemsBtn;
    @FXML
    private Button posMenuBtn;
    @FXML
    private Button invoicesBtn;
    @FXML
    private Button expensesBtn;
    @FXML
    private Button reportBtn;
    @FXML
    private Button usersBtn;
    @FXML
    private Button settingsBtn;

    @FXML
    private Button signoutBtn;
    @FXML
    private Label userName;
    //End

    //Dashboard Section Start
    //End


    // Item Section Start
    @FXML
    private Button items_AddBtn;
    @FXML
    private Button items_UpdateBtn;
    @FXML
    private Button items_DeleteBtn;
    @FXML
    private Button items_ClearBtn;
    @FXML
    private TextField items_Name;
    @FXML
    private ComboBox<String> items_Category;
    @FXML
    private TextField items_UnitPrice;
    @FXML
    private TextField items_Size;
    @FXML
    private ComboBox<String> items_Status;
    @FXML
    private ImageView items_ImageView;
    @FXML
    private Button items_ImportBtn;
    @FXML
    private TableView<ItemsDataModel> items_TableView;
    @FXML
    private TableColumn<ItemsDataModel, String> items_Col_ItemsSn;
    @FXML
    private TableColumn<ItemsDataModel, String> items_Col_ItemsName;
    @FXML
    private TableColumn<ItemsDataModel, String> items_Col_Category;
    @FXML
    private TableColumn<ItemsDataModel, String> items_Col_Size;
    @FXML
    private TableColumn<ItemsDataModel, String> items_Col_UnitPrice;
    @FXML
    private TableColumn<ItemsDataModel, String> items_Col_Status;
    @FXML
    private TableColumn<ItemsDataModel, String> items_Col_Date;
    //End


    //POS Menu Section Start
    @FXML
    private GridPane menuGridPane;
    @FXML
    private ScrollPane menuScrollPane;
    @FXML
    private TextField menu_searchItem;
    @FXML
    private Button mealsBtn;
    @FXML
    private Button drinksBtn;
    @FXML
    private Button packagesBtn;
    @FXML
    private Button othersBtn;
    @FXML
    private TableView<?> menuTableView;
    @FXML
    private TableColumn<?, ?> menu_Col_ItemName;
    @FXML
    private TableColumn<?, ?> menu_Col_Qty;
    @FXML
    private TableColumn<?, ?> menu_Col_Price;
    @FXML
    private Label menu_SubTotal;
    @FXML
    private TextField menu_Discount;
    @FXML
    private TextField menu_OthersCharge;
    @FXML
    private TextField menu_Note;
    @FXML
    private Label menu_Total;
    @FXML
    private ComboBox<String> menu_OrderType;
    @FXML
    private TextField menu_ServedBy;
    @FXML
    private Button menu_RemoveBtn;
    @FXML
    private Button menu_ClearBtn;
    @FXML
    private Button menu_InvoiceBtn;
    //End
	

    //Invoices Section Start
    //End
	
    //Expense Section Start
    @FXML
    private Button expense_AddBtn;
    @FXML
    private Button expense_UpdateBtn;
    @FXML
    private Button expense_DeleteBtn;
    @FXML
    private Button expense_ClearBtn;
    @FXML
    private TextField expense_Amount;
    @FXML
    private ComboBox<String> expense_Category;
    @FXML
    private TextArea expense_Discription;	
    @FXML
    private TextField expense_By;
    @FXML
    private DatePicker expense_Date;	
    @FXML
    private TableView<ExpenseDataModel> expense_TableView;
    @FXML
    private TableColumn<ExpenseDataModel, String> expense_Col_Sn;
    @FXML
    private TableColumn<ExpenseDataModel, String> expense_Col_Amount;
    @FXML
    private TableColumn<ExpenseDataModel, String> expense_Col_Category;
    @FXML
    private TableColumn<ExpenseDataModel, String> expense_Col_Description;
    @FXML
    private TableColumn<ExpenseDataModel, String> expense_Col_ExpensedBy;
    @FXML
    private TableColumn<ExpenseDataModel, String> expense_Col_Date;  
    
    @FXML
    private DatePicker expenseDatePicker;
    @FXML
    private Label selectedDateExpense;
    @FXML
    private Button getExpSpecificBtn;
    @FXML
    private DatePicker startExpDatePicker;
    @FXML
    private DatePicker endExpDatePicker;
    @FXML
    private Label dateRangeExpense;
    @FXML
    private Button getExpDateRangeBtn;
    @FXML
    private Button getExp_ClearBtn;

    @FXML
    private Label todayExpense;
    @FXML
    private Label yesterdayExpense;
    @FXML
    private Label thisweekExpense;
    @FXML
    private Label thismonthExpense;
    @FXML
    private Label thisyearExpense;
    @FXML
    private Label totalExpense;
    //End
	
    //Reports Section Start
    //End
    
    
    //Users Section Start
    
    @FXML
    private Label userDisplayName; 
    @FXML
    private ImageView userImage;    
    @FXML
    private Label adminUserName;
    @FXML
    private Label userRole;
    @FXML
    private Label userStatus;
    @FXML
    private Label activeQuestion;
    @FXML
    private Label userDate;
    @FXML
    private Button editProfileBtn;
    @FXML
    private Button changeUserPassBtn;


    @FXML
    private Button empAddBtn;
    @FXML
    private Button empUpdateBtn;
    @FXML
    private Button empDeleteBtn;

    private static int emp_id;
    private static boolean check_pass;
    private static String emp_pass;
    private static Date emp_date;
    
    @FXML
    private TextField emp_username;
    @FXML
    private PasswordField emp_password;
    @FXML
    private ComboBox<String> emp_user_role;
    @FXML
    private ComboBox<String> emp_user_status;
    
    @FXML
    private TextField emp_passPlainText;
    @FXML
    private CheckBox showPassCheckBox;
    
    @FXML
    private TableView<EmployeeDataModel> empUser_TableView;
    @FXML
    private TableColumn<EmployeeDataModel, String> emp_Col_Sn;
    @FXML
    private TableColumn<EmployeeDataModel, String> emp_Col_UserName;
    @FXML
    private TableColumn<EmployeeDataModel, String> emp_Col_Password;
    @FXML
    private TableColumn<EmployeeDataModel, String> emp_Col_UserRole;
    @FXML
    private TableColumn<EmployeeDataModel, String> emp_Col_Status;
    @FXML
    private TableColumn<EmployeeDataModel, String> emp_Col_Date;
    //End

    //Settings Section Start
    //End

    //// START DASHBOARD SECTION
    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboardBtn) {
            dashboadrForm.setVisible(true);
            itemsForm.setVisible(false);
            posMenuForm.setVisible(false);
            invoicesForm.setVisible(false);
            expensesForm.setVisible(false);
            reportForm.setVisible(false);
            usersForm.setVisible(false);
            settingsForm.setVisible(false);
            
            loadAdminData(1);

        } else if (event.getSource() == itemsBtn) {
            dashboadrForm.setVisible(false);
            itemsForm.setVisible(true);
            posMenuForm.setVisible(false);
            invoicesForm.setVisible(false);
            expensesForm.setVisible(false);
            reportForm.setVisible(false);
            usersForm.setVisible(false);
            settingsForm.setVisible(false);
            
            itemsCategoryList();
            itemsStatusList();
            itemsShowData();
            setDynamicColumnWidthForItem();
            itemsClearBtn();
            expenseClearBtn();

        } else if (event.getSource() == posMenuBtn) {
            dashboadrForm.setVisible(false);
            itemsForm.setVisible(false);
            posMenuForm.setVisible(true);
            invoicesForm.setVisible(false);
            expensesForm.setVisible(false);
            reportForm.setVisible(false);
            usersForm.setVisible(false);
            settingsForm.setVisible(false);

            menuDisplayCard();
            itemsClearBtn();
            expenseClearBtn();

        } else if (event.getSource() == invoicesBtn) {
            dashboadrForm.setVisible(false);
            itemsForm.setVisible(false);
            posMenuForm.setVisible(false);
            invoicesForm.setVisible(true);
            expensesForm.setVisible(false);
            reportForm.setVisible(false);
            usersForm.setVisible(false);
            settingsForm.setVisible(false);
            
            itemsClearBtn();
            expenseClearBtn();

        } else if (event.getSource() == expensesBtn) {
            dashboadrForm.setVisible(false);
            itemsForm.setVisible(false);
            posMenuForm.setVisible(false);
            invoicesForm.setVisible(false);
            expensesForm.setVisible(true);
            reportForm.setVisible(false);
            usersForm.setVisible(false);
            settingsForm.setVisible(false);
            
            expenseCategoryList();
            expenseShowData();
            setDynamicColumnWidthForExp();
            itemsClearBtn();
            expenseClearBtn();
            loadExpenseData();
            getExpDateValidation();
            getExpDateRangeValidation();
            
            

        } else if (event.getSource() == reportBtn) {
            dashboadrForm.setVisible(false);
            itemsForm.setVisible(false);
            posMenuForm.setVisible(false);
            invoicesForm.setVisible(false);
            expensesForm.setVisible(false);
            reportForm.setVisible(true);
            usersForm.setVisible(false);
            settingsForm.setVisible(false);
            
            itemsClearBtn();
            expenseClearBtn();

        } else if (event.getSource() == usersBtn) {
            dashboadrForm.setVisible(false);
            itemsForm.setVisible(false);
            posMenuForm.setVisible(false);
            invoicesForm.setVisible(false);
            expensesForm.setVisible(false);
            reportForm.setVisible(false);
            usersForm.setVisible(true);
            settingsForm.setVisible(false);
            
            loadAdminData(1);
            empUserRoleList();
            empUserStatusList();
            empUserShowData();
            showPass();
            empClearBtn();
            itemsClearBtn();
            expenseClearBtn();

        } else if (event.getSource() == settingsBtn) {
            dashboadrForm.setVisible(false);
            itemsForm.setVisible(false);
            posMenuForm.setVisible(false);
            invoicesForm.setVisible(false);
            expensesForm.setVisible(false);
            reportForm.setVisible(false);
            usersForm.setVisible(false);
            settingsForm.setVisible(true);
            
            itemsClearBtn();
            expenseClearBtn();
        }

    }
    // LETS PROCEED TO OUR DASHBOARD FORM : )
    
    public void logout() {

        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE MAIN FORM 
                signoutBtn.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM AND SHOW IT 
                Parent root = FXMLLoader.load(getClass().getResource("/view/authPage.fxml"));

                Stage stage = new Stage();
                Scene scene = new Scene(root);

                stage.setTitle("GoPpo Management System");

                stage.setScene(scene);
                stage.show();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void displayUsername() {

        String user = xValue.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        String name = "Welcome, " + user + "!";
        userName.setText(name);
        //userName.setText(user);

    } 
    //// END DASHBOARD SECTION
    

    
    //// START ITEM SECTION
    private final String[] itemsCategoryList = {"Meals", "Drinks", "Packages", "Others"};
    public void itemsCategoryList() {

        List<String> xVal = new ArrayList<>();

        for (String data : itemsCategoryList) {
            xVal.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(xVal);
        items_Category.setItems(listData);
    }
    private final String[] itemsStatusList = {"Available", "Unavailable"};
    public void itemsStatusList() {

        List<String> xVal = new ArrayList<>();

        for (String data : itemsStatusList) {
            xVal.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(xVal);
        items_Status.setItems(listData);

    }
    // LETS MAKE A BEHAVIOR FOR IMPORT BTN FIRST
    public void itemsImportBtn() {
        
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        
        File file = fileChooser.showOpenDialog(main_Form.getScene().getWindow());
        
        if (file != null) {
            
            path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 146, 146, false, true);
            items_ImageView.setImage(image);
            
            try {
                // Get the original file name
                String originalFileName = file.getName();
                String newImageName = "item_image_" + originalFileName;

                // Copy the selected file to the MyFolder directory with the new image name
                File destination = new File(IMAGE_DIR + File.separator + newImageName);
                Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Save the image path to the SQLite database
                imagePath = destination.getAbsolutePath();

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }
    private void itemInsertQry() {
        try {
            String insertData = "INSERT INTO items "
                    + "(items_name, category, size, unit_price, status, image, date) "
                    + "VALUES(?,?,?,?,?,?,?)";

            prepare = db.connection.prepareStatement(insertData);

            prepare.setString(1, items_Name.getText());
            prepare.setString(2, (String) items_Category.getSelectionModel().getSelectedItem());
            prepare.setString(3, items_Size.getText());
            prepare.setString(4, items_UnitPrice.getText());
            prepare.setString(5, (String) items_Status.getSelectionModel().getSelectedItem());
            prepare.setString(6, imagePath);
            prepare.setLong(7, System.currentTimeMillis());

            prepare.executeUpdate();

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();

            System.out.println("-> Item Data Inserted!.");
            itemsShowData();
            itemsClearBtn();

        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("-> "+e);
        }
    }
    public void itemsAddBtn() {
        db.getConnection();
        
        String unitPriceText = items_UnitPrice.getText();
        double itemsUnitPrice = 0.0;
        
        if (items_Name.getText().isEmpty()
                || items_Category.getSelectionModel().getSelectedItem() == null
                || items_UnitPrice.getText().isEmpty()
                || items_Status.getSelectionModel().getSelectedItem() == null
                || imagePath.isEmpty() || imagePath == null ) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else if (unitPriceText != null) {
            
            try {
                itemsUnitPrice = Double.parseDouble(unitPriceText);
                if (itemsUnitPrice > 0) {
                    itemInsertQry();
                } else {
                    // Action to handle 0 or negative values
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Unit Price should be greater than 0.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid number
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid 'Unit Price' number format: "+ unitPriceText);
                alert.showAndWait();
            }
            
        } else {
            itemInsertQry();
        }
        
    }
    private void itemUpdateQry() {
        String updateData = "UPDATE items SET "
                    + "items_name = '" + items_Name.getText() + "', "
                    + "category = '" + items_Category.getSelectionModel().getSelectedItem() + "', "
                    + "size = '" + items_Size.getText() + "', "
                    + "unit_price = '" + items_UnitPrice.getText() + "', "
                    + "status = '" + items_Status.getSelectionModel().getSelectedItem() + "', "
                    + "image = '" + imagePath + "', "
                    + "date = '" + System.currentTimeMillis() + "' WHERE id = " + 
                    items_TableView.getSelectionModel().getSelectedItem().getId();
        
        try {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE Item: " + items_Name.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = db.connection.prepareStatement(updateData);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                System.out.println("-> Item Data Updated!");
                // TO UPDATE YOUR TABLE VIEW
                itemsShowData();
                // TO CLEAR YOUR FIELDS
                itemsClearBtn();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("-> "+e);
        }
    }
    public void itemsUpdateBtn() {
        db.getConnection();

        String unitPriceText = items_UnitPrice.getText();
        double itemsUnitPrice = 0.0;
        
        if (items_Name.getText().isEmpty()
                || items_Category.getSelectionModel().getSelectedItem() == null
                || items_UnitPrice.getText().isEmpty()
                || items_Status.getSelectionModel().getSelectedItem() == null
                || imagePath.isEmpty() || imagePath == null 
                || items_TableView.getSelectionModel().getSelectedItem().getId() == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else if (unitPriceText != null) {
            
            try {
                itemsUnitPrice = Double.parseDouble(unitPriceText);
                if (itemsUnitPrice > 0) {
                    itemUpdateQry();
                } else {
                    // Action to handle 0 or negative values
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Unit Price should be greater than 0.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid number
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid 'Unit Price' number format: "+ unitPriceText);
                alert.showAndWait();
            }
            
        } else if(items_Name.getText() == null ? 
                items_TableView.getSelectionModel()
                        .getSelectedItem().getItemsName() == null : 
                items_Name.getText().equals(items_TableView.getSelectionModel()
                        .getSelectedItem().getItemsName())) {
            itemUpdateQry();
            
        } else {
            itemUpdateQry();
        }
    }
    public void itemsDeleteBtn() {
        if (id == 0) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please Select Item");
            alert.showAndWait();

        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Item: " + items_Name.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM items WHERE id = " + id;
                try {
                    prepare = db.connection.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();

                    System.out.println("-> Item Data Deleted!");
                    // TO UPDATE YOUR TABLE VIEW
                    itemsShowData();
                    // TO CLEAR YOUR FIELDS
                    itemsClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }
    public void itemsClearBtn() {
        items_Name.setText("");
        items_Category.getSelectionModel().clearSelection();
        items_Size.setText("");
        items_UnitPrice.setText("");
        items_Status.getSelectionModel().clearSelection();
        imagePath = "";
        items_ImageView.setImage(null);
        id = 0;
    }
    // MERGE ALL DATAS
    public ObservableList<ItemsDataModel> itemsDataList() {

        ObservableList<ItemsDataModel> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM items";

        db.getConnection();

        try {

            prepare = db.connection.prepareStatement(sql);
            result = prepare.executeQuery();

            ItemsDataModel itemData;

            while (result.next()) {

                itemData = new ItemsDataModel(
                        result.getInt("id"),
                        result.getString("items_name"),
                        result.getString("category"),
                        result.getString("size"),
                        result.getDouble("unit_price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(itemData);

            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    // TO SHOW DATA ON OUR TABLE
    private ObservableList<ItemsDataModel> itemsListData;
    public void itemsShowData() {
        itemsListData = itemsDataList();

        items_Col_ItemsSn.setCellValueFactory(new PropertyValueFactory<>("itemsCode"));
        items_Col_ItemsSn.setCellValueFactory(cellData -> {
            int index = items_TableView.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleStringProperty(String.valueOf(index));
        });
        
        items_Col_ItemsName.setCellValueFactory(new PropertyValueFactory<>("itemsName"));
        items_Col_Category.setCellValueFactory(new PropertyValueFactory<>("category"));
        items_Col_Size.setCellValueFactory(new PropertyValueFactory<>("size"));
        items_Col_UnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        items_Col_Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        items_Col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));

        items_TableView.setItems(itemsListData);

    }
    private void setDynamicColumnWidthForItem() {
        items_Col_ItemsSn.maxWidthProperty().bind(items_TableView.widthProperty().multiply(0.5));
        items_Col_ItemsName.maxWidthProperty().bind(items_TableView.widthProperty().multiply(1.8));
        items_Col_Category.maxWidthProperty().bind(items_TableView.widthProperty().multiply(1.2));
        items_Col_Size.maxWidthProperty().bind(items_TableView.widthProperty().multiply(1.0833333333));
        items_Col_UnitPrice.maxWidthProperty().bind(items_TableView.widthProperty().multiply(1.0833333333));
        items_Col_Status.maxWidthProperty().bind(items_TableView.widthProperty().multiply(1.0833333333));
        items_Col_Date.maxWidthProperty().bind(items_TableView.widthProperty().multiply(1.0833333333));
    }
    public void itemsSelectData() {

    ItemsDataModel itemData = items_TableView.getSelectionModel().getSelectedItem();
    int num = items_TableView.getSelectionModel().getSelectedIndex();

    if ((num - 1) < -1) {
        return;
    }

    items_Name.setText(itemData.getItemsName());
    items_Size.setText(itemData.getSize());
    items_UnitPrice.setText(String.valueOf(itemData.getUnitPrice()));
    items_Category.setValue(itemData.getCategory());
    items_Status.setValue(itemData.getStatus()); 
    
    xValue.date = String.valueOf(itemData.getDate());
    
    imagePath = itemData.getImage();
    String path = "File:" + itemData.getImage();
    id = itemData.getId();

    image = new Image(path, 146, 146, false, true);
    items_ImageView.setImage(image);
}
    //// END ITEM SECTION
    
    //// START POS MENU SECTION
    private final ObservableList<ItemsDataModel> cardListData = FXCollections.observableArrayList();
    public ObservableList<ItemsDataModel> menuGetData() {

        String sql = "SELECT * FROM items";

        ObservableList<ItemsDataModel> listData = FXCollections.observableArrayList();
        db.getConnection();

        try {
            prepare = db.connection.prepareStatement(sql);
            result = prepare.executeQuery();

            ItemsDataModel item;

            while (result.next()) {
                item = new ItemsDataModel(result.getInt("id"),
                        result.getString("items_name"),
                        result.getString("category"),
                        result.getString("size"),
                        result.getDouble("unit_price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date"));

                listData.add(item);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }
    public void menuDisplayCard() {

        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        menuGridPane.getChildren().clear();
        menuGridPane.getRowConstraints().clear();
        menuGridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/view/cardItem.fxml"));
                AnchorPane pane = load.load();
                CardItemController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 7) {
                    column = 0;
                    row += 1;
                }

                menuGridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(8));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private final String[] menuOrderTypeList = {"Table", "Parcel"};
    public void menuOrderTypeList() {

        List<String> xVal = new ArrayList<>();

        for (String data : menuOrderTypeList) {
            xVal.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(xVal);
        menu_OrderType.setItems(listData);
    }
    //// END POS MENU SECTION
    
    //// START EXPENSE SECTION
    private final String[] expenseCategoryList = {"Eutility Bill", "Living Cost", "Emplyee Salery", "Buy Goods"};
    public void expenseCategoryList() {

        List<String> xVal = new ArrayList<>();

        for (String data : expenseCategoryList) {
            xVal.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(xVal);
        expense_Category.setItems(listData);
    }
    private void expenseInsertQry() {
        try {
            String insertData = "INSERT INTO expenses "
                        + "(ex_amount, ex_category, ex_description, ex_by, ex_date) "
                        + "VALUES(?,?,?,?,?)";

            prepare = db.connection.prepareStatement(insertData);

            prepare.setString(1, expense_Amount.getText());
            prepare.setString(2, (String) expense_Category.getSelectionModel().getSelectedItem());
            prepare.setString(3, expense_Discription.getText());
            prepare.setString(4, expense_By.getText());
            // Convert LocalDate to java.sql.Date
            java.sql.Date sqlDate = java.sql.Date.valueOf(expense_Date.getValue());
            // Use setDate() instead of setLong()
            prepare.setDate(5, sqlDate);
            //prepare.setLong(5, System.currentTimeMillis());

            prepare.executeUpdate();

            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Added!");
            alert.showAndWait();

            System.out.println("-> Expense Data Inserted!");
            expenseShowData();
            loadExpenseData();
            expenseClearBtn();
            
        } catch (Exception e) {
            e.printStackTrace();
            //System.out.println("-> "+e);
        }
    }
    public void expenseAddBtn() {
        db.getConnection();

        String amountText = expense_Amount.getText();
        double expenseAmount = 0.0;
        
        if (expense_Amount.getText().isEmpty()
                || expense_Category.getSelectionModel().getSelectedItem() == null
                || expense_Discription.getText().isEmpty()
                || expense_By.getText().isEmpty()
                || expense_Date.getValue() == null ) {

            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else if (amountText != null) {
            
            try {
                expenseAmount = Double.parseDouble(amountText);
                if (expenseAmount > 0) {
                    expenseInsertQry();
                } else {
                    // Action to handle 0 or negative values
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Amount should be greater than 0.");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                // Handle the case where the input is not a valid number
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid 'Amount' number format: "+ amountText);
                alert.showAndWait();
            }
            
        } else {
            expenseInsertQry();
        }
        
    }
    private void expenseUpdateQry() {
        
    }
    public void updateExpense(int exp_id, double exp_amount, String exp_category, String exp_discription, String exp_by, Date exp_date) {
        db.getConnection();
        
        //java.sql.Date sqlDate = java.sql.Date.valueOf(exp_date.getDaateVal());
        //long millis = sqlDate.getTime();
        
        long millis = exp_date.getTime();
        //System.out.println("-> Expense Data Value:" + millis);

        String updateData = "UPDATE expenses SET "
                    + "ex_amount = '" + exp_amount + "', "
                    + "ex_category = '" + exp_category + "', "
                    + "ex_description = '" + exp_discription + "', "
                    + "ex_by = '" + exp_by + "', "
                    + "ex_date = '" + millis + "' WHERE id = " + 
                    exp_id;
        
        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE an Expense Item?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = db.connection.prepareStatement(updateData);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                System.out.println("-> Expense Data Updated!");
                // TO UPDATE YOUR TABLE VIEW
                expenseShowData();
                loadExpenseData();
                // TO CLEAR YOUR FIELDS
                expenseClearBtn();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("-> "+e);
        }
    }
    public void expenseClearBtn() {

        expense_Amount.setText("");
        expense_Category.getSelectionModel().clearSelection();
        expense_Discription.setText("");
        expense_By.setText("");
        expense_Date.setValue(null);
        //expense_Date.getEditor().clear();
    }   
    public void deleteExpense(int id) {
    // Confirmation alert before deletion
    alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Message");
    alert.setHeaderText(null);
    alert.setContentText("Are you sure you want to DELETE this Expense Item?");
    Optional<ButtonType> option = alert.showAndWait();

    // If the user confirms the deletion
    if (option.isPresent() && option.get().equals(ButtonType.OK)) {
        String deleteData = "DELETE FROM expenses WHERE id = " + id;
        try {
            // Execute the delete query
            prepare = db.connection.prepareStatement(deleteData);
            prepare.executeUpdate();

            // Show success alert
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success Message");
            alert.setHeaderText(null);
            alert.setContentText("Expense successfully deleted!");
            alert.showAndWait();

            System.out.println("-> Expense Data Deleted!");
            
            // Refresh the table and clear fields
            expenseShowData();
            loadExpenseData();
            expenseClearBtn();


        } catch (Exception e) {
            e.printStackTrace();
        }
    } else {
        // Show cancellation alert
//        alert = new Alert(AlertType.ERROR);
//        alert.setTitle("Cancellation Message");
//        alert.setHeaderText(null);
//        alert.setContentText("Deletion cancelled.");
//        alert.showAndWait();
    }
}
    // MERGE ALL DATAS
    public ObservableList<ExpenseDataModel> expenseDataList() {
        ObservableList<ExpenseDataModel> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM expenses ORDER BY id DESC";
        db.getConnection();

        try {

            prepare = db.connection.prepareStatement(sql);
            result = prepare.executeQuery();

            ExpenseDataModel expData;
            
            while (result.next()) {

                expData = new ExpenseDataModel(
                        result.getInt("id"),
			result.getDouble("ex_amount"),
                        result.getString("ex_category"),
                        result.getString("ex_description"),
                        result.getString("ex_by"),
                        result.getDate("ex_date"));

                listData.add(expData);

            } 

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    // TO SHOW DATA ON OUR TABLE
    private ObservableList<ExpenseDataModel> expenseListData;
    public void expenseShowData() {
        expenseListData = expenseDataList();
        
        expense_Col_Sn.setCellValueFactory(new PropertyValueFactory<>("id"));
        expense_Col_Sn.setCellValueFactory(cellData -> {
            int index = expense_TableView.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleStringProperty(String.valueOf(index));
        });

        // Setting up the columns for the TableView
        expense_Col_Amount.setCellValueFactory(new PropertyValueFactory<>("exAmount"));
        expense_Col_Category.setCellValueFactory(new PropertyValueFactory<>("exCategory"));
        expense_Col_Description.setCellValueFactory(new PropertyValueFactory<>("exDescription"));
        expense_Col_ExpensedBy.setCellValueFactory(new PropertyValueFactory<>("exBy"));
        expense_Col_Date.setCellValueFactory(new PropertyValueFactory<>("exDate"));

        // Check if the action column is already added, to avoid duplicates
        boolean actionColumnExists = false;
        for (TableColumn<ExpenseDataModel, ?> column : expense_TableView.getColumns()) {
            if (column.getText().equals("Actions")) {
                actionColumnExists = true;
                break;
            }
        }

        if (!actionColumnExists) {
            // Creating an action column for Edit and Delete buttons
            TableColumn<ExpenseDataModel, ExpenseDataModel> expenseActionCol = new TableColumn<>("Actions");

            // Explicitly specify the generic types instead of using <>
            expenseActionCol.setCellFactory((final TableColumn<ExpenseDataModel, ExpenseDataModel> param) -> new TableCell<ExpenseDataModel, ExpenseDataModel>() {
                
                private final Button editButton = new Button("Edit");
                private final Button deleteButton = new Button("Delete");
                private final HBox actionButtons = new HBox(editButton, deleteButton);
                
                {
                    // Style the buttons
                    editButton.setStyle("-fx-background-color: #00a2ed; -fx-text-fill: white;");
                    deleteButton.setStyle("-fx-background-color: #ed0000; -fx-text-fill: white;");
                    actionButtons.setSpacing(10); // Add spacing between the buttons
                    actionButtons.setAlignment(Pos.CENTER); // Center-align the buttons in the cell
                    
                    // Set up edit button action
                    editButton.setOnAction(event -> {
                        // Get the selected expense data
                        ExpenseDataModel expense = getTableView().getItems().get(getIndex());
                        // Create a new Stage (window) for editing
                        Stage editStage = new Stage();
                        editStage.initModality(Modality.APPLICATION_MODAL); // Make the stage modal
                        editStage.setTitle("Edit Expense");
                        editStage.initStyle(StageStyle.UTILITY); // Make the window undecorated

                        // Create a layout for the new window
                        VBox editLayout = new VBox(10);
                        editLayout.setPadding(new Insets(10));
                        
                        // Create fields for Name, Description, Amount, Date, and Category
                        TextField amountField = new TextField(expense.getExAmount().toString()); 
                        
                        ComboBox<String> categoryComboBox = new ComboBox<>();
                        categoryComboBox.getItems().addAll("Category 1", "Category 2", "Category 3");
                        categoryComboBox.setValue(expense.getExCategory());
                        
                        TextArea descriptionField = new TextArea(expense.getExDescription());
                        TextField expenseByField = new TextField(String.valueOf(expense.getExBy()));
                        java.sql.Date sqlDate = (java.sql.Date) expense.getExDate();
                        LocalDate localDate = sqlDate.toLocalDate();  // Convert to LocalDate
                        //expense_Date.setValue(localDate); 
                        DatePicker datePicker = new DatePicker(localDate);
                        getExpDateValidationForUpdate(datePicker);
                        
                        // Set preferred width and height for the TextFields
                        amountField.setMinHeight(30);
                        amountField.setStyle("-fx-font-size: 14px;"); 
                        
                        //descriptionField.setMinHeight(80);
                        descriptionField.setStyle("-fx-font-size: 14px;"); 

                        expenseByField.setMinHeight(30);
                        expenseByField.setStyle("-fx-font-size: 14px;"); 
                        
                        categoryComboBox.setMinHeight(30);
                        categoryComboBox.setMinWidth(240);

                        // Add fields to the layout
                        editLayout.getChildren().addAll(
                            new Label("Amount:"), amountField,
                            new Label("Category:"), categoryComboBox,
                            new Label("Description:"), descriptionField,
                            new Label("Expense By:"), expenseByField,
                            new Label("Date:"), datePicker
                        );

                        // Create an Update button to confirm edits
                        Button updateButton = new Button("Update");
                        // Set button styling
                        updateButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");
                        
                        updateButton.setOnAction(updateEvent -> {
                            int exp_id = expense.getId();
                            
                            // Get the values from the input fields
                            String amountText = amountField.getText();
                            String categoryText = categoryComboBox.getValue();
                            String descriptionText = descriptionField.getText();
                            String byText = expenseByField.getText();
                            LocalDate dateValue = datePicker.getValue();
                            // Convert LocalDate back to java.sql.Date if needed
                            java.sql.Date selectedDate = java.sql.Date.valueOf(dateValue);

                            double expenseAmount = 0.0;

                            // Check if any fields are empty
                            if (amountText.isEmpty()
                                    || categoryText == null
                                    || descriptionText.isEmpty()
                                    || byText.isEmpty()
                                    || dateValue == null) {
                                System.out.println("-> Expense Data Empty" + dateValue);

                                // Show alert if any field is empty
                                Alert alert = new Alert(Alert.AlertType.WARNING);
                                alert.setTitle("Warning Message");
                                alert.setHeaderText(null);
                                alert.setContentText("Please fill all blank fields");
                                alert.showAndWait();

                            } else if (amountText != null) {
                                // If amountText is not empty, try to parse it as a double
                                try {
                                    expenseAmount = Double.parseDouble(amountText);

                                    // Check if the parsed expense amount is greater than 0
                                    if (expenseAmount > 0) {
                                        
                                        Double expAmount = Double.parseDouble(amountText);
                                        // Proceed with the update query since all validations passed
                                        updateExpense(exp_id, expAmount, categoryText, descriptionText, byText, selectedDate);
                                        System.out.println("-> Expense Data Updated!");
                                        editStage.close();
                                    } else {
                                        // Show an alert if the amount is less than or equal to 0
                                        Alert alert = new Alert(Alert.AlertType.WARNING);
                                        alert.setTitle("Warning Message");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Amount should be greater than 0.");
                                        alert.showAndWait();
                                    }
                                } catch (NumberFormatException e) {
                                    // Show an alert if the input for amount is not a valid number
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error Message");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Invalid 'Amount' number format: " + amountText);
                                    alert.showAndWait();
                                }

                            } else {
                                // Handle other cases if necessary (e.g., when amountText is somehow null)
                                //expenseUpdateQry();
                                System.out.println("-> ERROR: ExpenseUpdate");
                                editStage.close();
                            }
                        });

                        // Create a Close button
                        Button closeButton= new Button("Close");
                        closeButton.setOnAction(closeEvent -> editStage.close());

                        // Add buttons to the layout
                        HBox buttonLayout = new HBox(20, updateButton, closeButton);
                        editLayout.getChildren().add(buttonLayout);
                        buttonLayout.setAlignment(Pos.CENTER);
                        VBox.setVgrow(buttonLayout, Priority.ALWAYS);

                        // Set the scene and show the stage
                        Scene editScene = new Scene(editLayout, 522, 446);
                        
                        editStage.setMinWidth(522);
                        editStage.setMaxWidth(522);
                        editStage.setMinHeight(440);
                        editStage.setMaxHeight(440);
                        
                        editStage.setScene(editScene);
                        editStage.show();
                    });



                    
                    // Set up delete button action
                    deleteButton.setOnAction(event -> {
                        // Get the selected expense from the table
                        ExpenseDataModel expense = getTableView().getItems().get(getIndex());
                        // Assuming your ExpenseDataModel has an `id` field
                        int id = expense.getId();
                        // Call the deleteExpense method and pass the id
                        deleteExpense(id);
                        // Remove the expense from the TableView
                        //getTableView().getItems().remove(expense);
                    });
                    
                }
                
                @Override
                protected void updateItem(ExpenseDataModel item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null); // If the row is empty, remove the buttons
                    } else {
                        setGraphic(actionButtons); // Otherwise, show the buttons
                    }
                }
            });

            // Add the action column to the table only once
            expense_TableView.getColumns().add(expenseActionCol);
            expenseActionCol.maxWidthProperty().bind(expense_TableView.widthProperty().multiply(1.5));
        }

        // Set the data for the TableView
        expense_TableView.setItems(expenseListData);
    }
    private void setDynamicColumnWidthForExp() {
        expense_Col_Sn.maxWidthProperty().bind(expense_TableView.widthProperty().multiply(0.6));
        expense_Col_Amount.maxWidthProperty().bind(expense_TableView.widthProperty().multiply(1));
        expense_Col_Category.maxWidthProperty().bind(expense_TableView.widthProperty().multiply(1.6));
        expense_Col_Description.maxWidthProperty().bind(expense_TableView.widthProperty().multiply(3));
        expense_Col_ExpensedBy.maxWidthProperty().bind(expense_TableView.widthProperty().multiply(1.3));
        expense_Col_Date.maxWidthProperty().bind(expense_TableView.widthProperty().multiply(1));
    }
    private void loadExpenseData() {
        // Check if any label is null before proceeding
        if (todayExpense != null && yesterdayExpense != null && thisweekExpense != null && 
            thismonthExpense != null && thisyearExpense != null && totalExpense != null) {
            
            todayExpense.setText(String.format("%.2f TK", db.getTodayExpenses()));
            yesterdayExpense.setText(String.format("%.2f TK", db.getYesterdayExpenses()));
            thisweekExpense.setText(String.format("%.2f TK", db.getThisWeekExpenses()));
            thismonthExpense.setText(String.format("%.2f TK", db.getThisMonthExpenses()));
            thisyearExpense.setText(String.format("%.2f TK", db.getThisYearExpenses()));
            totalExpense.setText(String.format("%.2f TK", db.getTotalExpenses()));
        } else {
            System.err.println("One or more labels are not initialized!");
        }
    }
    public void getExpSpecificBtn() {
        onDateSelected();
    }
    public void onDateSelected() {
        LocalDate selectedDate = expenseDatePicker.getValue();
        if (selectedDate != null) {
            // Convert LocalDate to Date in milliseconds
            Date date = Date.from(selectedDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
            long millis = date.getTime(); // Convert to milliseconds

            // Fetch and display expense for the selected date
            double expenseForSelectedDate = db.getExpensesForDate(millis);
            selectedDateExpense.setText(String.format("%.2f TK", expenseForSelectedDate));
        }
    }
    public void getExpDateRangeBtn() {
        onDateRangeSelected();
    }
    public void onDateRangeSelected() {
        LocalDate startDate = startExpDatePicker.getValue();
        LocalDate endDate = endExpDatePicker.getValue();
        
        if (startDate == null || endDate == null) {
            if (startDate == null) {
            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Message");
            alert.setHeaderText(null);
            alert.setContentText("From date is Empty!");
            alert.showAndWait();
            } else{
                alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning Message");
                alert.setHeaderText(null);
                alert.setContentText("To date is Empty!");
                alert.showAndWait();
            }
            return;  
        } 

        if (startDate != null && endDate != null) {
            // Validation: Check if the start date is after the end date
            if (startDate.isAfter(endDate)) {
                dateRangeExpense.setText("Invalid Date Range!");
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid Date Range\nplease select 'From date' to 'To Date' correctly and get data.");
                alert.showAndWait();
                return;
            }

            // Convert LocalDate to milliseconds
            long startMillis = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime();
            long endMillis = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime() 
                             + (24 * 60 * 60 * 1000) - 1;  // Set the end time to the end of the day

            // Fetch and display expense for the selected date range
            double expenseForDateRange = db.getExpensesForDateRange(startMillis, endMillis);
            dateRangeExpense.setText(String.format("%.2f TK", expenseForDateRange));
        }
        
    }
    public void getExp_ClearBtn() {
        selectedDateExpense.setText("0.0");
        expenseDatePicker.setValue(null);
        dateRangeExpense.setText("0.0");
        startExpDatePicker.setValue(null);
        endExpDatePicker.setValue(null);
    } 
    public void getExpDateRangeValidation() {    
        // Disable previous dates
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                
                // Disable all past dates
                if (item.isBefore(startExpDatePicker.getValue())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #EEEEEE;"); // Optional: Grey out past dates
                }
            }
        };
        
        endExpDatePicker.setDayCellFactory(dayCellFactory);
    }
    public void getExpDateValidation() {    
        // Disable previous dates
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                
                // Disable all past dates
                if (item.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #EEEEEE;");
                }
            }
        };
        
        expense_Date.setDayCellFactory(dayCellFactory);
    }
    public void getExpDateValidationForUpdate(DatePicker dateValue) {    
        // Disable previous dates
        Callback<DatePicker, DateCell> dayCellFactory = dp -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                
                // Disable all past dates
                if (item.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #EEEEEE;");
                }
            }
        };
        
        dateValue.setDayCellFactory(dayCellFactory);
    }
      
    /// END EXPENSE SECTION/
    
    
    //// START USERS SECTION
    public void loadAdminData(int adminId) {
    UserDataModel user = db.getAdminUserData(adminId);

    if (user != null) {
        userDisplayName.setText(user.getDisplayName());
        adminUserName.setText(user.getUserName());
        userRole.setText(user.getUserRole());
        userStatus.setText(user.getStatus());
        activeQuestion.setText(user.getQuestion());
        userDate.setText(user.getDate().toString()); // Format the date as needed
        // Load image (if stored as a file path)
        Image userImg = new Image("file:" + user.getImage());
        userImage.setImage(userImg);
        //System.out.println("-------> : "+ user.getImage());
        }
    }
    
    private final String[] empUserRoleList = {"Manager","Cashier"};
    public void empUserRoleList() {

        List<String> xVal = new ArrayList<>();

        for (String data : empUserRoleList) {
            xVal.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(xVal);
        emp_user_role.setItems(listData);
    }
    private final String[] empUserStatusList = {"Active","Deactive"};
    public void empUserStatusList() {

        List<String> xVal = new ArrayList<>();

        for (String data : empUserStatusList) {
            xVal.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(xVal);
        emp_user_status.setItems(listData);
    }
    // MERGE ALL DATAS
    public ObservableList<EmployeeDataModel> empUserDataList() {
        ObservableList<EmployeeDataModel> listData = FXCollections.observableArrayList();
        
        String sql = "SELECT * FROM employees";
        db.getConnection();

        try {

            prepare = db.connection.prepareStatement(sql);
            result = prepare.executeQuery();

            EmployeeDataModel empData;
            
            while (result.next()) {

                empData = new EmployeeDataModel(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("password"),
                        result.getString("user_role"),
                        result.getString("status"),
                        result.getDate("date"));

                listData.add(empData);
            } 
            

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }
    private ObservableList<EmployeeDataModel> empUserListData;
    public void empUserShowData() {
        empUserListData = empUserDataList();

        emp_Col_Sn.setCellValueFactory(new PropertyValueFactory<>("id"));
        emp_Col_Sn.setCellValueFactory(cellData -> {
            int index = empUser_TableView.getItems().indexOf(cellData.getValue()) + 1;
            return new SimpleStringProperty(String.valueOf(index));
        });
        // Setting up the columns for the TableView
        emp_Col_UserName.setCellValueFactory(new PropertyValueFactory<>("username"));
        emp_Col_Password.setCellValueFactory(new PropertyValueFactory<>("password"));
        emp_Col_UserRole.setCellValueFactory(new PropertyValueFactory<>("userRole"));
        emp_Col_Status.setCellValueFactory(new PropertyValueFactory<>("status"));
        emp_Col_Date.setCellValueFactory(new PropertyValueFactory<>("date"));

        // Custom cell factory for the password column to show/hide password
        emp_Col_Password.setCellFactory(column -> new TableCell<EmployeeDataModel, String>() {
            private final Button showHideBtn = new Button("Show");
            private boolean isPasswordVisible = false;

            @Override
            protected void updateItem(String password, boolean empty) {
                super.updateItem(password, empty);

                if (empty || password == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if (isPasswordVisible) {
                        setText(password); // Show the plain password
                        showHideBtn.setText("Hide");
                    } else {
                        setText(maskPassword(password)); // Show masked password
                        showHideBtn.setText("Show");
                    }

                    // Style the button: height 20, grey background
                    showHideBtn.setStyle("-fx-background-color: #e4ae61; -fx-pref-height: 20px; -fx-font-size: 10px;");

                    // Toggle the password visibility when the button is clicked
                    showHideBtn.setOnAction(e -> {
                        isPasswordVisible = !isPasswordVisible;
                        updateItem(password, false);
                    });

                    setGraphic(showHideBtn); // Add the button to the cell
                }
            }

            // Method to mask the password (replace with asterisks)
            private String maskPassword(String password) {
                StringBuilder maskedPassword = new StringBuilder();
                for (int i = 0; i < password.length(); i++) {
                    maskedPassword.append('*');  // You can use any character, like '•'
                }
                return maskedPassword.toString();
            }
        });

        // Set the list of items for the TableView
        empUser_TableView.setItems(empUserListData);
    }
    public void showPass() {
        // Listener for the show/hide password CheckBox
        showPassCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                check_pass = true;
                // Show password in plain text
                emp_passPlainText.setText(emp_password.getText()); // Display current text
                emp_passPlainText.setVisible(true); // Show plain text field
                emp_password.setVisible(false); // Hide PasswordField
            } else {
                check_pass = false;
                // Hide password
                emp_password.setText(emp_passPlainText.getText()); // Set back to masked field
                emp_password.setVisible(true); // Show PasswordField
                emp_passPlainText.setVisible(false); // Hide plain text field
            }
        });
    }  
    public void empSelectData() {
        EmployeeDataModel empData = empUser_TableView.getSelectionModel().getSelectedItem();
        int num = empUser_TableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        emp_id = empData.getId();
        emp_date = empData.getDate();
        // Populate the fields with selected data
        emp_username.setText(empData.getUsername());
        emp_password.setText(empData.getPassword()); // Set the password (masked)
        emp_user_role.setValue(empData.getUserRole());
        emp_user_status.setValue(empData.getStatus());

        // Reset CheckBox state to keep password hidden when selecting new employee
        showPassCheckBox.setSelected(false);
        emp_passPlainText.setVisible(false); // Hide plain text field initially
    }    
    public void empSelectData1() {
    EmployeeDataModel empData = empUser_TableView.getSelectionModel().getSelectedItem();
    int num = empUser_TableView.getSelectionModel().getSelectedIndex();

    if ((num - 1) < -1) {
        return;
    }

        id = empData.getId();
        emp_username.setText(empData.getUsername());
        emp_password.setText(empData.getPassword()); // Initially mask the password
        emp_user_role.setValue(empData.getUserRole());
        emp_user_status.setValue(empData.getStatus());
        getEmpDate = String.valueOf(empData.getDate());
    }
    public void empRegBtn() {
        db.getConnection();
        
        String chkusername = emp_username.getText();
        
        if (emp_username.getText().isEmpty() 
            || emp_password.getText().isEmpty()
            || emp_user_role.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        }
        if(!chkusername.matches("^[\\w]+$")) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username: "+chkusername+"\nEnsures only word characters (letters, digits, and underscores) with no spaces");
            alert.showAndWait();
        }
        
        else{
            
            String regData = "INSERT INTO employees (username, password, user_role, status, date) VALUES (?, ?, ?, ?, ?)";

            try {

                // CHECK IF THE USERNAME IS ALREADY RECORDED IN employees Table
                String checkUsername = "SELECT username FROM employees WHERE username = '"
                        + emp_username.getText() + "'";

                prepare = db.connection.prepareStatement(checkUsername);
                result = prepare.executeQuery();
                
                // CHECK IF THE USERNAME IS ALREADY RECORDED In users Table
                String checkUsernameE = "SELECT username FROM admin WHERE username = '"
                        + emp_username.getText() + "'";

                prepare = db.connection.prepareStatement(checkUsernameE);
                ResultSet rs = prepare.executeQuery();


                if (result.next() || rs.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("'"+emp_username.getText()+"'" + " is already taken, now change username and try again!");
                    alert.showAndWait();
                } else if (emp_password.getText().length() < 6) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Password, atleast 6 characters are needed");
                    alert.showAndWait();
                } else {
                    prepare = db.connection.prepareStatement(regData);

                    prepare.setString(1, emp_username.getText());
                    prepare.setString(2, emp_password.getText());
                    prepare.setString(3, (String) emp_user_role.getSelectionModel().getSelectedItem());
                    prepare.setString(4, "Deactive");
                    prepare.setLong(5, System.currentTimeMillis());
    //                Date date = new Date();
    //                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    //                prepare.setString(7, String.valueOf(sqlDate));

                    prepare.executeUpdate();
                    System.out.println("-> Data Inserted!");

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully registered Account!");
                    alert.showAndWait();
                    
                    empUserShowData();
                    showPass();
                    empClearBtn();
                }

            } catch (SQLException e) {
                System.out.println("-> "+e.toString());
            }
        
        }
        

    }
    private void empUpdateQry() {
        long millis = emp_date.getTime();
        String pass1 = emp_password.getText();
        String pass2 = emp_passPlainText.getText();
        if(check_pass == true){
            emp_pass = pass2;
        }
        else {
            emp_pass = pass1;
        }
        
        String updateData = "UPDATE employees SET "
                    + "username = '" + emp_username.getText() + "', "
                    + "password = '" + emp_pass + "', "
                    + "user_role = '" + emp_user_role.getSelectionModel().getSelectedItem() + "', "
                    + "status = '" + emp_user_status.getSelectionModel().getSelectedItem() + "', "
                    + "date = '" + millis + "' WHERE id = " + 
                    empUser_TableView.getSelectionModel().getSelectedItem().getId();
        
        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE User Name: " + emp_username.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = db.connection.prepareStatement(updateData);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                System.out.println("-> Item Data Updated!");
                // TO UPDATE YOUR TABLE VIEW
                empUserShowData();
                // TO CLEAR YOUR FIELDS
                empClearBtn();
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("-> "+e);
        }
    }
    public void empUpdateBtn() {
        db.getConnection();
        
        if (emp_username.getText().isEmpty() 
            || emp_password.getText().isEmpty()
            || emp_user_role.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please Select User");
            alert.showAndWait();

        } else if(emp_username.getText() == null ? 
                empUser_TableView.getSelectionModel()
                        .getSelectedItem().getUsername() == null : 
                emp_username.getText().equals(empUser_TableView.getSelectionModel()
                        .getSelectedItem().getUsername())) {
            empUpdateQry();
            //System.out.println("-> Update Done!");
            
        } else {

            // CHECK ITEMS CODE
            String checkUsername = "SELECT username FROM employees WHERE username = '"
            + emp_username.getText() + "'";

            try {
                prepare = db.connection.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("'"+emp_username.getText()+"'" + " is already taken, change and try again.");
                    alert.showAndWait();
                } else {
                    empUpdateQry();
                    //System.out.println("-> Update Done!");
                }

            } catch (Exception e) {
                e.printStackTrace();
                //System.out.println("-> "+e);
            }

        }
    }
    public void empDeleteBtn() {
        if (emp_id == 0) {

            alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Message");
            alert.setHeaderText(null);
            alert.setContentText("Please Select User");
            alert.showAndWait();

        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE User: " + emp_username.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM employees WHERE id = " + emp_id;
                try {
                    prepare = db.connection.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Success Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();

                    System.out.println("-> User Deleted!");
                    // TO UPDATE YOUR TABLE VIEW
                    empUserShowData();
                    // TO CLEAR YOUR FIELDS
                    empClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                emp_id = 0;
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }
    public void empClearBtn() {
        emp_username.setText("");
        emp_password.setText("");
        emp_passPlainText.setText("");
        emp_user_role.getSelectionModel().clearSelection();
        emp_user_status.getSelectionModel().clearSelection();
        getEmpDate = "";
        emp_id = 0;
        id = 0;
    }
    public void editProfileFull() {
        db.getConnection();
        UserDataModel user = db.getAdminUserData(1);
        
        // Set up edit button action
        editProfileBtn.setOnAction(event -> {

            // Create a new Stage (window) for editing
            Stage editStage = new Stage();
            editStage.initModality(Modality.APPLICATION_MODAL); // Make the stage modal
            editStage.setTitle("Edit Profile");
            editStage.initStyle(StageStyle.UTILITY); // Make the window undecorated

            // Create a layout for the new window
            VBox editLayout = new VBox(6);
            editLayout.setPadding(new Insets(6));

            // Create fields for User Name, Display Name , Image and others
            //Label userName = new Label(user.getUserName().toString()); 
 
            //TextField userNameField = new TextField(user.getUserName().toString()); 
            HBox nameBox = new HBox();
            nameBox.setPadding(new Insets(5));  // Add padding for visibility
            TextField userNameField = new TextField(user.getUserName());
            userNameField.setEditable(false);
            userNameField.setMinHeight(30);
            nameBox.getChildren().add(userNameField);
            // Apply background and text color to be visible
            userNameField.setStyle("-fx-background-color: lightgray; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
    
            TextField displayNameField = new TextField(user.getDisplayName().toString()); 
            TextField profilePhotoField = new TextField(user.getImage().toString()); 
            
            // Set preferred width and height for the TextFields
            displayNameField.setMinHeight(30);
            displayNameField.setStyle("-fx-font-size: 14px;"); 
            
            profilePhotoField.setMinHeight(30);
            profilePhotoField.setMinWidth(240);

            HBox userRoleBox = new HBox();
            userRoleBox.setPadding(new Insets(5));  // Add padding for visibility
            TextField userRoleField = new TextField(user.getUserRole());
            userRoleField.setEditable(false);
            userRoleField.setMinHeight(30);
            userRoleBox.getChildren().add(userRoleField);
            // Apply background and text color to be visible
            userRoleField.setStyle("-fx-background-color: lightgray; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
            
            HBox statusBox = new HBox();
            statusBox.setPadding(new Insets(5));  // Add padding for visibility
            TextField statusField = new TextField(user.getStatus());
            statusField.setEditable(false);
            statusField.setMinHeight(30);
            statusBox.getChildren().add(statusField);
            // Apply background and text color to be visible
            statusField.setStyle("-fx-background-color: lightgray; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
           
            HBox dateBox = new HBox();
            dateBox.setPadding(new Insets(5));  // Add padding for visibility
            TextField dateField = new TextField(user.getDate().toString());
            dateField.setEditable(false);
            dateField.setMinHeight(30);
            dateBox.getChildren().add(dateField);
            // Apply background and text color to be visible
            dateField.setStyle("-fx-background-color: lightgray; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 16px;");
            
            
            // Add fields to the layout
            editLayout.getChildren().addAll( 
                new Label("User Name: *(Not Editable)"), userNameField,
                new Label("Display Name:"), displayNameField,
                new Label("Profile Photo:"), profilePhotoField,
                new Label("User Role: *(Not Editable)"), userRoleField,
                new Label("Status: *(Not Editable)"), statusField,
                new Label("Creation Date: *(Not Editable)"), dateField
            );

            // Create an Update button to confirm edits
            Button updateButton = new Button("Update");
            // Set button styling
            updateButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

            updateButton.setOnAction(updateEvent -> {

                // Get the values from the input fields
                String displayNameText = displayNameField.getText();
                String profilePhotoText = profilePhotoField.getText();

                // Check if any fields are empty
                if (displayNameText.isEmpty()
                        ||profilePhotoText.isEmpty() ) {
                    System.out.println("-> User Data Empty!");

                    // Show alert if any field is empty
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();

                } else {
                    // Proceed with the update query since all validations passed
                    updateProfile(displayNameText, profilePhotoText);
                    displayNameField.setText("");
                    profilePhotoField.setText("");
                    imagePath = "";
                    
                    //System.out.println("-> User Data Updated!");
                    editStage.close();
                }
            });

            // Create a Close button
            Button closeButton= new Button("Close");
            closeButton.setOnAction(closeEvent -> editStage.close());

            // Add buttons to the layout
            HBox buttonLayout = new HBox(20, updateButton, closeButton);
            editLayout.getChildren().add(buttonLayout);
            buttonLayout.setAlignment(Pos.CENTER);
            VBox.setVgrow(buttonLayout, Priority.ALWAYS);

            // Set the scene and show the stage
            Scene editScene = new Scene(editLayout, 522, 446);

            editStage.setMinWidth(522);
            editStage.setMaxWidth(522);
            editStage.setMinHeight(440);
            editStage.setMaxHeight(440);

            editStage.setScene(editScene);
            editStage.show();
        });
    }
    
    public void editProfile() {
        // Set up edit button action
        editProfileBtn.setOnAction(event -> {
            db.getConnection();
            UserDataModel user = db.getAdminUserData(1);

            // Create a new Stage (window) for editing
            Stage editStage = new Stage();
            editStage.initModality(Modality.APPLICATION_MODAL); // Make the stage modal
            editStage.setTitle("Edit Profile");
            editStage.initStyle(StageStyle.UTILITY); // Make the window undecorated

            // Create a layout for the new window
            VBox editLayout = new VBox(10);
            editLayout.setPadding(new Insets(10));

            // Create fields for Display Name, Profile Photo
            TextField displayNameField = new TextField(user.getDisplayName()); 
            ImageView profilePhotoView = new ImageView();
            Button ppImportBtn = new Button();
            
            ppImportBtn.setMinHeight(10);
            ppImportBtn.setMinWidth(70);
            ppImportBtn.setMaxWidth(70);
            ppImportBtn.setText("Import"); 
            
            imagePath = user.getImage();
            String path = "File:" + user.getImage();
            id = user.getId();
            image = new Image(path, 146, 146, false, true);
            profilePhotoView.setImage(image);
            
            // Set preferred width and height for the TextFields
            displayNameField.setMinHeight(30);
            displayNameField.setStyle("-fx-font-size: 14px;"); 
            
            // Add fields to the layout
            editLayout.getChildren().addAll( 
                new Label("Display Name:"), displayNameField,
                new Label("Profile Photo:"), profilePhotoView,
                ppImportBtn
            );
            
            ppImportBtn.setOnAction(updateEvent -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
                );

                File file = fileChooser.showOpenDialog(main_Form.getScene().getWindow());

                if (file != null) {

                    //path = file.getAbsolutePath();
                    image = new Image(file.toURI().toString(), 146, 146, false, true);
                    profilePhotoView.setImage(image);

                    try {
                        // Get the original file name
                        String originalFileName = file.getName();
                        String newImageName = "pp_" + originalFileName;

                        // Copy the selected file to the MyFolder directory with the new image name
                        File destination = new File(IMAGE_DIR + File.separator + newImageName);
                        Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

                        // Save the image path to the SQLite database
                        imagePath = destination.getAbsolutePath();

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // Create an Update button to confirm edits
            Button updateButton = new Button("Update");
            // Set button styling
            updateButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

            updateButton.setOnAction(updateEvent -> {

                // Get the values from the input fields
                String displayNameText = displayNameField.getText();

                // Check if any fields are empty
                if (displayNameText.isEmpty() ) {
                    System.out.println("-> User Data Empty!");

                    // Show alert if any field is empty
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();

                } else {
                    // Proceed with the update query since all validations passed
                    updateProfile(displayNameText, imagePath);
                    
                    displayNameField.setText("");
                    imagePath = "";
                    //System.out.println("-> User Data Updated!");
                    editStage.close();
                }
            });

            // Create a Close button
            Button closeButton= new Button("Close");
            closeButton.setOnAction(closeEvent -> editStage.close());

            // Add buttons to the layout
            HBox buttonLayout = new HBox(20, updateButton, closeButton);
            editLayout.getChildren().add(buttonLayout);
            buttonLayout.setAlignment(Pos.CENTER);
            VBox.setVgrow(buttonLayout, Priority.ALWAYS);

            // Set the scene and show the stage
            Scene editScene = new Scene(editLayout, 522, 446);

            editStage.setMinWidth(522);
            editStage.setMaxWidth(522);
            editStage.setMinHeight(440);
            editStage.setMaxHeight(440);

            editStage.setScene(editScene);
            editStage.show();
        });
    }
    public void updateProfile(String displayName, String profilePhoto) {
        db.getConnection();
        int user_id = 1;
        
        String updateData = "UPDATE admin SET display_name = '"
                            + displayName + "', image = '"
                            + profilePhoto + "' WHERE id = '"
                            + user_id + "'";
        
        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE your profile info?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = db.connection.prepareStatement(updateData);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                System.out.println("-> Your Profile Updated!");
                // TO UPDATE YOUR TABLE VIEW
                loadAdminData(1);
                
            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("-> "+e);
        }
    }
    
    
    public void changePassword() {
        // Set up edit button action
        changeUserPassBtn.setOnAction(event -> {
            db.getConnection();
            UserDataModel user = db.getAdminUserData(1);

            // Create a new Stage (window) for editing
            Stage editStage = new Stage();
            editStage.initModality(Modality.APPLICATION_MODAL); // Make the stage modal
            editStage.setTitle("Change Password");
            editStage.initStyle(StageStyle.UTILITY); // Make the window undecorated

            // Create a layout for the new window
            VBox editLayout = new VBox(10);
            editLayout.setPadding(new Insets(10));

            // Create fields for Name, Description, Amount, Date, and Category
            ComboBox<String> questionComboBox = new ComboBox<>();
            questionComboBox.getItems().addAll(
                "What is your favorite Color?",
                "What is your favorite food?",
                "What is your favorite person?");
            questionComboBox.setValue(user.getQuestion());
            TextField answerField = new TextField(user.getAnswer().toString()); 
            TextField passwordField = new TextField(user.getPassword().toString()); 
            
            // Set preferred width and height for the TextFields
            passwordField.setMinHeight(30);
            passwordField.setStyle("-fx-font-size: 14px;"); 

            questionComboBox.setMinHeight(30);
            questionComboBox.setMinWidth(240);

            answerField.setMinHeight(30);
            answerField.setMinWidth(240);

            // Add fields to the layout
            editLayout.getChildren().addAll(
                new Label("*Select Question? :"), questionComboBox,
                new Label("*Set Your Answer :"), answerField,
                new Label("*Set Your Password :"), passwordField
            );

            // Create an Update button to confirm edits
            Button updateButton = new Button("Update");
            // Set button styling
            updateButton.setStyle("-fx-background-color: black; -fx-text-fill: white;");

            updateButton.setOnAction(updateEvent -> {

                // Get the values from the input fields
                String questionText = questionComboBox.getValue();
                String answerText = answerField.getText();
                String passwordText = passwordField.getText();

                // Check if any fields are empty
                if (passwordText.isEmpty()
                        ||answerText.isEmpty() ) {
                    System.out.println("-> User Data Empty!");

                    // Show alert if any field is empty
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Warning Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields");
                    alert.showAndWait();

                } else if (passwordField.getText().length() < 6 || passwordField.getText().length() < 6) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Password, Atleast 6 characters are needed");
                    alert.showAndWait();
                } else {
                    // Proceed with the update query since all validations passed
                    updateAdminPass(passwordText, questionText, answerText);
                    
                    questionComboBox.getSelectionModel().clearSelection();
                    answerField.setText("");
                    passwordField.setText("");
                    
                    editStage.close();
                }
            });

            // Create a Close button
            Button closeButton= new Button("Close");
            closeButton.setOnAction(closeEvent -> editStage.close());

            // Add buttons to the layout
            HBox buttonLayout = new HBox(20, updateButton, closeButton);
            editLayout.getChildren().add(buttonLayout);
            buttonLayout.setAlignment(Pos.CENTER);
            VBox.setVgrow(buttonLayout, Priority.ALWAYS);

            // Set the scene and show the stage
            Scene editScene = new Scene(editLayout, 522, 446);

            editStage.setMinWidth(522);
            editStage.setMaxWidth(522);
            editStage.setMinHeight(440);
            editStage.setMaxHeight(440);

            editStage.setScene(editScene);
            editStage.show();
        });
    }
    public void updateAdminPass(String password, String question, String answer) {
        //db.getConnection();
        int user_id = 1;
        
        String updateData = "UPDATE admin SET password = '"
                            + password + "', question = '"
                            + question + "', answer = '"
                            + answer + "' WHERE id = '"
                            + user_id + "'";
        
        try {

            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to change your password?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                prepare = db.connection.prepareStatement(updateData);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();
                
                System.out.println("-> Your Password Updated!");
                db.getAdminUserData(1);
                
//                // TO HIDE MAIN FORM 
//                changeUserPassBtn.getScene().getWindow().hide();
//                // LINK YOUR LOGIN FORM AND SHOW IT 
//                Parent root = FXMLLoader.load(getClass().getResource("/view/authPage.fxml"));
//                Stage stage = new Stage();
//                Scene scene = new Scene(root);
//                stage.setTitle("GoPpo Management System");
//                stage.setScene(scene);
//                stage.show();

            } else {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("-> "+e);
        }
    }
    
    

    
    /// END USERS SECTION/
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Create the folder if not exists
        File folder = new File("Images");
        if (!folder.exists()) {
            folder.mkdir();
        }
        loadAdminData(1);
        //displayUsername(); 
        
        //dashboardDisplayNC();
        //dashboardDisplayTI();
        //dashboardTotalI();
        //dashboardNSP();
        //dashboardIncomeChart();
        //dashboardCustomerChart();
        
        itemsCategoryList();
        itemsStatusList();
        itemsShowData();
        setDynamicColumnWidthForItem();
        
        menuOrderTypeList();
        menuDisplayCard();
        //menuGetOrder();
        //menuDisplayTotal();
        //menuShowOrderData();
        //customersShowData();
        
        getExpDateValidation();
        getExpDateRangeValidation();
        expenseCategoryList();
        expenseShowData();
        setDynamicColumnWidthForExp();
        loadExpenseData();
        
        
        empUserRoleList();
        empUserStatusList();
        empUserShowData();
        showPass();
        
    }

}
