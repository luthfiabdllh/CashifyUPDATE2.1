package org.cashify.cashifyupdate2;

class Employee extends User {
    public Employee(int id, String username, String password, User.Role role) {
        super(id, username, password, role);
    }

    @Override
    public void displayDashboard() {
        // Implementasi spesifik untuk Employee
    }
}