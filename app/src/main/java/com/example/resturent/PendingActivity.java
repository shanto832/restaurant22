//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//
//public class PendingActivity extends AppCompatActivity {
//
//    private String waiterId;
//    private String waiterName;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pending);
//
//        // Retrieve the ID and Name from the Intent
//        Intent intent = getIntent();
//        waiterId = intent.getStringExtra("waiterId");
//        waiterName = intent.getStringExtra("waiterName");
//
//        // Store them (no need to show them in this activity)
//        // For now, just show a toast indicating the ID and Name were passed
//        Toast.makeText(this, "Waiter ID: " + waiterId + "\nWaiter Name: " + waiterName, Toast.LENGTH_SHORT).show();
//
//        // Optionally, you can log or store the values in a database if needed
//    }
//}
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//import java.util.ArrayList;
//import java.util.List;
//
//public class PendingActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private OrderWithTableAdapter adapter;
//    private List<OrderWithTable> ordersList;
//    private String waiterId;
//    private String waiterName;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pending);
//
//        // Retrieve the ID and Name from the Intent
//        Intent intent = getIntent();
//        waiterId = intent.getStringExtra("waiterId");
//        waiterName = intent.getStringExtra("waiterName");
//
//        // Display the passed data in a Toast (for verification)
//        Toast.makeText(this, "Waiter ID: " + waiterId + "\nWaiter Name: " + waiterName, Toast.LENGTH_SHORT).show();
//
//        // Initialize RecyclerView and Adapter
//        recyclerView = findViewById(R.id.rvPendingOrders);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        ordersList = new ArrayList<>();
//        adapter = new OrderWithTableAdapter(ordersList);
//        recyclerView.setAdapter(adapter);
//
//        // Fetch orders from Firestore
//        fetchOrdersFromFirestore();
//    }
//
//    private void fetchOrdersFromFirestore() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference ordersRef = db.collection("OrderWithTable");
//
//        ordersRef.get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        QuerySnapshot querySnapshot = task.getResult();
//                        if (querySnapshot != null && !querySnapshot.isEmpty()) {
//                            // Clear existing data
//                            ordersList.clear();
//
//                            // Add orders to the list
//                            for (QueryDocumentSnapshot document : querySnapshot) {
//                                OrderWithTable order = document.toObject(OrderWithTable.class);
//                                ordersList.add(order);
//                            }
//
//                            // Notify the adapter that the data has changed
//                            adapter.notifyDataSetChanged();
//                        } else {
//                            Toast.makeText(this, "No orders found", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(this, "Failed to load orders", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//}
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.annotation.Nullable;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//
//public class PendingActivity extends AppCompatActivity {
//
//    private ListView listView;
//    private String waiterId;
//    private String waiterName;
//    private FirebaseFirestore db;
//    private OrderAdapter orderAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pending);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Retrieve waiter ID and Name
//        Intent intent = getIntent();
//        waiterId = intent.getStringExtra("waiterId");
//        waiterName = intent.getStringExtra("waiterName");
//
//        // Initialize ListView
//        listView = findViewById(R.id.listViewPendingOrders);
//        orderAdapter = new OrderAdapter(this, R.layout.list_item_order);
//        listView.setAdapter(orderAdapter);
//
//        // Fetch and display orders from Firestore
//        db.collection("OrderWithTable")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable com.google.firebase.firestore.FirebaseFirestoreException error) {
//                        if (error != null) {
//                            Toast.makeText(PendingActivity.this, "Error loading orders", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        if (value != null) {
//                            for (var document : value) {
//                                OrderWithTable order = document.toObject(OrderWithTable.class);
//                                orderAdapter.add(order);
//                            }
//                        }
//                    }
//                });
//
//        // Set item click listener to navigate to TableWaiterActivity
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                OrderWithTable selectedOrder = orderAdapter.getItem(position);
//
//                if (selectedOrder != null) {
//                    // Pass selected order and waiter details to TableWaiterActivity
//                    Intent intent = new Intent(PendingActivity.this, TableWaiterActivity.class);
//                    intent.putExtra("waiterId", waiterId);
//                    intent.putExtra("waiterName", waiterName);
//                    intent.putExtra("foodName", selectedOrder.getFoodName());
//                    intent.putExtra("tableNumber", selectedOrder.getTableNumber());
//                    intent.putExtra("quantity", selectedOrder.getQuantity());
//                    startActivity(intent);
//                }
//            }
//        });
//    }
//}
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.annotation.Nullable;
//
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.firestore.DocumentSnapshot;
//
//public class PendingActivity extends AppCompatActivity {
//
//    private ListView listView;
//    private String waiterId;
//    private String waiterName;
//    private FirebaseFirestore db;
//    private OrderAdapter orderAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pending);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Retrieve waiter ID and Name from Intent
//        Intent intent = getIntent();
//        waiterId = intent.getStringExtra("waiterId");
//        waiterName = intent.getStringExtra("waiterName");
//
//        // Initialize ListView and Adapter
//        listView = findViewById(R.id.listViewPendingOrders);
//        orderAdapter = new OrderAdapter(this, R.layout.list_item_order);
//        listView.setAdapter(orderAdapter);
//
//        // Fetch and display orders from Firestore
//        db.collection("OrderWithTable")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable com.google.firebase.firestore.FirebaseFirestoreException error) {
//                        if (error != null) {
//                            Toast.makeText(PendingActivity.this, "Error loading orders", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        if (value != null) {
//                            // Add the orders from Firestore to the adapter
//                            for (DocumentSnapshot document : value) {
//                                OrderWithTable order = document.toObject(OrderWithTable.class);
//                                if (order != null) {
//                                    orderAdapter.add(order);
//                                }
//                            }
//                        }
//                    }
//                });
//
//        // Set item click listener to navigate to TableWaiterActivity and handle transaction finalization
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                OrderWithTable selectedOrder = orderAdapter.getItem(position);
//
//                if (selectedOrder != null) {
//                    // Use the correct method name to access customer phone number and user name
//                    String userPhoneNumber = selectedOrder.getCustomerPhoneNumber(); // Corrected to 'getCustomerPhoneNumber()'
//                    String userName = selectedOrder.getUserName(); // Corrected to 'getUserName()'
//
//                    // Fetch the price of the food from FoodItems collection
//                    fetchFoodPriceAndCreateTransaction(selectedOrder);
//                }
//            }
//        });
//    }
//
//    private void fetchFoodPriceAndCreateTransaction(OrderWithTable selectedOrder) {
//        // Retrieve food name and quantity from the selected order
//        String foodName = selectedOrder.getFoodName();
//        int quantity = selectedOrder.getQuantity();
//        String customerName = selectedOrder.getUserName(); // Correct field for customer name
//        String customerPhoneNumber = selectedOrder.getCustomerPhoneNumber(); // Correct field for customer phone number
//
//        // Fetch unit price from FoodItems collection
//        db.collection("FoodItems")
//                .whereEqualTo("foodName", foodName)
//                .get()
//                .addOnSuccessListener(querySnapshot -> {
//                    if (!querySnapshot.isEmpty()) {
//                        // Assume that the food item collection contains a single document for each foodName
//                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
//
//                        // Retrieve the food price from the document
//                        Object priceObject = documentSnapshot.get("foodPrice");
//
//                        if (priceObject instanceof String) {
//                            // If the price is a String, try to parse it as a double
//                            try {
//                                double unitPrice = Double.parseDouble((String) priceObject);
//                                // Calculate the total price
//                                double totalPrice = unitPrice * quantity;
//
//                                // Create a new TransactionFinalDetails object and add it to Firestore
//                                createTransactionFinalDetails(selectedOrder, unitPrice, totalPrice);
//                            } catch (NumberFormatException e) {
//                                // Handle invalid food price format
//                                Toast.makeText(PendingActivity.this, "Invalid food price for " + foodName, Toast.LENGTH_SHORT).show();
//                            }
//                        } else if (priceObject instanceof Number) {
//                            // If the price is already a number, use it directly
//                            double unitPrice = ((Number) priceObject).doubleValue();
//                            // Calculate the total price
//                            double totalPrice = unitPrice * quantity;
//
//                            // Create a new TransactionFinalDetails object and add it to Firestore
//                            createTransactionFinalDetails(selectedOrder, unitPrice, totalPrice);
//                        } else {
//                            // Handle case where food price is invalid or missing
//                            Toast.makeText(PendingActivity.this, "Invalid or missing food price for " + foodName, Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(PendingActivity.this, "Food item not found in FoodItems collection", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(PendingActivity.this, "Error fetching food price", Toast.LENGTH_SHORT).show();
//                });
//    }
//
//    private void createTransactionFinalDetails(OrderWithTable selectedOrder, double unitPrice, double totalPrice) {
//        // Create a new TransactionFinalDetails object with the necessary fields
//        TransactionFinalDetails transactionDetails = new TransactionFinalDetails(
//                selectedOrder.getFoodName(), unitPrice, selectedOrder.getQuantity(),
//                totalPrice, selectedOrder.getUserName(), selectedOrder.getCustomerPhoneNumber());
//
//        // Add the new transaction details to the TransactionFinalDetails collection in Firestore
//        db.collection("TransactionFinalDetails")
//                .add(transactionDetails)
//                .addOnSuccessListener(documentReference -> {
//                    // Show a Toast on successful entry
//                    Toast.makeText(PendingActivity.this, "Transaction finalized successfully", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(e -> {
//                    // Show a Toast on error
//                    Toast.makeText(PendingActivity.this, "Error finalizing transaction", Toast.LENGTH_SHORT).show();
//                });
//    }
//}
package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

