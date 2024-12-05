//package com.example.resturent;
//
//import android.os.Bundle;
//import android.widget.ListView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentSnapshot;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//
//public class RunningOrdersActivity extends AppCompatActivity {
//
//    private ListView listView;
//    private ArrayList<String> orderList;
//    private FirebaseFirestore db;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_running_orders);
//
//        // Initialize Firebase Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Initialize ListView and ArrayList
//        listView = findViewById(R.id.listViewOrders);
//        orderList = new ArrayList<>();
//
//        // Fetch current orders from Firestore
//        CollectionReference ordersRef = db.collection("ElaboratedOrderDetails");
//        ordersRef.get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        QuerySnapshot documentSnapshots = task.getResult();
//                        if (documentSnapshots != null) {
//                            for (DocumentSnapshot document : documentSnapshots) {
//                                String orderDetails = "Waiter: " + document.getString("waiterName") +
//                                        "\nFood: " + document.getString("foodName") +
//                                        "\nTable: " + document.getLong("tableNumber") +
//                                        "\nQuantity: " + document.getLong("quantity");
//                                orderList.add(orderDetails);
//                            }
//                            // Display orders in ListView using RunningOrderAdapter
//                            RunningOrderAdapter adapter = new RunningOrderAdapter(this, orderList);
//                            listView.setAdapter(adapter);
//                        }
//                    } else {
//                        Toast.makeText(RunningOrdersActivity.this, "Error getting orders", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//}
package com.example.resturent;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RunningOrdersActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> orderList;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_running_orders);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize ListView and ArrayList
        listView = findViewById(R.id.listViewOrders);
        orderList = new ArrayList<>();

        // Fetch current orders from Firestore
        CollectionReference ordersRef = db.collection("ElaboratedOrderDetails");
        ordersRef.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot documentSnapshots = task.getResult();
                        if (documentSnapshots != null) {
                            for (DocumentSnapshot document : documentSnapshots) {
                                // Fetch the waiter ID along with other data
                                String waiterId = document.getString("waiterId");
                                String waiterName = document.getString("waiterName");
                                String foodName = document.getString("foodName");
                                long tableNumber = document.getLong("tableNumber");
                                long quantity = document.getLong("quantity");

                                // Create a string that includes Waiter ID and other details
                                String orderDetails = "Waiter ID: " + waiterId +
                                        "\nWaiter Name: " + waiterName +
                                        "\nFood: " + foodName +
                                        "\nTable: " + tableNumber +
                                        "\nQuantity: " + quantity;

                                // Add the order to the list
                                orderList.add(orderDetails);
                            }

                            // Display orders in ListView using RunningOrderAdapter
                            RunningOrderAdapter adapter = new RunningOrderAdapter(this, orderList);
                            listView.setAdapter(adapter);
                        }
                    } else {
                        Toast.makeText(RunningOrdersActivity.this, "Error getting orders", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
