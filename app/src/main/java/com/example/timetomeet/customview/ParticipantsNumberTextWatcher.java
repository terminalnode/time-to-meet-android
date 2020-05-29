package com.example.timetomeet.customview;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Spinner;

import com.example.timetomeet.retrofit.entity.ConferenceRoomSeating;

public class ParticipantsNumberTextWatcher implements TextWatcher {
  private boolean ignoreNextChange;
  private Spinner seatingSpinner;

  public ParticipantsNumberTextWatcher(Spinner seatingSpinner) {
    this.seatingSpinner = seatingSpinner;
    ignoreNextChange = false;
  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {
  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    s = "1";
  }

  @Override
  public void afterTextChanged(Editable s) {
    String text = s.toString();

    if (ignoreNextChange) {
      ignoreNextChange = false;
      return;
    }

    // Get numeric value from text
    int value = 1;
    try {
      value = Integer.parseInt(text);
    } catch (NumberFormatException ignored) {}

    ConferenceRoomSeating crs = (ConferenceRoomSeating) seatingSpinner.getSelectedItem();
    int maxValue = crs.getNumberOfSeats();

    if (value < 1) {
      ignoreNextChange = true;
      s.clear();
      ignoreNextChange = true;
      s.append('1');

    } else if (value > maxValue) {
      ignoreNextChange = true;
      s.clear();
      ignoreNextChange = true;
      s.append(String.format("%s", maxValue));
    }
  }
}
