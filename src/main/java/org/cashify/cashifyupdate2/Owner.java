package org.cashify.cashifyupdate2;

class Owner extends User {
    public Owner(int id, String username, String password, User.Role role) {
        super(id, username, password, role);
    }

    @Override
    public void displayDashboard() {
        // Implementasi spesifik untuk Owner
    }
}