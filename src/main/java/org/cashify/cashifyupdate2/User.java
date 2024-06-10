package org.cashify.cashifyupdate2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class User {
    private int id;
    private String username;
    private String password;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    private Role role;  // Properly referencing the Role enum

    // Enum Role inside the User class
    public enum Role {
        EMPLOYEE,
        CUSTOMER,
        OWNER
    }

    public User(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        User user = null;
        String query = "SELECT * FROM User WHERE username = ? AND password = ?";

        try (Connection connection = DatabaseConnection.getCon();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("Id_User");
                String roleString = resultSet.getString("role");
                Role role = Role.valueOf(roleString);
                boolean isMember = resultSet.getBoolean("is_member");

                switch (role) {
                    case CUSTOMER:
                        user = new Customer(id, username, password, isMember); // parameter role dihapus
                        break;
                    case OWNER:
                        user = new Owner(id, username, password, role);
                        break;
                    case EMPLOYEE:
                        user = new Employee(id, username, password, role);
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }
    public static void displayAllUsers() {
        String query = "SELECT * FROM User";

        try (Connection connection = DatabaseConnection.getCon();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("Id_User");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String roleString = resultSet.getString("role");
                Role role = Role.valueOf(roleString);
                boolean isMember = resultSet.getBoolean("is_member");

                System.out.println("ID: " + id + ", Username: " + username + ", Role: " + role + ", Is Member: " + isMember);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public abstract void displayDashboard();
}