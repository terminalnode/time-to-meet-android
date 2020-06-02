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
import com.example.timetomeet.activity.createbooking.BookingCoordinator;
import com.example.timetomeet.retrofit.entity.availableroom.AvailableRoom;

import java.util.List;

public class AvailableRoomsRecyclerAdapter extends RecyclerView.Adapter<AvailableRoomsRecyclerAdapter.ViewHolder> {
  private LayoutInflater inflater;
  private List<AvailableRoom> availableRooms;
  private Fragment parentFragment;
  private Context context;
  private BookingCoordinator bookingCoordinator;

  public AvailableRoomsRecyclerAdapter(
      Context context,
      List<AvailableRoom> availableRooms,
      Fragment parentFragment,
      BookingCoordinator bookingCoordinator
  ) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.availableRooms = availableRooms;
    this.parentFragment = parentFragment;
    this.bookingCoordinator = bookingCoordinator;
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
    String cityName = Helper.getLocalizedName(availableRoom.getAssociatedCity(), locale, context);
    String venueName = Helper.getLocalizedName(availableRoom.getAssociatedVenue(), locale, context);

    holder.setAvailableRoom(availableRoom);
    holder.venueNameTextView.setText(venueName);
    holder.cityNameTextView.setText(cityName);
    holder.amPriceTextView.setText(String.format("%.02f kr", availableRoom.getPreNoonPrice()));
    holder.pmPriceTextView.setText(String.format("%.02f kr", availableRoom.getAfterNoonPrice()));
    holder.fullDayPriceTextView.setText(String.format("%.02f kr", availableRoom.getFullDayPrice()));

    if (availableRoom.getId31() == null) {
      holder.amPriceLabel.setAlpha(0.5F);
      holder.amPriceTextView.setAlpha(0.5F);

    } else if (availableRoom.getId32() == null) {
      holder.pmPriceLabel.setAlpha(0.5F);
      holder.pmPriceTextView.setAlpha(0.5F);
    }

    if (availableRoom.getId31() == null || availableRoom.getId32() == null) {
      holder.fullDayPriceLabel.setAlpha(0.5F);
      holder.fullDayPriceTextView.setAlpha(0.5F);
    }
  }

  @Override
  public int getItemCount() {
    return availableRooms.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView venueNameTextView;
    TextView cityNameTextView;
    TextView amPriceLabel;
    TextView amPriceTextView;
    TextView pmPriceLabel;
    TextView pmPriceTextView;
    TextView fullDayPriceLabel;
    TextView fullDayPriceTextView;
    AvailableRoom availableRoom;

    ViewHolder(View itemView) {
      super(itemView);
      venueNameTextView = itemView.findViewById(R.id.venueName);
      cityNameTextView = itemView.findViewById(R.id.cityName);
      amPriceLabel = itemView.findViewById(R.id.amPriceLabel);
      amPriceTextView = itemView.findViewById(R.id.amPrice);
      pmPriceLabel = itemView.findViewById(R.id.pmPriceLabel);
      pmPriceTextView = itemView.findViewById(R.id.pmPrice);
      fullDayPriceLabel = itemView.findViewById(R.id.fullDayPriceLabel);
      fullDayPriceTextView = itemView.findViewById(R.id.fullDayPrice);
      itemView.setOnClickListener(this::onClick);
    }

    void setAvailableRoom(AvailableRoom availableRoom) {
      this.availableRoom = availableRoom;
    }

    public void onClick(View view) {
      bookingCoordinator.setSelectedRoom(availableRoom);
      NavHostFragment
          .findNavController(parentFragment)
          .navigate(R.id.action_SearchResultFragment_to_RoomDetailsFragment);
    }
  }
}
