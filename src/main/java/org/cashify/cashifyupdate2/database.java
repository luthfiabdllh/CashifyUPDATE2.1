package org.cashify.cashifyupdate2;

import java.sql.Connection;
import java.sql.DriverManager;

public class database {
        public static Connection connectDB() {

            try {

                Class.forName("com.mysql.jdbc.Driver");

                Connection connect = DriverManager.getConnection("jdbc:mysql://localhost/cafe", "root", ""); // LINK YOUR DATABASE
                return connect;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        public static void main(String[] args) {
        try {
            // Memanggil metode getCon untuk mendapatkan koneksi ke database
            Connection connection = DatabaseConnection.getCon();
            if (connection != null) {
                System.out.println("Koneksi ke database berhasil!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}