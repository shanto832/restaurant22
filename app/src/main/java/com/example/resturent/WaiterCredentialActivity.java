package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class WaiterCredentialActivity extends AppCompatActivity {

    private EditText edtWaiterId, edtWaiterName;
    private Button btnSubmit, btnShowPendingOrders;
    private FirebaseFirestore db;
    private CollectionReference waiterCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_credential);

        edtWaiterId = findViewById(R.id.edtWaiterId);
        edtWaiterName = findViewById(R.id.edtWaiterName);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnShowPendingOrders = findViewById(R.id.btnShowPendingOrders);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();
        waiterCollection = db.collection("WaiterInfo");

        // Disable the 'Show Pending Orders' button to make it non-clickable
        btnShowPendingOrders.setEnabled(false);

        // Submit credentials
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateCredentials();
            }
        });
    }

    private void validateCredentials() {
        String waiterId = edtWaiterId.getText().toString().trim();
        String waiterName = edtWaiterName.getText().toString().trim();

        if (waiterId.isEmpty() || waiterName.isEmpty()) {
            Toast.makeText(this, "Please enter both ID and Name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Query Firestore to check if the waiter ID and name match
        waiterCollection.whereEqualTo("waiterId", waiterId)
                .whereEqualTo("waiterName", waiterName)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        QuerySnapshot documents = task.getResult();
                        if (documents != null && !documents.isEmpty()) {
                            // If a matching record is found, navigate to PendingActivity
                            Intent intent = new Intent(WaiterCredentialActivity.this, PendingActivity.class);
                            intent.putExtra("waiterId", waiterId);
                            intent.putExtra("waiterName", waiterName);
                            startActivity(intent);
                        } else {
                            // If no matching record is found
                            Toast.makeText(this, "Invalid ID or Name", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Handle any errors that occurred during the query
                        Toast.makeText(this, "Error validating credentials: " + task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
