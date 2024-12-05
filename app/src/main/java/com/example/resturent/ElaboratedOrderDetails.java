package com.example.resturent;

public class ElaboratedOrderDetails {

    private String waiterId;
    private String waiterName;
    private String foodName;
    private int tableNumber;
    private int quantity;

    // No-argument constructor for Firestore
    public ElaboratedOrderDetails() {}

    // Constructor to initialize the fields
    public ElaboratedOrderDetails(String waiterId, String waiterName, String foodName, int tableNumber, int quantity) {
        this.waiterId = waiterId;
        this.waiterName = waiterName;
        this.foodName = foodName;
        this.tableNumber = tableNumber;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getWaiterId() {
        return waiterId;
    }

    public void setWaiterId(String waiterId) {
        this.waiterId = waiterId;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
