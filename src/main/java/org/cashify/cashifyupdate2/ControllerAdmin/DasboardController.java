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
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.cashify.cashifyupdate2.Card.ProductCardController;
import org.cashify.cashifyupdate2.Customer.CustomerData;
import org.cashify.cashifyupdate2.Database.DatabaseConnection;
import org.cashify.cashifyupdate2.Database.data;
import org.cashify.cashifyupdate2.Product.ProductData;

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

            dashboard_TI.setText("$" + ti);

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
            dashboard_TotalI.setText("$" + ti);

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
                load.setLocation(getClass().getResource("/org/cashify/cashifyupdate2/CardProduct.fxml"));
                AnchorPane pane = load.load();
                ProductCardController cardC = load.getController();
                cardC.setData(cardListData.get(q));

                if (column == 3) {
                    column = 0;
                    row += 1;
                }

                menu_gridPane.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

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

                JasperDesign jDesign = JRXmlLoader.load("E:\\UAS\\CashifyUPDATE2.0\\src\\main\\resources\\org\\cashify\\cashifyupdate2\\report.jrxml");
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
                Parent root = FXMLLoader.load(getClass().getResource("/org/cashify/cashifyupdate2/LogIn.fxml"));
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

        } else if (event.getSource() == managementButton) {
            dashboard_form.setVisible(false);
            inventory_form.setVisible(false);
            product_form.setVisible(false);
            report_form.setVisible(false);
            management_form.setVisible(true);
            customers_form.setVisible(false);
            customersShowData();
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
                || data.path == null || data.id == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        String updateData = "UPDATE product SET prod_id = ?, prod_name = ?, type = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE id = ?";
        connect = DatabaseConnection.getCon();

        try {
            // Membaca gambar sebagai byte array
            File imageFile = new File(data.path);
            byte[] imageBytes = null;
            if (imageFile.exists()) {
                FileInputStream fis = new FileInputStream(imageFile);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int readNum;
                while ((readNum = fis.read(buf)) != -1) {
                    bos.write(buf, 0, readNum);
                }
                imageBytes = bos.toByteArray();
                fis.close();
                bos.close();
            }

            // Mendapatkan tanggal saat ini
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE Product ID: " + inventory_productID.getText() + "?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get().equals(ButtonType.OK)) {
                prepare = connect.prepareStatement(updateData);
                prepare.setString(1, inventory_productID.getText());
                prepare.setString(2, inventory_productName.getText());
                prepare.setString(3, (String) inventory_type.getSelectionModel().getSelectedItem());
                prepare.setInt(4, Integer.parseInt(inventory_stock.getText()));
                prepare.setDouble(5, Double.parseDouble(inventory_price.getText()));
                prepare.setString(6, (String) inventory_status.getSelectionModel().getSelectedItem());
                if (imageBytes != null) {
                    prepare.setBytes(7, imageBytes);
                } else {
                    prepare.setNull(7, java.sql.Types.BLOB);
                }
                prepare.setDate(8, sqlDate);
                prepare.setInt(9, data.id);

                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Updated!");
                alert.showAndWait();

                // Update tabel dan bersihkan input
                inventoryShowData();
                inventoryClearBtn();
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



    public void inventoryDeleteBtn() {
        if (data.id == null || data.id == 0) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
            return;
        }

        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Error Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to DELETE Product ID: " + inventory_productID.getText() + "?");
        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get().equals(ButtonType.OK)) {
            String deleteData = "DELETE FROM product WHERE id = ?";
            try {
                connect = DatabaseConnection.getCon();
                prepare = connect.prepareStatement(deleteData);
                prepare.setInt(1, data.id);
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                // TO UPDATE YOUR TABLE VIEW
                inventoryShowData();
                // TO CLEAR YOUR FIELDS
                inventoryClearBtn();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Cancelled");
            alert.showAndWait();
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
                Image image = new Image(is);
                inventory_imageView.setImage(image);
            } else {
                // Jika gambar tidak ada, kosongkan ImageView
                inventory_imageView.setImage(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public void inventorySelectData() {
//
//        ProductData prodData = inventory_tableView.getSelectionModel().getSelectedItem();
//        int num = inventory_tableView.getSelectionModel().getSelectedIndex();
//
//        if ((num - 1) < -1) {
//            return;
//        }
//
//        inventory_productID.setText(prodData.getProductId());
//        inventory_productName.setText(prodData.getProductName());
//        inventory_stock.setText(String.valueOf(prodData.getStock()));
//        inventory_price.setText(String.valueOf(prodData.getPrice()));
//
//        data.path = Arrays.toString(prodData.getImage());
//
//        String path = "File:" + prodData.getImage();
//        data.date = String.valueOf(prodData.getDate());
//        data.id = prodData.getId();
//
//        byte[] imageBytes = result.getBytes("image");
//        InputStream is = new ByteArrayInputStream(imageBytes);
//        Image image = new Image(is);
////        image = new Image(path, 120, 127, false, true);
//        inventory_imageView.setImage(image);
//    }



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

        customersShowData();

    }

}