package org.cashify.cashifyupdate2.Card;

import org.cashify.cashifyupdate2.Database.DatabaseConnection;
import org.cashify.cashifyupdate2.Database.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class cardDao implements cardService {
    private Connection connect;

    public cardDao() {
        connect = DatabaseConnection.getCon();
    }

    @Override
    public void addBtn(ProductData prodData, int qty) throws SQLException {
        String prodID = prodData.getProductId();
        String checkAvailable = "SELECT status, stock FROM product WHERE prod_id = ?";
        String updateStockQuery = "UPDATE product SET prod_name = ?, type = ?, stock = ?, price = ?, status = ?, image = ?, date = ? WHERE prod_id = ?";
        String insertDataQuery = "INSERT INTO customer (customer_id, prod_id, prod_name, type, quantity, price, date, image, em_username) VALUES (?,?,?,?,?,?,?,?,?)";

        PreparedStatement prepare = null;
        ResultSet result = null;

        try {
            // Check product availability and stock
            prepare = connect.prepareStatement(checkAvailable);
            prepare.setString(1, prodID);
            result = prepare.executeQuery();

            if (!result.next()) {
                throw new IllegalStateException("Product not found");
            }

            int stock = result.getInt("stock");
            String status = result.getString("status");

            // If stock is zero, update product status to "Unavailable"
            if (stock == 0) {
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
                throw new IllegalStateException("Product is unavailable");
            }

            // Check if product is available and quantity is valid
            if (!status.equals("Available") || qty == 0) {
                throw new IllegalStateException("Product is not available or quantity is zero");
            }

            // Check if requested quantity is available
            if (stock < qty) {
                throw new IllegalStateException("Invalid. This product is out of stock");
            }

            // Calculate total price
            double totalP = qty * prodData.getPrice();
            Date date = new Date();
            java.sql.Date sqlDate = new java.sql.Date(date.getTime());

            // Insert customer order
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

            // Update product stock
            int updatedStock = stock - qty;
            prepare = connect.prepareStatement(updateStockQuery);
            prepare.setString(1, prodData.getProductName());
            prepare.setString(2, prodData.getType());
            prepare.setInt(3, updatedStock);
            prepare.setDouble(4, prodData.getPrice());
            prepare.setString(5, updatedStock == 0 ? "Unavailable" : status);
            prepare.setBytes(6, prodData.getImage());
            prepare.setDate(7, new java.sql.Date(prodData.getDate().getTime()));
            prepare.setString(8, prodID);
            prepare.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (result != null) result.close();
            if (prepare != null) prepare.close();
        }
    }
}
