//package com.example.resturent;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import androidx.recyclerview.widget.RecyclerView;
//import java.util.List;
//
//public class OrderWithTableAdapter extends RecyclerView.Adapter<OrderWithTableAdapter.OrderViewHolder> {
//
//    private List<OrderWithTable> ordersList;
//
//    public OrderWithTableAdapter(List<OrderWithTable> ordersList) {
//        this.ordersList = ordersList;
//    }
//
//    @Override
//    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_order, parent, false);
//        return new OrderViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(OrderViewHolder holder, int position) {
//        OrderWithTable order = ordersList.get(position);
//        holder.tvTableNumber.setText(String.valueOf(order.getTableNumber()));
//        holder.tvFoodName.setText(order.getFoodName());
//        holder.tvQuantity.setText(String.valueOf(order.getQuantity()));
//    }
//
//    @Override
//    public int getItemCount() {
//        return ordersList.size();
//    }
//
//    public class OrderViewHolder extends RecyclerView.ViewHolder {
//
//        public TextView tvTableNumber, tvFoodName, tvQuantity;
//
//        public OrderViewHolder(View view) {
//            super(view);
//            tvTableNumber = view.findViewById(R.id.tvTableNumber);
//            tvFoodName = view.findViewById(R.id.tvFoodName);
//            tvQuantity = view.findViewById(R.id.tvQuantity);
//        }
//    }
//}
package com.example.resturent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrderWithTableAdapter extends RecyclerView.Adapter<OrderWithTableAdapter.OrderViewHolder> {

    private List<OrderWithTable> ordersList;

    public OrderWithTableAdapter(List<OrderWithTable> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(OrderViewHolder holder, int position) {
        OrderWithTable order = ordersList.get(position);

        // Set the text with labels for clarity
        holder.tvFoodName.setText("Food Name: " + order.getFoodName());
        holder.tvQuantity.setText("Quantity: " + order.getQuantity());
        holder.tvTableNumber.setText("Table Number: " + order.getTableNumber());
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        public TextView tvFoodName, tvQuantity, tvTableNumber;

        public OrderViewHolder(View view) {
            super(view);
            tvFoodName = view.findViewById(R.id.tvFoodName);
            tvQuantity = view.findViewById(R.id.tvQuantity);
            tvTableNumber = view.findViewById(R.id.tvTableNumber);
        }
    }
}

