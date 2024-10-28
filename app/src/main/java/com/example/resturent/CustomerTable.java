package com.example.resturent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class CustomerTable extends AppCompatActivity {
   private int tableNumbert;

    private Button btnTable1, btnTable2, btnTable3, btnTable4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_table);

        // Initialize the TextView
        TextView tvChooseTable = findViewById(R.id.tvChooseTable);
        tvChooseTable.setText("Choose a table:");

        // Initialize buttons
        btnTable1 = findViewById(R.id.btnTable1);
        btnTable2 = findViewById(R.id.btnTable2);
        btnTable3 = findViewById(R.id.btnTable3);
        btnTable4 = findViewById(R.id.btnTable4);

        // Set click listeners for each button
        btnTable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTableSelection(1);
                tableNumbert=1;

            }
        });

        btnTable2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTableSelection(2);
                tableNumbert=2;
            }
        });

        btnTable3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTableSelection(3);
                tableNumbert=3;
            }
        });

        btnTable4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTableSelection(4);
                tableNumbert=4;
            }
        });
    }

    private void showTableSelection(int tableNumber) {
        Toast.makeText(this, "You selected Table " + tableNumber+ tableNumbert, Toast.LENGTH_SHORT).show();
    }
}
