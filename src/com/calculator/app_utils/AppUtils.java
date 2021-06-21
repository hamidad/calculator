package com.calculator.app_utils;

import java.util.Arrays;

public final class AppUtils {

    private AppUtils() {
    }

    public static boolean arrayContainsValue(String[] array, String string) {
        return Arrays.asList(array).contains(string);
    }

    public static String getLastChar(String str) {
        if (str.length() > 0) {
            return str.substring(str.length() - 1);
        }
        return null;
    }
}
