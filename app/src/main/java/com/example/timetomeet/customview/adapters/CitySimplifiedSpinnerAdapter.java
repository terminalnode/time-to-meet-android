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
import com.example.timetomeet.retrofit.entity.CitySimplified;

import java.util.List;

public class CitySimplifiedSpinnerAdapter extends ArrayAdapter<CitySimplified> {
  private int listLayout;
  private Context context;

  public CitySimplifiedSpinnerAdapter(
      @NonNull Context context,
      int listView,
      int textView,
      @NonNull List<CitySimplified> objects
  ) {
    super(context, listView, textView, objects);
    this.context = context;
    this.listLayout = listView;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(listLayout, parent, false);

    CitySimplified currentCity = getItem(position);
    TextView cityNameTextView = convertView.findViewById(R.id.cityNameTextView);
    cityNameTextView.setText(Helper.getLocalizedName(currentCity,context));

    return convertView;
  }

  @Override
  public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    return getView(position, convertView, parent);
  }
}
