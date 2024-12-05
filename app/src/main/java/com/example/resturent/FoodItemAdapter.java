
//package com.example.resturent;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {
//
//    private List<FoodItem> foodItemList;
//    private int selectedTableNumber;
//    private Map<String, Integer> quantityMap = new HashMap<>();
//
//    public FoodItemAdapter(List<FoodItem> foodItemList, int selectedTableNumber) {
//        this.foodItemList = foodItemList;
//        this.selectedTableNumber = selectedTableNumber;
//    }
//
//    @NonNull
//    @Override
//    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
//        return new FoodItemViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
//        FoodItem foodItem = foodItemList.get(position);
//        holder.foodNameTextView.setText(foodItem.getFoodName());
//        holder.foodPriceTextView.setText("Price: $" + foodItem.getFoodPrice());
//
//        holder.quantityEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                int quantity = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());
//                quantityMap.put(foodItem.getFoodName(), quantity);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });
//    }
//
//    public Map<String, Integer> getQuantityMap() {
//        return quantityMap;
//    }
//
//    @Override
//    public int getItemCount() {
//        return foodItemList.size();
//    }
//
//    static class FoodItemViewHolder extends RecyclerView.ViewHolder {
//        TextView foodNameTextView, foodPriceTextView;
//        EditText quantityEditText;
//
//        public FoodItemViewHolder(@NonNull View itemView) {
//            super(itemView);
//            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
//            foodPriceTextView = itemView.findViewById(R.id.foodPriceTextView);
//            quantityEditText = itemView.findViewById(R.id.quantityEditText);
//        }
//    }
//}
//
package com.example.resturent;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodItemAdapter extends RecyclerView.Adapter<FoodItemAdapter.FoodItemViewHolder> {

    private List<FoodItem> foodItemList;
    private int selectedTableNumber;
    private Map<String, Integer> quantityMap = new HashMap<>();

    public FoodItemAdapter(List<FoodItem> foodItemList, int selectedTableNumber) {
        this.foodItemList = foodItemList;
        this.selectedTableNumber = selectedTableNumber;
    }

    @NonNull
    @Override
    public FoodItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new FoodItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodItemViewHolder holder, int position) {
        FoodItem foodItem = foodItemList.get(position);
        holder.foodNameTextView.setText(foodItem.getFoodName());
        holder.foodPriceTextView.setText("Price: $" + foodItem.getFoodPrice());

        // Check if the image URL is available
        String imageUrl = foodItem.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .into(holder.foodImageView);  // Load the image into the ImageView
        } else {
            // Load default image if URL is null or empty
            holder.foodImageView.setImageResource(R.drawable.default_food_image);
        }

        holder.quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int quantity = s.toString().isEmpty() ? 0 : Integer.parseInt(s.toString());
                quantityMap.put(foodItem.getFoodName(), quantity);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public Map<String, Integer> getQuantityMap() {
        return quantityMap;
    }

    @Override
    public int getItemCount() {
        return foodItemList.size();
    }

    static class FoodItemViewHolder extends RecyclerView.ViewHolder {
        TextView foodNameTextView, foodPriceTextView;
        EditText quantityEditText;
        ImageView foodImageView;  // ImageView to display food image

        public FoodItemViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTextView = itemView.findViewById(R.id.foodNameTextView);
            foodPriceTextView = itemView.findViewById(R.id.foodPriceTextView);
            quantityEditText = itemView.findViewById(R.id.quantityEditText);
            foodImageView = itemView.findViewById(R.id.foodImageView);  // Initialize ImageView
        }
    }
}

