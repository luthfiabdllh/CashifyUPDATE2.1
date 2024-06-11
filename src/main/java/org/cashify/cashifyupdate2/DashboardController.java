package org.cashify.cashifyupdate2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class DashboardController {
    @FXML
    private Button dashboardButton;
    @FXML
    private Button productButton;
    @FXML
    private Button inventoryButton;
    @FXML
    private Button reportButton;
    @FXML
    private Button userManagementButton;
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<?> productTableView;
    @FXML
    private TableColumn<?, ?> productNameColumn;
    @FXML
    private TableColumn<?, ?> priceColumn;
    @FXML
    private TableColumn<?, ?> quantityColumn;
    @FXML
    private TextField amountField;

    private User currentUser;

    public void initialize(User user) {
        this.currentUser = user;
        setupActions();
    }

    private void setupActions() {
        logoutButton.setOnAction(event -> handleLogout());
        // Add other button actions here
    }

    private void handleLogout() {
        // Close the dashboard and return to login screen
        logoutButton.getScene().getWindow().hide();
        LoginApp.main(new String[]{});
    }

}
