package org.cashify.cashifyupdate2.Login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginApp extends Application {
    private LoginService loginService;

    @Override
    public void start(Stage primaryStage) {
        loginService = new LoginService();

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/cashify/cashifyupdate2/Login.fxml"));

            Parent root = loader.load();

            Scene scene = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
