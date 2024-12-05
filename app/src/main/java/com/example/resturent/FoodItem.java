
//package com.example.resturent;
//
//public class FoodItem {
//    private String foodName;
//    private double foodPrice;
//    private int foodQuantity;
//
//    // No-argument constructor (required for Firestore)
//    public FoodItem() {}
//
//    // Constructor with fields
//    public FoodItem(String foodName, String foodPrice, String foodQuantity) {
//        this.foodName = foodName;
//        this.foodPrice = Double.parseDouble(foodPrice); // Convert String to double
//        this.foodQuantity = Integer.parseInt(foodQuantity); // Convert String to int
//    }
//
//    // Getters
//    public String getFoodName() {
//        return foodName;
//    }
//
//    public double getFoodPrice() {
//        return foodPrice;
//    }
//
//    public int getFoodQuantity() {
//        return foodQuantity;
//    }
//
//    // Setters
//    public void setFoodQuantity(String foodQuantity) {
//        this.foodQuantity = Integer.parseInt(foodQuantity); // Convert String to int
//    }
//
//    public void setFoodPrice(String foodPrice) {
//        this.foodPrice = Double.parseDouble(foodPrice); // Convert String to double
//    }
//
//    public void setFoodName(String foodName) {
//        this.foodName = foodName;
//    }
//}
package com.example.resturent;
public class FoodItem {
    private String foodName;
    private double foodPrice;
    private int foodQuantity;
    private String imageUrl;  // New field for image URL

    // No-argument constructor (required for Firestore)
    public FoodItem() {}

    // Constructor with fields
    public FoodItem(String foodName, String foodPrice, String foodQuantity, String imageUrl) {
        this.foodName = foodName;
        this.foodPrice = Double.parseDouble(foodPrice); // Convert String to double
        this.foodQuantity = Integer.parseInt(foodQuantity); // Convert String to int
        this.imageUrl = imageUrl;  // Set the image URL
    }

    // Getters and Setters
    public String getFoodName() {
        return foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFoodQuantity(String foodQuantity) {
        this.foodQuantity = Integer.parseInt(foodQuantity); // Convert String to int
    }

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = Double.parseDouble(foodPrice); // Convert String to double
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}

