package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.FoodBeverage;
import com.example.timetomeet.retrofit.entity.VenueFoodBeverage;

import java.util.List;
import java.util.Map;

public class SelectFoodRecyclerAdapter
    extends RecyclerView.Adapter<SelectFoodRecyclerAdapter.ViewHolder> {
  private Context context;
  private LayoutInflater inflater;
  private List<VenueFoodBeverage> foodAlternatives;
  private Map<Long, FoodBeverage> foodMap;

  public SelectFoodRecyclerAdapter(
      Context context,
      List<VenueFoodBeverage> foodAlternatives,
      Map<Long, FoodBeverage> foodMap
  ) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.foodAlternatives = foodAlternatives;
    this.foodMap = foodMap;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.single_select_food, parent, false);
    return new SelectFoodRecyclerAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    VenueFoodBeverage venueFoodBeverage = foodAlternatives.get(position);
    FoodBeverage foodBeverage = foodMap.get(venueFoodBeverage.getFoodBeverage());
    String foodBeverageName = Helper.getLocalizedName(foodBeverage, context);

    holder.selectFoodCheckBox.setText(foodBeverageName);
    holder.selectFoodCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
      venueFoodBeverage.setSelected(isChecked);
    });
  }

  @Override
  public int getItemCount() {
    return foodAlternatives.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    CheckBox selectFoodCheckBox;
    TextView timeSelectTextView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      selectFoodCheckBox = itemView.findViewById(R.id.selectFoodCheckBox);
      timeSelectTextView = itemView.findViewById(R.id.timeSelectTextView);
    }
  }
}
