//package com.example.resturent;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//import com.google.firebase.firestore.FirebaseFirestore;
//import java.util.List;
//
//public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {
//
//    private List<OrderWithTable> orderList;
//    private FirebaseFirestore db;
//
//    public OrdersAdapter(List<OrderWithTable> orderList, FirebaseFirestore db) {
//        this.orderList = orderList;
//        this.db = db;
//    }
//
//    @NonNull
//    @Override
//    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item2_order, parent, false); // Updated to item2_order
//        return new OrderViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
//        OrderWithTable order = orderList.get(position);
//        holder.tableNumberTextView.setText("Table: " + order.getTableNumber());
//        holder.foodNameTextView.setText("Food: " + order.getFoodName());
//        holder.quantityTextView.setText("Quantity: " + order.getQuantity());
//
//        holder.itemView.setOnClickListener(v -> {
//            db.collection("OrderWithTable").document(order.getId())
//                    .update("status", "ready")
//                    .addOnSuccessListener(aVoid ->
//                            Toast.makeText(holder.itemView.getContext(), "Order marked as ready!", Toast.LENGTH_SHORT).show()
//                    )
//                    .addOnFailureListener(e ->
//                            Toast.makeText(holder.itemView.getContext(), "Failed to update order.", Toast.LENGTH_SHORT).show()
//                    );
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return orderList.size();
//    }
//
//    static class OrderViewHolder extends RecyclerView.ViewHolder {
//        TextView tableNumberTextView, foodNameTextView, quantityTextView;
//
//        OrderViewHolder(View itemView) {
//            super(itemView);
//            tableNumberTextView = itemView.findViewById(R.id.tableNumberTextView);
//            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
//            quantityTextView = itemView.findViewById(R.id.quantityTextView);
//        }
//    }
//}
package com.example.resturent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.OrderViewHolder> {

    private List<OrderWithTable> orders;
    private OrderClickListener clickListener;

    // Define the interface for item click listener
    public interface OrderClickListener {
        void onOrderClick(OrderWithTable order);
    }

    public OrdersAdapter(List<OrderWithTable> orders, OrderClickListener clickListener) {
        this.orders = orders;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item2_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        OrderWithTable order = orders.get(position);
        holder.foodNameTextView.setText(order.getFoodName());
        holder.tableNumberTextView.setText("Table: " + order.getTableNumber());
        holder.quantityTextView.setText("Quantity: " + order.getQuantity());

        // Set click listener on the item view
        holder.itemView.setOnClickListener(v -> clickListener.onOrderClick(order));
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView, tableNumberTextView, quantityTextView;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            tableNumberTextView = itemView.findViewById(R.id.tableNumberTextView);
            quantityTextView = itemView.findViewById(R.id.quantityTextView);
        }
    }
}



