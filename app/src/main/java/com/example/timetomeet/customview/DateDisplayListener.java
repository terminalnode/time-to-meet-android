package com.example.timetomeet.customview;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

public class DateDisplayListener implements View.OnClickListener {

  private DatePickerDialog datePickerDialog;
  private TextView textView;

  public DateDisplayListener(Context context, TextView textView) {
    datePickerDialog = new DatePickerDialog(context);
    datePickerDialog.setOnDateSetListener(this::onDateSet);
    this.textView = textView;
  }

  private void onDateSet(DatePicker datePicker, int year, int month, int day) {
    textView.setText(String.format("%s-%02d-%02d", year, month + 1, day));
  }

  @Override
  public void onClick(View view) {
    datePickerDialog.show();
    Log.i("dateTest", "qwerty");
  }
}
