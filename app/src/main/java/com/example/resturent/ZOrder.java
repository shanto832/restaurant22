package com.example.resturent;

public class ZOrder {
    private String waiterId;
    private String waiterName;
    private int tableNumber;
    private String foodName;
    private int quantity;

    // Constructor
    public ZOrder(String waiterId, String waiterName, int tableNumber, String foodName, int quantity) {
        this.waiterId = waiterId;
        this.waiterName = waiterName;
        this.tableNumber = tableNumber;
        this.foodName = foodName;
        this.quantity = quantity;
    }

    // Getters
    public String getWaiterId() {
        return waiterId;
    }

    public String getWaiterName() {
        return waiterName;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public String getFoodName() {
        return foodName;
    }

    public int getQuantity() {
        return quantity;
    }
}
