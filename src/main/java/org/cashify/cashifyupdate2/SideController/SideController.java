package org.cashify.cashifyupdate2.SideController;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class SideController {
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

    private Alert alert;

    public void dashboardButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cashify/cashifyupdate2/AdminGUI/Dashboard-AdminGUI.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Cafe Shop Management System");
            stage.setMinWidth(1280);
            stage.setMinHeight(720);
            stage.setScene(scene);
            stage.show();

            dashboardButton.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void productButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cashify/cashifyupdate2/Product-AdminGUI.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Cafe Shop Management System");
            stage.setMinWidth(1280);
            stage.setMinHeight(720);
            stage.setScene(scene);
            stage.show();

            productButton.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inventoryButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cashify/cashifyupdate2/Inventory-AdminGUI.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Cafe Shop Management System");
            stage.setMinWidth(1280);
            stage.setMinHeight(720);
            stage.setScene(scene);
            stage.show();

            inventoryButton.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void managementButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cashify/cashifyupdate2/UserManagement-AdminGUI.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Cafe Shop Management System");
            stage.setMinWidth(1280);
            stage.setMinHeight(720);
            stage.setScene(scene);
            stage.show();

            managementButton.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reportButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cashify/cashifyupdate2/Report-AdminGUI.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setTitle("Cafe Shop Management System");
            stage.setMinWidth(1280);
            stage.setMinHeight(720);
            stage.setScene(scene);
            stage.show();

            reportButton.getScene().getWindow().hide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void logout() {

        try {

            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // TO HIDE MAIN FORM
                logoutButton.getScene().getWindow().hide();

                // LINK YOUR LOGIN FORM AND SHOW IT
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
}
