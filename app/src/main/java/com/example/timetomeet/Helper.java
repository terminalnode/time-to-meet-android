package com.example.timetomeet;

import android.content.Context;
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
   * @return The locale being used in String format, e.g. "en" or "sv".
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

  /**
   * Get the description of an object implementing the LocalizableDescription interface.
   * The method looks up the locale automatically.
   * @param localizableDescription The object implementing LocalizableDescription.
   * @param context The context in which the description is being fetched.
   * @return A String containing the localized description.
   */
  public static String getLocalizedDescription(
      LocalizableDescription localizableDescription,
      Context context
  ) {
    return getLocalizedDescription(localizableDescription, getLocale(), context);
  }

  /**
   * Get the description of an object implementing the LocalizableDescription interface.
   * @param localizableDescription The object implementing LocalizableDescription.
   * @param locale The locale we're fetching the description for.
   * @param context The context in which the description is being fetched.
   * @return A String containing the localized description.
   */
  public static String getLocalizedDescription(
      LocalizableDescription localizableDescription,
      String locale,
      Context context
  ) {
    if (localizableDescription == null) {
      return context.getResources().getString(R.string.object_missing_place_holder);
    }

    String descriptionSv = localizableDescription.getDescriptionSv();
    String descriptionEn = localizableDescription.getDescriptionEn();
    boolean descriptionSvEmpty = descriptionSv == null || descriptionSv.trim().isEmpty();
    boolean descriptionEnEmpty = descriptionEn == null || descriptionEn.trim().isEmpty();

    if (descriptionSvEmpty && descriptionEnEmpty) {
      return Resources.getSystem().getString(R.string.empty_description_place_holder);
    } else if (descriptionSvEmpty) {
      return descriptionEn;
    } else if (descriptionEn == null) {
      return descriptionSv;

    }

    return "en".equals(locale) ? descriptionEn : descriptionSv;
  }

  /**
   * Get the name of an object implementing the LocalizableName interface.
   * The method looks up the locale automatically.
   * @param localizableName The object implementing LocalizableName.
   * @param context The context in which the name is being fetched.
   * @return A String containing the localized name.
   */
  public static String getLocalizedName(
      LocalizableName localizableName,
      Context context
  ) {
    return getLocalizedName(localizableName, getLocale(), context);
  }

  /**
   * Get the name of an object implementing the LocalizableName interface.
   * @param localizableName The object implementing LocalizableName.
   * @param locale The locale we're fetching the name for.
   * @param context The context in which the name is being fetched.
   * @return A String containing the localized name.
   */
  public static String getLocalizedName(LocalizableName localizableName, String locale, Context context) {
    if (localizableName == null) {
      return context.getResources().getString(R.string.object_missing_place_holder);
    }

    String nameSv = localizableName.getNameSv();
    String nameEn = localizableName.getNameEn();
    boolean nameSvEmpty = nameSv == null || nameSv.trim().isEmpty();
    boolean nameEnEmpty = nameEn == null || nameEn.trim().isEmpty();

    if (nameSvEmpty && nameEnEmpty) {
      return context.getResources().getString(R.string.missing_name_place_holder);
    } else if (nameSvEmpty) {
      return nameEn;
    } else if (nameEnEmpty) {
      return nameSv;
    }

    return "en".equals(locale) ? nameEn : nameSv;
  }
}
