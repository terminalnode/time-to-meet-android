package com.example.timetomeet.customview.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.retrofit.entity.PaymentAlternative;

import java.util.List;
import java.util.Map;

public class PaymentAlternativeSpinnerAdapter extends ArrayAdapter<PaymentAlternative> {
  private int listLayout;
  private Context context;

  public PaymentAlternativeSpinnerAdapter(
      @NonNull Context context,
      @NonNull List<PaymentAlternative> objects)
  {
    super(context, R.layout.single_payment_alternative, objects);
    this.context = context;
    this.listLayout = R.layout.single_payment_alternative;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater inflater = LayoutInflater.from(getContext());
    convertView = inflater.inflate(listLayout, parent, false);

    PaymentAlternative currentPaymentAlternative = getItem(position);
    TextView paymentAlternativeTextView = convertView.findViewById(R.id.paymentAlternativeTextView);
    paymentAlternativeTextView.setText(Helper.getLocalizedName(currentPaymentAlternative, context));

    return convertView;
  }

  @Override
  public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    return getView(position, convertView, parent);
  }
}
