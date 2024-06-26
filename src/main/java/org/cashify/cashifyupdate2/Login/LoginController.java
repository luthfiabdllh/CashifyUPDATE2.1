package org.cashify.cashifyupdate2.Login;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.cashify.cashifyupdate2.Database.data;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField si_username;
    @FXML
    private PasswordField si_password;
    @FXML
    private Button si_loginBtn;

    private Alert alert;

    public void loginBtn() {
        if (si_username.getText().isEmpty() || si_password.getText().isEmpty()) {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Incorrect Username/Password");
            alert.showAndWait();
        } else {
            LoginModel loginModel = new LoginModel(si_username.getText(), si_password.getText());
            LoginDao loginDao = new LoginDao();
            try {
                if (loginDao.login(loginModel)) {
                    data.username = si_username.getText();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login!");
                    alert.showAndWait();

                    // Load the appropriate dashboard based on the user role
                    String role = loginModel.getRole();
                    String fxmlFile = "";

                    switch (role.toLowerCase()) {
                        case "admin":
                            fxmlFile = "/org/cashify/cashifyupdate2/AdminGUI/Dashboard-AdminGUI.fxml";
                            break;
                        case "employee":
                            fxmlFile = "/org/cashify/cashifyupdate2/EmployeeGUI/EmployeeGUI.fxml";
                            break;
                        case "customer":
                            fxmlFile = "/org/cashify/cashifyupdate2/CustomerGUI/CustomerGUI.fxml";
                            break;
                        default:
                            alert = new Alert(Alert.AlertType.ERROR);
                            alert.setTitle("Error Message");
                            alert.setHeaderText(null);
                            alert.setContentText("Invalid role");
                            alert.showAndWait();
                            return;
                    }

                    FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
                    Parent root = loader.load();
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    stage.setTitle("Cashify");
                    stage.setMinWidth(1280);
                    stage.setMinHeight(720);
                    stage.setScene(scene);
                    stage.show();

                    // Hide the current login window
                    si_loginBtn.getScene().getWindow().hide();
                } else {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Incorrect Username/Password");
                    alert.showAndWait();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

