package org.cashify.cashifyupdate2.ControllerAdmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.cashify.cashifyupdate2.Customer.CustomerData;
import org.cashify.cashifyupdate2.Database.DatabaseConnection;
import org.cashify.cashifyupdate2.Database.data;
import org.cashify.cashifyupdate2.Product.ProductData;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class DasboardController implements Initializable{
    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private AnchorPane product_form;
    @FXML
    private AnchorPane inventory_form;
    @FXML
    private AnchorPane report_form;
    @FXML
    private AnchorPane management_form;
    //    SideButton
    @FXML
    private Button productButton;
    @FXML
    private Button inventoryButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button managementButton;
    @FXML
    private Button dashboardButton;
    @FXML
    private Button logoutButton;


//  Alert
    private Alert alert;
//  Conection
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
//  Image
    private Image image;
//  List
    private ObservableList<ProductData> cardListData = FXCollections.observableArrayList();
//  Report
//    @FXML
//    private AnchorPane dashboard_form;
//    @FXML
//    private Label dashboard_NC;
//    @FXML
//    private Label dashboard_TI;
//    @FXML
//    private Label dashboard_TotalI;
//    @FXML
//    private Label dashboard_NSP;
//    @FXML
//    private AreaChart<?, ?> dashboard_incomeChart;
//    @FXML
//    private BarChart<?, ?> dashboard_CustomerChart;
//  Inventory
    @FXML
    private TableView<ProductData> inventory_tableView;
    @FXML
    private TableColumn<ProductData, String> inventory_col_productID;
    @FXML
    private TableColumn<ProductData, String> inventory_col_productName;
    @FXML
    private TableColumn<ProductData, String> inventory_col_type;
    @FXML
    private TableColumn<ProductData, String> inventory_col_stock;
    @FXML
    private TableColumn<ProductData, String> inventory_col_price;
    @FXML
    private TableColumn<ProductData, String> inventory_col_status;
    @FXML
    private TableColumn<ProductData, String> inventory_col_date;
    @FXML
    private ImageView inventory_imageView;
    @FXML
    private Button inventory_importBtn;
    @FXML
    private Button inventory_addBtn;
    @FXML
    private Button inventory_updateBtn;
    @FXML
    private Button inventory_clearBtn;
    @FXML
    private Button inventory_deleteBtn;
    @FXML
    private TextField inventory_productID;
    @FXML
    private TextField inventory_productName;
    @FXML
    private TextField inventory_stock;
    @FXML
    private TextField inventory_price;
    @FXML
    private ComboBox<?> inventory_status;
    @FXML
    private ComboBox<?> inventory_type;
//  menu
//    @FXML
//    private ScrollPane menu_scrollPane;
//    @FXML
//    private GridPane menu_gridPane;
//    @FXML
//    private TableView<ProductData> menu_tableView;
//    @FXML
//    private TableColumn<ProductData, String> menu_col_productName;
//    @FXML
//    private TableColumn<ProductData, String> menu_col_quantity;
//    @FXML
//    private TableColumn<ProductData, String> menu_col_price;
//    @FXML
//    private Label menu_total;
//    @FXML
//    private TextField menu_amount;
//    @FXML
//    private Label menu_change;
//    @FXML
//    private Button menu_payBtn;
//    @FXML
//    private Button menu_removeBtn;
//    @FXML
//    private Button menu_receiptBtn;
    // dashboard
//    @FXML
//    private AnchorPane dashboard_form;
    // customer
//    @FXML
//    private AnchorPane customers_form;
//    @FXML
//    private TableView<CustomerData> customers_tableView;
//    @FXML
//    private TableColumn<CustomerData, String> customers_col_customerID;
//    @FXML
//    private TableColumn<CustomerData, String> customers_col_total;
//    @FXML
//    private TableColumn<CustomerData, String> customers_col_date;
//    @FXML
//    private TableColumn<CustomerData, String> customers_col_cashier;


//  Sidebar
    public void logout() {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logoutButton.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/ org/cashify/cashifyupdate2/LogIn.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setTitle("Cafe Shop Management System");
                stage.setScene(scene);
                stage.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void switchForm(ActionEvent event) {

        if (event.getSource() == dashboardButton) {
            dashboard_form.setVisible(true);
            inventory_form.setVisible(false);
            product_form.setVisible(false);
            report_form.setVisible(false);
            management_form.setVisible(false);


        } else if (event.getSource() == inventoryButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(true);
            product_form.setVisible(false);
            report_form.setVisible(false);
            management_form.setVisible(false);

        } else if (event.getSource() == productButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            product_form.setVisible(true);
            report_form.setVisible(false);
            management_form.setVisible(false);

        } else if (event.getSource() == reportButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            product_form.setVisible(false);
            report_form.setVisible(true);
            management_form.setVisible(false);
        } else if (event.getSource() == managementButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            product_form.setVisible(false);
            report_form.setVisible(false);
            management_form.setVisible(true);
        }
    }

//  Inventory
    public void inventoryAddBtn() {

        if (inventory_productID.getText().isEmpty()
                || inventory_productName.getText().isEmpty()
                || inventory_type.getSelectionModel().getSelectedItem() == null
                || inventory_stock.getText().isEmpty()
                || inventory_price.getText().isEmpty()
                || inventory_status.getSelectionModel().getSelectedItem() == null
                || data.path == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            // CHECK PRODUCT ID
            String checkProdID = "SELECT prod_id FROM product WHERE prod_id = '"
                    + inventory_productID.getText() + "'";

            connect = DatabaseConnection.getCon();

            try {

                statement = connect.createStatement();
                result = statement.executeQuery(checkProdID);

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(inventory_productID.getText() + " is already taken");
                    alert.showAndWait();
                } else {
                    String insertData = "INSERT INTO product "
                            + "(prod_id, prod_name, type, stock, price, status, image, date) "
                            + "VALUES(?,?,?,?,?,?,?,?)";

                    prepare = connect.prepareStatement(insertData);
                    prepare.setString(1, inventory_productID.getText());
                    prepare.setString(2, inventory_productName.getText());
                    prepare.setString(3, (String) inventory_type.getSelectionModel().getSelectedItem());
                    prepare.setString(4, inventory_stock.getText());
                    prepare.setString(5, inventory_price.getText());
                    prepare.setString(6, (String) inventory_status.getSelectionModel().getSelectedItem());

                    String path = data.path;
                    path = path.replace("\\", "\\\\");

                    prepare.setString(7, path);

                    // TO GET CURRENT DATE
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(8, String.valueOf(sqlDate));

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    inventoryShowData();
                    inventoryClearBtn();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryUpdateBtn() {

        if (inventory_productID.getText().isEmpty()
                || inventory_productName.getText().isEmpty()
                || inventory_type.getSelectionModel().getSelectedItem() == null
                || inventory_stock.getText().isEmpty()
                || inventory_price.getText().isEmpty()
                || inventory_status.getSelectionModel().getSelectedItem() == null
                || data.path == null || data.id == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String path = data.path;
            path = path.replace("\\", "\\\\");

            String updateData = "UPDATE product SET "
                    + "prod_id = '" + inventory_productID.getText() + "', prod_name = '"
                    + inventory_productName.getText() + "', type = '"
                    + inventory_type.getSelectionModel().getSelectedItem() + "', stock = '"
                    + inventory_stock.getText() + "', price = '"
                    + inventory_price.getText() + "', status = '"
                    + inventory_status.getSelectionModel().getSelectedItem() + "', image = '"
                    + path + "', date = '"
                    + data.date + "' WHERE id = " + data.id;

            connect = DatabaseConnection.getCon();

            try {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE PRoduct ID: " + inventory_productID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(updateData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW
                    inventoryShowData();
                    // TO CLEAR YOUR FIELDS
                    inventoryClearBtn();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void inventoryDeleteBtn() {
        if (data.id == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Product ID: " + inventory_productID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE FROM product WHERE id = " + data.id;
                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW
                    inventoryShowData();
                    // TO CLEAR YOUR FIELDS
                    inventoryClearBtn();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
                alert.showAndWait();
            }
        }
    }

    public void inventoryClearBtn() {

        inventory_productID.setText("");
        inventory_productName.setText("");
        inventory_type.getSelectionModel().clearSelection();
        inventory_stock.setText("");
        inventory_price.setText("");
        inventory_status.getSelectionModel().clearSelection();
        data.path = "";
        data.id = 0;
        inventory_imageView.setImage(null);

    }

    // LETS MAKE A BEHAVIOR FOR IMPORT BTN FIRST
    public void inventoryImportBtn() {

        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*png", "*jpg"));

        File file = openFile.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {

            data.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 120, 127, false, true);

            inventory_imageView.setImage(image);
        }
    }

    // MERGE ALL DATAS
    public ObservableList<ProductData> inventoryDataList() {

        ObservableList<ProductData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM product";

        connect = DatabaseConnection.getCon();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prodData;

            while (result.next()) {

                prodData = new ProductData(
                        result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getString("image"),
                        result.getDate("date")) {
                };

                listData.add(prodData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    // TO SHOW DATA ON OUR TABLE
    private ObservableList<ProductData> inventoryListData;

    public void inventoryShowData() {
        inventoryListData = inventoryDataList();

        inventory_col_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        inventory_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        inventory_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        inventory_col_stock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        inventory_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        inventory_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        inventory_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        inventory_tableView.setItems(inventoryListData);

    }

    public void inventorySelectData() {

        ProductData prodData = inventory_tableView.getSelectionModel().getSelectedItem();
        int num = inventory_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        inventory_productID.setText(prodData.getProductId());
        inventory_productName.setText(prodData.getProductName());
        inventory_stock.setText(String.valueOf(prodData.getStock()));
        inventory_price.setText(String.valueOf(prodData.getPrice()));

        data.path = prodData.getImage();

        String path = "File:" + prodData.getImage();
        data.date = String.valueOf(prodData.getDate());
        data.id = prodData.getId();

        image = new Image(path, 120, 127, false, true);
        inventory_imageView.setImage(image);
    }

    private String[] typeList = {"Meals", "Drinks"};

    public void inventoryTypeList() {

        List<String> typeL = new ArrayList<>();

        for (String data : typeList) {
            typeL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(typeL);
        inventory_type.setItems(listData);
    }

    private String[] statusList = {"Available", "Unavailable"};

    public void inventoryStatusList() {

        List<String> statusL = new ArrayList<>();

        for (String data : statusList) {
            statusL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(statusL);
        inventory_status.setItems(listData);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        inventoryTypeList();
        inventoryStatusList();
        inventoryShowData();
    }

}
