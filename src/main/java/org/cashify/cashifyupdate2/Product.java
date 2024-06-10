package org.cashify.cashifyupdate2;

public class Product extends Item {
    private int stock;

    public Product(int itemId, String itemName, double price) {
        super(itemId, itemName, price);
    }

    public void setStock(int stock){
        this.stock = stock;
    }

    public int getStock(){
        return stock;
    }

    public void addStock(int newStock){
        this.stock = this.stock + newStock;
    }

    public void displayDetails(){
        System.out.println("Product ID: " + getItemId());
        System.out.println("Product name: " + getItemName());
        System.out.println("Price: IDR." + getPrice());
        System.out.println("Stock: " + getStock());
    }

//    public void saveToDatabase() {
//        String query = "INSERT INTO Product (itemId, itemName, price, stock) VALUES (?, ?, ?, ?)";
//
//        try (Connection connection = DatabaseConnection.getCon();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setInt(1, getItemId());
//            statement.setString(2, getItemName());
//            statement.setDouble(3, getPrice());
//            statement.setInt(4, getStock());
//
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void loadFromDatabase(int itemId) {
//        String query = "SELECT * FROM Product WHERE itemId = ?";
//
//        try (Connection connection = DatabaseConnection.getCon();
//             PreparedStatement statement = connection.prepareStatement(query)) {
//
//            statement.setInt(1, itemId);
//
//            ResultSet resultSet = statement.executeQuery();
//            if (resultSet.next()) {
//                String itemName = resultSet.getString("itemName");
//                double price = resultSet.getDouble("price");
//                int stock = resultSet.getInt("stock");
//
//                // Mengatur nilai properti berdasarkan data dari basis data
//                setItemName(itemName);
//                setPrice(price);
//                setStock(stock);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
}
