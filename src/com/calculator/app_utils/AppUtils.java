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

    public static String removeLastChar(String str) {
        return removeLastChars(str, 1);
    }

    public static String removeLastChars(String str, int chars) {
        if (str.length() >= chars) {
            return str.substring(0, str.length() - chars);
        }
        return str;
    }

    public static boolean charIsDigit(char character) {
        return Character.toString(character).matches("\\d+");
    }

}
