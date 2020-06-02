package com.example.timetomeet.customview.adapters;

import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timetomeet.Helper;
import com.example.timetomeet.R;
import com.example.timetomeet.customview.ParticipantsNumberTextWatcher;
import com.example.timetomeet.retrofit.entity.FoodBeverage;
import com.example.timetomeet.retrofit.entity.VenueFoodBeverage;
import com.example.timetomeet.retrofit.entity.conferenceroom.ConferenceRoomSeating;

import java.util.List;
import java.util.Map;

public class SelectFoodRecyclerAdapter
    extends RecyclerView.Adapter<SelectFoodRecyclerAdapter.ViewHolder> {
  private Context context;
  private LayoutInflater inflater;
  private List<VenueFoodBeverage> foodAlternatives;
  private Map<Long, FoodBeverage> foodMap;
  private ConferenceRoomSeating conferenceRoomSeating;

  public SelectFoodRecyclerAdapter(
      Context context,
      List<VenueFoodBeverage> foodAlternatives,
      Map<Long, FoodBeverage> foodMap,
      ConferenceRoomSeating conferenceRoomSeating) {
    this.context = context;
    this.inflater = LayoutInflater.from(context);
    this.foodAlternatives = foodAlternatives;
    this.foodMap = foodMap;
    this.conferenceRoomSeating = conferenceRoomSeating;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = inflater.inflate(R.layout.single_select_food, parent, false);
    return new SelectFoodRecyclerAdapter.ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    VenueFoodBeverage venueFoodBeverage = foodAlternatives.get(position);

    // Fetch the foodBeverage and associate it with the holder object.
    FoodBeverage foodBeverage = foodMap.get(venueFoodBeverage.getFoodBeverage());
    holder.setVenueFoodBeverage(venueFoodBeverage);

    // Find and capitalize the food/beverage name
    String foodBeverageName = Helper.getLocalizedName(foodBeverage, context);
    foodBeverageName = Character.toUpperCase(foodBeverageName.charAt(0)) + foodBeverageName.substring(1);

    holder.selectFoodCheckBox.setText(foodBeverageName);
    holder.selectFoodCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
      venueFoodBeverage.setSelected(isChecked);
      if (isChecked) {
        holder.showExtraViews();
      } else {
        holder.hideExtraViews();
      }
    });

    holder.timeSelectTextView.setOnClickListener(view -> {
      holder.timePickerDialog.show();
    });
  }

  @Override
  public int getItemCount() {
    return foodAlternatives.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    CheckBox selectFoodCheckBox;
    TextView timeSelectTextView;
    EditText commentEditText;
    TextView numberOfPeopleHeader;
    EditText numberOfPeopleEditText;
    ImageView bellImageView;
    TimePickerDialog timePickerDialog;
    VenueFoodBeverage venueFoodBeverage;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      selectFoodCheckBox = itemView.findViewById(R.id.selectFoodCheckBox);
      timeSelectTextView = itemView.findViewById(R.id.timeSelectTextView);
      commentEditText = itemView.findViewById(R.id.commentEditText);
      numberOfPeopleHeader = itemView.findViewById(R.id.numberOfPeopleHeader);
      numberOfPeopleEditText = itemView.findViewById(R.id.numberOfPeopleEditText);
      bellImageView = itemView.findViewById(R.id.bellImageView);

      // Set text watcher to make sure we can't enter
      // more participants than the available seatings.
      numberOfPeopleEditText.addTextChangedListener(
          new ParticipantsNumberTextWatcher(conferenceRoomSeating));

      // Create the TimePickerDialog
      timePickerDialog = new TimePickerDialog(
          context, this::onTimeSet, 12, 10, true);
    }

    public String getComment() {
      return commentEditText.getText().toString();
    }

    public int getNumberOfParticipants() {
      int numberOfParticipants = 0;
      String rawNumberOfParticipants = numberOfPeopleEditText.getText().toString();

      try {
        numberOfParticipants = Integer.parseInt(rawNumberOfParticipants);
      } catch (NumberFormatException ignored) { }

      return numberOfParticipants;
    }

    /**
     * Hides the extra stuff such as setting time/number of participants etc.
     */
    public void hideExtraViews() {
      timeSelectTextView.setVisibility(View.GONE);
      commentEditText.setVisibility(View.GONE);
      numberOfPeopleHeader.setVisibility(View.GONE);
      numberOfPeopleEditText.setVisibility(View.GONE);
      bellImageView.setVisibility(View.GONE);
    }

    /**
     * Shows the extra stuff such as setting time/number of participants etc.
     */
    public void showExtraViews() {
      timeSelectTextView.setVisibility(View.VISIBLE);
      commentEditText.setVisibility(View.VISIBLE);
      numberOfPeopleHeader.setVisibility(View.VISIBLE);
      numberOfPeopleEditText.setVisibility(View.VISIBLE);
      bellImageView.setVisibility(View.VISIBLE);
    }

    public void setVenueFoodBeverage(VenueFoodBeverage venueFoodBeverage) {
      this.venueFoodBeverage = venueFoodBeverage;

      // Add myself to the venueFoodBeverage object.
      // Hold my beverage, I'm going in.
      venueFoodBeverage.setViewHolder(this);
    }

    private void onTimeSet(TimePicker timePicker, int hour, int minute) {
      timeSelectTextView.setText(String.format("Time to serve: %02d:%02d", hour, minute));
      venueFoodBeverage.setSelectedTime(String.format("%02d:%02d:00", hour, minute));
    }
  }
}
