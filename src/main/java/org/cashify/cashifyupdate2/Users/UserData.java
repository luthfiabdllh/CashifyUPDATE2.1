package org.cashify.cashifyupdate2.Users;

public class UserData {
    public String username;
    public String password;
    public String role;

    public UserData(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }
}
