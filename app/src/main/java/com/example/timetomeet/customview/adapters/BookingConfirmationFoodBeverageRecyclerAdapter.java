package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.BookingConfirmationFoodBeverage;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.IWantedToMakeAStringButFailed;

import java.util.List;

public class BookingConfirmationFoodBeverageRecyclerAdapter
      extends RecyclerView.Adapter<BookingConfirmationFoodBeverageRecyclerAdapter.ViewHolder> {
  LayoutInflater inflater;
  List<BookingConfirmationFoodBeverage> food;

  public BookingConfirmationFoodBeverageRecyclerAdapter(
        Context context,
        List<BookingConfirmationFoodBeverage> food
    ) {
      this.inflater = LayoutInflater.from(context);
      this.food = food;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = inflater.inflate(R.layout.single_booking_confirmation_food_beverage, parent, false);
      return new BookingConfirmationFoodBeverageRecyclerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
      BookingConfirmationFoodBeverage thisFood = food.get(position);
      holder.foodBeverageTextView.setText(thisFood.getName());
    }

    @Override
    public int getItemCount() {
      return food.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
      TextView foodBeverageTextView;

      public ViewHolder(@NonNull View itemView) {
        super(itemView);
        foodBeverageTextView = itemView.findViewById(R.id.foodBeverageTextView);
      }
    }
  }
