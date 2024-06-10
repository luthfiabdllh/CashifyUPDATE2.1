package org.cashify.cashifyupdate2;

public class Customer extends User {
    private boolean isMember;

    public Customer(int id, String username, String password, boolean isMember) {
        super(id, username, password, User.Role.CUSTOMER);
        this.isMember = isMember;
    }

    public boolean isMember() {
        return isMember;
    }

    public void buyProduct(Product product, int quantity) {
        double price = product.getPrice() * quantity;
        if (isMember) {
            price *= 0.9; // Diskon 10%
        }
        System.out.println("Total price: " + price);
    }

    @Override
    public void displayDashboard() {
        System.out.println("Welcome to Customer Dashboard");
    }
}
