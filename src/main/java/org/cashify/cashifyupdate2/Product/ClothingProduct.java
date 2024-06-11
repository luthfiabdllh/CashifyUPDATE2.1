package org.cashify.cashifyupdate2.Product;

import java.util.Date;

public class ClothingProduct extends ProductData {
    private String size;
    private String color;

    public ClothingProduct(Integer id, String productId, String productName, String type, Integer stock, Integer quantity, Double price, String status, String image, Date date, String size, String color) {
        super(id, productId, productName, type, stock, quantity, price, status, image, date);
        this.size = size;
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
