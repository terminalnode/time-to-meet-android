package com.example.timetomeet.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.timetomeet.Logging;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.DateDisplayListener;

public class FirstFragment extends Fragment {
  private ConstraintLayout startDateDisplay, endDateDisplay;

  @Override
  public View onCreateView(
      LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState
  ) {
    Log.i(Logging.CreateBookingActivity, "Inflating fragment 1");
    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_first, container, false);
  }

  public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    startDateDisplay = view.findViewById(R.id.startDateDisplay);
    endDateDisplay = view.findViewById(R.id.endDateDisplay);
    TextView startDateText = startDateDisplay.findViewById(R.id.dateTextView);
    TextView endDateText = endDateDisplay.findViewById(R.id.dateTextView);

    startDateText.setText(R.string.pick_start_date);
    endDateText.setText(R.string.pick_end_date);
    startDateDisplay.setOnClickListener(new DateDisplayListener(getContext(), startDateText));
    endDateDisplay.setOnClickListener(new DateDisplayListener(getContext(), endDateText));

    view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        NavHostFragment.findNavController(FirstFragment.this)
            .navigate(R.id.action_FirstFragment_to_SecondFragment);
      }
    });
  }
}
