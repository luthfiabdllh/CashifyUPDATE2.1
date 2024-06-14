package org.cashify.cashifyupdate2.Product;
import java.util.Date;

public abstract class ProductData {
    private int id;
    private String productId;
    private String productName;
    private String type;
    private int stock;
    private int quantity;
    private Double price;
    private String status;
    private String image;
    private Date date;

    public ProductData(Integer id, String productId,
                       String productName, String type, Integer stock,
                       Double price, String status, String image, java.sql.Date date) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.image = image;
        this.date = date;
    }

    public ProductData(Integer id, String productId, String productName, String type, Integer quantity, Double price, String image, java.sql.Date date){
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.type = type;
        this.price = price;
        this.image = image;
        this.date = date;
        this.quantity = quantity;
    }


    public Integer getId() {
        return id;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getType() {
        return type;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Double getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
}