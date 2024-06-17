package org.cashify.cashifyupdate2.Card;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.cashify.cashifyupdate2.ControllerAdmin.DasboardController;
import org.cashify.cashifyupdate2.Product.ProductData;

public class ProductCardController extends Card implements Initializable {
//  atrribute fxml
    @FXML
    private Spinner<Integer> prod_spinner;
    @FXML
    private Button prod_addBtn;
    private SpinnerValueFactory<Integer> spin;

    @Override
    public void setData(ProductData prodData) {
        super.setData(prodData);
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
        super.initialize(location, resources);
        setQuantity();
    }
}
