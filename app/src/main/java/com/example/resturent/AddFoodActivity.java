package com.example.resturent;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity {

    private EditText editTextFoodName, editTextFoodPrice, editTextFoodQuantity;

    // Firestore instance
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        // Initialize Firestore instance
        db = FirebaseFirestore.getInstance();

        editTextFoodName = findViewById(R.id.editTextFoodName);
        editTextFoodPrice = findViewById(R.id.editTextFoodPrice);
        editTextFoodQuantity = findViewById(R.id.editTextFoodQuantity);
        Button submitButton = findViewById(R.id.submitButton);
        Button addMoreButton = findViewById(R.id.addMoreButton);

        submitButton.setOnClickListener(v -> submitFoodItem());
        addMoreButton.setOnClickListener(v -> clearInputFields());
    }

    private void submitFoodItem() {
        String foodName = editTextFoodName.getText().toString().trim();
        String foodPrice = editTextFoodPrice.getText().toString().trim();
        String foodQuantity = editTextFoodQuantity.getText().toString().trim();

        if (TextUtils.isEmpty(foodName) || TextUtils.isEmpty(foodPrice) || TextUtils.isEmpty(foodQuantity)) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a map for the food item
        Map<String, Object> foodItem = new HashMap<>();
        foodItem.put("foodName", foodName);
        foodItem.put("foodPrice", foodPrice);
        foodItem.put("foodQuantity", foodQuantity);

        // Add a new document with an auto-generated ID to the "FoodItems" collection
        db.collection("FoodItems").add(foodItem)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddFoodActivity.this, "Food item added successfully", Toast.LENGTH_SHORT).show();
                    clearInputFields();
                })
                .addOnFailureListener(e -> Toast.makeText(AddFoodActivity.this, "Failed to add food item: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }

    private void clearInputFields() {
        editTextFoodName.setText("");
        editTextFoodPrice.setText("");
        editTextFoodQuantity.setText("");
    }
}
