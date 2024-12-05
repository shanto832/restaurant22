//
//package com.example.resturent;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.firestore.QuerySnapshot;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ShowFoodItemsActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private FoodItemAdapter adapter;
//    private List<FoodItem> foodItemList;
//    private FirebaseFirestore db;
//    private int selectedTableNumber; // Variable to hold the selected table number
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_food_items);
//
//        // Retrieve the selected table number passed from the previous activity
//        selectedTableNumber = getIntent().getIntExtra("selectedTableNumber", -1);
//
//        // If no table is selected, show an error message
//        if (selectedTableNumber == -1) {
//            Toast.makeText(this, "Error: No table selected.", Toast.LENGTH_SHORT).show();
//            finish(); // Close this activity if no table was selected
//            return;
//        }
//
//        // Display the selected table number in a Toast message (Optional)
//        Toast.makeText(this, "You selected Table " + selectedTableNumber, Toast.LENGTH_SHORT).show();
//
//        // Initialize Firestore instance
//        db = FirebaseFirestore.getInstance();
//
//        // Initialize RecyclerView
//        recyclerView = findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        // Initialize the food item list and adapter
//        foodItemList = new ArrayList<>();
//        adapter = new FoodItemAdapter(foodItemList, selectedTableNumber); // Pass the selected table number to the adapter
//        recyclerView.setAdapter(adapter);
//
//        // Fetch food items from Firestore
//        fetchFoodItemsFromFirestore();
//    }
//
//    private void fetchFoodItemsFromFirestore() {
//        db.collection("FoodItems")
//                .get()
//                .addOnCompleteListener(task -> {
//                    if (task.isSuccessful()) {
//                        foodItemList.clear(); // Clear the list before adding new data
//                        QuerySnapshot querySnapshot = task.getResult();
//                        if (querySnapshot != null) {
//                            for (QueryDocumentSnapshot document : querySnapshot) {
//                                FoodItem foodItem = document.toObject(FoodItem.class);
//                                foodItemList.add(foodItem);
//                            }
//                            adapter.notifyDataSetChanged(); // Refresh RecyclerView
//                        }
//                    } else {
//                        Log.e("Firestore", "Error getting documents", task.getException());
//                        Toast.makeText(ShowFoodItemsActivity.this, "Failed to load food items.", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//}
//
//package com.example.resturent;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
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
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_food_items);
//
//        selectedTableNumber = getIntent().getIntExtra("selectedTableNumber", -1);
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
//        Intent intent = new Intent(this, FoodItemDetailActivity.class);
//        intent.putExtra("selectedTableNumber", selectedTableNumber);
//
//        ArrayList<String> foodNames = new ArrayList<>(selectedQuantities.keySet());
//        ArrayList<Integer> quantities = new ArrayList<>(selectedQuantities.values());
//        intent.putStringArrayListExtra("foodNames", foodNames);
//        intent.putIntegerArrayListExtra("quantities", quantities);
//
//        startActivity(intent);
//    }
//}
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
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
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_food_items);
//
//        selectedTableNumber = getIntent().getIntExtra("selectedTableNumber", -1);
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
//        startActivity(intent);
//    }
//}
//package com.example.resturent;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//
//import java.util.ArrayList;
//import java.util.HashMap;
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
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_show_food_items);
//
//        selectedTableNumber = getIntent().getIntExtra("selectedTableNumber", -1);
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
//        startActivity(intent);
//    }
//}

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

        // Pass the selected data to FoodItemDetailActivity
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
    }
}



