//package com.example.resturent;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.TextView;
//
//import java.util.List;
//
//public class TransactionAdapter extends BaseAdapter {
//
//    private Context context;
//    private int layout;
//    private List<TransactionFinalDetails> transactionList;
//
//    public TransactionAdapter(Context context, int layout, List<TransactionFinalDetails> transactionList) {
//        this.context = context;
//        this.layout = layout;
//        this.transactionList = transactionList;
//    }
//
//    @Override
//    public int getCount() {
//        return transactionList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return transactionList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
//        }
//
//        // Get the current transaction
//        TransactionFinalDetails transaction = transactionList.get(position);
//
//        // Set the transaction details
//        TextView customerNameTextView = convertView.findViewById(R.id.customerNameTextView);
//        TextView customerPhoneTextView = convertView.findViewById(R.id.customerPhoneTextView);
//        TextView foodNameTextView = convertView.findViewById(R.id.foodNameTextView);
//        TextView unitPriceTextView = convertView.findViewById(R.id.unitPriceTextView);
//        TextView quantityTextView = convertView.findViewById(R.id.quantityTextView);
//        TextView totalPriceTextView = convertView.findViewById(R.id.totalPriceTextView);
//
//        customerNameTextView.setText(transaction.getCustomerName());
//        customerPhoneTextView.setText(transaction.getCustomerPhoneNumber());
//        foodNameTextView.setText(transaction.getFoodName());
//        unitPriceTextView.setText("₹" + transaction.getUnitPrice());
//        quantityTextView.setText(String.valueOf(transaction.getQuantity()));
//        totalPriceTextView.setText("₹" + transaction.getTotalPrice());
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

public class TransactionAdapter extends ArrayAdapter<TransactionFinalDetails> {

    private Context context;
    private int resource;

    public TransactionAdapter(Context context, int resource) {
        super(context, resource);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
        }

        // Get the current transaction
        TransactionFinalDetails transaction = getItem(position);

        if (transaction != null) {
            // Populate the views with transaction data
            TextView customerNameTextView = convertView.findViewById(R.id.customerNameTextView);
            TextView customerPhoneTextView = convertView.findViewById(R.id.customerPhoneTextView);
            TextView foodNameTextView = convertView.findViewById(R.id.foodNameTextView);
            TextView unitPriceTextView = convertView.findViewById(R.id.unitPriceTextView);
            TextView quantityTextView = convertView.findViewById(R.id.quantityTextView);
            TextView totalPriceTextView = convertView.findViewById(R.id.totalPriceTextView);

            customerNameTextView.setText(transaction.getCustomerName());
            customerPhoneTextView.setText(transaction.getCustomerPhoneNumber());
            foodNameTextView.setText(transaction.getFoodName());
            unitPriceTextView.setText(String.format("$%.2f", transaction.getUnitPrice()));
            quantityTextView.setText(String.valueOf(transaction.getQuantity()));
            totalPriceTextView.setText(String.format("$%.2f", transaction.getTotalPrice()));
        }

        return convertView;
    }
}
