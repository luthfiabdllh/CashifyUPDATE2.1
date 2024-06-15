package org.cashify.cashifyupdate2.Card;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.cashify.cashifyupdate2.ControllerAdmin.DasboardController;
import org.cashify.cashifyupdate2.Product.ProductData;
import org.cashify.cashifyupdate2.Product.ProductDao;

public class ProductCardController implements Initializable {

    @FXML
    private AnchorPane card_form;

    @FXML
    private Label prod_name;

    @FXML
    private Label prod_price;

    @FXML
    private ImageView prod_imageView;

    @FXML
    private Spinner<Integer> prod_spinner;

    @FXML
    private Button prod_addBtn;

    private ProductData prodData;
    private SpinnerValueFactory<Integer> spin;

    private Alert alert;

    private ProductDao productDao;

    public ProductCardController() {
        productDao = new ProductDao();
    }

    public void setData(ProductData prodData) {
        this.prodData = prodData;

        if (prodData != null) {
            prod_name.setText(prodData.getProductName());
            prod_price.setText("Rp. " + prodData.getPrice());

            byte[] imageBytes = prodData.getImage();
            if (imageBytes != null) {
                ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                Image image = new Image(bis);
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

    public void setQuantity() {
        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
        prod_spinner.setValueFactory(spin);
    }

    public void addBtn() {
        DasboardController mForm = new DasboardController();
        mForm.customerID();

        int qty = prod_spinner.getValue();

        if (prodData != null && prodData.getProductId() != null) {
            try {
                productDao.addBtn(prodData, qty);
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Added!");
                alert.showAndWait();

                mForm.menuGetTotal();
            } catch (IllegalStateException e) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setQuantity();
    }
}
