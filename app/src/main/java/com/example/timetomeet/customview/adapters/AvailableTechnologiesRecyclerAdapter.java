package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.Technology;

import java.util.List;

public class AvailableTechnologiesRecyclerAdapter
    extends RecyclerView.Adapter<AvailableTechnologiesRecyclerAdapter.ViewHolder> {
  private LayoutInflater inflater;
  private List<Technology> availableTechnologies;

  public AvailableTechnologiesRecyclerAdapter(Context context, List<Technology> availableTechnologies) {
    this.inflater = LayoutInflater.from(context);
    this.availableTechnologies = availableTechnologies;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.single_available_technology, parent, false);
    return new AvailableTechnologiesRecyclerAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Technology technology = availableTechnologies.get(position);
    holder.availableTechnologyTextView.setText(Helper.getLocalizedName(technology));
  }

  @Override
  public int getItemCount() {
    return availableTechnologies.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView availableTechnologyTextView;

    ViewHolder(View itemView) {
      super(itemView);
      availableTechnologyTextView = itemView.findViewById(R.id.availableTechnologyTextView);
    }
  }
}
