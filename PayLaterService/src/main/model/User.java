package main.model;

public class User {
    private String userName;
    private String userEmail;
    private double originalCreditLimit;
    private double remainingCreditLimit;
    private double dueAmount;

    public User(String userName, String userEmail, double originalCreditLimit) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.originalCreditLimit = originalCreditLimit;
        this.remainingCreditLimit = originalCreditLimit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getOriginalCreditLimit() {
        return originalCreditLimit;
    }

    public void setOriginalCreditLimit(double originalCreditLimit) {
        this.originalCreditLimit = originalCreditLimit;
    }

    public double getRemainingCreditLimit() {
        return remainingCreditLimit;
    }

    public void setRemainingCreditLimit(double remainingCreditLimit) {
        this.remainingCreditLimit = remainingCreditLimit;
    }

    public double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public void updateRemainingCreditLimit(double transactionAmount){
        this.remainingCreditLimit -= transactionAmount;
    }

    public void updateDueAmount(double transactionAmount){
        dueAmount += transactionAmount;
    }


    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", originalCreditLimit=" + originalCreditLimit +
                ", remainingCreditLimit=" + remainingCreditLimit +
                ", dueAmount=" + dueAmount +
                '}';
    }
}
