package org.cashify.cashifyupdate2.Card;
import org.cashify.cashifyupdate2.Product.ProductData;

import java.sql.SQLException;

public interface cardService {
    public void addBtn(ProductData prodData, int qty)throws SQLException;
}
