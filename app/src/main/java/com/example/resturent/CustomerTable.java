package com.example.resturent;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerTable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_table);

        TextView tvChooseTable = findViewById(R.id.tvChooseTable);
        tvChooseTable.setText("Choose a table:");

        setupTableButton(R.id.btnTable1, 1);
        setupTableButton(R.id.btnTable2, 2);
        setupTableButton(R.id.btnTable3, 3);
        setupTableButton(R.id.btnTable4, 4);
    }

    private void setupTableButton(int buttonId, int tableNumber) {
        Button button = findViewById(buttonId);
        button.setOnClickListener(v -> Toast.makeText(this, "You selected Table " + tableNumber, Toast.LENGTH_SHORT).show());
    }
}
