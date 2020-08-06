package main.model;

public class Transaction {
    private String userName;
    private String merchantName;
    private double transactionAmount;
    private double discountAmount;
    private double actualPaybleAmount;

    public Transaction(String userName, String merchantName, double transactionAmount) {
        this.userName = userName;
        this.merchantName = merchantName;
        this.transactionAmount = transactionAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getActualPaybleAmount() {
        return actualPaybleAmount;
    }

    public void setActualPaybleAmount(double actualPaybleAmount) {
        this.actualPaybleAmount = actualPaybleAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "userName='" + userName + '\'' +
                ", merchantName='" + merchantName + '\'' +
                ", transactionAmount=" + transactionAmount +
                ", discountAmount=" + discountAmount +
                ", actualPaybleAmount=" + actualPaybleAmount +
                '}';
    }
}
