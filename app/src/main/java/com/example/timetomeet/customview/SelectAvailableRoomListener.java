package com.example.timetomeet.customview;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.AvailableRoom;

public class SelectAvailableRoomListener implements View.OnClickListener {
  private Fragment parentFragment;
  private Bundle bookingBundle;
  private AvailableRoom availableRoom;

  public SelectAvailableRoomListener(
      Context context,
      Fragment parentFragment,
      Bundle bookingBundle,
      AvailableRoom availableRoom) {
    this.parentFragment = parentFragment;
    this.bookingBundle = bookingBundle;
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
