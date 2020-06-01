package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.ConferenceRoomTechnology;
import com.example.timetomeet.retrofit.entity.Technology;

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

    holder.techCheckBox.setText(techName);
    if (technology.isIncluded()) {
      holder.techCheckBox.setAlpha(0.5F);
      holder.techCheckBox.setChecked(true);
      holder.techCheckBox.setClickable(false);
    } else {
      holder.techCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
        technology.setSelected(isChecked);
      });
    }
  }

  @Override
  public int getItemCount() {
    return technologies.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    CheckBox techCheckBox;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      techCheckBox = itemView.findViewById(R.id.techCheckBox);
    }
  }
}
