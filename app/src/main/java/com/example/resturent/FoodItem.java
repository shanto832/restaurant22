package com.example.resturent;

import androidx.annotation.NonNull;

public class FoodItem {
    private String name;
    private String image;
    private double price;

    public FoodItem() {
        // Default constructor required for calls to DataSnapshot.getValue(FoodItem.class)
    }

    public FoodItem(String name, String image, double price) {
        this.name = name;
        this.image = image;
        this.price = price;
    }

    // Getters and setters (if necessary)
    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public double getPrice() {
        return price;
    }

    @NonNull
    @Override
    public String toString() {
        return "FoodItem{" +
                "name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
