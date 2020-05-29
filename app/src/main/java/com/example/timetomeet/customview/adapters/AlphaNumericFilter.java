package com.example.timetomeet.customview.adapters;

import android.text.InputFilter;
import android.text.Spanned;

public class AlphaNumericFilter implements InputFilter {
  @Override
  public CharSequence filter(
      CharSequence source, int start,
      int end, Spanned dest,
      int dstart, int dend)
  {
    // Sort out the non-alphanumeric characters
    StringBuilder sb = new StringBuilder();
    for (int i = start; i < end; i++) {
      char c = source.charAt(i);
      if (Character.isLetterOrDigit(c)) {
        sb.append(c);
      }
    }

    // If all characters are valid, return null, otherwise only return the filtered characters
    boolean allCharactersValid = (sb.length() == end - start);
    return allCharactersValid ? null : sb.toString();
  }
}
