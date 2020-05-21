package com.example.timetomeet.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.AvailableRoom;

import java.util.ArrayList;
import java.util.List;

public class AvailableRoomListAdapter extends ArrayAdapter<AvailableRoom> {
  private int listLayout;

  public AvailableRoomListAdapter(@NonNull Context context, int resource, @NonNull List<AvailableRoom> objects) {
    super(context, resource, objects);
    this.listLayout = resource;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(listLayout, parent, false);

    AvailableRoom availableRoom = getItem(position);
    TextView cityNameTextView = convertView.findViewById(R.id.cityNameTextView);
    cityNameTextView.setText("" + availableRoom);

    return convertView;
  }
}
