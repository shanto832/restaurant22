//package com.example.resturent;
//
//public class TransactionDetails {
//
//    private String foodName;
//    private int quantity;
//    private double unitPrice;
//    private double totalPrice;
//
//    // Default constructor (required for Firestore)
//    public TransactionDetails() {
//    }
//
//    // Constructor with parameters
//    public TransactionDetails(String foodName, int quantity, double unitPrice, double totalPrice) {
//        this.foodName = foodName;
//        this.quantity = quantity;
//        this.unitPrice = unitPrice;
//        this.totalPrice = totalPrice;
//    }
//
//    // Getters and setters
//    public String getFoodName() {
//        return foodName;
//    }
//
//    public void setFoodName(String foodName) {
//        this.foodName = foodName;
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
//    public double getUnitPrice() {
//        return unitPrice;
//    }
//
//    public void setUnitPrice(double unitPrice) {
//        this.unitPrice = unitPrice;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
//    public void setTotalPrice(double totalPrice) {
//        this.totalPrice = totalPrice;
//    }
//}
package com.example.resturent;
public class TransactionDetails {
    private String foodName;
    private int quantity;
    private double unitPrice;
    private double totalPrice;
    private String customerName;  // New field for customer name
    private String customerPhoneNumber;  // New field for customer phone number

    public TransactionDetails(String foodName, int quantity, double unitPrice, double totalPrice, String customerName, String customerPhoneNumber) {
        this.foodName = foodName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.customerName = customerName;
        this.customerPhoneNumber = customerPhoneNumber;
    }

    // Getters and setters for all fields
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
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
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }
}

