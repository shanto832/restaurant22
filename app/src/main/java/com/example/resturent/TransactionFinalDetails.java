//package com.example.resturent;
//
//public class TransactionFinalDetails {
//    private String foodName;
//    private double unitPrice;
//    private int quantity;
//    private double totalPrice;
//    private String customerName;
//    private String customerPhoneNumber;
//
//    // Constructor to initialize all fields
//    public TransactionFinalDetails(String foodName, double unitPrice, int quantity, double totalPrice, String customerName, String customerPhoneNumber) {
//        this.foodName = foodName;
//        this.unitPrice = unitPrice;
//        this.quantity = quantity;
//        this.totalPrice = totalPrice;
//        this.customerName = customerName;
//        this.customerPhoneNumber = customerPhoneNumber;
//    }
//
//    // Getters and Setters for all fields
//    public String getFoodName() {
//        return foodName;
//    }
//
//    public void setFoodName(String foodName) {
//        this.foodName = foodName;
//    }
//
//    public double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//
//    public String getCustomerName() {
//        return customerName;
//    }
//
//    public void setCustomerName(String customerName) {
//        this.customerName = customerName;
//    }
//
//    public String getCustomerPhoneNumber() {
//        return customerPhoneNumber;
//    }
//
//    public void setCustomerPhoneNumber(String customerPhoneNumber) {
//        this.customerPhoneNumber = customerPhoneNumber;
//    }
//}
package com.example.resturent;

public class TransactionFinalDetails {

    private String foodName;
    private double unitPrice;
    private int quantity;
    private double totalPrice;
    private String customerName;
    private String userPhoneNumber;

    // No-argument constructor (required for Firestore deserialization)
    public TransactionFinalDetails() {
        // Firestore requires this constructor to create an empty instance
    }

    // Constructor with parameters
    public TransactionFinalDetails(String foodName, double unitPrice, int quantity, double totalPrice, String customerName, String customerPhoneNumber) {
        this.foodName = foodName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.userPhoneNumber = customerPhoneNumber;
    }

    // Getters and setters
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhoneNumber() {
        return userPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.userPhoneNumber = customerPhoneNumber;
    }
}

