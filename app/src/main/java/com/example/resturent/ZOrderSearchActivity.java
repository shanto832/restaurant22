package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class ZOrderSearchActivity extends AppCompatActivity {

    private EditText editTextWaiterId, editTextTableNumber;
    private RecyclerView recyclerViewOrders;
    private ZOrderAdapter orderAdapter;
    private ArrayList<ZOrder> orderList = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zorder_search);

        editTextWaiterId = findViewById(R.id.editTextWaiterId);
        editTextTableNumber = findViewById(R.id.editTextTableNumber);
        recyclerViewOrders = findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new ZOrderAdapter(orderList);
        recyclerViewOrders.setAdapter(orderAdapter);

        Button buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonSubmit.setOnClickListener(v -> {
            String waiterId = editTextWaiterId.getText().toString().trim();
            String tableNumberStr = editTextTableNumber.getText().toString().trim();
            int tableNumber = tableNumberStr.isEmpty() ? -1 : Integer.parseInt(tableNumberStr);

            if (waiterId.isEmpty() && tableNumber == -1) {
                Toast.makeText(this, "Please enter either Waiter ID or Table Number.", Toast.LENGTH_SHORT).show();
            } else {
                fetchMatchingOrders(waiterId, tableNumber);
            }
        });

        Button buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(v -> {
            Intent intent = new Intent(ZOrderSearchActivity.this, Manage.class);
            startActivity(intent);
            finish();
        });
    }

    private void fetchMatchingOrders(String waiterId, int tableNumber) {
        orderList.clear();
        Query query;

        if (!waiterId.isEmpty() && tableNumber != -1) {
            query = db.collection("ElaboratedOrderDetails")
                    .whereEqualTo("waiterId", waiterId)
                    .whereEqualTo("tableNumber", tableNumber);
        } else if (!waiterId.isEmpty()) {
            query = db.collection("ElaboratedOrderDetails")
                    .whereEqualTo("waiterId", waiterId);
        } else {
            query = db.collection("ElaboratedOrderDetails")
                    .whereEqualTo("tableNumber", tableNumber);
        }

        query.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String waiterIdResult = document.getString("waiterId");
                            String waiterName = document.getString("waiterName");
                            int tableNumberResult = document.getLong("tableNumber").intValue();
                            String foodName = document.getString("foodName");
                            int quantity = document.getLong("quantity").intValue();

                            orderList.add(new ZOrder(waiterIdResult, waiterName, tableNumberResult, foodName, quantity));
                        }
                        orderAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
