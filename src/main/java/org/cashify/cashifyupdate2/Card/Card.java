package org.cashify.cashifyupdate2.Card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.cashify.cashifyupdate2.Product.ProductData;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public abstract class Card implements Initializable {
//  attribute fxml
    @FXML
    private AnchorPane card_form;
    @FXML
    private Label prod_name;
    @FXML
    private Label prod_price;
    @FXML
    private ImageView prod_imageView;
//  attribute model
    protected ProductData prodData;
    protected cardDao productDao;
//  attribute lain
    protected Alert alert;

    public Card() {
        productDao = new cardDao();
    }

    public void setData(ProductData prodData) {
        this.prodData = prodData;

        if (prodData != null) {
            prod_name.setText(prodData.getProductName());
            prod_price.setText("Rp. " + prodData.getPrice());

            byte[] imageBytes = prodData.getImage();
            if (imageBytes != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                Image image = new Image(bis, 200, 200, false, true);
                prod_imageView.setImage(image);
            } else {
                prod_imageView.setImage(null);
            }
        } else {
            prod_name.setText("Unknown");
            prod_price.setText("Rp. 0");
            prod_imageView.setImage(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
