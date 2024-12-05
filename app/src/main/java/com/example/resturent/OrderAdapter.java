//package com.example.resturent;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class OrderAdapter extends ArrayAdapter<OrderWithTable> {
//
//    private Context context;
//    private int resource;
//
//    public OrderAdapter(Context context, int resource) {
//        super(context, resource);
//        this.context = context;
//        this.resource = resource;
//    }
//
//    // Override to handle how each item is displayed in the list
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            LayoutInflater inflater = LayoutInflater.from(context);
//            convertView = inflater.inflate(resource, parent, false);
//        }
//
//        // Get the current order item
//        OrderWithTable order = getItem(position);
//
//        // Set the data into the view
//        TextView foodNameTextView = convertView.findViewById(R.id.foodNameTextView);
//        TextView tableNumberTextView = convertView.findViewById(R.id.tableNumberTextView);
//        TextView quantityTextView = convertView.findViewById(R.id.quantityTextView);
//
//        if (order != null) {
//            foodNameTextView.setText("Food: " + order.getFoodName());
//            tableNumberTextView.setText("Table: " + order.getTableNumber());
//            quantityTextView.setText("Quantity: " + order.getQuantity());
//        }
//
//        return convertView;
//    }
//}
package com.example.resturent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class OrderAdapter extends ArrayAdapter<OrderWithTable> {

    private Context context;
    private int resource;

    public OrderAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    // Override to handle how each item is displayed in the list
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
        }

        // Get the current order item
        OrderWithTable order = getItem(position);

        // Set the data into the view
        TextView foodNameTextView = convertView.findViewById(R.id.foodNameTextView);
        TextView tableNumberTextView = convertView.findViewById(R.id.tableNumberTextView);
        TextView quantityTextView = convertView.findViewById(R.id.quantityTextView);
        TextView customerNameTextView = convertView.findViewById(R.id.customerNameTextView); // New TextView for customer name

        if (order != null) {
            foodNameTextView.setText("Food: " + order.getFoodName());
            tableNumberTextView.setText("Table: " + order.getTableNumber());
            quantityTextView.setText("Quantity: " + order.getQuantity());

            // Use getUserName() instead of getCustomerName() because that's the method in OrderWithTable
            customerNameTextView.setText("Customer: " + order.getUserName());
        }

        return convertView;
    }
}

