package org.cashify.cashifyupdate2.Login;

public class LoginService {

    public User login(String username, String password) {
        return User.getUserByUsernameAndPassword(username, password);
    }
}
