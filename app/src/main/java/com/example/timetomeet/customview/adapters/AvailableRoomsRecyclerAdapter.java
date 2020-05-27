package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.AvailableRoom;

import java.util.List;

public class AvailableRoomsRecyclerAdapter extends RecyclerView.Adapter<AvailableRoomsRecyclerAdapter.ViewHolder> {
  private LayoutInflater inflater;
  private List<AvailableRoom> availableRooms;
  private Fragment parentFragment;
  private Bundle bookingBundle;

  public AvailableRoomsRecyclerAdapter(
      Context context,
      List<AvailableRoom> availableRooms,
      Fragment parentFragment,
      Bundle bookingBundle) {
    this.inflater = LayoutInflater.from(context);
    this.availableRooms = availableRooms;
    this.parentFragment = parentFragment;
    this.bookingBundle = bookingBundle;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.single_available_room, parent, false);
    return new AvailableRoomsRecyclerAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    AvailableRoom availableRoom = availableRooms.get(position);
    String locale = Helper.getLocale();
    String cityName = Helper.getLocalizedName(availableRoom.getAssociatedCity(), locale);
    String venueName = Helper.getLocalizedName(availableRoom.getAssociatedVenue(), locale);

    holder.setAvailableRoom(availableRoom);
    holder.venueNameTextView.setText(venueName);
    holder.cityNameTextView.setText(cityName);
    holder.amPriceTextView.setText(String.format("%.02f kr", availableRoom.getPreNoonPrice()));
    holder.pmPriceTextView.setText(String.format("%.02f kr", availableRoom.getAfterNoonPrice()));
    holder.fullDayPriceView.setText(String.format("%.02f kr", availableRoom.getFullDayPrice()));
  }

  @Override
  public int getItemCount() {
    return availableRooms.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView venueNameTextView;
    TextView cityNameTextView;
    TextView amPriceTextView;
    TextView pmPriceTextView;
    TextView fullDayPriceView;
    AvailableRoom availableRoom;

    ViewHolder(View itemView) {
      super(itemView);
      venueNameTextView = itemView.findViewById(R.id.venueName);
      cityNameTextView = itemView.findViewById(R.id.cityName);
      amPriceTextView = itemView.findViewById(R.id.amPrice);
      pmPriceTextView = itemView.findViewById(R.id.pmPrice);
      fullDayPriceView = itemView.findViewById(R.id.fullDayPrice);

      itemView.setOnClickListener(this);
    }

    void setAvailableRoom(AvailableRoom availableRoom) {
      this.availableRoom = availableRoom;
    }

    @Override
    public void onClick(View view) {
      bookingBundle.putParcelable(Helper.BUNDLE_SELECTED_ROOM, availableRoom);
      NavHostFragment
          .findNavController(parentFragment)
          .navigate(R.id.action_SelectRoomFragment_to_AvailableRoomFragment);
    }
  }
}