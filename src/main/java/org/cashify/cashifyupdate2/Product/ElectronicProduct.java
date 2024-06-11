package org.cashify.cashifyupdate2.Product;

import java.util.Date;

public class ElectronicProduct extends ProductData {
    private String warranty;

    public ElectronicProduct(Integer id, String productId, String productName, String type, Integer stock, Integer quantity, Double price, String status, String image, Date date, String warranty) {
        super(id, productId, productName, type, stock, quantity, price, status, image, date);
        this.warranty = warranty;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}

