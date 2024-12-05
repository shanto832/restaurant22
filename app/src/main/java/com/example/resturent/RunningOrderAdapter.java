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
//public class RunningOrderAdapter extends ArrayAdapter<String> {
//
//    public RunningOrderAdapter(Context context, List<String> orders) {
//        super(context, 0, orders);
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
//        }
//
//        String order = getItem(position);
//        TextView orderTextView = convertView.findViewById(R.id.orderTextView);
//        orderTextView.setText(order);
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

public class RunningOrderAdapter extends ArrayAdapter<String> {

    public RunningOrderAdapter(Context context, List<String> orders) {
        super(context, 0, orders);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.order_item, parent, false);
        }

        String order = getItem(position);
        TextView orderTextView = convertView.findViewById(R.id.orderTextView);
        orderTextView.setText(order);

        return convertView;
    }
}

