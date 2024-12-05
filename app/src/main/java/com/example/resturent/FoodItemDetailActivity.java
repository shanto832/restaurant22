package com.example.resturent;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FoodItemDetailActivity extends AppCompatActivity {

    private TextView orderDetailsTextView, timerTextView;
    private Button confirmOrderButton;
    private int selectedTableNumber;
    private ArrayList<String> foodNames;
    private ArrayList<Integer> quantities;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CountDownTimer countDownTimer;
    private long timeLeftInMillis = 600000; // 10 minutes in milliseconds

    // Flag to stop the timer if the order is marked ready
    private boolean orderReady = false;
    private DocumentReference orderReference;

    // New variables to store customer information
    private String userName;
    private String userPhoneNumber;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item_detail);

        orderDetailsTextView = findViewById(R.id.orderDetailsTextView);
        confirmOrderButton = findViewById(R.id.confirmOrderButton);
        timerTextView = findViewById(R.id.timerTextView);

        // Get data from the previous activity
        selectedTableNumber = getIntent().getIntExtra("selectedTableNumber", -1);
        foodNames = getIntent().getStringArrayListExtra("foodNames");
        quantities = getIntent().getIntegerArrayListExtra("quantities");

        // Get customer details from the Intent
        userName = getIntent().getStringExtra("userName");
        userPhoneNumber = getIntent().getStringExtra("userPhoneNumber");

        // Display the order details (table number, food items, and quantities)
        StringBuilder orderDetails = new StringBuilder();
        orderDetails.append("Table Number: ").append(selectedTableNumber).append("\n");
        for (int i = 0; i < foodNames.size(); i++) {
            orderDetails.append(foodNames.get(i)).append(" - Quantity: ").append(quantities.get(i)).append("\n");
        }
        orderDetailsTextView.setText(orderDetails.toString());

        confirmOrderButton.setOnClickListener(v -> {
            // Disable the button to prevent multiple clicks
            confirmOrderButton.setEnabled(false);
            confirmOrder();
        });
    }

    // Method to confirm order and save it to Firestore
    private void confirmOrder() {
        if (selectedTableNumber != -1 && foodNames != null && !foodNames.isEmpty() && quantities != null && !quantities.isEmpty()) {
            for (int i = 0; i < foodNames.size(); i++) {
                String foodName = foodNames.get(i);
                int quantity = quantities.get(i);

                Map<String, Object> orderData = new HashMap<>();
                orderData.put("tableNumber", selectedTableNumber);
                orderData.put("foodName", foodName);
                orderData.put("quantity", quantity);
                orderData.put("userName", userName); // Store customer name
                orderData.put("userPhoneNumber", userPhoneNumber); // Store customer phone number
                orderData.put("status", "in-progress"); // Order initially in-progress

                // Add the order to Firestore
                db.collection("OrderWithTable")
                        .add(orderData)
                        .addOnSuccessListener(documentReference -> {
                            orderReference = documentReference;
                            Toast.makeText(FoodItemDetailActivity.this, "Order confirmed!", Toast.LENGTH_SHORT).show();
                            startTimer(); // Start countdown timer after order confirmation
                        })
                        .addOnFailureListener(e -> {
                            Toast.makeText(FoodItemDetailActivity.this, "Failed to confirm order. Try again.", Toast.LENGTH_SHORT).show();
                            // Re-enable the button if there is an error
                            confirmOrderButton.setEnabled(true);
                        });
            }
        } else {
            Toast.makeText(this, "Please check the order details.", Toast.LENGTH_SHORT).show();
            // Re-enable the button if the order details are incorrect
            confirmOrderButton.setEnabled(true);
        }
    }

    // Method to start the countdown timer
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (orderReady) {
                    cancel(); // Stop timer if the order is marked as ready
                    timerTextView.setText("00:00");
                    Toast.makeText(FoodItemDetailActivity.this, "Your order is ready!", Toast.LENGTH_SHORT).show();
                    return;
                }
                timeLeftInMillis = millisUntilFinished;
                updateTimerDisplay();
            }

            @Override
            public void onFinish() {
                if (!orderReady) {
                    timerTextView.setText("00:00"); // Timer reaches 00:00
                }
            }
        }.start();

        // Listen for changes to order status in Firestore
        if (orderReference != null) {
            orderReference.addSnapshotListener((snapshot, e) -> {
                if (e != null) {
                    return; // Return if there's an error
                }
                if (snapshot != null && snapshot.exists()) {
                    String status = snapshot.getString("status");
                    if ("ready".equals(status)) {
                        orderReady = true; // Mark order as ready to stop timer
                    }
                }
            });
        }
    }

    // Method to update the timer display
    private void updateTimerDisplay() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel(); // Stop the timer when the activity is destroyed
        }
    }
}
