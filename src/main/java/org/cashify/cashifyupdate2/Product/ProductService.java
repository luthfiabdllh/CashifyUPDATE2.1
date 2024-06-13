package org.cashify.cashifyupdate2.Product;

import org.cashify.cashifyupdate2.Product.ProductData;

import java.sql.SQLException;

public interface ProductService {
    public void addBtn(ProductData prodData, int qty)throws SQLException;
}
