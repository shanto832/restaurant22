
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.firestore.FirebaseFirestore;
//
//public class TableWaiterActivity extends AppCompatActivity {
//
//    private TextView txtWaiterId, txtWaiterName, txtFoodName, txtTableNumber, txtQuantity;
//    private FirebaseFirestore db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_table_waiter);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Initialize TextViews
//        txtWaiterId = findViewById(R.id.txtWaiterId);
//        txtWaiterName = findViewById(R.id.txtWaiterName);
//        txtFoodName = findViewById(R.id.txtFoodName);
//        txtTableNumber = findViewById(R.id.txtTableNumber);
//        txtQuantity = findViewById(R.id.txtQuantity);
//
//        // Retrieve the data passed from PendingActivity
//        String waiterId = getIntent().getStringExtra("waiterId");
//        String waiterName = getIntent().getStringExtra("waiterName");
//        String foodName = getIntent().getStringExtra("foodName");
//        int tableNumber = getIntent().getIntExtra("tableNumber", -1);
//        int quantity = getIntent().getIntExtra("quantity", -1);
//
//        // Display the data in the TextViews
//        txtWaiterId.setText("Waiter ID: " + waiterId);
//        txtWaiterName.setText("Waiter Name: " + waiterName);
//        txtFoodName.setText("Food: " + foodName);
//        txtTableNumber.setText("Table Number: " + tableNumber);
//        txtQuantity.setText("Quantity: " + quantity);
//
//        // Create an object to store in Firestore
//        ElaboratedOrderDetails orderDetails = new ElaboratedOrderDetails(
//                waiterId, waiterName, foodName, tableNumber, quantity);
//
//        // Add the order details to Firestore
//        addOrderToFirestore(orderDetails);
//
//
//        FloatingActionButton fab = findViewById(R.id.home_manage);
//        fab.setOnClickListener(v -> {
//            Intent intent = new Intent(TableWaiterActivity.this, Manage.class);
//            startActivity(intent);
//        });
//
//    }
//
//    private void addOrderToFirestore(ElaboratedOrderDetails orderDetails) {
//        // Add the order to Firestore under the "ElaboratedOrderDetails" collection
//        db.collection("ElaboratedOrderDetails")
//                .add(orderDetails)
//                .addOnSuccessListener(documentReference -> {
//                    // Show a Toast on successful entry
//                    Toast.makeText(TableWaiterActivity.this, "Successful entry", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(e -> {
//                    // Handle errors
//                    Toast.makeText(TableWaiterActivity.this, "Error adding order", Toast.LENGTH_SHORT).show();
//                });
//    }
//}
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//
//public class TableWaiterActivity extends AppCompatActivity {
//
//    private TextView txtWaiterId, txtWaiterName, txtFoodName, txtTableNumber, txtQuantity;
//    private FirebaseFirestore db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_table_waiter);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Initialize TextViews
//        txtWaiterId = findViewById(R.id.txtWaiterId);
//        txtWaiterName = findViewById(R.id.txtWaiterName);
//        txtFoodName = findViewById(R.id.txtFoodName);
//        txtTableNumber = findViewById(R.id.txtTableNumber);
//        txtQuantity = findViewById(R.id.txtQuantity);
//
//        // Retrieve the data passed from PendingActivity
//        String waiterId = getIntent().getStringExtra("waiterId");
//        String waiterName = getIntent().getStringExtra("waiterName");
//        String foodName = getIntent().getStringExtra("foodName");
//        int tableNumber = getIntent().getIntExtra("tableNumber", -1);
//        int quantity = getIntent().getIntExtra("quantity", -1);
//
//        // Display the data in the TextViews
//        txtWaiterId.setText("Waiter ID: " + waiterId);
//        txtWaiterName.setText("Waiter Name: " + waiterName);
//        txtFoodName.setText("Food: " + foodName);
//        txtTableNumber.setText("Table Number: " + tableNumber);
//        txtQuantity.setText("Quantity: " + quantity);
//
//        // Create an object to store in Firestore for ElaboratedOrderDetails
//        ElaboratedOrderDetails orderDetails = new ElaboratedOrderDetails(
//                waiterId, waiterName, foodName, tableNumber, quantity);
//
//        // Fetch unitPrice from FoodItems collection and calculate totalPrice
//        fetchFoodPriceAndAddToDatabase(foodName, quantity, orderDetails);
//
//        // Set up the floating action button to navigate to Manage activity
//        FloatingActionButton fab = findViewById(R.id.home_manage);
//        fab.setOnClickListener(v -> {
//            Intent intent = new Intent(TableWaiterActivity.this, Manage.class);
//            startActivity(intent);
//        });
//    }
//
//    private void fetchFoodPriceAndAddToDatabase(String foodName, int quantity, ElaboratedOrderDetails orderDetails) {
//        // Search for the food price in the FoodItems collection
//        db.collection("FoodItems")
//                .whereEqualTo("foodName", foodName)
//                .get()
//                .addOnSuccessListener(querySnapshot -> {
//                    if (!querySnapshot.isEmpty()) {
//                        // Assume that the food items collection contains a single document for each foodName
//                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
//
//                        // Retrieve foodPrice as an Object, which can be a String or Number
//                        Object priceObject = documentSnapshot.get("foodPrice");
//
//                        if (priceObject instanceof String) {
//                            // Convert the String to a double if it's a valid number
//                            try {
//                                double unitPrice = Double.parseDouble((String) priceObject);
//                                // Calculate the total price
//                                double totalPrice = unitPrice * quantity;
//
//                                // Create a TransactionDetails object
//                                TransactionDetails transactionDetails = new TransactionDetails(
//                                        foodName, quantity, unitPrice, totalPrice);
//
//                                // Add both ElaboratedOrderDetails and TransactionDetails to Firestore
//                                addOrderToFirestore(orderDetails, transactionDetails);
//                            } catch (NumberFormatException e) {
//                                // Handle invalid string format (if it's not a valid number)
//                                Toast.makeText(TableWaiterActivity.this, "Invalid food price for " + foodName, Toast.LENGTH_SHORT).show();
//                            }
//                        } else if (priceObject instanceof Number) {
//                            // If it's already a Number, use it directly
//                            double unitPrice = ((Number) priceObject).doubleValue();
//
//                            // Calculate the total price
//                            double totalPrice = unitPrice * quantity;
//
//                            // Create a TransactionDetails object
//                            TransactionDetails transactionDetails = new TransactionDetails(
//                                    foodName, quantity, unitPrice, totalPrice);
//
//                            // Add both ElaboratedOrderDetails and TransactionDetails to Firestore
//                            addOrderToFirestore(orderDetails, transactionDetails);
//                        } else {
//                            // Handle case where foodPrice is missing or invalid
//                            Toast.makeText(TableWaiterActivity.this, "Invalid food price for " + foodName, Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(TableWaiterActivity.this, "Food item not found", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(TableWaiterActivity.this, "Error fetching food price", Toast.LENGTH_SHORT).show();
//                });
//    }
//
//
//
//    private void addOrderToFirestore(ElaboratedOrderDetails orderDetails, TransactionDetails transactionDetails) {
//        // Add the order to Firestore under the "ElaboratedOrderDetails" collection
//        db.collection("ElaboratedOrderDetails")
//                .add(orderDetails)
//                .addOnSuccessListener(documentReference -> {
//                    // Show a Toast on successful entry
//                    Toast.makeText(TableWaiterActivity.this, "Successful entry to ElaboratedOrderDetails", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(TableWaiterActivity.this, "Error adding to ElaboratedOrderDetails", Toast.LENGTH_SHORT).show();
//                });
//
//        // Add the transaction details to Firestore under the "TransactionDetails" collection
//        db.collection("TransactionDetails")
//                .add(transactionDetails)
//                .addOnSuccessListener(documentReference -> {
//                    // Show a Toast on successful entry
//                    Toast.makeText(TableWaiterActivity.this, "Successful entry to TransactionDetails", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(TableWaiterActivity.this, "Error adding to TransactionDetails", Toast.LENGTH_SHORT).show();
//                });
//    }
//}
package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class TableWaiterActivity extends AppCompatActivity {

    private TextView txtWaiterId, txtWaiterName, txtFoodName, txtTableNumber, txtQuantity;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_waiter);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize TextViews
        txtWaiterId = findViewById(R.id.txtWaiterId);
        txtWaiterName = findViewById(R.id.txtWaiterName);
        txtFoodName = findViewById(R.id.txtFoodName);
        txtTableNumber = findViewById(R.id.txtTableNumber);
        txtQuantity = findViewById(R.id.txtQuantity);

        // Retrieve the data passed from PendingActivity or FoodItemDetailActivity
        String waiterId = getIntent().getStringExtra("waiterId");
        String waiterName = getIntent().getStringExtra("waiterName");
        String foodName = getIntent().getStringExtra("foodName");
        int tableNumber = getIntent().getIntExtra("tableNumber", -1);
        int quantity = getIntent().getIntExtra("quantity", -1);
        String customerName = getIntent().getStringExtra("customerName");
        String customerPhoneNumber = getIntent().getStringExtra("customerPhoneNumber");

        // Display the data in the TextViews
        txtWaiterId.setText("Waiter ID: " + waiterId);
        txtWaiterName.setText("Waiter Name: " + waiterName);
        txtFoodName.setText("Food: " + foodName);
        txtTableNumber.setText("Table Number: " + tableNumber);
        txtQuantity.setText("Quantity: " + quantity);

        // Create an object to store in Firestore for ElaboratedOrderDetails (no change needed here)
        ElaboratedOrderDetails orderDetails = new ElaboratedOrderDetails(
                waiterId, waiterName, foodName, tableNumber, quantity);

        // Fetch unitPrice from FoodItems collection and calculate totalPrice
        fetchFoodPriceAndAddToDatabase(foodName, quantity, orderDetails, customerName, customerPhoneNumber);

        // Set up the floating action button to navigate to Manage activity
        FloatingActionButton fab = findViewById(R.id.home_manage);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(TableWaiterActivity.this, Manage.class);
            startActivity(intent);
        });
    }

    private void fetchFoodPriceAndAddToDatabase(String foodName, int quantity, ElaboratedOrderDetails orderDetails, String customerName, String customerPhoneNumber) {
        // Search for the food price in the FoodItems collection
        db.collection("FoodItems")
                .whereEqualTo("foodName", foodName)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    if (!querySnapshot.isEmpty()) {
                        // Assume that the food items collection contains a single document for each foodName
                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);

                        // Retrieve foodPrice as an Object, which can be a String or Number
                        Object priceObject = documentSnapshot.get("foodPrice");

                        if (priceObject instanceof String) {
                            // Convert the String to a double if it's a valid number
                            try {
                                double unitPrice = Double.parseDouble((String) priceObject);
                                // Calculate the total price
                                double totalPrice = unitPrice * quantity;

                                // Create a TransactionDetails object (now with customer info)
                                TransactionDetails transactionDetails = new TransactionDetails(
                                        foodName, quantity, unitPrice, totalPrice, customerName, customerPhoneNumber);

                                // Add both ElaboratedOrderDetails and TransactionDetails to Firestore
                                addOrderToFirestore(orderDetails, transactionDetails);
                            } catch (NumberFormatException e) {
                                // Handle invalid string format (if it's not a valid number)
                                Toast.makeText(TableWaiterActivity.this, "Invalid food price for " + foodName, Toast.LENGTH_SHORT).show();
                            }
                        } else if (priceObject instanceof Number) {
                            // If it's already a Number, use it directly
                            double unitPrice = ((Number) priceObject).doubleValue();

                            // Calculate the total price
                            double totalPrice = unitPrice * quantity;

                            // Create a TransactionDetails object (now with customer info)
                            TransactionDetails transactionDetails = new TransactionDetails(
                                    foodName, quantity, unitPrice, totalPrice, customerName, customerPhoneNumber);

                            // Add both ElaboratedOrderDetails and TransactionDetails to Firestore
                            addOrderToFirestore(orderDetails, transactionDetails);
                        } else {
                            // Handle case where foodPrice is missing or invalid
                            Toast.makeText(TableWaiterActivity.this, "Invalid food price for " + foodName, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(TableWaiterActivity.this, "Food item not found", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(TableWaiterActivity.this, "Error fetching food price", Toast.LENGTH_SHORT).show();
                });
    }

    private void addOrderToFirestore(ElaboratedOrderDetails orderDetails, TransactionDetails transactionDetails) {
        // Add the order to Firestore under the "ElaboratedOrderDetails" collection
        db.collection("ElaboratedOrderDetails")
                .add(orderDetails)
                .addOnSuccessListener(documentReference -> {
                    // Show a Toast on successful entry
                    Toast.makeText(TableWaiterActivity.this, "Successful entry to ElaboratedOrderDetails", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(TableWaiterActivity.this, "Error adding to ElaboratedOrderDetails", Toast.LENGTH_SHORT).show();
                });

        // Add the transaction details to Firestore under the "TransactionDetails" collection
        db.collection("TransactionDetails")
                .add(transactionDetails)
                .addOnSuccessListener(documentReference -> {
                    // Show a Toast on successful entry
                    Toast.makeText(TableWaiterActivity.this, "Successful entry to TransactionDetails", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(TableWaiterActivity.this, "Error adding to TransactionDetails", Toast.LENGTH_SHORT).show();
                });
    }
}

