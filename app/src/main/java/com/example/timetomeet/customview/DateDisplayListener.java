package com.example.timetomeet.customview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DateDisplayListener implements View.OnClickListener {
  private DatePickerDialog datePickerDialog;
  private DatePickerDialog limitedDatePicker;
  private TextView dateDisplayTextView;
  int year, month, day;

  public DateDisplayListener(Context context, TextView dateDisplayTextView) {
    datePickerDialog = new DatePickerDialog(context);
    datePickerDialog.setOnDateSetListener(this::onDateSet);
    this.dateDisplayTextView = dateDisplayTextView;
    this.limitedDatePicker = null;
    year = 0;
    month = 0;
    day = 0;

    datePickerDialog
        .getDatePicker()
        .setMinDate(System.currentTimeMillis());
  }

  //----- Methods -----//
  private void onDateSet(DatePicker datePicker, int year, int month, int day) {
    dateDisplayTextView.setText(String.format("%s-%02d-%02d", year, month + 1, day));
    this.year = year;
    this.month = month;
    this.day = day;

    if (limitedDatePicker != null) {
      Calendar c = Calendar.getInstance();
      c.set(year, month, day);

      limitedDatePicker
          .getDatePicker()
          .setMinDate(c.getTimeInMillis());
    }
  }

  public void setLimitedDatePicker(DatePickerDialog limitedDatePicker) {
    this.limitedDatePicker = limitedDatePicker;
  }

  @Override
  public void onClick(View view) {
    datePickerDialog.show();
  }

  //---- Getters -----//
  public int getYear() {
    return year;
  }

  public int getMonth() {
    return month;
  }

  public int getDay() {
    return day;
  }

  public DatePickerDialog getDatePickerDialog() {
    return datePickerDialog;
  }
}
