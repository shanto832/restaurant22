package com.example.resturent;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;

public class ZOrderDetailsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewOrders;
    private ZOrderAdapter orderAdapter;
    private ArrayList<ZOrder> orderList = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zorder_details);

        recyclerViewOrders = findViewById(R.id.recyclerViewOrders);
        recyclerViewOrders.setLayoutManager(new LinearLayoutManager(this));

        orderAdapter = new ZOrderAdapter(orderList);
        recyclerViewOrders.setAdapter(orderAdapter);

        fetchOrderDetails();

        Button buttonHome = findViewById(R.id.buttonHome);
        buttonHome.setOnClickListener(v -> {
            // Navigate to Manage activity
            Intent intent = new Intent(ZOrderDetailsActivity.this, Manage.class);
            startActivity(intent);
        });

        Button buttonSearch = findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(v -> {
            // Navigate to ZOrderSearchActivity
            Intent intent = new Intent(ZOrderDetailsActivity.this, ZOrderSearchActivity.class);
            startActivity(intent);
        });
    }

    private void fetchOrderDetails() {
        db.collection("ElaboratedOrderDetails")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String waiterId = document.getString("waiterId");
                            String waiterName = document.getString("waiterName");
                            int tableNumber = document.getLong("tableNumber").intValue();
                            String foodName = document.getString("foodName");
                            int quantity = document.getLong("quantity").intValue();

                            orderList.add(new ZOrder(waiterId, waiterName, tableNumber, foodName, quantity));
                        }
                        orderAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(this, "Failed to load data", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
