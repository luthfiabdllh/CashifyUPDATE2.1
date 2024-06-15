//package org.cashify.cashifyupdate2.Product;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.util.ResourceBundle;
//
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.Spinner;
//import javafx.scene.control.SpinnerValueFactory;
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import org.cashify.cashifyupdate2.Database.DatabaseConnection;
//
//public class ProductCardController implements Initializable {
//
//    @FXML
//    private Label prod_name;
//
//    @FXML
//    private Label prod_price;
//
//    @FXML
//    private Label prod_stock;
//
//    @FXML
//    private ImageView prod_imageView;
//
//    @FXML
//    private Spinner<Integer> prod_spinner;
//
//    @FXML
//    private Button prod_addBtn;
//
//    private ProductData prodData;
//    private ProductService productService;
//    private SpinnerValueFactory<Integer> spin;
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        setQuantity();
//        productService = new ProductDao();
//    }
//
//    public void setData(ProductData prodData) {
//        this.prodData = prodData;
//
//        prod_name.setText(prodData.getProductName());
//        prod_price.setText("Rp. " + prodData.getPrice());
//        prod_stock.setText(String.valueOf(prodData.getStock()));
//
//        String path = "File:" + prodData.getImage();
//        Image image = new Image(path, 190, 94, false, true);
//        prod_imageView.setImage(image);
//
//        // Menampilkan informasi tambahan berdasarkan jenis produk
//        if (prodData instanceof FoodProduct) {
//            FoodProduct foodProduct = (FoodProduct) prodData;
//            prod_stock.setText("Stock: " + foodProduct.getStock() + ", Expires: " + foodProduct.getExpirationDate());
//        } else if (prodData instanceof ClothingProduct) {
//            ClothingProduct clothingProduct = (ClothingProduct) prodData;
//            prod_stock.setText("Stock: " + clothingProduct.getStock() + ", Size: " + clothingProduct.getSize() + ", Color: " + clothingProduct.getColor());
//        } else if (prodData instanceof ElectronicProduct) {
//            ElectronicProduct electronicProduct = (ElectronicProduct) prodData;
//            prod_stock.setText("Stock: " + electronicProduct.getStock() + ", Warranty: " + electronicProduct.getWarranty());
//        }
//    }
//
//    public void setQuantity() {
//        spin = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0);
//        prod_spinner.setValueFactory(spin);
//    }
//
//    @FXML
//    public void addBtn() {
//        int qty = prod_spinner.getValue();
//        try {
//            productService.addBtn(prodData, qty);
//            showAlert(Alert.AlertType.INFORMATION, "Information Message", "Successfully Added!");
//        } catch (IllegalStateException e) {
//            showAlert(Alert.AlertType.ERROR, "Error Message", e.getMessage());
//        } catch (Exception e) {
//            e.printStackTrace();
//            showAlert(Alert.AlertType.ERROR, "Error Message", "Something went wrong. Please try again.");
//        }
//    }
//
//    private void showAlert(Alert.AlertType alertType, String title, String content) {
//        Alert alert = new Alert(alertType);
//        alert.setTitle(title);
//        alert.setHeaderText(null);
//        alert.setContentText(content);
//        alert.showAndWait();
//    }
//}
