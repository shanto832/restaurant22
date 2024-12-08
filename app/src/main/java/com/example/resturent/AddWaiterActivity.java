package com.example.resturent;



import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class AddWaiterActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private CollectionReference waiterCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_waiter);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();
        waiterCollection = db.collection("WaiterInfo");

        EditText waiterIdEditText = findViewById(R.id.waiterIdEditText);
        EditText waiterNameEditText = findViewById(R.id.waiterNameEditText);
        Button addButton = findViewById(R.id.addButton);

        addButton.setOnClickListener(v -> {
            String waiterId = waiterIdEditText.getText().toString().trim();
            String waiterName = waiterNameEditText.getText().toString().trim();

            if (waiterId.isEmpty() || waiterName.isEmpty()) {
                Toast.makeText(AddWaiterActivity.this, "Please enter both waiter ID and name", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check if the waiter ID already exists
            waiterCollection.whereEqualTo("waiterId", waiterId).get()
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            QuerySnapshot documents = task.getResult();
                            if (documents != null && !documents.isEmpty()) {
                                Toast.makeText(AddWaiterActivity.this, "Waiter ID already exists", Toast.LENGTH_SHORT).show();
                            } else {
                                // Add new waiter to Firestore
                                db.collection("WaiterInfo").document(waiterId)
                                        .set(new Waiter(waiterId, waiterName))
                                        .addOnSuccessListener(aVoid -> {
                                            Toast.makeText(AddWaiterActivity.this, "Waiter added successfully", Toast.LENGTH_SHORT).show();
                                            finish(); // Close the activity after adding
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(AddWaiterActivity.this, "Error adding waiter: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        });
                            }
                        } else {
                            Toast.makeText(AddWaiterActivity.this, "Error checking waiter ID: " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }

    public static class Waiter {
        public String waiterId;
        public String waiterName;

        public Waiter(String waiterId, String waiterName) {
            this.waiterId = waiterId;
            this.waiterName = waiterName;
        }
    }
}
