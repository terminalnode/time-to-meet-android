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
import com.example.timetomeet.retrofit.entity.ConferenceRoomTechnology;
import com.example.timetomeet.retrofit.entity.Technology;
import com.google.android.material.chip.Chip;

import java.util.List;
import java.util.Map;

public class SelectTechnologyRecyclerAdapter
    extends RecyclerView.Adapter<SelectTechnologyRecyclerAdapter.ViewHolder> {
  private Context context;
  private LayoutInflater inflater;
  private List<ConferenceRoomTechnology> technologies;
  private Map<Long, Technology> technologyMap;

  public SelectTechnologyRecyclerAdapter(
      Context context,
      List<ConferenceRoomTechnology> technologies,
      Map<Long, Technology> technologyMap
  ) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.technologies = technologies;
    this.technologyMap = technologyMap;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.single_select_technology, parent, false);
    return new SelectTechnologyRecyclerAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ConferenceRoomTechnology technology = technologies.get(position);
    Technology associatedTechnology = technologyMap.get(technology.getTechnology());
    String techName = Helper.getLocalizedName(associatedTechnology, context);

    Log.i("YOLO", "ConfRoomTech: " + technology);
    Log.i("YOLO", "AssocTech: " + associatedTechnology);
    Log.i("YOLO", "The name of that tech??? " + techName);
    Log.i("YOLO", "Is it checkable? " + holder.selectTechChip.isCheckable());

    holder.technologyNameTextView.setText(techName);
    if (technology.isIncluded()) {
      Log.i("YOLO", String.format("%s is included, should be selected.", techName));
      holder.selectTechChip.setCheckable(false);
      holder.selectTechChip.setChecked(true);
      holder.selectTechChip.setAlpha(0.5F);
      holder.technologyNameTextView.setAlpha(0.5F);
    } else {
      holder.selectTechChip.setOnClickListener(view -> {
        Log.i("YOLO", String.format("%s is not included, should be selectABLE.", techName));
        boolean isChecked = holder.selectTechChip.isChecked();
        technology.setSelected(isChecked);
        Log.i("YOLO", String.format("Set selected value for %s to %s", techName, isChecked));
      });
    }
  }

  @Override
  public int getItemCount() {
    return technologies.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    Chip selectTechChip;
    TextView technologyNameTextView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      selectTechChip = itemView.findViewById(R.id.selectTechChip);
      technologyNameTextView = itemView.findViewById(R.id.technologyNameTextView);
    }
  }
}
