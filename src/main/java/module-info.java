module org.cashify.cashifyupdate2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;
    requires jasperreports;
    requires java.desktop;

    opens org.cashify.cashifyupdate2 to javafx.fxml;
    exports org.cashify.cashifyupdate2;
    exports org.cashify.cashifyupdate2.Product;
    opens org.cashify.cashifyupdate2.Product to javafx.fxml;
    exports org.cashify.cashifyupdate2.Database;
    opens org.cashify.cashifyupdate2.Database to javafx.fxml;
    exports org.cashify.cashifyupdate2.Login;
    opens org.cashify.cashifyupdate2.Login to javafx.fxml;
    exports org.cashify.cashifyupdate2.SideController;
    opens org.cashify.cashifyupdate2.SideController to javafx.fxml;
    exports org.cashify.cashifyupdate2.ControllerAdmin;
    opens org.cashify.cashifyupdate2.ControllerAdmin to javafx.fxml;
    opens org.cashify.cashifyupdate2.Card to javafx.fxml;
    exports org.cashify.cashifyupdate2.Card;
    opens org.cashify.cashifyupdate2.Customer to javafx.base;
    opens org.cashify.cashifyupdate2.Users to javafx.base;
    opens org.cashify.cashifyupdate2.ControllerCustomer;
    exports org.cashify.cashifyupdate2.ControllerCustomer;
    opens org.cashify.cashifyupdate2.ControllerEmployee;
    exports org.cashify.cashifyupdate2.ControllerEmployee;
}