package org.cashify.cashifyupdate2.Login;


import org.cashify.cashifyupdate2.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginDao implements LoginService {
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    @Override
    public Boolean login(LoginModel loginModel) {
        String selectData = "SELECT username, password FROM users WHERE username = ? and password = ?";
        connect = DatabaseConnection.getCon();

        try {
            prepare = connect.prepareStatement(selectData);
            prepare.setString(1, loginModel.getUsername());
            prepare.setString(2, loginModel.getPassword());


            result = prepare.executeQuery();
            return result.next();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

}
