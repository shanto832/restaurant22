//package com.example.resturent;
//
//public class OrderWithTable {
//
//    private int tableNumber;
//    private String foodName;
//    private int quantity;
//
//    // No-argument constructor required for Firestore
//    public OrderWithTable() {}
//
//    public OrderWithTable(int tableNumber, String foodName, int quantity) {
//        this.tableNumber = tableNumber;
//        this.foodName = foodName;
//        this.quantity = quantity;
//    }
//
//    public int getTableNumber() {
//        return tableNumber;
//    }
//
//    public String getFoodName() {
//        return foodName;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//}
//package com.example.resturent;
//
//public class OrderWithTable {
//
//    private String id;  // Add an id field
//    private int tableNumber;
//    private String foodName;
//    private int quantity;
//    private String orderStatus;
//
//    // No-argument constructor required for Firestore
//    public OrderWithTable() {}
//
//    public OrderWithTable(int tableNumber, String foodName, int quantity) {
//        this.tableNumber = tableNumber;
//        this.foodName = foodName;
//        this.quantity = quantity;
//        this.orderStatus = "in-progress"; // Default status when order is created
//    }
//
//    // Getter and setter for id
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    // Other getters and setters
//    public int getTableNumber() {
//        return tableNumber;
//    }
//
//    public String getFoodName() {
//        return foodName;
//    }
//
//    public int getQuantity() {
//        return quantity;
//    }
//
//    public String getOrderStatus() {
//        return orderStatus;
//    }
//
//    public void setOrderStatus(String orderStatus) {
//        this.orderStatus = orderStatus;
//    }
//}
//package com.example.resturent;
//public class OrderWithTable {
//    private String id;  // This will store the document ID
//    private int tableNumber;
//    private String foodName;
//    private int quantity;
//    private String status; // "in-progress", "completed", etc.
//    private String customerName; // New field for customer name
//    private String customerPhoneNumber; // New field for customer phone number
//
//    // Getters and setters for all fields
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public int getTableNumber() {
//        return tableNumber;
//    }
//
//    public void setTableNumber(int tableNumber) {
//        this.tableNumber = tableNumber;
//    }
//
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
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
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
public class OrderWithTable {
    private String foodName;
    private int tableNumber;
    private int quantity;
    private String userName;  // Assuming 'userName' is the correct field name
    private String userPhoneNumber;  // Correct field name for phone number
    private String id;  // Field for Firestore document ID

    // No-argument constructor (required by Firestore)
    public OrderWithTable() {}

    // Getter and setter methods
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCustomerPhoneNumber() {
        return userPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.userPhoneNumber = customerPhoneNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}