public class PendingActivity extends AppCompatActivity {

    private ListView listView;
    private String waiterId;
    private String waiterName;
    private FirebaseFirestore db;
    private OrderAdapter orderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Retrieve waiter ID and Name from Intent
        Intent intent = getIntent();
        waiterId = intent.getStringExtra("waiterId");
        waiterName = intent.getStringExtra("waiterName");

        // Initialize ListView and Adapter
        listView = findViewById(R.id.listViewPendingOrders);
        orderAdapter = new OrderAdapter(this, R.layout.list_item_order);
        listView.setAdapter(orderAdapter);

        // Fetch and display orders from Firestore
        db.collection("OrderWithTable")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable com.google.firebase.firestore.FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(PendingActivity.this, "Error loading orders", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (value != null) {
                            // Add the orders from Firestore to the adapter
                            for (DocumentSnapshot document : value) {
                                OrderWithTable order = document.toObject(OrderWithTable.class);
                                if (order != null) {
                                    orderAdapter.add(order);
                                }
                            }
                        }
                    }
                });

        // Set item click listener to navigate to TableWaiterActivity and handle transaction finalization
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OrderWithTable selectedOrder = orderAdapter.getItem(position);

                if (selectedOrder != null) {
                    // Use the correct method name to access customer phone number and user name
                    String userPhoneNumber = selectedOrder.getCustomerPhoneNumber(); // Corrected to 'getCustomerPhoneNumber()'
                    String userName = selectedOrder.getUserName(); // Corrected to 'getUserName()'

                    // Pass selected order details to TableWaiterActivity
                    Intent intent = new Intent(PendingActivity.this, TableWaiterActivity.class);
                    intent.putExtra("waiterId", waiterId);
                    intent.putExtra("waiterName", waiterName);
                    intent.putExtra("foodName", selectedOrder.getFoodName());
                    intent.putExtra("tableNumber", selectedOrder.getTableNumber());
                    intent.putExtra("quantity", selectedOrder.getQuantity());
                    intent.putExtra("customerName", userName); // Customer's name from OrderWithTable
                    intent.putExtra("customerPhoneNumber", userPhoneNumber); // Customer's phone number from OrderWithTable
                    startActivity(intent);

                    // Fetch the price of the food from FoodItems collection and create transaction
                    fetchFoodPriceAndCreateTransaction(selectedOrder);
                }
            }
        });
    }

    private void fetchFoodPriceAndCreateTransaction(OrderWithTable selectedOrder) {
        // Retrieve food name and quantity from the selected order
        String foodName = selectedOrder.getFoodName();
        int quantity = selectedOrder.getQuantity();
        String customerName = selectedOrder.getUserName(); // Correct field for customer name
        String customerPhoneNumber = selectedOrder.getCustomerPhoneNumber(); // Correct field for customer phone number

        // Fetch unit price from FoodItems collection
        db.collection("FoodItems")
                .whereEqualTo("foodName", foodName)
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    if (!querySnapshot.isEmpty()) {
                        // Assume that the food item collection contains a single document for each foodName
                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);

                        // Retrieve the food price from the document
                        Object priceObject = documentSnapshot.get("foodPrice");

                        if (priceObject instanceof String) {
                            // If the price is a String, try to parse it as a double
                            try {
                                double unitPrice = Double.parseDouble((String) priceObject);
                                // Calculate the total price
                                double totalPrice = unitPrice * quantity;

                                // Create a new TransactionFinalDetails object and add it to Firestore
                                createTransactionFinalDetails(selectedOrder, unitPrice, totalPrice);
                            } catch (NumberFormatException e) {
                                // Handle invalid food price format
                                Toast.makeText(PendingActivity.this, "Invalid food price for " + foodName, Toast.LENGTH_SHORT).show();
                            }
                        } else if (priceObject instanceof Number) {
                            // If the price is already a number, use it directly
                            double unitPrice = ((Number) priceObject).doubleValue();
                            // Calculate the total price
                            double totalPrice = unitPrice * quantity;

                            // Create a new TransactionFinalDetails object and add it to Firestore
                            createTransactionFinalDetails(selectedOrder, unitPrice, totalPrice);
                        } else {
                            // Handle case where food price is invalid or missing
                            Toast.makeText(PendingActivity.this, "Invalid or missing food price for " + foodName, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PendingActivity.this, "Food item not found in FoodItems collection", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(PendingActivity.this, "Error fetching food price", Toast.LENGTH_SHORT).show();
                });
    }

    private void createTransactionFinalDetails(OrderWithTable selectedOrder, double unitPrice, double totalPrice) {
        // Create a new TransactionFinalDetails object with the necessary fields
        TransactionFinalDetails transactionDetails = new TransactionFinalDetails(
                selectedOrder.getFoodName(), unitPrice, selectedOrder.getQuantity(),
                totalPrice, selectedOrder.getUserName(), selectedOrder.getCustomerPhoneNumber());

        // Add the new transaction details to the TransactionFinalDetails collection in Firestore
        db.collection("TransactionFinalDetails")
                .add(transactionDetails)
                .addOnSuccessListener(documentReference -> {
                    // Show a Toast on successful entry
                    Toast.makeText(PendingActivity.this, "Transaction finalized successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    // Show a Toast on error
                    Toast.makeText(PendingActivity.this, "Error finalizing transaction", Toast.LENGTH_SHORT).show();
                });
    }
}
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.annotation.Nullable;
//
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.firestore.DocumentSnapshot;
//import android.util.Log;
//
//public class PendingActivity extends AppCompatActivity {
//
//    private ListView listView;
//    private String waiterId;
//    private String waiterName;
//    private FirebaseFirestore db;
//    private OrderAdapter orderAdapter;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_pending);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Retrieve waiter ID and Name from Intent
//        Intent intent = getIntent();
//        waiterId = intent.getStringExtra("waiterId");
//        waiterName = intent.getStringExtra("waiterName");
//
//        // Initialize ListView and Adapter
//        listView = findViewById(R.id.listViewPendingOrders);
//        orderAdapter = new OrderAdapter(this, R.layout.list_item_order);
//        listView.setAdapter(orderAdapter);
//
//        // Fetch and display orders from Firestore
//        db.collection("OrderWithTable")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable com.google.firebase.firestore.FirebaseFirestoreException error) {
//                        if (error != null) {
//                            Toast.makeText(PendingActivity.this, "Error loading orders", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        if (value != null) {
//                            // Add the orders from Firestore to the adapter
//                            for (DocumentSnapshot document : value) {
//                                OrderWithTable order = document.toObject(OrderWithTable.class);
//                                if (order != null) {
//                                    orderAdapter.add(order);
//                                }
//                            }
//                        }
//                    }
//                });
//
//        // Set item click listener to navigate to TableWaiterActivity and handle transaction finalization
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                OrderWithTable selectedOrder = orderAdapter.getItem(position);
//
//                if (selectedOrder != null) {
//                    // Access phone number and customer name
//                    String userPhoneNumber = selectedOrder.getCustomerPhoneNumber();  // This should match your Firestore field name
//                    String userName = selectedOrder.getUserName();  // Customer's name
//
//                    // Log the retrieved phone number and name
//                    Log.d("PendingActivity", "User Name: " + userName);
//                    Log.d("PendingActivity", "Phone Number: " + userPhoneNumber);
//
//                    // Pass selected order details to TableWaiterActivity
//                    Intent intent = new Intent(PendingActivity.this, TableWaiterActivity.class);
//                    intent.putExtra("waiterId", waiterId);
//                    intent.putExtra("waiterName", waiterName);
//                    intent.putExtra("foodName", selectedOrder.getFoodName());
//                    intent.putExtra("tableNumber", selectedOrder.getTableNumber());
//                    intent.putExtra("quantity", selectedOrder.getQuantity());
//                    intent.putExtra("customerName", userName);  // Customer's name from OrderWithTable
//                    intent.putExtra("customerPhoneNumber", userPhoneNumber);  // Customer's phone number from OrderWithTable
//                    startActivity(intent);
//
//                    // Fetch the price of the food from FoodItems collection and create transaction
//                    fetchFoodPriceAndCreateTransaction(selectedOrder);
//                }
//            }
//        });
//    }
//
//    private void fetchFoodPriceAndCreateTransaction(OrderWithTable selectedOrder) {
//        // Retrieve food name, quantity, and customer details
//        String foodName = selectedOrder.getFoodName();
//        int quantity = selectedOrder.getQuantity();
//        String customerName = selectedOrder.getUserName();  // Correct field for customer name
//        String userPhoneNumber = selectedOrder.getCustomerPhoneNumber();  // Correct field for customer phone number
//
//        // Log the customer details before proceeding
//        Log.d("PendingActivity", "Creating transaction for: " + customerName + " - " + userPhoneNumber);
//
//        // Fetch unit price from FoodItems collection
//        db.collection("FoodItems")
//                .whereEqualTo("foodName", foodName)
//                .get()
//                .addOnSuccessListener(querySnapshot -> {
//                    if (!querySnapshot.isEmpty()) {
//                        // Assume that the food item collection contains a single document for each foodName
//                        DocumentSnapshot documentSnapshot = querySnapshot.getDocuments().get(0);
//
//                        // Retrieve the food price from the document
//                        Object priceObject = documentSnapshot.get("foodPrice");
//
//                        if (priceObject instanceof String) {
//                            // If the price is a String, try to parse it as a double
//                            try {
//                                double unitPrice = Double.parseDouble((String) priceObject);
//                                // Calculate the total price
//                                double totalPrice = unitPrice * quantity;
//
//                                // Create a new TransactionFinalDetails object and add it to Firestore
//                                createTransactionFinalDetails(selectedOrder, unitPrice, totalPrice);
//                            } catch (NumberFormatException e) {
//                                // Handle invalid food price format
//                                Toast.makeText(PendingActivity.this, "Invalid food price for " + foodName, Toast.LENGTH_SHORT).show();
//                            }
//                        } else if (priceObject instanceof Number) {
//                            // If the price is already a number, use it directly
//                            double unitPrice = ((Number) priceObject).doubleValue();
//                            // Calculate the total price
//                            double totalPrice = unitPrice * quantity;
//
//                            // Create a new TransactionFinalDetails object and add it to Firestore
//                            createTransactionFinalDetails(selectedOrder, unitPrice, totalPrice);
//                        } else {
//                            // Handle case where food price is invalid or missing
//                            Toast.makeText(PendingActivity.this, "Invalid or missing food price for " + foodName, Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        Toast.makeText(PendingActivity.this, "Food item not found in FoodItems collection", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .addOnFailureListener(e -> {
//                    Toast.makeText(PendingActivity.this, "Error fetching food price", Toast.LENGTH_SHORT).show();
//                });
//    }
//
//    private void createTransactionFinalDetails(OrderWithTable selectedOrder, double unitPrice, double totalPrice) {
//        // Log to verify customer phone number before creating transaction
//        Log.d("PendingActivity", "Creating Transaction for " + selectedOrder.getUserName() + " with phone " + selectedOrder.getCustomerPhoneNumber());
//
//        // Create a new TransactionFinalDetails object with the necessary fields
//        TransactionFinalDetails transactionDetails = new TransactionFinalDetails(
//                selectedOrder.getFoodName(), unitPrice, selectedOrder.getQuantity(),
//                totalPrice, selectedOrder.getUserName(), selectedOrder.getCustomerPhoneNumber());
//
//        // Add the new transaction details to the TransactionFinalDetails collection in Firestore
//        db.collection("TransactionFinalDetails")
//                .add(transactionDetails)
//                .addOnSuccessListener(documentReference -> {
//                    // Show a Toast on successful entry
//                    Toast.makeText(PendingActivity.this, "Transaction finalized successfully", Toast.LENGTH_SHORT).show();
//                })
//                .addOnFailureListener(e -> {
//                    // Show a Toast on error
//                    Toast.makeText(PendingActivity.this, "Error finalizing transaction", Toast.LENGTH_SHORT).show();
//                });
//    }
//}





