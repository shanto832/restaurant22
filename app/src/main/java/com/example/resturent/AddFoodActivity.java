package com.example.resturent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class AddFoodActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText etFoodName, etFoodPrice;
    private ImageView ivFoodImage;

    private Uri imageUri;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        // Initialize views
        etFoodName = findViewById(R.id.etFoodName);
        etFoodPrice = findViewById(R.id.etFoodPrice);
        ivFoodImage = findViewById(R.id.ivFoodImage);
        Button btnSelectImage = findViewById(R.id.btnSelectImage);
        Button btnUploadFood = findViewById(R.id.btnUploadFood);

        // Initialize Firebase references
        databaseReference = FirebaseDatabase.getInstance().getReference("foods");
        storageReference = FirebaseStorage.getInstance().getReference("food_images");

        // Set click listener for the image selection button
        btnSelectImage.setOnClickListener(v -> openGallery());

        // Set click listener for the upload button
        btnUploadFood.setOnClickListener(v -> uploadFoodItem());
    }

    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            ivFoodImage.setImageURI(imageUri);
        }
    }

    private void uploadFoodItem() {
        String name = etFoodName.getText().toString().trim();
        String priceStr = etFoodPrice.getText().toString().trim();

        if (name.isEmpty() || imageUri == null || priceStr.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            Log.d("AddFoodActivity", "All fields are required");
            return;
        }

        double price;
        try {
            price = Double.parseDouble(priceStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid price format", Toast.LENGTH_SHORT).show();
            Log.e("AddFoodActivity", "Invalid price format: " + priceStr);
            return;
        }

        String foodId = databaseReference.push().getKey();
        Log.d("AddFoodActivity", "Food ID: " + foodId);

        if (foodId != null) {
            Log.d("AddFoodActivity", "Database path: " + databaseReference.child(foodId).toString());

            // Create a map to hold food item data
            Map<String, Object> foodItemMap = new HashMap<>();
            foodItemMap.put("name", name);
            foodItemMap.put("image", "placeholder_image_url"); // Set this later with the real URL
            foodItemMap.put("price", price);

            // Store the item in the database
            databaseReference.child(foodId).setValue(foodItemMap)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(AddFoodActivity.this, "Food item added successfully", Toast.LENGTH_SHORT).show();
                            clearFields();
                            Log.d("AddFoodActivity", "Food item added to database successfully");
                        } else {
                            Log.e("AddFoodActivity", "Error adding food item: " + task.getException());
                            Toast.makeText(AddFoodActivity.this, "Failed to add food item", Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            Log.e("AddFoodActivity", "Food ID is null");
        }
    }


    private void clearFields() {
        etFoodName.setText("");
        etFoodPrice.setText("");
        ivFoodImage.setImageResource(android.R.color.darker_gray); // Reset image view
        imageUri = null; // Clear the selected image URI
    }
}
