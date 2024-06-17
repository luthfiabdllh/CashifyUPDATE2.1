package org.cashify.cashifyupdate2.ControllerAdmin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.cashify.cashifyupdate2.Card.ProductCardController;
import org.cashify.cashifyupdate2.Card.ProductCardViewController;
import org.cashify.cashifyupdate2.Customer.CustomerData;
import org.cashify.cashifyupdate2.Database.DatabaseConnection;
import org.cashify.cashifyupdate2.Database.data;
import org.cashify.cashifyupdate2.Product.ProductData;
import org.cashify.cashifyupdate2.Users.UserData;

import java.io.*;
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
    private Button customersButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Label username;

    //  Alert
    private Alert alert;
    //  Conection
    protected Connection connect;
    protected PreparedStatement prepare;
    protected Statement statement;
    protected ResultSet result;
    //  Image
    private Image image;
    //  List
    private ObservableList<ProductData> cardListData = FXCollections.observableArrayList();

    @FXML
    private Label dashboard_NC;
    @FXML
    private Label dashboard_TI;
    @FXML
    private Label dashboard_TotalI;
    @FXML
    private Label dashboard_NSP;
    @FXML
    private AreaChart<?, ?> dashboard_incomeChart;
    @FXML
    private BarChart<?, ?> dashboard_CustomerChart;
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
    @FXML
    private ScrollPane menu_scrollPane;
    @FXML
    private GridPane menu_gridPane;
    @FXML
    private TableView<ProductData> menu_tableView;
    @FXML
    private TableColumn<ProductData, String> menu_col_productName;
    @FXML
    private TableColumn<ProductData, String> menu_col_quantity;
    @FXML
    private TableColumn<ProductData, String> menu_col_price;
    @FXML
    private Label menu_total;
    @FXML
    private TextField menu_amount;
    @FXML
    private Label menu_change;
    @FXML
    private Button menu_payBtn;
    @FXML
    private Button menu_removeBtn;
    @FXML
    private Button menu_receiptBtn;
    // dashboard
    //    @FXML
    //    private AnchorPane dashboard_form;
    // customer
    @FXML
    private AnchorPane customers_form;
    @FXML
    private TableView<CustomerData> customers_tableView;
    @FXML
    private TableColumn<CustomerData, String> customers_col_customerID;
    @FXML
    private TableColumn<CustomerData, String> customers_col_total;
    @FXML
    private TableColumn<CustomerData, String> customers_col_date;
    @FXML
    private TableColumn<CustomerData, String> customers_col_cashier;

    private int cID;

//    Sign UP
    @FXML
    private TextField su_username;
    @FXML
    private TextField su_password;
    @FXML
    private ComboBox<?> su_role;
    @FXML
    private Button su_signupBtn;
    @FXML
    private Button su_updateBtn;

    @FXML
    private TableView<UserData> users_tableView;
    @FXML
    private TableColumn<UserData, String> users_col_username;
    @FXML
    private TableColumn<UserData, String> users_col_password;
    @FXML
    private TableColumn<UserData, String> users_col_role;
    @FXML
    private Button user_deleteBtn;


