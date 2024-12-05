package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class WaiterCredentialActivity extends AppCompatActivity {

    private EditText edtWaiterId, edtWaiterName;
    private Button btnSubmit, btnShowPendingOrders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiter_credential);

        edtWaiterId = findViewById(R.id.edtWaiterId);
        edtWaiterName = findViewById(R.id.edtWaiterName);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnShowPendingOrders = findViewById(R.id.btnShowPendingOrders);

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
        String waiterId = edtWaiterId.getText().toString();
        String waiterName = edtWaiterName.getText().toString();

        if (waiterId.isEmpty() || waiterName.isEmpty()) {
            Toast.makeText(this, "Please enter both ID and Name", Toast.LENGTH_SHORT).show();
            return;
        }

        // Valid waiter IDs
        String[] validIds = {"rms1", "rms2", "rms3", "rms4"};

        for (String validId : validIds) {
            if (waiterId.equals(validId)) {
                // If ID is valid, pass ID and Name to PendingActivity
                Intent intent = new Intent(WaiterCredentialActivity.this, PendingActivity.class);
                intent.putExtra("waiterId", waiterId);
                intent.putExtra("waiterName", waiterName);
                startActivity(intent);
                return;
            }
        }

        // If ID is invalid
        Toast.makeText(this, "Invalid ID", Toast.LENGTH_SHORT).show();
    }

    private void openPendingOrders() {
        // Open PendingActivity
        String waiterId = edtWaiterId.getText().toString();
        String waiterName = edtWaiterName.getText().toString();

        if (!waiterId.isEmpty() && !waiterName.isEmpty()) {
            Intent intent = new Intent(WaiterCredentialActivity.this, PendingActivity.class);
            intent.putExtra("waiterId", waiterId);
            intent.putExtra("waiterName", waiterName);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please enter both ID and Name", Toast.LENGTH_SHORT).show();
        }
    }
}
