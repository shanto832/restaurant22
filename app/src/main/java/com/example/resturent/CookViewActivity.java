//package com.example.resturent;
//
//import android.os.Bundle;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CookViewActivity extends AppCompatActivity {
//
//    private RecyclerView ordersRecyclerView;
//    private OrdersAdapter ordersAdapter;
//    private FirebaseFirestore db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cook_view);
//
//        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
//        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        db = FirebaseFirestore.getInstance();
//        loadPendingOrders();
//    }
//
//    private void loadPendingOrders() {
//        db.collection("OrderWithTable")
//                .whereEqualTo("status", "in-progress")
//                .get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//                    List<OrderWithTable> orderList = new ArrayList<>();
//                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
//                        OrderWithTable order = document.toObject(OrderWithTable.class);
//                        order.setId(document.getId()); // Set document ID for each order
//                        orderList.add(order);
//                    }
//                    ordersAdapter = new OrdersAdapter(orderList, db);
//                    ordersRecyclerView.setAdapter(ordersAdapter);
//                })
//                .addOnFailureListener(e ->
//                        Toast.makeText(CookViewActivity.this, "Error loading orders.", Toast.LENGTH_SHORT).show()
//                );
//    }
//}
//package com.example.resturent;
//
//import android.os.Bundle;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CookViewActivity extends AppCompatActivity {
//
//    private RecyclerView ordersRecyclerView;
//    private OrdersAdapter ordersAdapter;
//    private FirebaseFirestore db;
//    private List<OrderWithTable> orderList; // Make this a class field to allow access across methods
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cook_view);
//
//        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
//        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        db = FirebaseFirestore.getInstance();
//        loadPendingOrders();
//    }
//
//    private void loadPendingOrders() {
//        db.collection("OrderWithTable")
//                .whereEqualTo("status", "in-progress")
//                .get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//                    orderList = new ArrayList<>();
//                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
//                        OrderWithTable order = document.toObject(OrderWithTable.class);
//                        order.setId(document.getId()); // Set document ID for each order
//                        orderList.add(order);
//                    }
//                    // Pass in the click listener for item removal
//                    ordersAdapter = new OrdersAdapter(orderList, this::onOrderClicked);
//                    ordersRecyclerView.setAdapter(ordersAdapter);
//                })
//                .addOnFailureListener(e ->
//                        Toast.makeText(CookViewActivity.this, "Error loading orders.", Toast.LENGTH_SHORT).show()
//                );
//    }
//
//    // Callback for when an order item is clicked
//    private void onOrderClicked(OrderWithTable order) {
//        // Remove the order from the local list and notify the adapter
//        orderList.remove(order);
//        ordersAdapter.notifyDataSetChanged(); // Refresh the RecyclerView
//    }
//}
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.android.material.bottomappbar.BottomAppBar;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import java.util.ArrayList;
//import java.util.List;
//
//public class CookViewActivity extends AppCompatActivity {
//
//    private RecyclerView ordersRecyclerView;
//    private OrdersAdapter ordersAdapter;
//    private FirebaseFirestore db;
//    private List<OrderWithTable> orderList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_cook_view);
//
//        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
//        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        db = FirebaseFirestore.getInstance();
//        loadPendingOrders();
//
//        // Set up the floating action button to navigate to ManageActivity
//        FloatingActionButton fab = findViewById(R.id.fab_manage);
//        fab.setOnClickListener(v -> {
//            Intent intent = new Intent(CookViewActivity.this, Manage.class);
//            startActivity(intent);
//        });
//    }
//
//    private void loadPendingOrders() {
//        db.collection("OrderWithTable")
//                .whereEqualTo("status", "in-progress")
//                .get()
//                .addOnSuccessListener(queryDocumentSnapshots -> {
//                    orderList = new ArrayList<>();
//                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
//                        OrderWithTable order = document.toObject(OrderWithTable.class);
//                        order.setId(document.getId());
//                        orderList.add(order);
//                    }
//                    ordersAdapter = new OrdersAdapter(orderList, this::onOrderClicked);
//                    ordersRecyclerView.setAdapter(ordersAdapter);
//                })
//                .addOnFailureListener(e ->
//                        Toast.makeText(CookViewActivity.this, "Error loading orders.", Toast.LENGTH_SHORT).show()
//                );
//    }
//
//    private void onOrderClicked(OrderWithTable order) {
//        orderList.remove(order);
//        ordersAdapter.notifyDataSetChanged();
//    }
//}


package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class CookViewActivity extends AppCompatActivity {

    private RecyclerView ordersRecyclerView;
    private OrdersAdapter ordersAdapter;
    private FirebaseFirestore db;
    private List<OrderWithTable> orderList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_view);

        ordersRecyclerView = findViewById(R.id.ordersRecyclerView);
        ordersRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        loadPendingOrders();

        // Set up the floating action button to navigate to ManageActivity
        FloatingActionButton fab = findViewById(R.id.fab_manage);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(CookViewActivity.this, Manage.class);
            startActivity(intent);
        });
    }

    private void loadPendingOrders() {
        db.collection("OrderWithTable")
                .whereEqualTo("status", "in-progress")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    orderList = new ArrayList<>();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        OrderWithTable order = document.toObject(OrderWithTable.class);
                        order.setId(document.getId());
                        orderList.add(order);
                    }
                    ordersAdapter = new OrdersAdapter(orderList, this::onOrderClicked);
                    ordersRecyclerView.setAdapter(ordersAdapter);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(CookViewActivity.this, "Error loading orders.", Toast.LENGTH_SHORT).show();
                });
    }

    private void onOrderClicked(OrderWithTable order) {
        // Update order status to "ready" in Firestore
        db.collection("OrderWithTable")
                .document(order.getId())
                .update("status", "ready")
                .addOnSuccessListener(aVoid -> {
                    // Notify the adapter to refresh the UI
                    ordersAdapter.notifyDataSetChanged();
                    Toast.makeText(CookViewActivity.this, "Order marked as ready.", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(CookViewActivity.this, "Failed to update order status.", Toast.LENGTH_SHORT).show();
                });
        orderList.remove(order);
        ordersAdapter.notifyDataSetChanged();
    }
}


