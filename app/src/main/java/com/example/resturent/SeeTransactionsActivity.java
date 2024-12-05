//package com.example.resturent;
//
//import android.os.Bundle;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.firestore.DocumentSnapshot;
//
//import java.util.ArrayList;
//
//public class SeeTransactionsActivity extends AppCompatActivity {
//
//    private FirebaseFirestore db;
//    private ListView listView;
//    private TextView totalRevenueTextView;
//    private TransactionAdapter transactionAdapter;
//    private ArrayList<TransactionFinalDetails> transactionList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_see_transactions);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Initialize views
//        listView = findViewById(R.id.listViewTransactions);
//        totalRevenueTextView = findViewById(R.id.totalRevenueTextView);
//        transactionList = new ArrayList<>();
//        transactionAdapter = new TransactionAdapter(this, R.layout.list_item_transaction, transactionList);
//        listView.setAdapter(transactionAdapter);
//
//        // Fetch transactions and calculate total revenue
//        fetchTransactions();
//    }
//
//    private void fetchTransactions() {
//        db.collection("TransactionFinalDetails")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(QuerySnapshot value, com.google.firebase.firestore.FirebaseFirestoreException error) {
//                        if (error != null) {
//                            Toast.makeText(SeeTransactionsActivity.this, "Error fetching transactions", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        double totalRevenue = 0;
//
//                        // Clear the previous list
//                        transactionList.clear();
//
//                        if (value != null) {
//                            // Loop through the documents and add them to the list
//                            for (DocumentSnapshot document : value) {
//                                TransactionFinalDetails transaction = document.toObject(TransactionFinalDetails.class);
//                                if (transaction != null) {
//                                    // Add the transaction to the list
//                                    transactionList.add(transaction);
//
//                                    // Add the total price to the revenue
//                                    totalRevenue += transaction.getTotalPrice();
//                                }
//                            }
//                        }
//
//                        // Update the adapter with the new data
//                        transactionAdapter.notifyDataSetChanged();
//
//                        // Display the total revenue
//                        totalRevenueTextView.setText("Total Revenue: ₹" + totalRevenue);
//                    }
//                });
//    }
//}
//package com.example.resturent;
//
//import android.os.Bundle;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.firestore.DocumentSnapshot;
//import androidx.annotation.Nullable;
//import java.util.ArrayList;
//
//public class SeeTransactionsActivity extends AppCompatActivity {
//
//    private ListView listView;
//    private TransactionAdapter transactionAdapter;
//    private FirebaseFirestore db;
//    private TextView totalRevenueText;
//    private double totalRevenue = 0.0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_see_transactions);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Initialize the ListView and the adapter
//        listView = findViewById(R.id.listViewTransactions);
//        transactionAdapter = new TransactionAdapter(this, R.layout.list_item_transaction);
//        listView.setAdapter(transactionAdapter);
//
//        // Initialize total revenue text
//        totalRevenueText = findViewById(R.id.totalRevenueText);
//
//        // Fetch transactions from Firestore
//        db.collection("TransactionFinalDetails")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable com.google.firebase.firestore.FirebaseFirestoreException error) {
//                        if (error != null) {
//                            Toast.makeText(SeeTransactionsActivity.this, "Error loading transactions", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        if (value != null) {
//                            totalRevenue = 0.0;
//                            transactionAdapter.clear();  // Clear existing data
//
//                            // Add the transactions to the adapter
//                            for (DocumentSnapshot document : value) {
//                                TransactionFinalDetails transaction = document.toObject(TransactionFinalDetails.class);
//                                if (transaction != null) {
//                                    transactionAdapter.add(transaction);
//                                    totalRevenue += transaction.getTotalPrice();  // Add to the total revenue
//                                }
//                            }
//
//                            // Update total revenue TextView
//                            totalRevenueText.setText(String.format("Total Revenue: $%.2f", totalRevenue));
//                        }
//                    }
//                });
//    }
//}
//package com.example.resturent;
//
//import android.os.Bundle;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.app.AppCompatActivity;
//import com.google.firebase.firestore.EventListener;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QuerySnapshot;
//import com.google.firebase.firestore.DocumentSnapshot;
//import androidx.annotation.Nullable;
//import java.util.ArrayList;
//
//public class SeeTransactionsActivity extends AppCompatActivity {
//
//    private ListView listView;
//    private TransactionAdapter transactionAdapter;
//    private FirebaseFirestore db;
//    private TextView totalRevenueText;
//    private double totalRevenue = 0.0;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_see_transactions);
//
//        // Initialize Firestore
//        db = FirebaseFirestore.getInstance();
//
//        // Initialize the ListView and the adapter
//        listView = findViewById(R.id.listViewTransactions);
//        transactionAdapter = new TransactionAdapter(this, R.layout.list_item_transaction);
//        listView.setAdapter(transactionAdapter);
//
//        // Initialize total revenue text
//        totalRevenueText = findViewById(R.id.totalRevenueText);
//
//        // Fetch transactions from Firestore
//        db.collection("TransactionFinalDetails")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable com.google.firebase.firestore.FirebaseFirestoreException error) {
//                        if (error != null) {
//                            Toast.makeText(SeeTransactionsActivity.this, "Error loading transactions", Toast.LENGTH_SHORT).show();
//                            return;
//                        }
//
//                        if (value != null) {
//                            // Reset total revenue to 0.0 each time new data is fetched
//                            totalRevenue = 0.0;
//                            transactionAdapter.clear();  // Clear existing data
//
//                            // Add the transactions to the adapter and calculate total revenue
//                            for (DocumentSnapshot document : value) {
//                                TransactionFinalDetails transaction = document.toObject(TransactionFinalDetails.class);
//                                if (transaction != null) {
//                                    transactionAdapter.add(transaction);
//
//                                    // Make sure totalPrice is a valid number and then add it to totalRevenue
//                                    double transactionTotalPrice = transaction.getTotalPrice();
//                                    totalRevenue += transactionTotalPrice;
//                                }
//                            }
//
//                            // After all data is processed, update the total revenue display
//                            totalRevenueText.setText(String.format("Total Revenue: $%.2f", totalRevenue));
//                        }
//                    }
//                });
//    }
//}
package com.example.resturent;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;
import androidx.annotation.Nullable;

