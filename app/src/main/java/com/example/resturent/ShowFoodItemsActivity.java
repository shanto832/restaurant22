
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//public class ShowFoodItemsActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private FoodItemAdapter adapter;
//    private List<FoodItem> foodItemList;
//    private FirebaseFirestore db;
//    private int selectedTableNumber;
//    private String userName;
//    private String userPhoneNumber;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_food_items);
//
//        // Retrieve the data passed from CustomerTable Activity
//        selectedTableNumber = getIntent().getIntExtra("selectedTableNumber", -1);
//        userName = getIntent().getStringExtra("userName"); // Get the name
//        userPhoneNumber = getIntent().getStringExtra("userPhoneNumber"); // Get the phone number
//
//        db = FirebaseFirestore.getInstance();
//
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        foodItemList = new ArrayList<>();
//        adapter = new FoodItemAdapter(foodItemList, selectedTableNumber);
//        recyclerView.setAdapter(adapter);
//
//        Button submitButton = findViewById(R.id.submitButton);
//        submitButton.setOnClickListener(v -> submitOrder());
//
//        fetchFoodItemsFromFirestore();
//    }
//
//    private void fetchFoodItemsFromFirestore() {
//        db.collection("FoodItems").get().addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                foodItemList.clear();
//                for (QueryDocumentSnapshot document : task.getResult()) {
//                    FoodItem foodItem = document.toObject(FoodItem.class);
//                    foodItemList.add(foodItem);
//                }
//                adapter.notifyDataSetChanged();
//            } else {
//                Toast.makeText(this, "Failed to load food items.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void submitOrder() {
//        Map<String, Integer> selectedQuantities = adapter.getQuantityMap();
//
//        // Pass the selected data to FoodItemDetailActivity
//        Intent intent = new Intent(this, FoodItemDetailActivity.class);
//        intent.putExtra("selectedTableNumber", selectedTableNumber);
//
//        // Send the food names and quantities to the next activity
//        ArrayList<String> foodNames = new ArrayList<>(selectedQuantities.keySet());
//        ArrayList<Integer> quantities = new ArrayList<>(selectedQuantities.values());
//        intent.putStringArrayListExtra("foodNames", foodNames);
//        intent.putIntegerArrayListExtra("quantities", quantities);
//
//        // Pass the customer data to the next activity
//        intent.putExtra("userName", userName); // Send the user name
//        intent.putExtra("userPhoneNumber", userPhoneNumber); // Send the user phone number
//
//        startActivity(intent);
//    }
//}

package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowFoodItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FoodItemAdapter adapter;
    private List<FoodItem> foodItemList;
    private FirebaseFirestore db;
    private int selectedTableNumber;
    private String userName;
    private String userPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food_items);

        // Retrieve the data passed from CustomerTable Activity
        selectedTableNumber = getIntent().getIntExtra("selectedTableNumber", -1);
        userName = getIntent().getStringExtra("userName"); // Get the name
        userPhoneNumber = getIntent().getStringExtra("userPhoneNumber"); // Get the phone number

        db = FirebaseFirestore.getInstance();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        foodItemList = new ArrayList<>();
        adapter = new FoodItemAdapter(foodItemList, selectedTableNumber);
        recyclerView.setAdapter(adapter);

        Button submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(v -> submitOrder());

        fetchFoodItemsFromFirestore();
    }

    private void fetchFoodItemsFromFirestore() {
        db.collection("FoodItems").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                foodItemList.clear();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    FoodItem foodItem = document.toObject(FoodItem.class);
                    foodItemList.add(foodItem);
                }
                adapter.notifyDataSetChanged();
            } else {
                Toast.makeText(this, "Failed to load food items.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitOrder() {
        Map<String, Integer> selectedQuantities = adapter.getQuantityMap();

        // Check quantities against the database
        db.collection("FoodItems").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                boolean allAvailable = true; // Flag to check if all quantities are valid
                StringBuilder unavailableMessage = new StringBuilder(); // Message for unavailable items

                for (QueryDocumentSnapshot document : task.getResult()) {
                    FoodItem foodItem = document.toObject(FoodItem.class);
                    String foodName = foodItem.getFoodName();
                    int availableQuantity = foodItem.getFoodQuantity(); // Get quantity as int directly

                    if (selectedQuantities.containsKey(foodName)) {
                        int selectedQuantity = selectedQuantities.get(foodName);

                        if (selectedQuantity > availableQuantity) {
                            allAvailable = false;
                            unavailableMessage.append(foodName)
                                    .append(": Only ")
                                    .append(availableQuantity)
                                    .append(" available\n");
                        }
                    }
                }

                if (allAvailable) {
                    // If all quantities are valid, proceed to the next activity
                    Intent intent = new Intent(this, FoodItemDetailActivity.class);
                    intent.putExtra("selectedTableNumber", selectedTableNumber);

                    // Send the food names and quantities to the next activity
                    ArrayList<String> foodNames = new ArrayList<>(selectedQuantities.keySet());
                    ArrayList<Integer> quantities = new ArrayList<>(selectedQuantities.values());
                    intent.putStringArrayListExtra("foodNames", foodNames);
                    intent.putIntegerArrayListExtra("quantities", quantities);

                    // Pass the customer data to the next activity
                    intent.putExtra("userName", userName); // Send the user name
                    intent.putExtra("userPhoneNumber", userPhoneNumber); // Send the user phone number

                    startActivity(intent);
                } else {
                    // Show a Toast message with unavailable items
                    Toast.makeText(this, "Some quantities are not available:\n" + unavailableMessage.toString().trim(), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, "Failed to check food availability.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}





