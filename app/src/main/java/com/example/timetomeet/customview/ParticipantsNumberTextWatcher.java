package com.example.timetomeet.customview;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Spinner;

import com.example.timetomeet.retrofit.entity.conferenceroom.ConferenceRoomSeating;

public class ParticipantsNumberTextWatcher implements TextWatcher {
  private boolean ignoreNextChange;
  private Spinner seatingSpinner;
  private ConferenceRoomSeating conferenceRoomSeating;

  /**
   * Constructor for adjusting behaviour to seatingSpinner.
   * The selected object will be retrieved from the spinner every time a change is made,
   * making sure the number never exceeds the maximum number of allowed seats.
   * @param seatingSpinner The spinner containing ConferenceRoomSeating objects.
   */
  public ParticipantsNumberTextWatcher(Spinner seatingSpinner) {
    this(seatingSpinner, null);
  }

  /**
   * Constructor for adjusting behaviour to a constant ConferenceRoomSeating object.
   * Works same as the constructor above, except the maximum number of allowed seats
   * does not change.
   * @param conferenceRoomSeating The ConferenceRoomSeating object.
   */
  public ParticipantsNumberTextWatcher(ConferenceRoomSeating conferenceRoomSeating) {
    this(null, conferenceRoomSeating);
  }

  private ParticipantsNumberTextWatcher(Spinner s, ConferenceRoomSeating crs) {
    this.seatingSpinner = s;
    this.conferenceRoomSeating = crs;
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
    } else if (text.isEmpty()) {
      return;
    }

    // Get numeric value from text
    int value = 1;
    try {
      value = Integer.parseInt(text);
    } catch (NumberFormatException ignored) {
      ignoreNextChange = true;
      s.clear();
      ignoreNextChange = true;
      s.append('1');
    }

    // Find out the maximum allowed value
    if (seatingSpinner != null) {
      conferenceRoomSeating = (ConferenceRoomSeating) seatingSpinner.getSelectedItem();
    }
    int maxValue = conferenceRoomSeating.getNumberOfSeats();

    // Ensure that the text has a value between 1 and maxValue
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

    // Should already be false, just making sure
    ignoreNextChange = false;
  }
}
