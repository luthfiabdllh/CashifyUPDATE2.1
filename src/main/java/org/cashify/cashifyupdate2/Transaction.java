package org.cashify.cashifyupdate2;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private Product product;
    private int quantity;
    private double totalPrice;
    private LocalDateTime transactionDate;

    public Transaction(int transactionId, Product product, int quantity, LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.product = product;
        this.quantity = quantity;
        this.transactionDate = transactionDate;
        this.totalPrice = calculateTotalPrice();
    }

    public int getTransactionId() {
        return transactionId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
        this.totalPrice = calculateTotalPrice(); // Recalculate total price when product changes
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.totalPrice = calculateTotalPrice(); // Recalculate total price when quantity changes
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double calculateTotalPrice() {
        return product.getPrice() * quantity;
    }

    public double calculateTotalPrice(Member member, int usePoints, boolean usePoint) {
        double finalPrice = calculateTotalPrice();
        if (member.isMember()) {
            if (member.getPoints() >= usePoints && usePoint) {
                finalPrice -= (usePoints * 1000);
                member.usePoints(usePoints);
            }
            // Add points to member based on the final price
            int pointsEarned = (int) (finalPrice / 10000);
            member.addPoints(pointsEarned);
        }
        return finalPrice;
    }

    public void displayDetails() {
        System.out.println("Transaction ID: " + getTransactionId());
        System.out.println("Product: " + getProduct().getItemName());
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Total Price: " + getTotalPrice());
        System.out.println("Transaction Date: " + getTransactionDate());
    }

//    public void saveToDatabase() {
//        String query = "INSERT INTO Transactions (transactionId, productId, quantity, totalPrice, transactionDate) VALUES (?, ?, ?, ?, ?)";
//
//        try (Connection connection = DatabaseConnection.getCon();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setInt(1, getTransactionId());
//            statement.setInt(2, getProduct().getItemId());
//            statement.setInt(3, getQuantity());
//            statement.setDouble(4, getTotalPrice());
//            statement.setTimestamp(5, java.sql.Timestamp.valueOf(getTransactionDate()));
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Transaction loadFromDatabase(int transactionId) {
//        String query = "SELECT * FROM Transactions WHERE transactionId = ?";
//        Transaction transaction = null;
//
//        try (Connection connection = DatabaseConnection.getCon();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setInt(1, transactionId);
//
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                int productId = resultSet.getInt("productId");
//                int quantity = resultSet.getInt("quantity");
//                double totalPrice = resultSet.getDouble("totalPrice");
//                LocalDateTime transactionDate = resultSet.getTimestamp("transactionDate").toLocalDateTime();
//
//                // Load product details from database
//                Product product = loadProductFromDatabase(productId);
//
//                transaction = new Transaction(transactionId, product, quantity, transactionDate);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return transaction;
//    }
//
//    private static Product loadProductFromDatabase(int productId) {
//        String query = "SELECT * FROM Product WHERE itemId = ?";
//        Product product = null;
//
//        try (Connection connection = DatabaseConnection.getCon();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setInt(1, productId);
//
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                String itemName = resultSet.getString("itemName");
//                double price = resultSet.getDouble("price");
//                int stock = resultSet.getInt("stock");
//
//                product = new Product(productId, itemName, price);
//                product.setStock(stock);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return product;
//    }
}
