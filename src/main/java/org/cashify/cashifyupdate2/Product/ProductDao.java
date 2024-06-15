package org.cashify.cashifyupdate2.Product;

import org.cashify.cashifyupdate2.Database.DatabaseConnection;
import org.cashify.cashifyupdate2.Database.data;
import org.cashify.cashifyupdate2.Product.ProductData;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ProductDao implements ProductService {
    private Connection connect;

    public ProductDao() {
        connect = DatabaseConnection.getCon();
    }

    @Override
    public void addBtn(ProductData prodData, int qty) throws SQLException {
        String prodID = prodData.getProductId();
        String checkAvailable = "SELECT status FROM product WHERE prod_id = ?";
        String checkStockQuery = "SELECT stock FROM product WHERE prod_id = ?";
        String updateStockQuery = "UPDATE product SET prod_name = ?, type = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE prod_id = ?";
        String insertDataQuery = "INSERT INTO customer (customer_id, prod_id, prod_name, type, quantity, price, date, image, em_username) VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement prepare;
        ResultSet result;

        try {
            int checkStck = 0;
            prepare = connect.prepareStatement(checkStockQuery);
            prepare.setString(1, prodID);
            result = prepare.executeQuery();

            if (result.next()) {
                checkStck = result.getInt("stock");
            }

            if (checkStck == 0) {
                prepare = connect.prepareStatement(updateStockQuery);
                prepare.setString(1, prodData.getProductName());
                prepare.setString(2, prodData.getType());
                prepare.setInt(3, 0);
                prepare.setDouble(4, prodData.getPrice());
                prepare.setString(5, "Unavailable");
                prepare.setBytes(6, prodData.getImage());
                prepare.setDate(7, new java.sql.Date(prodData.getDate().getTime()));
                prepare.setString(8, prodID);
                prepare.executeUpdate();
            }

            prepare = connect.prepareStatement(checkAvailable);
            prepare.setString(1, prodID);
            result = prepare.executeQuery();

            String check = "";
            if (result.next()) {
                check = result.getString("status");
            }

            if (!check.equals("Available") || qty == 0) {
                throw new IllegalStateException("Product is not available or quantity is zero");
            } else {
                if (checkStck < qty) {
                    throw new IllegalStateException("Invalid. This product is Out of stock");
                } else {
                    double totalP = qty * prodData.getPrice();
                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare = connect.prepareStatement(insertDataQuery);
                    prepare.setString(1, String.valueOf(data.cID));
                    prepare.setString(2, prodID);
                    prepare.setString(3, prodData.getProductName());
                    prepare.setString(4, prodData.getType());
                    prepare.setInt(5, qty);
                    prepare.setDouble(6, totalP);
                    prepare.setDate(7, sqlDate);
                    prepare.setBytes(8, prodData.getImage());
                    prepare.setString(9, data.username);
                    prepare.executeUpdate();

                    int upStock = checkStck - qty;

                    prepare = connect.prepareStatement(updateStockQuery);
                    prepare.setString(1, prodData.getProductName());
                    prepare.setString(2, prodData.getType());
                    prepare.setInt(3, upStock);
                    prepare.setDouble(4, prodData.getPrice());
                    prepare.setString(5, check);
                    prepare.setBytes(6, prodData.getImage());
                    prepare.setDate(7, new java.sql.Date(prodData.getDate().getTime()));
                    prepare.setString(8, prodID);
                    prepare.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
