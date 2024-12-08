package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class WaiterActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private CollectionReference waiterCollection;
    private ListView waiterListView;
    private ArrayList<String> waiterList;
    private ArrayAdapter<String> adapter;
    private String selectedWaiterId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();
        waiterCollection = db.collection("WaiterInfo");

        // Initialize ListView and Adapter
        waiterListView = findViewById(R.id.waiterListView);
        waiterList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, waiterList);
        waiterListView.setAdapter(adapter);

        // Load data from Firestore
        loadWaiterData();

        // Set item click listener for the ListView
        waiterListView.setOnItemClickListener((parentView, view, position, id) -> {
            String selectedItem = waiterList.get(position);
            selectedWaiterId = selectedItem.split(" ")[0]; // Extract the waiterId from the string
            showDeleteConfirmation();
        });

        // Set up the buttons
        Button homeButton = findViewById(R.id.homeButton);
        Button addButton = findViewById(R.id.addButton);
        Button confirmDeleteButton = findViewById(R.id.confirmDeleteButton);

        homeButton.setOnClickListener(v -> {
            // Navigate to Home (implement later)
            Intent intent = new Intent(WaiterActivity.this, Manage.class);
            startActivity(intent);
        });

        addButton.setOnClickListener(v -> {
            // Open AddWaiterActivity
            Intent intent = new Intent(WaiterActivity.this, AddWaiterActivity.class);
            startActivity(intent);
        });

        confirmDeleteButton.setOnClickListener(v -> {
            if (!selectedWaiterId.isEmpty()) {
                deleteWaiter(selectedWaiterId);
            } else {
                Toast.makeText(WaiterActivity.this, "No waiter selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadWaiterData() {
        waiterCollection.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                QuerySnapshot documents = task.getResult();
                if (documents != null) {
                    waiterList.clear();
                    for (QueryDocumentSnapshot document : documents) {
                        String waiterId = document.getString("waiterId");
                        String waiterName = document.getString("waiterName");
                        if (waiterId != null && waiterName != null) {
                            waiterList.add(waiterId + " " + waiterName);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(WaiterActivity.this, "Error getting data: " + task.getException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showDeleteConfirmation() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Delete")
                .setMessage("Are you sure you want to delete this record?")
                .setPositiveButton("Yes", (dialog, which) -> {
                    deleteWaiter(selectedWaiterId);
                })
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .show();
    }

    private void deleteWaiter(String waiterId) {
        waiterCollection.document(waiterId).delete()
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(WaiterActivity.this, "Record deleted", Toast.LENGTH_SHORT).show();
                    // Remove from local list and refresh the list view
                    for (int i = 0; i < waiterList.size(); i++) {
                        if (waiterList.get(i).startsWith(waiterId)) {
                            waiterList.remove(i);
                            break;
                        }
                    }
                    adapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(WaiterActivity.this, "Error deleting record: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
