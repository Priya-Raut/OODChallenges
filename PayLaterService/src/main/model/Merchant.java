package main.model;

public class Merchant {
    private String merchantName;
    private double discountPercentage;

    public Merchant(String merchantName, double discountPercentage) {
        this.merchantName = merchantName;
        this.discountPercentage = discountPercentage/100;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "merchantName='" + merchantName + '\'' +
                ", discountPercentage=" + discountPercentage +
                '}';
    }
}
