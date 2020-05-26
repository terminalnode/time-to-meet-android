package com.example.timetomeet.customview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.AvailableRoom;
import com.example.timetomeet.retrofit.entity.CitySimplified;
import com.example.timetomeet.retrofit.entity.Venue;

import java.util.List;
import java.util.Map;

public class AvailableRoomListAdapter extends ArrayAdapter<AvailableRoom> {
  Map<Long, CitySimplified> cityMap;
  Fragment parentFragment;

  public AvailableRoomListAdapter(
      @NonNull Context context,
      @NonNull List<AvailableRoom> objects,
      @NonNull Map<Long, CitySimplified> cityMap,
      Fragment parentFragment
  ) {
    super(context, R.layout.single_available_room, objects);
    this.cityMap = cityMap;
    this.parentFragment = parentFragment;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(R.layout.single_available_room, parent, false);

    SelectAvailableRoomListener listener = new SelectAvailableRoomListener(getContext(), parentFragment);
    convertView.setOnClickListener(listener);

    AvailableRoom availableRoom = getItem(position);
    TextView venueNameTextView = convertView.findViewById(R.id.venueName);
    TextView cityNameTextView = convertView.findViewById(R.id.cityName);
    TextView amPriceTextView = convertView.findViewById(R.id.amPrice);
    TextView pmPriceTextView = convertView.findViewById(R.id.pmPrice);
    TextView fullDayPriceView = convertView.findViewById(R.id.fullDayPrice);

    if (availableRoom != null) {
      CitySimplified city = cityMap.get(availableRoom.getCityId());
      Venue venue = availableRoom.getAssociatedVenue();

      String locale = Helper.getLocale();
      String cityName = Helper.getLocalizedName(city, locale);
      String venueName = Helper.getLocalizedName(venue, locale);

      venueNameTextView.setText(venueName);
      cityNameTextView.setText(cityName);
      amPriceTextView.setText(String.format("%.02f kr", availableRoom.getPreNoonPrice()));
      pmPriceTextView.setText(String.format("%.02f kr", availableRoom.getAfterNoonPrice()));
      fullDayPriceView.setText(String.format("%.02f kr", availableRoom.getFullDayPrice()));
    }

    return convertView;
  }
}
