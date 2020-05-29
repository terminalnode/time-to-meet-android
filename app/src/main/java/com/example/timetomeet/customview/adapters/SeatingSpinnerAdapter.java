package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.ConferenceRoomSeating;
import com.example.timetomeet.retrofit.entity.Seating;

import java.util.List;
import java.util.Map;

public class SeatingSpinnerAdapter extends ArrayAdapter<ConferenceRoomSeating> {
  private Context context;
  private Map<Long, Seating> seatingMap;
  private int layout;

  public SeatingSpinnerAdapter(
      @NonNull Context context,
      @NonNull List<ConferenceRoomSeating> objects,
      Map<Long, Seating> seatingMap
  ) {
    super(context, R.layout.single_available_seating, objects);
    this.context = context;
    this.seatingMap = seatingMap;
    this.layout = R.layout.single_available_seating;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(layout, parent, false);

    ConferenceRoomSeating currentCRSeating = getItem(position);
    Seating currentSeating = seatingMap.get(currentCRSeating.getSeatingId());

    TextView seatingNameTextView = convertView.findViewById(R.id.seatingNameTextView);
    seatingNameTextView.setText(Helper.getLocalizedName(currentSeating, context));
    TextView numberOfSeatsTextView = convertView.findViewById(R.id.numberOfSeatsTextView);
    numberOfSeatsTextView.setText(String.format("%s", currentCRSeating.getNumberOfSeats()));

    return convertView;
  }

  @Override
  public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    return getView(position, convertView, parent);
  }
}
