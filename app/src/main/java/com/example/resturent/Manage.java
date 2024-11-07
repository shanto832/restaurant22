package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;

public class Manage extends AppCompatActivity {

    private Button btnAddFood, btnSeeCurrentOrders, btnTransactionHistory, btnCustomerDetails, btnWaiterDetails;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize buttons
        btnAddFood = findViewById(R.id.btnAddFood);
        btnSeeCurrentOrders = findViewById(R.id.btnSeeCurrentOrders);
        btnTransactionHistory = findViewById(R.id.btnTransactionHistory);
        btnCustomerDetails = findViewById(R.id.btnCustomerDetails);
        btnWaiterDetails = findViewById(R.id.btnWaiterDetails);

        // Set click listener for "Add Food" button
        btnAddFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Manage.this, AddFoodActivity.class);
                startActivity(intent);
            }
        });

        // Add click listeners for other buttons as needed
        // Example for "See Current Orders"
        btnSeeCurrentOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start SeeCurrentOrdersActivity (not implemented here)
            }
        });

        // Repeat for other buttons...
    }
}