public class SeeTransactionsActivity extends AppCompatActivity {

    private ListView listView; // Declare ListView here
    private TransactionAdapter transactionAdapter;
    private FirebaseFirestore db;
    private TextView totalRevenueText;
    private double totalRevenue = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_transactions); // Ensure this is the correct layout

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize the ListView and the adapter
        listView = findViewById(R.id.listViewTransactions); // This should match the ID in the XML
        transactionAdapter = new TransactionAdapter(this, R.layout.list_item_transaction);
        listView.setAdapter(transactionAdapter);

        // Initialize total revenue text
        totalRevenueText = findViewById(R.id.totalRevenueText);

        // Fetch transactions from Firestore
        db.collection("TransactionFinalDetails")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable com.google.firebase.firestore.FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(SeeTransactionsActivity.this, "Error loading transactions", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        if (value != null) {
                            totalRevenue = 0.0;
                            transactionAdapter.clear();  // Clear existing data

                            // Add the transactions to the adapter
                            for (DocumentSnapshot document : value) {
                                TransactionFinalDetails transaction = document.toObject(TransactionFinalDetails.class);
                                if (transaction != null) {
                                    transactionAdapter.add(transaction);
                                    totalRevenue += transaction.getTotalPrice();  // Add to the total revenue
                                }
                            }

                            // Update total revenue TextView
                            totalRevenueText.setText(String.format("Total Revenue: $%.2f", totalRevenue));
                        }
                    }
                });
    }
}



