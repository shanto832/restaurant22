package com.example.resturent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ZOrderAdapter extends RecyclerView.Adapter<ZOrderAdapter.OrderViewHolder> {

    private ArrayList<ZOrder> orderList;

    // Constructor
    public ZOrderAdapter(ArrayList<ZOrder> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_z_order, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        ZOrder currentOrder = orderList.get(position);
        holder.textViewWaiterId.setText(currentOrder.getWaiterId());
        holder.textViewWaiterName.setText(currentOrder.getWaiterName());
        holder.textViewTableNumber.setText(String.valueOf(currentOrder.getTableNumber()));
        holder.textViewFoodName.setText(currentOrder.getFoodName());
        holder.textViewQuantity.setText(String.valueOf(currentOrder.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // ViewHolder class
    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textViewWaiterId, textViewWaiterName, textViewTableNumber, textViewFoodName, textViewQuantity;

        public OrderViewHolder(View itemView) {
            super(itemView);
            textViewWaiterId = itemView.findViewById(R.id.textViewWaiterId);
            textViewWaiterName = itemView.findViewById(R.id.textViewWaiterName);
            textViewTableNumber = itemView.findViewById(R.id.textViewTableNumber);
            textViewFoodName = itemView.findViewById(R.id.textViewFoodName);
            textViewQuantity = itemView.findViewById(R.id.textViewQuantity);
        }
    }
}
