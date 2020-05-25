package com.example.timetomeet;

import android.content.res.Resources;

import com.example.timetomeet.retrofit.LocalizableDescription;
import com.example.timetomeet.retrofit.LocalizableName;

/**
 * This class contains various helper methods that can be
 * used in multiple parts of the application.
 */
public class Helper {
  // Preferences
  public static final String PREF_USERNAME = "username";
  public static final String PREF_PASSWORD = "password";

  // Bundle keys
  public static final String BUNDLE_CITIES = "bundle-cities-list";
  public static final String BUNDLE_TOKEN = "bundle-token";
  public static final String BUNDLE_TECHNOLOGIES = "bundle-technologies";
  public static final String BUNDLE_PAYMENT_ALTERNATIVES = "bundle-payment-alternatives";
  public static final String BUNDLE_AVAILABLE_ROOMS_LIST = "bundle-available-rooms-list";
  public static final String BUNDLE_FOOD_BEVARAGE_LIST = "bundle-food-bevarage-list";
  public static final String BUNDLE_FOOD_BEVARAGE_GROUP_LIST = "bundle-food-bevarage-group-list";
  public static final String BUNDLE_AVAILABLE_TECHNOLOGY_LIST = "bundle-available-technology-list";
  public static final String BUNDLE_SELECTED_ROOM = "bundle-selected-room";
  public static final String BUNDLE_STANDARD_SEATING = "bundle-standard-seating";

  // Other
  private static String[] supportedLocales = new String[]{"en", "sv"};

  /**
   * Determine if we should use Swedish or English names.
   * @return
   */
  public static String getLocale() {
    String currentLocale = "en";
    boolean localeIsSupported = false;

    // Get system locale
    try {
      currentLocale = Resources.getSystem()
          .getConfiguration()
          .getLocales()
          .getFirstMatch(supportedLocales)
          .getLanguage();
    } catch (NullPointerException ignored) { }

    // Check if system locale is supported
    for (String supportedLocale : supportedLocales) {
      if (supportedLocale.equals(currentLocale)) {
        localeIsSupported = true;
        break;
      }
    }

    return localeIsSupported ? currentLocale : "sv";
  }

  public static String getLocalizedDescription(LocalizableDescription localizableDescription) {
    return getLocalizedDescription(localizableDescription, getLocale());
  }

  public static String getLocalizedDescription(LocalizableDescription localizableDescription, String locale) {
    String descriptionSv = localizableDescription.getDescriptionSv();
    String descriptionEn = localizableDescription.getDescriptionEn();

    if (descriptionSv == null && descriptionEn == null) {
      return  "en".equals(locale) ?
          "No description." :
          "Beskrivning saknas.";

    } else if (descriptionSv == null) {
      return  descriptionEn;

    } else if (descriptionEn == null) {
      return  descriptionSv;

    } else {
      return  "en".equals(locale) ?
          descriptionEn :
          descriptionSv;
    }
  }

  public static String getLocalizedName(LocalizableName localizableName) {
    return getLocalizedName(localizableName, getLocale());
  }

  public static String getLocalizedName(LocalizableName localizableName, String locale) {
    if (localizableName == null) {
      return "en".equals(locale) ?
          "Missing object" :
          "Objekt saknas";
    }

    String nameSv = localizableName.getNameSv();
    String nameEn = localizableName.getNameEn();

    if (nameSv == null && nameEn == null) {
      return "en".equals(locale) ?
          "No name" :
          "Namnlös";

    } else if (nameSv == null) {
      return  nameEn;

    } else if (nameEn == null) {
      return  nameSv;

    } else {
      return "en".equals(locale) ?
          nameEn :
          nameSv;
    }
  }
}
