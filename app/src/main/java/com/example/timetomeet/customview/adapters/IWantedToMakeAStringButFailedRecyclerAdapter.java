package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.bookingconfirmation.IWantedToMakeAStringButFailed;

import java.util.List;

public class IWantedToMakeAStringButFailedRecyclerAdapter
    extends RecyclerView.Adapter<IWantedToMakeAStringButFailedRecyclerAdapter.ViewHolder> {
  List<IWantedToMakeAStringButFailed> wannabeStrings;
  LayoutInflater inflater;

  public IWantedToMakeAStringButFailedRecyclerAdapter(
      Context context,
      List<IWantedToMakeAStringButFailed> wannabeStrings
  ) {
    this.wannabeStrings = wannabeStrings;
    this.inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.single_wannabe_string, parent, false);
    return new IWantedToMakeAStringButFailedRecyclerAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    IWantedToMakeAStringButFailed wannabeString = wannabeStrings.get(position);
    String actualString = wannabeString.toString();
    holder.wannabeStringTextView.setText(actualString);
  }

  @Override
  public int getItemCount() {
    return wannabeStrings.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView wannabeStringTextView;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      wannabeStringTextView = itemView.findViewById(R.id.wannabeStringTextView);
    }
  }
}
