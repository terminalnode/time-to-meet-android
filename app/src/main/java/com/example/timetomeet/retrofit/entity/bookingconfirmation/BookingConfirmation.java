package com.example.timetomeet.retrofit.entity.bookingconfirmation;

import android.util.Log;

import com.example.timetomeet.Logging;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookingConfirmation {
  @SerializedName("sender")
  private String sender;

  @SerializedName("booking_plant")
  private BookingConfirmationVenue bookingVenue;

  @SerializedName("booking_details")
  private BookingConfirmationDetails bookingDetails;

  @SerializedName("booked_conference_rooms_with_price")
  private List<BookedConferenceRoomWithPrice> bookedConferenceRoomWithPriceList;

  @SerializedName("number_of_participants")
  private String numberOfParticipants;

  @SerializedName("bookingFoodBeverage")
  private List<BookingConfirmationFoodBeverage> bookingFoodBeverage;

  @SerializedName("sum_total_excl_vat")
  private Double sumTotalExclVat;

  @SerializedName("booked_tech")
  private List<IWantedToMakeAStringButFailed> bookedTechList;

  @SerializedName("postmarkBool")
  private BookingConfirmationPostmarkBool postmarkBool;

  @SerializedName("customer_number")
  private String customerNumber;

  @SerializedName("personFirstName")
  private String myFirstName;

  @SerializedName("personLastName")
  private String myLastName;

  @SerializedName("personPhoneNumber")
  private String myPhoneNumber;

  @SerializedName("booked_by_person")
  private BookingConfirmationBookedByPerson bookedByPerson;

  @SerializedName("booker_invoice_address_or_empty_string")
  private String bookerInvoiceAddress;

  @SerializedName("specialRequest")
  private String specialRequest;

  // was null, no idea what this is
  //@SerializedName("agreement_provider")
  //private Object agreementProvider;

  @SerializedName("booking_accept_decline")
  private BookingConfirmationAcceptDecline acceptDecline;

  //----- Constructors -----//
  public BookingConfirmation() {
  }

  //----- Methods -----//
  public void prettyPrint() {
    Log.i(Logging.BookingConfirmation, "sender=" + sender);
    Log.i(Logging.BookingConfirmation, "bookingVenue=" + bookingVenue);
    Log.i(Logging.BookingConfirmation, "bookingDetails=" + bookingDetails);
    Log.i(Logging.BookingConfirmation, "bookedConferenceRoomWithPriceList=" + bookedConferenceRoomWithPriceList);
    Log.i(Logging.BookingConfirmation, "numberOfParticipants=" + numberOfParticipants);
    Log.i(Logging.BookingConfirmation, "sumTotalExclVat=" + sumTotalExclVat);
    Log.i(Logging.BookingConfirmation, "postmarkBool=" + postmarkBool);
    Log.i(Logging.BookingConfirmation, "customerNumber=" + customerNumber);
    Log.i(Logging.BookingConfirmation, "myFirstName=" + myFirstName);
    Log.i(Logging.BookingConfirmation, "myLastName=" + myLastName);
    Log.i(Logging.BookingConfirmation, "myPhoneNumber=" + myPhoneNumber);
    Log.i(Logging.BookingConfirmation, "bookedByPerson=" + bookedByPerson);
    Log.i(Logging.BookingConfirmation, "bookerInvoiceAddress=" + bookerInvoiceAddress);
    Log.i(Logging.BookingConfirmation, "specialRequest=" + specialRequest);
    Log.i(Logging.BookingConfirmation, "acceptDecline=" + acceptDecline);
    Log.i(Logging.BookingConfirmation, "bookingFoodBeverage=" + bookingFoodBeverage);
    Log.i(Logging.BookingConfirmation, "bookedTechList=" + bookedTechList);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("BookingConfirmation{");
    sb.append("sender='").append(sender).append('\'');
    sb.append(", bookingVenue=").append(bookingVenue);
    sb.append(", bookingDetails=").append(bookingDetails);
    sb.append(", bookedConferenceRoomWithPriceList=").append(bookedConferenceRoomWithPriceList);
    sb.append(", numberOfParticipants='").append(numberOfParticipants).append('\'');
    sb.append(", bookingFoodBeverage=").append(bookingFoodBeverage);
    sb.append(", sumTotalExclVat=").append(sumTotalExclVat);
    sb.append(", bookedTechList=").append(bookedTechList);
    sb.append(", postmarkBool=").append(postmarkBool);
    sb.append(", customerNumber='").append(customerNumber).append('\'');
    sb.append(", myFirstName='").append(myFirstName).append('\'');
    sb.append(", myLastName='").append(myLastName).append('\'');
    sb.append(", myPhoneNumber='").append(myPhoneNumber).append('\'');
    sb.append(", bookedByPerson=").append(bookedByPerson);
    sb.append(", bookerInvoiceAddress='").append(bookerInvoiceAddress).append('\'');
    sb.append(", specialRequest='").append(specialRequest).append('\'');
    sb.append(", acceptDecline=").append(acceptDecline);
    sb.append('}');
    return sb.toString();
  }

  //----- Setters -----//
  public void setSender(String sender) {
    this.sender = sender;
  }

  public void setBookingVenue(BookingConfirmationVenue bookingVenue) {
    this.bookingVenue = bookingVenue;
  }

  public void setBookingDetails(BookingConfirmationDetails bookingDetails) {
    this.bookingDetails = bookingDetails;
  }

  public void setBookedConferenceRoomWithPriceList(List<BookedConferenceRoomWithPrice> bookedConferenceRoomWithPriceList) {
    this.bookedConferenceRoomWithPriceList = bookedConferenceRoomWithPriceList;
  }

  public void setNumberOfParticipants(String numberOfParticipants) {
    this.numberOfParticipants = numberOfParticipants;
  }

  public void setSumTotalExclVat(Double sumTotalExclVat) {
    this.sumTotalExclVat = sumTotalExclVat;
  }

  public void setPostmarkBool(BookingConfirmationPostmarkBool postmarkBool) {
    this.postmarkBool = postmarkBool;
  }

  public void setCustomerNumber(String customerNumber) {
    this.customerNumber = customerNumber;
  }

  public void setMyFirstName(String myFirstName) {
    this.myFirstName = myFirstName;
  }

  public void setMyLastName(String myLastName) {
    this.myLastName = myLastName;
  }

  public void setMyPhoneNumber(String myPhoneNumber) {
    this.myPhoneNumber = myPhoneNumber;
  }

  public void setBookedByPerson(BookingConfirmationBookedByPerson bookedByPerson) {
    this.bookedByPerson = bookedByPerson;
  }

  public void setBookerInvoiceAddress(String bookerInvoiceAddress) {
    this.bookerInvoiceAddress = bookerInvoiceAddress;
  }

  public void setSpecialRequest(String specialRequest) {
    this.specialRequest = specialRequest;
  }

  public void setAcceptDecline(BookingConfirmationAcceptDecline acceptDecline) {
    this.acceptDecline = acceptDecline;
  }

  public void setBookedFoodBeverage(List<BookingConfirmationFoodBeverage> bookedFoodBeverage) {
    this.bookingFoodBeverage = bookedFoodBeverage;
  }

  public void setBookedTechList(List<IWantedToMakeAStringButFailed> bookedTechList) {
    this.bookedTechList = bookedTechList;
  }

  //----- Getters -----//
  public String getSender() {
    return sender;
  }

  public BookingConfirmationVenue getBookingVenue() {
    return bookingVenue;
  }

  public BookingConfirmationDetails getBookingDetails() {
    return bookingDetails;
  }

  public List<BookedConferenceRoomWithPrice> getBookedConferenceRoomWithPriceList() {
    return bookedConferenceRoomWithPriceList;
  }

  public String getNumberOfParticipants() {
    return numberOfParticipants;
  }

  public Double getSumTotalExclVat() {
    return sumTotalExclVat;
  }

  public BookingConfirmationPostmarkBool getPostmarkBool() {
    return postmarkBool;
  }

  public String getCustomerNumber() {
    return customerNumber;
  }

  public String getMyFirstName() {
    return myFirstName;
  }

  public String getMyLastName() {
    return myLastName;
  }

  public String getMyPhoneNumber() {
    return myPhoneNumber;
  }

  public BookingConfirmationBookedByPerson getBookedByPerson() {
    return bookedByPerson;
  }

  public String getBookerInvoiceAddress() {
    return bookerInvoiceAddress;
  }

  public String getSpecialRequest() {
    return specialRequest;
  }

  public BookingConfirmationAcceptDecline getAcceptDecline() {
    return acceptDecline;
  }

  public List<BookingConfirmationFoodBeverage> getBookedFoodBeverage() {
    return bookingFoodBeverage;
  }

  public List<IWantedToMakeAStringButFailed> getBookedTechList() {
    return bookedTechList;
  }
}