//    Product
    @FXML
    private GridPane product_gridPane;
    @FXML
    private ScrollPane product_scrollPane;

    public void productDisplayCard() {
        cardListData.clear();
        cardListData.addAll(menuGetData());

        int row = 0;
        int column = 0;

        product_gridPane.getChildren().clear();
        product_gridPane.getRowConstraints().clear();
        product_gridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/org/cashify/cashifyupdate2/Card/CardProductView.fxml"));
                AnchorPane panel = load.load();
                ProductCardViewController cardv = load.getController();
                cardv.setData(cardListData.get(q));

                if (column == 5) {
                    column = 0;
                    row += 1;
                }

                product_gridPane.add(panel, column++, row);

                GridPane.setMargin(panel, new Insets(3));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




    public void userUpdateBtn() {

        if (su_username.getText().isEmpty()
                || su_password.getText().isEmpty()
                || su_role.getSelectionModel().getSelectedItem() == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {

            String updateData ="UPDATE users SET " +
                    "username='" + su_username.getText() + "', " +
                    "password='" + su_password.getText() + "', " +
                    "role='" + su_role.getSelectionModel().getSelectedItem() + "' " +
                    "WHERE username='" + su_username.getText() + "'";

            connect = DatabaseConnection.getCon();

            try {

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE : " + su_username.getText() + "?");
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
                    userShowData();
                    // TO CLEAR YOUR FIELDS
                    userClearBtn();
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



    public void userDeleteBtn() {
        if (data.username == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE account : " + su_username.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                String deleteData = "DELETE from users  " + "WHERE username='" + su_username.getText() + "'";

                try {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("successfully Deleted!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW
                    userShowData();
                    // TO CLEAR YOUR FIELDS
                    userClearBtn();

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



    public void userSelectData() {

        UserData usData = users_tableView.getSelectionModel().getSelectedItem();
        int num = users_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }

        su_username.setText(usData.getUsername());
        su_password.setText(usData.getPassword());
    }

    public ObservableList<UserData> userDataList() {

        ObservableList<UserData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM users";

        connect = DatabaseConnection.getCon();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            UserData usData;

            while (result.next()) {

                usData = new UserData(result.getString("username"),
                        result.getString("password"),
                        result.getString("role"));

                listData.add(usData);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }



    private ObservableList<UserData> userListData;

    public void userShowData() {
        userListData = userDataList();

        users_col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
        users_col_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        users_col_role.setCellValueFactory(new PropertyValueFactory<>("role"));

        users_tableView.setItems(userListData);
    }



    public void user_regBtn() {

        if (su_username.getText().isEmpty() || su_password.getText().isEmpty()
                || su_role.getSelectionModel().getSelectedItem()== null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {

            String regData = "INSERT INTO users (username, password, role) "
                    + "VALUES(?,?,?)";
            connect = DatabaseConnection.getCon();

            try {
                // CHECK IF THE USERNAME IS ALREADY RECORDED
                String checkUsername = "SELECT username FROM users WHERE username = '"
                        + su_username.getText() + "'";

                prepare = connect.prepareStatement(checkUsername);
                result = prepare.executeQuery();

                if (result.next()) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(su_username.getText() + " is already taken");
                    alert.showAndWait();
                } else if (su_password.getText().length() < 8) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Invalid Password, atleast 8 characters are needed");
                    alert.showAndWait();
                } else {
                    prepare = connect.prepareStatement(regData);
                    prepare.setString(1, su_username.getText());
                    prepare.setString(2, su_password.getText());
                    prepare.setString(3, (String) su_role.getSelectionModel().getSelectedItem());


                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully registered Account!");
                    alert.showAndWait();

                    userShowData();
                    userClearBtn();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void userClearBtn() {
        su_username.setText("");
        su_password.setText("");
        su_role.getSelectionModel().clearSelection();
    }

    private String[] usertypeList = {"admin", "employee", "customer"};

    public void userTypeList() {

        List<String> typeL = new ArrayList<>();

        for (String data : usertypeList) {
            typeL.add(data);
        }

        ObservableList llistData = FXCollections.observableArrayList(typeL);
        su_role.setItems(llistData);
    }







    public ObservableList<CustomerData> customersDataList() {

        ObservableList<CustomerData> listData = FXCollections.observableArrayList();
        String sql = "SELECT * FROM receipt";
        connect = DatabaseConnection.getCon();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            CustomerData cData;

            while (result.next()) {
                cData = new CustomerData(result.getInt("id"),
                        result.getInt("customer_id"),
                        result.getDouble("total"),
                        result.getDate("date"),
                        result.getString("em_username"));

                listData.add(cData);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    private ObservableList<CustomerData> customersListData;

    public void customersShowData() {
        customersListData = customersDataList();

        customers_col_customerID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customers_col_total.setCellValueFactory(new PropertyValueFactory<>("total"));
        customers_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        customers_col_cashier.setCellValueFactory(new PropertyValueFactory<>("emUsername"));

        customers_tableView.setItems(customersListData);
    }


    public void dashboardDisplayNC() {

        String sql = "SELECT COUNT(id) FROM receipt";
        connect = DatabaseConnection.getCon();

        try {
            int nc = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                nc = result.getInt("COUNT(id)");
            }
            dashboard_NC.setText(String.valueOf(nc));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dashboardDisplayTI() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String sql = "SELECT SUM(total) FROM receipt WHERE date = '"
                + sqlDate + "'";

        connect = DatabaseConnection.getCon();

        try {
            double ti = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getDouble("SUM(total)");
            }

            dashboard_TI.setText("Rp. " + ti);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardTotalI() {
        String sql = "SELECT SUM(total) FROM receipt";

        connect = DatabaseConnection.getCon();

        try {
            float ti = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                ti = result.getFloat("SUM(total)");
            }
            dashboard_TotalI.setText("Rp. " + ti);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardNSP() {

        String sql = "SELECT COUNT(quantity) FROM customer";

        connect = DatabaseConnection.getCon();

        try {
            int q = 0;
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                q = result.getInt("COUNT(quantity)");
            }
            dashboard_NSP.setText(String.valueOf(q));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardIncomeChart() {
        dashboard_incomeChart.getData().clear();

        String sql = "SELECT date, SUM(total) FROM receipt GROUP BY date ORDER BY TIMESTAMP(date)";
        connect = DatabaseConnection.getCon();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getFloat(2)));
            }

            dashboard_incomeChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dashboardCustomerChart(){
        dashboard_CustomerChart.getData().clear();

        String sql = "SELECT date, COUNT(id) FROM receipt GROUP BY date ORDER BY TIMESTAMP(date)";
        connect = DatabaseConnection.getCon();
        XYChart.Series chart = new XYChart.Series();
        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            while (result.next()) {
                chart.getData().add(new XYChart.Data<>(result.getString(1), result.getInt(2)));
            }

            dashboard_CustomerChart.getData().add(chart);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //
    public ObservableList<ProductData> menuGetData() {

        String sql = "SELECT * FROM product";

        ObservableList<ProductData> listData = FXCollections.observableArrayList();
        DatabaseConnection.getCon();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prod;

            while(result.next()) {
                prod = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getBytes("image"),
                        result.getDate("date"));

                listData.add(prod);
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

        menu_gridPane.getChildren().clear();
        menu_gridPane.getRowConstraints().clear();
        menu_gridPane.getColumnConstraints().clear();

        for (int q = 0; q < cardListData.size(); q++) {

            try {
                FXMLLoader load = new FXMLLoader();
                load.setLocation(getClass().getResource("/org/cashify/cashifyupdate2/Card/CardProduct.fxml"));
                AnchorPane pane = load.load();
                ProductCardController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(5));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public ObservableList<ProductData> menuGetOrder() {
        customerID();
        ObservableList<ProductData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM customer WHERE customer_id = " + cID;

        connect = DatabaseConnection.getCon();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            ProductData prod;

            while (result.next()) {
                prod = new ProductData(result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("quantity"),
                        result.getDouble("price"),
                        result.getBytes("image"),
                        result.getDate("date"));
                listData.add(prod);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listData;
    }

    private ObservableList<ProductData> menuOrderListData;

    public void menuShowOrderData() {
        menuOrderListData = menuGetOrder();

        menu_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        menu_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        menu_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        menu_tableView.setItems(menuOrderListData);
    }
    private int getid;

    public void menuSelectOrder() {
        ProductData prod = menu_tableView.getSelectionModel().getSelectedItem();
        int num = menu_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        // TO GET THE ID PER ORDER
        getid = prod.getId();

    }

    private double totalP;

    public void menuDisplayTotal() {
        menuGetTotal();
        menu_total.setText("Rp. " + totalP);
    }

    private double amount;
    private double change;

    public void menuAmount() {
        menuGetTotal();
        if (menu_amount.getText().isEmpty() || totalP == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Invalid :3");
            alert.showAndWait();
        } else {
            try {
                amount = Double.parseDouble(menu_amount.getText());
                if (amount < totalP) {
                    menu_amount.setText("");
                    // Debug: Print pesan jika jumlah kurang dari total
                    System.out.println("Debug: Amount is less than totalP.");
                } else {
                    change = amount - totalP;
                    menu_change.setText("Rp. " + change);
                    // Debug: Print nilai kembalian
                    System.out.println("Debug: Change = " + change);
                }
            } catch (NumberFormatException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Invalid number format.");
                alert.showAndWait();
            }
        }
    }

    public void menuPayBtn() {

        if (totalP == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please choose your order first!");
            alert.showAndWait();
        } else {
            menuGetTotal();
            String insertPay = "INSERT INTO receipt (customer_id, total, date, em_username) "
                    + "VALUES(?,?,?,?)";

            connect = DatabaseConnection.getCon();

            try {

                if (amount == 0) {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Messaged");
                    alert.setHeaderText(null);
                    alert.setContentText("Something wrong :3");
                    alert.showAndWait();
                } else {
                    alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Confirmation Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Are you sure?");
                    Optional<ButtonType> option = alert.showAndWait();

                    if (option.get().equals(ButtonType.OK)) {
                        customerID();
                        menuGetTotal();
                        prepare = connect.prepareStatement(insertPay);
                        prepare.setString(1, String.valueOf(cID));
                        prepare.setString(2, String.valueOf(totalP));

                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                        prepare.setString(3, String.valueOf(sqlDate));
                        prepare.setString(4, data.username);

                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successful.");
                        alert.showAndWait();

                        menuShowOrderData();

                    } else {
                        alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Infomation Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Cancelled.");
                        alert.showAndWait();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void menuRemoveBtn() {

        if (getid == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the order you want to remove");
            alert.showAndWait();
        } else {
            String deleteData = "DELETE FROM customer WHERE id = " + getid;
            connect = DatabaseConnection.getCon();
            try {
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete this order?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)) {
                    prepare = connect.prepareStatement(deleteData);
                    prepare.executeUpdate();
                }

                menuShowOrderData();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void menuReceiptBtn() {

        if (totalP == 0 || menu_amount.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setContentText("Please order first");
            alert.showAndWait();
        } else {
            HashMap map = new HashMap();
            map.put("getReceipt", (cID - 1));

            try {

                JasperDesign jDesign = JRXmlLoader.load("E:\\UAS\\CashifyUPDATE2.0\\src\\main\\resources\\org\\cashify\\cashifyupdate2\\receipt\\report.jrxml");
                JasperReport jReport = JasperCompileManager.compileReport(jDesign);
                JasperPrint jPrint = JasperFillManager.fillReport(jReport, map, connect);

                JasperViewer.viewReport(jPrint, false);

                menuRestart();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public void menuRestart() {
        totalP = 0;
        change = 0;
        amount = 0;
        menu_total.setText("Rp 0.0");
        menu_amount.setText("");
        menu_change.setText("Rp 0.0");
    }

    public void customerID()  {

        String sql = "SELECT MAX(customer_id) FROM customer";
        connect = DatabaseConnection.getCon();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if (result.next()) {
                cID = result.getInt("MAX(customer_id)");
            }

            String checkCID = "SELECT MAX(customer_id) FROM receipt";
            prepare = connect.prepareStatement(checkCID);
            result = prepare.executeQuery();
            int checkID = 0;
            if (result.next()) {
                checkID = result.getInt("MAX(customer_id)");
            }

            if (cID == 0) {
                cID += 1;
            } else if (cID == checkID) {
                cID += 1;
            }

            data.cID = cID;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void menuGetTotal() {
        customerID();
        String total = "SELECT SUM(price) FROM customer WHERE customer_id = " + cID;

        connect = DatabaseConnection.getCon();

        try {

            prepare = connect.prepareStatement(total);
            result = prepare.executeQuery();

            if (result.next()) {
                totalP = result.getDouble("SUM(price)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    //  Sidebar
    public void displayUsername() {

        String user = data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);

        username.setText(user);

    }


    public void logout() {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                logoutButton.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/org/cashify/cashifyupdate2/Login/LogIn.fxml"));
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
            customers_form.setVisible(false);
            menuDisplayCard();
            menuDisplayTotal();
            menuShowOrderData();

        } else if (event.getSource() == inventoryButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(true);
            product_form.setVisible(false);
            report_form.setVisible(false);
            management_form.setVisible(false);
            customers_form.setVisible(false);
            inventoryTypeList();
            inventoryStatusList();
            inventoryShowData();

        } else if (event.getSource() == productButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            product_form.setVisible(true);
            report_form.setVisible(false);
            management_form.setVisible(false);
            customers_form.setVisible(false);
            productDisplayCard();
        } else if (event.getSource() == reportButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            product_form.setVisible(false);
            report_form.setVisible(true);
            management_form.setVisible(false);
            customers_form.setVisible(false);
            dashboardDisplayNC();
            dashboardDisplayTI();
            dashboardTotalI();
            dashboardNSP();
            dashboardIncomeChart();
            dashboardCustomerChart();
        } else if (event.getSource() == customersButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            product_form.setVisible(false);
            report_form.setVisible(false);
            management_form.setVisible(false);
            customers_form.setVisible(true);
            customersShowData();

        } else if (event.getSource() == managementButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            product_form.setVisible(false);
            report_form.setVisible(false);
            management_form.setVisible(true);
            customers_form.setVisible(false);
            userShowData();
            userTypeList();

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

                    // Konversi gambar ke byte array
                    File imageFile = new File(data.path);
                    FileInputStream fis = new FileInputStream(imageFile);
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    for (int readNum; (readNum = fis.read(buf)) != -1; ) {
                        bos.write(buf, 0, readNum);
                    }
                    byte[] imageBytes = bos.toByteArray();
                    prepare.setBytes(7, imageBytes);

                    // Mendapatkan tanggal saat ini
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setDate(8, sqlDate);

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // Perbarui tampilan tabel
                    inventoryShowData();
                    // Bersihkan form input
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
                || data.id == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String updateData = "UPDATE product SET "
                    + "prod_id = ?, prod_name = ?, type = ?, stock = ?, price = ?, status = ?, image = ?, date = ? "
                    + "WHERE id = ?";

            connect = DatabaseConnection.getCon();

            try {
                // Convert image to byte array if a new image is provided
                byte[] imageBytes = null;
                if (data.path != null && !data.path.isEmpty()) {
                    File imageFile = new File(data.path);

                    if (!imageFile.exists()) {
                        alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Image file does not exist");
                        alert.showAndWait();
                        return;
                    }

                    try (FileInputStream fis = new FileInputStream(imageFile);
                         ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

                        byte[] buf = new byte[1024];
                        int readNum;
                        while ((readNum = fis.read(buf)) != -1) {
                            bos.write(buf, 0, readNum);
                        }
                        imageBytes = bos.toByteArray();
                    }
                }

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Product ID: " + inventory_productID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                    try (PreparedStatement prepare = connect.prepareStatement(updateData)) {
                        prepare.setString(1, inventory_productID.getText());
                        prepare.setString(2, inventory_productName.getText());
                        prepare.setString(3, (String) inventory_type.getSelectionModel().getSelectedItem());
                        prepare.setInt(4, Integer.parseInt(inventory_stock.getText()));
                        prepare.setDouble(5, Double.parseDouble(inventory_price.getText()));
                        prepare.setString(6, (String) inventory_status.getSelectionModel().getSelectedItem());

                        if (imageBytes != null) {
                            prepare.setBytes(7, imageBytes);
                        } else {
                            // Retrieve current image from database if no new image is provided
                            String selectImage = "SELECT image FROM product WHERE id = ?";
                            try (PreparedStatement selectPrepare = connect.prepareStatement(selectImage)) {
                                selectPrepare.setInt(1, data.id);
                                try (ResultSet resultSet = selectPrepare.executeQuery()) {
                                    if (resultSet.next()) {
                                        prepare.setBytes(7, resultSet.getBytes("image"));
                                    } else {
                                        alert = new Alert(Alert.AlertType.ERROR);
                                        alert.setTitle("Error Message");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Product not found");
                                        alert.showAndWait();
                                        return;
                                    }
                                }
                            }
                        }

                        // Get current date
                        Date date = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        prepare.setDate(8, sqlDate);

                        prepare.setInt(9, data.id); // ID of the product to update

                        prepare.executeUpdate();

                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information Message");
                        alert.setHeaderText(null);
                        alert.setContentText("Successfully Updated!");
                        alert.showAndWait();

                        // Update table view
                        inventoryShowData();
                        // Clear input fields
                        inventoryClearBtn();
                    }
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Update cancelled.");
                    alert.showAndWait();
                }
            } catch (Exception e) {
                e.printStackTrace();
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Error updating product: " + e.getMessage());
                alert.showAndWait();
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
            image = new Image(file.toURI().toString(), 100, 100, false, true);

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

            ProductData prod;

            while (result.next()) {

                prod = new ProductData(
                        result.getInt("id"),
                        result.getString("prod_id"),
                        result.getString("prod_name"),
                        result.getString("type"),
                        result.getInt("stock"),
                        result.getDouble("price"),
                        result.getString("status"),
                        result.getBytes("image"),
                        result.getDate("date")) {
                };

                listData.add(prod);

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

        if (num < 0 || prodData == null || prodData.getId() == null) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Selected product data is invalid");
            alert.showAndWait();
            return;
        }

        inventory_productID.setText(prodData.getProductId());
        inventory_productName.setText(prodData.getProductName());
        inventory_stock.setText(String.valueOf(prodData.getStock()));
        inventory_price.setText(String.valueOf(prodData.getPrice()));

        data.id = prodData.getId();

        try {
            // Menampilkan gambar dari byte array
            byte[] imageBytes = prodData.getImage();
            if (imageBytes != null && imageBytes.length > 0) {
                InputStream is = new ByteArrayInputStream(imageBytes);
                Image image = new Image(is, 100, 100, false, true);
                inventory_imageView.setImage(image);
            } else {
                // Jika gambar tidak ada, kosongkan ImageView
                inventory_imageView.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        displayUsername();

        dashboardDisplayNC();
        dashboardDisplayTI();
        dashboardTotalI();
        dashboardNSP();
        dashboardIncomeChart();
        dashboardCustomerChart();

        inventoryTypeList();
        inventoryStatusList();
        inventoryShowData();

        menuDisplayCard();
        menuGetOrder();
        menuDisplayTotal();
        menuShowOrderData();

        productDisplayCard();

        customersShowData();

        userShowData();
        userTypeList();



    }

}