package com.example.timetomeet.customview;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.timetomeet.R;

public class SelectAvailableRoomListener implements View.OnClickListener {
  private Fragment parentFragment;

  public SelectAvailableRoomListener(Context context, Fragment parentFragment) {
    this.parentFragment = parentFragment;
  }

  @Override
  public void onClick(View view) {
    NavHostFragment
        .findNavController(parentFragment)
        .navigate(R.id.action_SelectRoomFragment_to_AvailableRoomFragment);
  }
}
