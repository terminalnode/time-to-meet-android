package com.example.timetomeet.customview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class DateDisplayListener implements View.OnClickListener {
  private DatePickerDialog datePickerDialog;
  private TextView dateDisplayTextView;

  public DateDisplayListener(Context context, TextView dateDisplayTextView) {
    datePickerDialog = new DatePickerDialog(context);
    datePickerDialog.setOnDateSetListener(this::onDateSet);
    this.dateDisplayTextView = dateDisplayTextView;

    datePickerDialog
        .getDatePicker()
        .setMinDate(System.currentTimeMillis());
  }

  //----- Methods -----//
  private void onDateSet(DatePicker datePicker, int year, int month, int day) {
    dateDisplayTextView.setText(String.format("%s-%02d-%02d", year, month + 1, day));
  }

  @Override
  public void onClick(View view) {
    datePickerDialog.show();
  }

  //---- Getters -----//
  public DatePickerDialog getDatePickerDialog() {
    return datePickerDialog;
  }
}
