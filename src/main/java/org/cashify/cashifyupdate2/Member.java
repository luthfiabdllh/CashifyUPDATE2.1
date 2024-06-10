package org.cashify.cashifyupdate2;

public class Member extends Customer{
    private int points;

    public Member(int id, String username, String password, boolean isMember, int initialPoints) {
        super(id, username, password, isMember);
        this.points = initialPoints;
    }

    public int getPoints(){
        return points;
    }

    public void addPoints(int newPoints){
        this.points = this.points + newPoints;
    }

    public void usePoints(int usePoints){
        if (this.points >= usePoints){
            this.points = this.points - usePoints;
        } else {
            System.out.println("Not enough points");
        }
    }

    public void displayDashboard(){
        System.out.println("Welcome to Member Dashboard");
    }
}
