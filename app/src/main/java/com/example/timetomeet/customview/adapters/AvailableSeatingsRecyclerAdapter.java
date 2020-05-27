package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.ConferenceRoom;
import com.example.timetomeet.retrofit.entity.ConferenceRoomSeating;
import com.example.timetomeet.retrofit.entity.Seating;

public class AvailableSeatingsRecyclerAdapter
    extends RecyclerView.Adapter<AvailableSeatingsRecyclerAdapter.ViewHolder> {
  private LayoutInflater inflater;
  private ConferenceRoom chosenConferenceRoom;
  private Context context;

  public AvailableSeatingsRecyclerAdapter(
      Context context,
      ConferenceRoom conferenceRoom) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.chosenConferenceRoom = conferenceRoom;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.single_available_seating, parent, false);
    return new AvailableSeatingsRecyclerAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    ConferenceRoomSeating numberOfSeats = chosenConferenceRoom.getDefaultSeating().get(position);
    Seating seatingName = chosenConferenceRoom.getSeats().get(position);
    holder.numberOfSeatsTextView.setText(String.format("%s", numberOfSeats.getNumberOfSeats()));
    holder.seatingNameTextView.setText(Helper.getLocalizedName(seatingName, context));
  }

  @Override
  public int getItemCount() {
    return chosenConferenceRoom.getDefaultSeating().size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView numberOfSeatsTextView;
    TextView seatingNameTextView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      numberOfSeatsTextView = itemView.findViewById(R.id.numberOfSeatsTextView);
      seatingNameTextView = itemView.findViewById(R.id.seatingNameTextView);
    }

    @Override
    public void onClick(View v) {

    }
  }
}
