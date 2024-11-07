package com.example.resturent;

public class FoodItem {
    private String name;
    private String price;
    private String quantity;

    // Default constructor required for calls to DataSnapshot.getValue(FoodItem.class)
    public FoodItem() {}

    public FoodItem(String name, String price, String quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPrice() { return price; }
    public void setPrice(String price) { this.price = price; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }
}
