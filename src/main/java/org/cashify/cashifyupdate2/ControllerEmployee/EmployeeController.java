package org.cashify.cashifyupdate2.ControllerEmployee;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import org.cashify.cashifyupdate2.ControllerAdmin.DasboardController;
import org.cashify.cashifyupdate2.Customer.CustomerData;
import org.cashify.cashifyupdate2.Product.ProductData;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

public class EmployeeController extends DasboardController {
    protected Connection connect;
    protected PreparedStatement prepare;
    protected Statement statement;
    protected ResultSet result;

    @Override
    public void switchForm(ActionEvent event) {
        super.switchForm(event);
    }

    @Override
    public void menuReceiptBtn() {
        super.menuReceiptBtn();
    }

    @Override
    public ObservableList<ProductData> menuGetOrder() {
        return super.menuGetOrder();
    }

    @Override
    public ObservableList<ProductData> menuGetData() {
        return super.menuGetData();
    }

    @Override
    public void menuDisplayCard() {
        super.menuDisplayCard();
    }

    @Override
    public void menuAmount() {
        super.menuAmount();
    }

    @Override
    public void menuPayBtn() {
        super.menuPayBtn();
    }

    @Override
    public void menuDisplayTotal() {
        super.menuDisplayTotal();
    }

    @Override
    public void menuGetTotal() {
        super.menuGetTotal();
    }

    @Override
    public void menuRemoveBtn() {
        super.menuRemoveBtn();
    }

    @Override
    public void menuRestart() {
        super.menuRestart();
    }

    @Override
    public void menuSelectOrder() {
        super.menuSelectOrder();
    }

    @Override
    public void menuShowOrderData() {
        super.menuShowOrderData();
    }

    @Override
    public ObservableList<CustomerData> customersDataList() {
        return super.customersDataList();
    }

    @Override
    public void customerID() {
        super.customerID();
    }

    @Override
    public void customersShowData() {
        super.customersShowData();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }
}
