package org.cashify.cashifyupdate2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

    public User login(String username, String password) {
        return User.getUserByUsernameAndPassword(username, password);
    }
}
