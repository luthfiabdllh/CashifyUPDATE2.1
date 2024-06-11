package org.cashify.cashifyupdate2.Product;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProductCardController {

    @FXML
    private VBox productCard;

    @FXML
    private ImageView productImage;

    @FXML
    private Label productName;

    @FXML
    private Label productPrice;

    @FXML
    private Button addButton;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private Label productStock;

    private ProductData productData;

    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;


    public void setProductData(ProductData productData) {
        this.productData = productData;
        updateCard();
    }

    private void updateCard() {
        productName.setText(productData.getProductName());
        productPrice.setText("Rp. " + productData.getPrice().toString());
        productStock.setText(productData.getStock().toString());
        // Set product image
        // Example: productImage.setImage(new Image(productData.getImage()));
    }

    @FXML
    private void handleAddButton() {
        int quantity = quantitySpinner.getValue();
        // Handle the add button action, e.g., add to cart
    }
}
