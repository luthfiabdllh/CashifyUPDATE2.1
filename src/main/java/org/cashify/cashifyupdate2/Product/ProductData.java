package org.cashify.cashifyupdate2.Product;
import java.util.Date;

public class ProductData {
    private Integer id;
    private String productId;
    private String productName;
    private String type;
    private Integer stock;
    private Integer quantity;
    private Double price;
    private String status;
    private byte[] image;
    private Date date;

    public ProductData(Integer id, String productId, String productName, String type, Integer stock, Double price, String status, byte[] image, java.sql.Date date) {
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

    public ProductData(Integer id, String productId, String productName, String type, Integer quantity, Double price, byte[] image, java.sql.Date date){
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

    public byte[] getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }
}