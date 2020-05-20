package com.example.timetomeet;

import android.content.res.Resources;

/**
 * This class contains various helper methods that can be
 * used in multiple parts of the application.
 */
public class Helper {
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
}
